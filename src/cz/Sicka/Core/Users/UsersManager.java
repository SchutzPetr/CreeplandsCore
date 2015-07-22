package cz.Sicka.Core.Users;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.bukkit.entity.Player;

import cz.Sicka.Core.Core;

public class UsersManager {
	private Map<String, User> loadedUsers= new HashMap<String, User>();
	private List<UUID> loadedOfflineUsers= new ArrayList<UUID>();
	
	
	public void onPlayerJoin(Player player){
		User user = new User(player.getUniqueId(), Core.getUserDataManager().getUserData(player.getUniqueId()));
		user.setLastSeen(System.currentTimeMillis());
		this.loadedUsers.put(player.getUniqueId().toString(), user);
		Core.getUserCatche().cacheUser(player.getUniqueId(), player.getName());
	}
	
	public void onPlayerLeave(Player player){
		User user = this.loadedUsers.get(player.getUniqueId().toString());
		user.saveData();
		user.setLastSeen(System.currentTimeMillis());
		this.loadedUsers.remove(player.getUniqueId().toString());
	}
	
	public Collection<User> getLoadedUsers(){
		return this.loadedUsers.values();
	}
	
	public User getUser(UUID uniqueId){
		if(this.loadedUsers.containsKey(uniqueId.toString())){
			return this.loadedUsers.get(uniqueId.toString());
		}else{
			this.loadOfflineUser(uniqueId);
			return this.getUser(uniqueId);
		}
	}
	
	public void loadUser(Player player){
		User user = new User(player.getUniqueId(), Core.getUserDataManager().getUserData(player.getUniqueId()));
		this.loadedUsers.put(player.getUniqueId().toString(), user);
		Core.getUserCatche().cacheUser(player.getUniqueId(), player.getName());
	}
	
	public void loadOfflineUser(UUID uuid){
		User user = new User(uuid, Core.getUserDataManager().getUserData(uuid));
		this.loadedUsers.put(uuid.toString(), user);
		this.loadedOfflineUsers.add(uuid);
		Core.getUserCatche().cacheUser(uuid, Core.getInstance().getServer().getOfflinePlayer(uuid).getName());
	}
}
