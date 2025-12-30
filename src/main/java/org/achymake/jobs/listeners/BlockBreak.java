package org.achymake.jobs.listeners;

import net.milkbowl.vault.economy.Economy;
import org.achymake.jobs.Jobs;
import org.achymake.jobs.data.Message;
import org.achymake.jobs.data.Userdata;
import org.achymake.jobs.events.JobLvlChangeEvent;
import org.achymake.jobs.handlers.BlockHandler;
import org.achymake.jobs.handlers.RandomHandler;
import org.achymake.jobs.job.Farmer;
import org.achymake.jobs.job.Lumberjack;
import org.achymake.jobs.job.Miner;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.plugin.PluginManager;

public class BlockBreak implements Listener {
    private Jobs getInstance() {
        return Jobs.getInstance();
    }
    private Message getMessage() {
        return getInstance().getMessage();
    }
    private Userdata getUserdata() {
        return getInstance().getUserdata();
    }
    private BlockHandler getBlockHandler() {
        return getInstance().getBlockHandler();
    }
    private RandomHandler getRandomHandler() {
        return getInstance().getRandomHandler();
    }
    private Farmer getFarmer() {
        return getInstance().getFarmer();
    }
    private Miner getMiner() {
        return getInstance().getMiner();
    }
    private Lumberjack getLumberjack() {
        return getInstance().getLumberjack();
    }
    private Economy getEconomy() {
        return getInstance().getEconomy();
    }
    private PluginManager getPluginManager() {
        return getInstance().getPluginManager();
    }
    public BlockBreak() {
        getPluginManager().registerEvents(this, getInstance());
    }
    @EventHandler(priority = EventPriority.MONITOR)
    public void onBlockBreak(BlockBreakEvent event) {
        if (event.isCancelled())return;
        var player = event.getPlayer();
        var block = event.getBlock();
        var material = block.getType();
        if (getBlockHandler().isPlaced(block)) {
            getBlockHandler().removePlaced(block);
        } else if (getFarmer().isEnabled(material)) {
            if (player.getInventory().getItemInMainHand().containsEnchantment(Enchantment.SILK_TOUCH))return;
            if (!getBlockHandler().isRightAge(block))return;
            if (!player.hasPermission("jobs.job.farmer"))return;
            if (!getRandomHandler().isTrue(getFarmer().getChance(material)))return;
            var job = Jobs.jobs.farmer;
            var money = getRandomHandler().nextDouble(getFarmer().getMoneyMin(material), getFarmer().getMoneyMax(material));
            getEconomy().depositPlayer(player, money);
            getMessage().sendActionBar(player, getMessage().get("event.money.message", getEconomy().currencyNameSingular() + getEconomy().format(money)));
            var exp = getRandomHandler().nextDouble(getFarmer().getExpMin(material), getFarmer().getExpMax(material));
            var result = getUserdata().addExp(player, job, exp);
            var lvl = getUserdata().getLvl(player, job);
            if (result >= lvl) {
                getPluginManager().callEvent(new JobLvlChangeEvent(player, job, lvl + 1, lvl));
            }
        } else if (getMiner().isEnabled(material)) {
            if (player.getInventory().getItemInMainHand().containsEnchantment(Enchantment.SILK_TOUCH))return;
            if (!player.hasPermission("jobs.job.miner"))return;
            if (!getRandomHandler().isTrue(getMiner().getChance(material)))return;
            var job = Jobs.jobs.miner;
            var money = getRandomHandler().nextDouble(getMiner().getMoneyMin(material), getMiner().getMoneyMax(material));
            getEconomy().depositPlayer(player, money);
            getMessage().sendActionBar(player, getMessage().get("event.money.message", getEconomy().currencyNameSingular() + getEconomy().format(money)));
            var exp = getRandomHandler().nextDouble(getMiner().getExpMin(material), getMiner().getExpMax(material));
            var result = getUserdata().addExp(player, job, exp);
            var lvl = getUserdata().getLvl(player, job);
            if (result >= lvl) {
                getPluginManager().callEvent(new JobLvlChangeEvent(player, job, lvl + 1, lvl));
            }
        } else if (getLumberjack().isEnabled(material)) {
            if (!player.hasPermission("jobs.job.lumberjack"))return;
            if (!getRandomHandler().isTrue(getLumberjack().getChance(material)))return;
            var job = Jobs.jobs.lumberjack;
            var money = getRandomHandler().nextDouble(getLumberjack().getMoneyMin(material), getLumberjack().getMoneyMax(material));
            getEconomy().depositPlayer(player, money);
            getMessage().sendActionBar(player, getMessage().get("event.money.message", getEconomy().currencyNameSingular() + getEconomy().format(money)));
            var exp = getRandomHandler().nextDouble(getLumberjack().getExpMin(material), getLumberjack().getExpMax(material));
            var result = getUserdata().addExp(player, job, exp);
            var lvl = getUserdata().getLvl(player, job);
            if (result >= lvl) {
                getPluginManager().callEvent(new JobLvlChangeEvent(player, job, lvl + 1, lvl));
            }
        }
    }
}