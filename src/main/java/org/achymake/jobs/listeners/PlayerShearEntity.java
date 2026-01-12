package org.achymake.jobs.listeners;

import net.milkbowl.vault.economy.Economy;
import org.achymake.jobs.Jobs;
import org.achymake.jobs.data.Message;
import org.achymake.jobs.data.Userdata;
import org.achymake.jobs.events.JobLvlChangeEvent;
import org.achymake.jobs.handlers.GameModeHandler;
import org.achymake.jobs.handlers.RandomHandler;
import org.achymake.jobs.job.Shepherd;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerShearEntityEvent;
import org.bukkit.plugin.PluginManager;

public class PlayerShearEntity implements Listener {
    private Jobs getInstance() {
        return Jobs.getInstance();
    }
    private Message getMessage() {
        return getInstance().getMessage();
    }
    private Userdata getUserdata() {
        return getInstance().getUserdata();
    }
    public GameModeHandler getGameModeHandler() {
        return getInstance().getGameModeHandler();
    }
    private RandomHandler getRandomHandler() {
        return getInstance().getRandomHandler();
    }
    private Shepherd getShepherd() {
        return getInstance().getShepherd();
    }
    private Economy getEconomy() {
        return getInstance().getEconomy();
    }
    private PluginManager getPluginManager() {
        return getInstance().getPluginManager();
    }
    public PlayerShearEntity() {
        getPluginManager().registerEvents(this, getInstance());
    }
    @EventHandler(priority = EventPriority.MONITOR)
    public void onPlayerShearEntity(PlayerShearEntityEvent event) {
        if (event.isCancelled())return;
        var player = event.getPlayer();
        if (!player.getGameMode().equals(getGameModeHandler().get("survival")))return;
        var entity = event.getEntity();
        var entityType = entity.getType();
        if (!getShepherd().isEnabled(entityType))return;
        if (!player.hasPermission("jobs.job.shepherd"))return;
        if (!getRandomHandler().isTrue(getShepherd().getChance(entityType)))return;
        var job = Jobs.jobs.shepherd;
        var money = getRandomHandler().nextDouble(getShepherd().getMoneyMin(entityType), getShepherd().getMoneyMax(entityType));
        getEconomy().depositPlayer(player, money);
        getMessage().sendActionBar(player, getMessage().get("event.money.message", getEconomy().currencyNameSingular() + getMessage().format(money)));
        var exp = getRandomHandler().nextDouble(getShepherd().getExpMin(entityType), getShepherd().getExpMax(entityType));
        var result = getUserdata().addExp(player, job, exp);
        var lvl = getUserdata().getLvl(player, job);
        if (result >= lvl) {
            getPluginManager().callEvent(new JobLvlChangeEvent(player, job, lvl + 1, lvl));
        }
    }
}