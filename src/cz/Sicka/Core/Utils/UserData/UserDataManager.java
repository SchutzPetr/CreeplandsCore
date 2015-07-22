package cz.Sicka.Core.Utils.UserData;

import java.io.File;
import java.io.IOException;
import java.util.UUID;
import java.util.logging.Level;

import cz.Sicka.Core.Core;
import cz.Sicka.Core.Utils.Configuration.Configuration;

public class UserDataManager {
	private File userFolder;
	private Core plugin;
	
	public UserDataManager(Core instance){
		this.plugin = instance;
		File dataF = this.plugin.getDataFolder();
		this.userFolder = new File(dataF, "Users");
		if (!userFolder.isDirectory()) {
			userFolder.mkdirs();
        }
	}
	
	public UserData getUserData(UUID uuid){
		return getUserData(uuid.toString());
	}
	
	public UserData createUserData(UUID uuid){
		return createUserData(uuid.toString());
	}
	
	public boolean isUserDataExist(UUID uuid){
		return isUserDataExist(uuid.toString());
	}
	
	public boolean removeUserData(String uuid){
		File userFile = new File(userFolder, uuid + ".yml");
		if(isUserDataExist(userFile)){
			return userFile.delete();
		}else{
			Core.logMessage(Level.WARNING, "Class: UserManager. I cant found user Configuration! User UUID --> " + uuid);
			return false;
		}
	}

	
	public UserData getUserData(String uuid){
		File userFile = new File(userFolder, uuid + ".yml");
		if(isUserDataExist(userFile)){
			return new UserData(new Configuration(userFile));
		}else{
			Core.logMessage(Level.WARNING, "Class: UserManager. I cant found user Configuration! User UUID --> " + uuid);
			return createUserData(uuid);	
		}
	}
	
	public UserData createUserData(String uuid){
		File userFile = new File(userFolder, uuid + ".yml");
		if(isUserDataExist(userFile)){
			Core.logMessage(Level.WARNING, "Class: UserManager. User Configuration already exist! User UUID --> " + uuid);
		}else{
			try {
				userFile.createNewFile();
				Configuration configuration = new Configuration(userFile);
				configuration.getConfig().set("FileInfo.Version", Core.getInstance().Version);
				configuration.getConfig().set("UserInfo.UUID", uuid);
				configuration.getConfig().set("UserInfo.LastName", Core.getUserCatche().getCachedName(UUID.fromString(uuid)));
				configuration.getConfig().set("UserInfo.LastSeen", System.currentTimeMillis());
				
				configuration.getConfig().set("AreaProtection.MaxAreas", plugin.getConfigurationManager().getConfiguration().getMaxAreas());
				configuration.getConfig().set("AreaProtection.MaxExpanseToClaim", plugin.getConfigurationManager().getConfiguration().getMaxExpanseToClaim());
				configuration.getConfig().createSection("AreaProtection.Areas");
				configuration.saveConfig();
				return new UserData(configuration);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	public boolean isUserDataExist(String uuid){
		File userFile = new File(userFolder, uuid + ".yml");
		return userFile.exists();
	}
	
	public boolean isUserDataExist(File userFile){
		return userFile.exists();
	}

}
