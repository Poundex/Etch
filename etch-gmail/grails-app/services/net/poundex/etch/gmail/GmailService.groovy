package net.poundex.etch.gmail

import com.google.api.client.auth.oauth2.Credential
import com.google.api.client.http.javanet.NetHttpTransport
import com.google.api.client.json.jackson2.JacksonFactory
import com.google.api.services.gmail.Gmail
import net.poundex.etch.mail.MailService
import net.poundex.etch.mail.MailService.UnreadCount
import org.springframework.beans.factory.annotation.Autowired

class GmailService implements MailService<GmailAccount>
{
	@Autowired
	GoogleLoginService googleLoginService

	@Override
	UnreadCount getUnreadCount(GmailAccount mailAccount)
	{
		Credential credential = googleLoginService.getLiveCredential(mailAccount.googleAccount)
		Gmail gmail = new Gmail.Builder(new NetHttpTransport(), new JacksonFactory(), credential).build()
		int mails = gmail.users().labels().get("me", "INBOX").execute().get("messagesUnread")
		return new UnreadCount(mails)
	}

	@Override
	Class<GmailAccount> getAccountType()
	{
		return GmailAccount
	}
}
