package net.poundex.etch.gmail

import com.google.api.client.auth.oauth2.Credential
import com.google.api.client.auth.oauth2.TokenResponse
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow
import com.google.api.client.googleapis.auth.oauth2.GoogleRefreshTokenRequest
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse
import com.google.api.client.http.javanet.NetHttpTransport
import com.google.api.client.json.jackson2.JacksonFactory
import net.poundex.etch.google.GoogleAccount
import org.grails.web.util.WebUtils

import javax.servlet.http.HttpSession

class GoogleLoginService
{
	static private final String CLIENT_ID = "487754437303-alrojaqh2dg7rv59ijkfuod0m36hcl8j.apps.googleusercontent.com"
	static private final String CLIENT_SECRET = "h1mFP4ny2B0-aRf-hYsAPSna"
	private final String ATTR_GOOGLE_AUTH_CRED_HOLDER = "ATTR_GOOGLE_AUTH_CRED_HOLDER"
	static private final int ONE_MINUTE = 60

	public String beginAuthorise(GoogleAccount googleAccount)
	{
		GoogleAuthorizationCodeFlow flow = createAuthFlow()
		sessionFlow = flow
		sessionAccount = googleAccount

		return flow.newAuthorizationUrl().
				setApprovalPrompt("force").
				setAccessType("offline").
				setRedirectUri("http://local-google-dev.com:8080/googleLogin/auth").
				build()
	}

	public GoogleAccount completeAuthorise(String code)
	{
		GoogleAuthorizationCodeFlow flow = sessionFlow
		GoogleAccount account = sessionAccount
		if ( ! flow || ! account) throw new IllegalStateException("No account or flow")

		GoogleTokenResponse resp = flow.
				newTokenRequest(code).
				setRedirectUri("http://local-google-dev.com:8080/googleLogin/auth").
				execute()

		account.authorisation = resp.getRefreshToken()
		account.save()

		storeCredential(account, resp)
		return account
	}

	public Credential getLiveCredential(GoogleAccount googleAccount)
	{
		Credential sc = sessionCredentialHolder[googleAccount]
		if(isLive(sc)) return sc

		if( ! googleAccount.authorisation)
			throw new IllegalStateException("This google account is not authed")

		GoogleTokenResponse resp = new GoogleRefreshTokenRequest(new NetHttpTransport(), new JacksonFactory(), googleAccount.authorisation,
				CLIENT_ID, CLIENT_SECRET).execute()


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
				["email", "profile"]
		).setApprovalPrompt("force").build()
	}

	private Map<GoogleAccount, Credential> getSessionCredentialHolder()
	{
		Map<GoogleAccount, Credential> holder;
		holder = session.getAttribute(ATTR_GOOGLE_AUTH_CRED_HOLDER)

		if( ! holder)
		{
			holder = [:]
			session.setAttribute(ATTR_GOOGLE_AUTH_CRED_HOLDER, holder)
		}

		return holder
	}

	private Credential storeCredential(GoogleAccount account, TokenResponse response)
	{
		Credential credential = sessionFlow.createAndStoreCredential(response, account.username)
		sessionCredentialHolder[account] = credential
		return credential
	}

	private GoogleAuthorizationCodeFlow getSessionFlow()
	{
		return session.getAttribute("GoogleAuthFlow")
	}

	private void setSessionFlow(GoogleAuthorizationCodeFlow flow)
	{
		session.setAttribute("GoogleAuthFlow", flow)
	}

	private static HttpSession getSession()
	{
		return WebUtils.retrieveGrailsWebRequest().session
	}

	private GoogleAccount getSessionAccount()
	{
		return session.getAttribute("GoogleAuthAccount")
	}

	private void setSessionAccount(GoogleAccount account)
	{
		session.setAttribute("GoogleAuthAccount", account)
	}
}

