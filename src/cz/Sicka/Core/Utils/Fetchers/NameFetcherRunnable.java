package cz.Sicka.Core.Utils.Fetchers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;

import org.bukkit.scheduler.BukkitRunnable;

import cz.Sicka.Core.Core;

public class NameFetcherRunnable extends BukkitRunnable {
    private List<UUID> uuids;
    private Core plugin;

    public NameFetcherRunnable(List<UUID> uuids) {
        this.plugin = Core.getInstance();
        this.uuids = uuids;
    }

    public NameFetcherRunnable(UUID id) {
        this.plugin = Core.getInstance();
        this.uuids = new ArrayList<UUID>();
        this.uuids.add(id);
    }

    @Override
    public void run() {
        try {
            Map<UUID, String> returns = new NameFetcher(this.uuids).call();
            new CacheReturnedNames(returns).runTask(this.plugin);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private class CacheReturnedNames extends BukkitRunnable {
        private Map<UUID, String> returns;

        public CacheReturnedNames(Map<UUID, String> returns) {
            this.returns = returns;
        }

        @Override
        public void run() {
            for (Entry<UUID, String> entry : this.returns.entrySet()) {
            	Core.getUserCatche().cacheUser(entry.getKey(), entry.getValue());
            }
        }
    }
}
