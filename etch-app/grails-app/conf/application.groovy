jasypt {
	algorithm = "PBEWITHSHA256AND256BITAES-CBC-BC"
	providerName = "BC"
	password = EncryptionBootstrapper.encryptionPassword
	keyObtentionIterations = 1000
}