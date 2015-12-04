package net.poundex.etch.mail

import net.poundex.etch.block.BlockRenderer
import org.apache.catalina.Server
import org.opendolphin.core.server.ServerDolphin
import org.springframework.beans.factory.annotation.Autowired

/**
 * Created by poundex on 26/11/15.
 */
class MailBlockRenderer implements BlockRenderer<MailBlock>
{
	@Autowired
	MailDispatcherService mailDispatcherService

	@Override
	Map<String, BlockRenderer<MailBlock>> register()
	{
		['etch.mail': this]
	}

	@Override
	Closure render(MailBlock block)
	{
		return {
			etch.mail(title: block.mailAccount.name, mailAccount: block.mailAccount.id)
		}
	}
}
