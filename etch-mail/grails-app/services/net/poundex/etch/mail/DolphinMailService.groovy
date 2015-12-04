package net.poundex.etch.mail

import net.poundex.etch.dolphin.DolphinListener
import net.poundex.etch.dolphin.DolphinService
import org.opendolphin.core.ModelStoreEvent
import org.opendolphin.core.PresentationModel
import org.opendolphin.core.server.ServerDolphin
import org.springframework.beans.factory.annotation.Autowired

class DolphinMailService implements DolphinService
{
	static scope = 'session'

	@Autowired
	ServerDolphin serverDolphin

	@Autowired
	MailDispatcherService mailDispatcherService

	@DolphinListener('etch.mail.mail.pm')
	void unread(ModelStoreEvent e)
	{
		if (e.type != ModelStoreEvent.Type.ADDED) return

		PresentationModel pm = e.presentationModel
		Long accountID = pm['etch.mail.mailAccount.attr'].value
		MailAccount mailAccount
		if (!accountID || !(mailAccount = MailAccount.get(accountID)))
			throw new RuntimeException("no mail account id")

		pm['etch.mail.unread.attr'].setValue(mailDispatcherService.getUnreadCount(mailAccount).unread)
	}
}
