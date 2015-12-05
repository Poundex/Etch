package net.poundex.etch.util

class EtchConfig
{
	String var
	String value

	public static String get(EtchConfigVar var)
	{
		EtchConfig config = EtchConfig.findByVar(var.var)
		return config?.value ?: var.defaultValue
	}

	public static void set(EtchConfigVar var, String value)
	{
		EtchConfig config = EtchConfig.findByVar(var.var)
		if ( ! config) config = new EtchConfig(var: var.var)
		config.value = value
		config.save(flush: true, failOnError: true)
	}

	static constraints = {
	}

	public static enum EtchConfigVar
	{
		TEST_DATA_INSTALLED("App.TestDataInstalled", "false"),
		DEFAULT_DASHBOARD("Dashboards.DefaultDashboard", "0")

		String var
		String defaultValue

		EtchConfigVar(String var, String defaultValue)
		{
			this.var = var
			this.defaultValue = defaultValue
		}
	}
}
