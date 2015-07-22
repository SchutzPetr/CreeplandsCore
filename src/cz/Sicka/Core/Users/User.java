package cz.Sicka.Core.Users;

import java.util.List;
import java.util.UUID;
import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;

import cz.Sicka.Core.Core;
import cz.Sicka.Core.Utils.UserData.UserData;

public class User {
	private UUID uuid;
	private UserData userData;
	private String userName;
	
	/*
	 * AreaProtection Data
	 */
	/*private int maxAreas;
	private int maxBlockToClaim;
	private List<String> areas;*/
	
	/*
	 * CreeplandsHome
	 *
	private int maxHomes;
	private List<Location> homes;*/
	
	public User(UUID uuid, UserData userData){
		this.uuid = uuid;
		this.userData = userData;
		this.userName = Core.getUserCatche().getCachedName(uuid);
	}
	
	public void sendMessage(String message){
		OfflinePlayer off = Bukkit.getOfflinePlayer(getUniqueId());
		if(off == null){
			Core.logMessage(Level.WARNING, "[Error] " + "OfflinePlayer is null!");
			Core.logMessage(Level.WARNING, "---- " + "Class: User; Metod: sendMessage");
			Core.logMessage(Level.WARNING, "---- " + "Message: " +  message);
			return;
		}
		if(!off.hasPlayedBefore() || !off.isOnline()){
			if(!off.hasPlayedBefore()){
				Core.logMessage(Level.WARNING, "[Error] " + "OfflinePlayer hasnt Played Before!");
				Core.logMessage(Level.WARNING, "---- " + "Class: User; Metod: sendMessage");
				Core.logMessage(Level.WARNING, "---- " + "Message: " +  message);
			}else if(!off.isOnline()){
				Core.logMessage(Level.WARNING, "[Error] " + "OfflinePlayer is Offline!");
				Core.logMessage(Level.WARNING, "---- " + "Class: User; Metod: sendMessage");
				Core.logMessage(Level.WARNING, "---- " + "Message: " +  message);
			}else{
				Core.logMessage(Level.WARNING, "[Error] " + "Unknown");
				Core.logMessage(Level.WARNING, "---- " + "Class: User; Metod: sendMessage");
				Core.logMessage(Level.WARNING, "---- " + "Message: " +  message);
			}
			return;
		}
		off.getPlayer().sendMessage(message);
	}
	
	public OfflinePlayer getOfflinePlayer(){
		OfflinePlayer off = Bukkit.getOfflinePlayer(getUniqueId());
		if(off == null){
			Core.logMessage(Level.WARNING, "[Error] " + "OfflinePlayer is null!");
			Core.logMessage(Level.WARNING, "---- " + "Class: User; Metod: getOfflinePlayer");
			Core.logMessage(Level.WARNING, "---- " + "User UUID : " +  getUniqueId());
			return null;
		}else{
			return off;
		}
	}
	
	public void saveData(){
		this.userData.save();
	}
	
	/**
	 * @return the userName
	 */
	public String getName() {
		return userName;
	}
	
	/**
	 * @return the userData
	 */
	public UserData getData(){
		return userData;
	}
	
	/**
	 * @return the version
	 */
	public String getVersion() {
		return getData().getVersion();
	}
	
	/**
	 * @return the uuid
	 */
	public UUID getUniqueId() {
		return uuid;
	}

	/**
	 * @return the lastAccountName
	 */
	public String getLastAccountName() {
		return getData().getLastAccountName();
	}

	/**
	 * @return the maxAreas
	 */
	public int getMaxAreas() {
		return getData().getMaxAreas();
	}

	/**
	 * @return the maxBlockToClaim
	 */
	public int getMaxBlockToClaim() {
		return getData().getMaxBlockToClaim();
	}

	/**
	 * @return the areas
	 */
	public List<String> getAreas() {
		return getData().getAreas();
	}
	
	/**
	 * @return the lastSeen
	 */
	public long getLastSeen() {
		return getData().getLastSeen();
	}

	/**
	 * @param version the version to set
	 */
	public void setVersion(String version) {
		getData().setVersion(version);
	}

	/**
	 * @param uuid the uuid to set
	 */
	public void setUniqueId(String uuid) {
		getData().setUniqueId(uuid);
	}

	/**
	 * @param lastAccountName the lastAccountName to set
	 */
	public void setLastAccountName(String lastAccountName) {
		getData().setLastAccountName(lastAccountName);
	}

	/**
	 * @param lastSeen the lastSeen to set
	 */
	public void setLastSeen(long lastSeen) {
		getData().setLastSeen(lastSeen);
	}

	/**
	 * @param maxAreas the maxAreas to set
	 */
	public void setMaxAreas(int maxAreas) {
		getData().setMaxAreas(maxAreas);
	}

	/**
	 * @param maxBlockToClaim the maxBlockToClaim to set
	 */
	public void setMaxBlockToClaim(int maxBlockToClaim) {
		getData().setMaxBlockToClaim(maxBlockToClaim);
	}

	/**
	 * @param areas the areas to set
	 */
	public void addArea(String area) {
		getData().addArea(area);
	}
	
	/**
	 * @param areas the areas to set
	 */
	public void removeArea(String area) {
		getData().removeArea(area);
	}
}
