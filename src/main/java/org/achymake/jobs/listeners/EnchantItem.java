package org.achymake.jobs.listeners;

import net.milkbowl.vault.economy.Economy;
import org.achymake.jobs.Jobs;
import org.achymake.jobs.data.Message;
import org.achymake.jobs.data.Userdata;
import org.achymake.jobs.events.JobLvlChangeEvent;
import org.achymake.jobs.handlers.RandomHandler;
import org.achymake.jobs.job.Enchanter;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.enchantment.EnchantItemEvent;
import org.bukkit.plugin.PluginManager;

public class EnchantItem implements Listener {
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
    private Enchanter getEnchanter() {
        return getInstance().getEnchanter();
    }
    private Economy getEconomy() {
        return getInstance().getEconomy();
    }
    private PluginManager getPluginManager() {
        return getInstance().getPluginManager();
    }
    public EnchantItem() {
        getPluginManager().registerEvents(this, getInstance());
    }
    @EventHandler(priority = EventPriority.MONITOR)
    public void onEnchantItem(EnchantItemEvent event) {
        if (event.isCancelled())return;
        var player = event.getEnchanter();
        var itemStack = event.getItem();
        var material = itemStack.getType();
        if (!getEnchanter().isEnabled(material))return;
        if (!player.hasPermission("jobs.job.enchanter"))return;
        if (!getRandomHandler().isTrue(getEnchanter().getChance(material)))return;
        var job = Jobs.jobs.enchanter;
        var money = getRandomHandler().nextDouble(getEnchanter().getMoneyMin(material), getEnchanter().getMoneyMax(material));
        getEconomy().depositPlayer(player, money);
        getMessage().sendActionBar(player, getMessage().get("event.money.message", getEconomy().currencyNameSingular() + getMessage().format(money)));
        var exp = getRandomHandler().nextDouble(getEnchanter().getExpMin(material), getEnchanter().getExpMax(material));
        var result = getUserdata().addExp(player, job, exp);
        var lvl = getUserdata().getLvl(player, job);
        if (result >= lvl) {
            getPluginManager().callEvent(new JobLvlChangeEvent(player, job, lvl + 1, lvl));
        }
    }
}