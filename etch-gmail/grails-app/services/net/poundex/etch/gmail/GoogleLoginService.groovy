package net.poundex.etch.gmail

import com.google.api.client.auth.oauth2.Credential
import com.google.api.client.auth.oauth2.TokenResponse
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow
import com.google.api.client.googleapis.auth.oauth2.GoogleRefreshTokenRequest
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse
import com.google.api.client.http.javanet.NetHttpTransport
import com.google.api.client.json.jackson2.JacksonFactory
import com.google.api.services.gmail.GmailScopes
import net.poundex.etch.core.Secret
import net.poundex.etch.google.GoogleAccount

class GoogleLoginService
{
	static scope = 'session'

	static private final String CLIENT_ID = "487754437303-alrojaqh2dg7rv59ijkfuod0m36hcl8j.apps.googleusercontent.com"
	static private final String CLIENT_SECRET = "h1mFP4ny2B0-aRf-hYsAPSna"
	static private final int ONE_MINUTE = 60

	private Map<GoogleAccount, Credential> sessionCredentialHolder = [:]
	private GoogleAuthorizationCodeFlow sessionFlow
	private GoogleAccount sessionAccount

	public String beginAuthorise(GoogleAccount googleAccount)
	{
		sessionAccount = googleAccount

		return flow.newAuthorizationUrl().
				setApprovalPrompt("force").
				setAccessType("offline").
				setRedirectUri("http://local-google-dev.com:8080/googleLogin/auth").
				build()
	}

	public GoogleAccount completeAuthorise(String code)
	{
		if ( ! sessionFlow || ! sessionAccount) throw new IllegalStateException("No account or flow")

		GoogleTokenResponse resp = flow.
				newTokenRequest(code).
				setRedirectUri("http://local-google-dev.com:8080/googleLogin/auth").
				execute()

		if (sessionAccount.authorisation) sessionAccount.authorisation.delete()
		sessionAccount.authorisation = Secret.of(resp.getRefreshToken())
		sessionAccount.save()

		storeCredential(sessionAccount, resp)
		return sessionAccount
	}

	public Credential getLiveCredential(GoogleAccount googleAccount)
	{
		Credential sc = sessionCredentialHolder[googleAccount]
		if(isLive(sc)) return sc

		if( ! googleAccount.authorisation)
			throw new IllegalStateException("This google account is not authed")

		GoogleTokenResponse resp = new GoogleRefreshTokenRequest(new NetHttpTransport(), new JacksonFactory(), googleAccount.authorisation.secret,
				CLIENT_ID, CLIENT_SECRET).execute()

		return storeCredential(googleAccount, resp)
	}

	public static boolean isLive(Credential credential)
	{
		if( ! credential) return false
		return credential.expiresInSeconds > ONE_MINUTE
	}

	private GoogleAuthorizationCodeFlow createAuthFlow()
	{
		return new GoogleAuthorizationCodeFlow.Builder(
				new NetHttpTransport(),
				new JacksonFactory(),
				CLIENT_ID,
				CLIENT_SECRET,
				["email", "profile", GmailScopes.GMAIL_READONLY, GmailScopes.GMAIL_LABELS]
		).setApprovalPrompt("force").build()
	}

	private Credential storeCredential(GoogleAccount account, TokenResponse response)
	{
		Credential credential = flow.createAndStoreCredential(response, account.username)
		credential.refreshToken()
		sessionCredentialHolder[account] = credential
		return credential
	}

	private GoogleAuthorizationCodeFlow getFlow()
	{
		if ( ! sessionFlow)
			sessionFlow = createAuthFlow()

		return sessionFlow
	}
}

