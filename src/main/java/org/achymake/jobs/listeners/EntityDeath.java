package org.achymake.jobs.listeners;

import net.milkbowl.vault.economy.Economy;
import org.achymake.jobs.Jobs;
import org.achymake.jobs.data.Message;
import org.achymake.jobs.data.Userdata;
import org.achymake.jobs.events.JobLvlChangeEvent;
import org.achymake.jobs.handlers.RandomHandler;
import org.achymake.jobs.job.Hunter;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.plugin.PluginManager;

public class EntityDeath implements Listener {
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
    private Hunter getHunter() {
        return getInstance().getHunter();
    }
    private Economy getEconomy() {
        return getInstance().getEconomy();
    }
    private PluginManager getPluginManager() {
        return getInstance().getPluginManager();
    }
    public EntityDeath() {
        getPluginManager().registerEvents(this, getInstance());
    }
    @EventHandler(priority = EventPriority.MONITOR)
    public void onEntityDeath(EntityDeathEvent event) {
        var entity = event.getEntity();
        if (entity instanceof Player)return;
        var player = entity.getKiller();
        if (player == null)return;
        var entityType = entity.getType();
        if (!getHunter().isEnabled(entityType))return;
        if (!player.hasPermission("jobs.job.hunter"))return;
        if (!getRandomHandler().isTrue(getHunter().getChance(entityType)))return;
        var job = Jobs.jobs.hunter;
        var money = getRandomHandler().nextDouble(getHunter().getMoneyMin(entityType), getHunter().getMoneyMax(entityType));
        getEconomy().depositPlayer(player, money);
        getMessage().sendActionBar(player, getMessage().get("event.money.message", getEconomy().currencyNameSingular() + getEconomy().format(money)));
        var exp = getRandomHandler().nextDouble(getHunter().getExpMin(entityType), getHunter().getExpMax(entityType));
        var result = getUserdata().addExp(player, job, exp);
        var lvl = getUserdata().getLvl(player, job);
        if (result >= lvl) {
            getPluginManager().callEvent(new JobLvlChangeEvent(player, job, lvl + 1, lvl));
        }
    }
}