package net.poundex.etch.mail

import org.springframework.context.ApplicationContext
import org.springframework.context.ApplicationContextAware

import javax.annotation.PostConstruct

class MailDispatcherService implements MailService<MailAccount>, ApplicationContextAware
{
	ApplicationContext applicationContext
	Map<Class<? extends MailAccount>, MailService> registry = [:]

    @Override
    MailService.UnreadCount getUnreadCount(MailAccount mailAccount)
    {
        return registry[mailAccount.class].getUnreadCount(mailAccount)
    }

	@PostConstruct
	void init()
	{
		applicationContext.getBeansOfType(MailService).values().each { MailService s ->
			if(s in MailDispatcherService) return
			registry[s.accountType] = s
		}
	}

	@Override
	Class getAccountType()
	{
		throw new UnsupportedOperationException()
	}
}
