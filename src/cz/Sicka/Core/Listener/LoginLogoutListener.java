package cz.Sicka.Core.Listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import cz.Sicka.Core.Core;

public class LoginLogoutListener implements Listener {
	
	
	@EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    public void onPlayerQuit(PlayerQuitEvent event) {
    	Core.getUserManager().onPlayerLeave(event.getPlayer());
    }

    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    public void onPlayerJoin(PlayerJoinEvent event) {
    	Core.getUserManager().onPlayerJoin(event.getPlayer());
    	
    }
}
