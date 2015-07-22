package cz.Sicka.Core;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import cz.Sicka.Core.Configuration.ConfigurationManager;
import cz.Sicka.Core.Listener.LoginLogoutListener;
import cz.Sicka.Core.Users.User;
import cz.Sicka.Core.Users.UserCatche;
import cz.Sicka.Core.Users.UsersManager;
import cz.Sicka.Core.Utils.CoreLogger;
import cz.Sicka.Core.Utils.UserData.UserDataManager;

public class Core extends JavaPlugin{
	private static Core plugin;
	
	private static Logger log = Logger.getLogger("Minecraft");
	private final static Logger LOGGER = Logger.getLogger(Core.class.getName());

	
	private static UserCatche userCatche;
	private static UsersManager userManager;

	public int Version = 1;

	private LoginLogoutListener loginLogout;

	private ConfigurationManager configurationManager;

	private static UserDataManager userDataManager;

	public static UserCatche getUserCatche() {
		return userCatche;
	}
	
	@Override
	public void onEnable(){
		plugin = this;
		configurationManager = new ConfigurationManager(plugin);
		userCatche = new UserCatche();
		userManager = new UsersManager();
		new CoreLogger();
		try {
			CoreLogger.setup();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		LOGGER.log(Level.INFO, "sssss");
		LOGGER.log(Level.INFO, "sss");
		LOGGER.log(Level.INFO, "sss");
		LOGGER.log(Level.INFO, "aaa");
		LOGGER.log(Level.WARNING, "www");
		userDataManager = new UserDataManager(this);
		
		loginLogout = new LoginLogoutListener();
		
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(loginLogout, this);
	}
	
	@Override
	public void onDisable(){
		for(User user : userManager.getLoadedUsers()){
			user.saveData();
		}
	}

	public static Core getInstance() {
		return plugin;
	}

	public static void logMessage(Level level, String record) {
		log.log(level, record);
		LOGGER.log(level, record);
	}

	/**
	 * @return the userManager
	 */
	public static UsersManager getUserManager() {
		return userManager;
	}

	/**
	 * @return the userDataManager
	 */
	public static UserDataManager getUserDataManager() {
		return userDataManager;
	}

	/**
	 * @return the LOGGER
	 */
	public Logger getCoreLogger() {
		return LOGGER;
	}

	public ConfigurationManager getConfigurationManager() {
		return configurationManager;
	}
}
