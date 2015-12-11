package net.poundex.etch.core

import com.bloomhealthco.jasypt.GormEncryptedStringType

class Secret
{
	String secret

	static constraints = {
	}

	static mapping = {
		secret type: GormEncryptedStringType
	}

	public String toString()
	{
		return secret
	}

	public static Secret of(String secret)
	{
		return new Secret(secret: secret).save()
	}
}
