package net.poundex.etch.google

import net.poundex.etch.core.Credentials

class GoogleAccount extends Credentials
{
	String username
//	GoogleRefreshToken authorisation
	String authorisation

	static constraints = {
		username()
		authorisation nullable: true
	}

	public String toString()
	{
		return "[GoogleAccount ${username} ${( ! authorisation) ? "UNAUTH" : "AUTHED"}]"
	}
}
