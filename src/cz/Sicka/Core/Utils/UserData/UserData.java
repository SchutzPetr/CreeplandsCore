package cz.Sicka.Core.Utils.UserData;

import java.util.List;
import java.util.logging.Level;

import cz.Sicka.Core.Core;
import cz.Sicka.Core.Utils.Configuration.Configuration;

public class UserData {
	Configuration config;
	
	String version;
	String uuid;
	String lastAccountName;
	long lastSeen;
	
	int maxAreas;
	int maxBlockToClaim;
	List<String> areas;
	
	public UserData(Configuration config){
		this.config = config;
		
		this.version = this.config.getConfig().getString("FileInfo.Version");
		this.uuid = this.config.getConfig().getString("UserInfo.UUID");
		this.lastAccountName = this.config.getConfig().getString("UserInfo.LastAccountName");
		this.lastSeen = this.config.getConfig().getLong("UserInfo.LastSeen");
		
		this.maxAreas = this.config.getConfig().getInt("AreaProtection.MaxAreas");
		this.maxBlockToClaim = this.config.getConfig().getInt("AreaProtection.MaxExpanseToClaim");
		this.areas = this.config.getConfig().getStringList("AreaProtection.Areas");
		
	}
	
	public void save(){
		this.config.saveConfig();
	}
	
	/**
	 * @return the Configuration
	 */
	public Configuration getData(){
		return this.config;
	}
	
	/**
	 * @return the version
	 */
	public String getVersion() {
		return version;
	}

	/**
	 * @return the uuid
	 */
	public String getUniqueId() {
		return uuid;
	}

	/**
	 * @return the lastAccountName
	 */
	public String getLastAccountName() {
		return lastAccountName;
	}

	/**
	 * @return the maxAreas
	 */
	public int getMaxAreas() {
		return maxAreas;
	}

	/**
	 * @return the maxBlockToClaim
	 */
	public int getMaxBlockToClaim() {
		return maxBlockToClaim;
	}

	/**
	 * @return the areas
	 */
	public List<String> getAreas() {
		return areas;
	}
	
	/**
	 * @return the lastSeen
	 */
	public long getLastSeen() {
		return lastSeen;
	}

	/**
	 * @param version the version to set
	 */
	public void setVersion(String version) {
		this.config.getConfig().set("FileInfo.Version", version);
		this.version = version;
	}

	/**
	 * @param uuid the uuid to set
	 */
	public void setUniqueId(String uuid) {
		this.config.getConfig().set("UserInfo.UUID", uuid);
		this.uuid = uuid;
	}

	/**
	 * @param lastAccountName the lastAccountName to set
	 */
	public void setLastAccountName(String lastAccountName) {
		this.config.getConfig().set("UserInfo.LastAccountName", lastAccountName);
		this.lastAccountName = lastAccountName;
	}

	/**
	 * @param lastSeen the lastSeen to set
	 */
	public void setLastSeen(long lastSeen) {
		this.config.getConfig().set("UserInfo.LastSeen", lastSeen);
		this.lastSeen = lastSeen;
	}

	/**
	 * @param maxAreas the maxAreas to set
	 */
	public void setMaxAreas(int maxAreas) {
		this.config.getConfig().set("AreaProtection.MaxAreas", maxAreas);
		this.maxAreas = maxAreas;
	}

	/**
	 * @param maxBlockToClaim the maxBlockToClaim to set
	 */
	public void setMaxBlockToClaim(int maxBlockToClaim) {
		this.config.getConfig().set("AreaProtection.MaxExpanseToClaim", maxBlockToClaim);
		this.maxBlockToClaim = maxBlockToClaim;
	}

	/**
	 * @param areas the areas to set
	 */
	public void addArea(String area) {
		if(this.areas.contains(area)){
			Core.logMessage(Level.WARNING, "Error! Area already exist! Area: " + area + " User: " + uuid);
			return;
		}
		this.areas.add(area);
		this.config.getConfig().set("AreaProtection.Areas", this.areas);
	}
	
	/**
	 * @param areas the areas to set
	 */
	public void removeArea(String area) {
		if(!this.areas.contains(area)){
			Core.logMessage(Level.WARNING, "Error! Area does not exist! Area: " + area + " User: " + uuid);
			return;
		}
		this.areas.remove(area);
		this.config.getConfig().set("AreaProtection.Areas", this.areas);
	}
	
}
