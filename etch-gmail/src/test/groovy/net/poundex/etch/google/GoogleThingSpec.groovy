package net.poundex.etch.google

import com.google.api.client.auth.oauth2.AuthorizationCodeFlow
import com.google.api.client.auth.oauth2.BearerToken
import com.google.api.client.auth.oauth2.ClientParametersAuthentication
import com.google.api.client.auth.oauth2.Credential
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport
import com.google.api.client.http.BasicAuthentication
import com.google.api.client.http.GenericUrl
import com.google.api.client.http.HttpTransport
import com.google.api.client.http.javanet.NetHttpTransport
import com.google.api.client.json.JsonFactory
import com.google.api.client.json.jackson2.JacksonFactory
import com.google.api.client.util.store.FileDataStoreFactory
import com.google.api.services.gmail.Gmail
import com.google.api.services.gmail.GmailScopes
import com.google.api.services.gmail.model.Label
import com.google.api.services.gmail.model.ListLabelsResponse
import spock.lang.Specification

public class GoogleThingSpec extends Specification
{

	/** Application name. */
	private static final String APPLICATION_NAME =
			"Gmail API Java Quickstart";

	/** Directory to store user credentials for this application. */
	private static final java.io.File DATA_STORE_DIR = new java.io.File(
			System.getProperty("user.home"), ".credentials/gmail-java-quickstart");

	/** Global instance of the {@link FileDataStoreFactory}. */
	private static FileDataStoreFactory DATA_STORE_FACTORY;

	/** Global instance of the JSON factory. */
	private static final JsonFactory JSON_FACTORY =
			JacksonFactory.getDefaultInstance();

	/** Global instance of the HTTP transport. */
	private static HttpTransport HTTP_TRANSPORT;

	/** Global instance of the scopes required by this quickstart. */
	private static final List<String> SCOPES =
			Arrays.asList(GmailScopes.GMAIL_LABELS)
	static private final String CLIENT_ID = "487754437303-alrojaqh2dg7rv59ijkfuod0m36hcl8j.apps.googleusercontent.com"
	static private final String CLIENT_SECRET = "h1mFP4ny2B0-aRf-hYsAPSna"

	static {
		try {
			HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
			DATA_STORE_FACTORY = new FileDataStoreFactory(DATA_STORE_DIR);
		} catch (Throwable t) {
			t.printStackTrace();
			System.exit(1);
		}
	}

	/**
	 * Creates an authorized Credential object.
	 * @return an authorized Credential object.
	 * @throws IOException
	 */
	public static Credential authorize() throws IOException {
		// Build flow and trigger user authorization request.
		GoogleAuthorizationCodeFlow flow =
				new GoogleAuthorizationCodeFlow.Builder(
						HTTP_TRANSPORT,
						JSON_FACTORY,
						CLIENT_ID,
						CLIENT_SECRET,
						SCOPES)
						.setDataStoreFactory(DATA_STORE_FACTORY)
						.setAccessType("offline")
						.build();
		Credential credential = new AuthorizationCodeInstalledApp(
				flow, new LocalServerReceiver()).authorize("user");
		System.out.println(
				"Credentials saved to " + DATA_STORE_DIR.getAbsolutePath());
		return credential;
	}

	/**
	 * Build and return an authorized Gmail client service.
	 * @return an authorized Gmail client service
	 * @throws IOException
	 */
	public static Gmail getGmailService() throws IOException {
		Credential credential = authorize();
		return new Gmail.Builder(HTTP_TRANSPORT, JSON_FACTORY, credential)
				.setApplicationName(APPLICATION_NAME)
				.build();
	}

	void "test thing"()
	{
//		setup:
	// Build a new authorized API client service.
		Gmail service = getGmailService();

		// Print the labels in the user's account.
		String user = "me";
		ListLabelsResponse listResponse =
				service.users().labels().list(user).execute();
		List<Label> labels = listResponse.getLabels();
		if (labels.size() == 0) {
			System.out.println("No labels found.");
		} else {
			System.out.println("Labels:");
			for (Label label : labels) {
				System.out.printf("- %s\n", label.getName());
			}
		}
	}

	void "thing2"()
	{
		setup:
//		AuthorizationCodeFlow acf = new AuthorizationCodeFlow.Builder(
//				BearerToken.authorizationHeaderAccessMethod(),
//				new NetHttpTransport(),
//				new JacksonFactory(),
//				new GenericUrl("https://accounts.google.com/o/oauth2/token"),
//				new ClientParametersAuthentication(
//						"487754437303-alrojaqh2dg7rv59ijkfuod0m36hcl8j.apps.googleusercontent.com",
//						"h1mFP4ny2B0-aRf-hYsAPSna"),
//				"487754437303-alrojaqh2dg7rv59ijkfuod0m36hcl8j.apps.googleusercontent.com",
//				"http://localhost:8080/googleLogin/auth").build()
//
//		println "url: ${acf.newAuthorizationUrl().build()}"

		GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
				new NetHttpTransport(),
				new JacksonFactory(),
				CLIENT_ID,
				CLIENT_SECRET,
				["email", "profile", *GmailScopes.all().toList()]
		).build()

		println "url: ${flow.newAuthorizationUrl().setRedirectUri("http://local-google-dev.com:8080/googleLogin/auth").build()}"


		expect:
		false
	}

}