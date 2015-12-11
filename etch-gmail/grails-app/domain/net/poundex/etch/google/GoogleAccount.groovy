package net.poundex.etch.google

import net.poundex.etch.core.Credentials
import net.poundex.etch.core.Secret

class GoogleAccount extends Credentials
{
	String username
	Secret authorisation

	static constraints = {
		username()
		authorisation nullable: true
	}

	boolean equals(o)
	{
		if (this.is(o)) return true
		if (!(o instanceof GoogleAccount)) return false

		GoogleAccount that = (GoogleAccount) o

		if (id != that.id) return false

		return true
	}

	int hashCode()
	{
		return (id != null ? id.hashCode() : 0)
	}

	public String toString()
	{
		return "[GoogleAccount ${username} ${( ! authorisation) ? "UNAUTH" : "AUTHED"}]"
	}
}
