package org.achymake.jobs.listeners;

import net.milkbowl.vault.economy.Economy;
import org.achymake.jobs.Jobs;
import org.achymake.jobs.data.Message;
import org.achymake.jobs.data.Userdata;
import org.achymake.jobs.events.JobLvlChangeEvent;
import org.achymake.jobs.handlers.GameModeHandler;
import org.achymake.jobs.handlers.RandomHandler;
import org.achymake.jobs.job.Farmer;
import org.achymake.replant.events.PlayerReplantEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;

public class PlayerReplant implements Listener {
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
    private Farmer getFarmer() {
        return getInstance().getFarmer();
    }
    private Economy getEconomy() {
        return getInstance().getEconomy();
    }
    private PluginManager getPluginManager() {
        return getInstance().getPluginManager();
    }
    public PlayerReplant() {
        getPluginManager().registerEvents(this, getInstance());
    }
    @EventHandler(priority = EventPriority.MONITOR)
    public void onPlayerReplant(PlayerReplantEvent event) {
        if (event.isCancelled())return;
        var player = event.getPlayer();
        if (!player.getGameMode().equals(getGameModeHandler().get("survival")))return;
        var block = event.getClickedBlock();
        var material = block.getType();
        if (!getFarmer().isEnabled(material))return;
        if (!player.hasPermission("jobs.job.farmer"))return;
        if (!getRandomHandler().isTrue(getFarmer().getChance(material)))return;
        var job = Jobs.jobs.farmer;
        var money = getRandomHandler().nextDouble(getFarmer().getMoneyMin(material), getFarmer().getMoneyMax(material));
        getEconomy().depositPlayer(player, money);
        getMessage().sendActionBar(player, getMessage().get("event.money.message", getEconomy().currencyNameSingular() + getMessage().format(money)));
        var exp = getRandomHandler().nextDouble(getFarmer().getExpMin(material), getFarmer().getExpMax(material));
        var result = getUserdata().addExp(player, job, exp);
        var lvl = getUserdata().getLvl(player, job);
        if (result >= lvl) {
            getPluginManager().callEvent(new JobLvlChangeEvent(player, job, lvl + 1, lvl));
        }
    }
}