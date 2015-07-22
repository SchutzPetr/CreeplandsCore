package cz.Sicka.Core.Users;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.bukkit.entity.Player;

import cz.Sicka.Core.Core;
import cz.Sicka.Core.Utils.Configuration.Configuration;
import cz.Sicka.Core.Utils.Fetchers.NameFetcherRunnable;
import cz.Sicka.Core.Utils.Fetchers.UUIDFetcherRunnable;

public class UserCatche {
	 private Configuration fileCache;
	 private File saveLocation;
	 
	 public UserCatche() {
		 File file = new File(Core.getInstance().getDataFolder(), "UsernameUUIDCache.yml");
		 if(!file.exists()){
			 try {
					file.createNewFile();
				} catch (IOException e) {
					e.printStackTrace();
				} 
		 }
		 saveLocation = file;
		 fileCache = new Configuration(file);
	 }
	
	public String getCachedName(UUID uuid) {
        if (uuid == null) {
            return "Server Land";
        }
        Player player = Core.getInstance().getServer().getPlayer(uuid);
        if (player != null) {
            return player.getName();
        }
        String name = fileCache.getConfig().getString(uuid.toString());
        if (name == null) {
            grabUsernameFromMojang(uuid);
        }
        return name;
    }

    public void cacheUser(UUID uuid, String name) {
        String old = fileCache.getConfig().getString(name);
        if (old != null && !old.equalsIgnoreCase(uuid.toString())) {
            // Refresh our cache for replaced user
            grabUsernameFromMojang(UUID.fromString(old));
        }
        fileCache.getConfig().set(uuid.toString(), name);
        fileCache.getConfig().set(name, uuid.toString());
        fileCache.saveConfig();
    }

    public UUID getCachedUUID(String name) {
        Player player = Core.getInstance().getServer().getPlayer(name);
        if (player != null) {
            return player.getUniqueId();
        }
        String uuid = fileCache.getConfig().getString(name);
        if (uuid != null) {
            return UUID.fromString(uuid);
        }
        grabUUIDFromMojang(name);
        return null;
    }

    private void grabUsernameFromMojang(UUID id) {
        new NameFetcherRunnable(id).runTaskAsynchronously(Core.getInstance());
    }

    private void grabUUIDFromMojang(String name) {
        new UUIDFetcherRunnable(name).runTaskAsynchronously(Core.getInstance());
    }

    public void save() throws IOException {
        fileCache.getConfig().save(saveLocation);
    }
}
