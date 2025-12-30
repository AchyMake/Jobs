package org.achymake.jobs.listeners;

import org.achymake.jobs.Jobs;
import org.achymake.jobs.handlers.BlockHandler;
import org.achymake.jobs.job.Lumberjack;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.plugin.PluginManager;

public class BlockPlace implements Listener {
    private Jobs getInstance() {
        return Jobs.getInstance();
    }
    private BlockHandler getBlockHandler() {
        return getInstance().getBlockHandler();
    }
    private Lumberjack getLumberjack() {
        return getInstance().getLumberjack();
    }
    private PluginManager getPluginManager() {
        return getInstance().getPluginManager();
    }
    public BlockPlace() {
        getPluginManager().registerEvents(this, getInstance());
    }
    @EventHandler(priority = EventPriority.MONITOR)
    public void onBlockPlace(BlockPlaceEvent event) {
        if (event.isCancelled())return;
        if (getLumberjack().isEnabled(event.getBlock().getType())) {
            getBlockHandler().setPlaced(event.getBlockPlaced());
        } else if (getInstance().getFarmer().isEnabled(event.getItemInHand().getType())) {
            getBlockHandler().setPlaced(event.getBlockPlaced());
        }
    }
}