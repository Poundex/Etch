package net.poundex.etch.ci

class JenkinsServer extends CIConnection
{
	String endpointURL
	JenkinsAPICredentials credentials

	static constraints = {
	}
}
