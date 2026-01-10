package org.achymake.jobs.listeners;

import net.milkbowl.vault.economy.Economy;
import org.achymake.jobs.Jobs;
import org.achymake.jobs.data.Message;
import org.achymake.jobs.data.Userdata;
import org.achymake.jobs.events.JobLvlChangeEvent;
import org.achymake.jobs.handlers.RandomHandler;
import org.achymake.jobs.job.Breeder;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityBreedEvent;
import org.bukkit.plugin.PluginManager;

public class EntityBreed implements Listener {
    private Jobs getInstance() {
        return Jobs.getInstance();
    }
    private Message getMessage() {
        return getInstance().getMessage();
    }
    private Userdata getUserdata() {
        return getInstance().getUserdata();
    }
    private RandomHandler getRandomHandler() {
        return getInstance().getRandomHandler();
    }
    private Breeder getBreeder() {
        return getInstance().getBreeder();
    }
    private Economy getEconomy() {
        return getInstance().getEconomy();
    }
    private PluginManager getPluginManager() {
        return getInstance().getPluginManager();
    }
    public EntityBreed() {
        getPluginManager().registerEvents(this, getInstance());
    }
    @EventHandler(priority = EventPriority.MONITOR)
    public void onEntityBreed(EntityBreedEvent event) {
        if (event.isCancelled())return;
        var player = (Player) event.getBreeder();
        if (player == null)return;
        var entityType = event.getEntityType();
        if (!getBreeder().isEnabled(entityType))return;
        if (!player.hasPermission("jobs.job.breeder"))return;
        if (!getRandomHandler().isTrue(getBreeder().getChance(entityType)))return;
        var job = Jobs.jobs.breeder;
        var money = getRandomHandler().nextDouble(getBreeder().getMoneyMin(entityType), getBreeder().getMoneyMax(entityType));
        getEconomy().depositPlayer(player, money);
        getMessage().sendActionBar(player, getMessage().get("event.money.message", getEconomy().currencyNameSingular() + getMessage().format(money)));
        var exp = getRandomHandler().nextDouble(getBreeder().getExpMin(entityType), getBreeder().getExpMax(entityType));
        var result = getUserdata().addExp(player, job, exp);
        var lvl = getUserdata().getLvl(player, job);
        if (result >= lvl) {
            getPluginManager().callEvent(new JobLvlChangeEvent(player, job, lvl + 1, lvl));
        }
    }
}