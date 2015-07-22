package cz.Sicka.Core.Configuration;

import java.io.File;

import cz.Sicka.Core.Core;
import cz.Sicka.Core.Utils.Configuration.Configuration;

public class CoreConfiguration {
	private Configuration conf;
	
	public CoreConfiguration(Core instance) {
		File configurationFile = new File(instance.getDataFolder(), "Configuration.yml");
		this.conf = new Configuration(configurationFile);	
	}
	/*
	 * @return the version of configuration file
	 */
	public int getVersion(){
		return this.conf.getConfig().getInt("Version");
	}
	/*
	 * @return 
	 */
	public int getMaxAreas(){
		return this.conf.getConfig().getInt("DefaultUserSettings.MaxAreas", 3);
	}
	/*
	 * @return 
	 */
	public int getMaxExpanseToClaim(){
		return this.conf.getConfig().getInt("DefaultUserSettings.MaxExpanseToClaim", 19200);
	}
}
