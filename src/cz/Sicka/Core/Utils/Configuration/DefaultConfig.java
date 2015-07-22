package cz.Sicka.Core.Utils.Configuration;

import java.io.File;
import java.util.logging.Level;

import org.bukkit.plugin.Plugin;

import cz.Sicka.Core.Core;

public class DefaultConfig {
	private File configfile = null;
	private String fileName;
	private Plugin plugin;
	
	public DefaultConfig(Plugin instance, String fileName){
		plugin = instance;
		this.fileName = fileName;
	}
	
	public void saveDefaultConfig() {
	    if(configfile == null) {
	       configfile = new File(plugin.getDataFolder(), fileName);
	    }
	    if(!configfile.exists()) {            
	        plugin.saveResource(fileName, false);
			Core.logMessage(Level.INFO, "Creating file: " + configfile.getAbsolutePath());
	    }
	}

}
