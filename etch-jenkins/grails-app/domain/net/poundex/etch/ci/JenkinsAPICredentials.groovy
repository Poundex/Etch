package net.poundex.etch.ci

import net.poundex.etch.core.Credentials
import net.poundex.etch.core.Secret

class JenkinsAPICredentials extends Credentials
{
	String username
	Secret apiKey

    static constraints = {
	    username blank: true
    }
}
