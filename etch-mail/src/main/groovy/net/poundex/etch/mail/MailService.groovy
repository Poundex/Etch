package net.poundex.etch.mail

import groovy.transform.Immutable

public interface MailService<T extends MailAccount>
{
	UnreadCount getUnreadCount(T mailAccount)
	Class<T> getAccountType()

	@Immutable
	public static class UnreadCount
	{
		int unread
	}
}