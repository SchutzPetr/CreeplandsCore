package cz.Sicka.Core.Configuration;

import cz.Sicka.Core.Core;
import cz.Sicka.Core.Utils.Configuration.DefaultConfig;

public class ConfigurationManager {
	private CoreConfiguration configuration;
	
	public ConfigurationManager(Core instance){
		DefaultConfig configuration = new DefaultConfig(instance, "Configuration.yml");
		configuration.saveDefaultConfig();
		this.configuration = new CoreConfiguration(instance);
	}

	/**
	 * @return the configuration
	 */
	public CoreConfiguration getConfiguration() {
		return configuration;
	}
}
