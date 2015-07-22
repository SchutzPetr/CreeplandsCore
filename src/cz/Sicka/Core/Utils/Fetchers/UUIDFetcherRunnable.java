package cz.Sicka.Core.Utils.Fetchers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;

import org.bukkit.scheduler.BukkitRunnable;

import cz.Sicka.Core.Core;

public class UUIDFetcherRunnable extends BukkitRunnable {
    private List<String> names;
    private Core plugin;

    public UUIDFetcherRunnable(List<String> names) {
        this.plugin = Core.getInstance();
        this.names = names;
    }

    public UUIDFetcherRunnable(String name) {
        this.plugin = Core.getInstance();
        this.names = new ArrayList<String>();
        this.names.add(name);
    }

    @Override
    public void run() {
        try {
            Map<String, UUID> returns = new UUIDFetcher(this.names).call();
            new CacheReturnedNames(returns).runTask(this.plugin);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private class CacheReturnedNames extends BukkitRunnable {
        private Map<String, UUID> returns;

        public CacheReturnedNames(Map<String, UUID> returns) {
            this.returns = returns;
        }

        @Override
        public void run() {
            for (Entry<String, UUID> entry : this.returns.entrySet()) {
            	Core.getUserCatche().cacheUser(entry.getValue(), entry.getKey());
            }
        }
    }
}
