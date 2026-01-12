package org.achymake.jobs.listeners;

import net.milkbowl.vault.economy.Economy;
import org.achymake.jobs.Jobs;
import org.achymake.jobs.data.Message;
import org.achymake.jobs.data.Userdata;
import org.achymake.jobs.events.JobLvlChangeEvent;
import org.achymake.jobs.handlers.GameModeHandler;
import org.achymake.jobs.handlers.RandomHandler;
import org.achymake.jobs.handlers.WorldHandler;
import org.achymake.jobs.job.Fisher;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Item;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.plugin.PluginManager;

public class PlayerFish implements Listener {
    private Jobs getInstance() {
        return Jobs.getInstance();
    }
    private FileConfiguration getConfig() {
        return getInstance().getConfig();
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
    private WorldHandler getWorldHandler() {
        return getInstance().getWorldHandler();
    }
    private Fisher getFisher() {
        return getInstance().getFisher();
    }
    private Economy getEconomy() {
        return getInstance().getEconomy();
    }
    private PluginManager getPluginManager() {
        return getInstance().getPluginManager();
    }
    public PlayerFish() {
        getPluginManager().registerEvents(this, getInstance());
    }
    @EventHandler(priority = EventPriority.MONITOR)
    public void onPlayerFish(PlayerFishEvent event) {
        if (event.isCancelled())return;
        var state = event.getState();
        var caught = event.getCaught();
        var stateSection = getConfig().getConfigurationSection("fishing." + state.name().toUpperCase());
        if (stateSection != null) {
            if (stateSection.getBoolean("sound.enable")) {
                var soundType = stateSection.getString("sound.type");
                var soundVolume = stateSection.getDouble("sound.volume");
                var soundPitch = stateSection.getDouble("sound.pitch");
                getWorldHandler().playSound(event.getPlayer(), soundType, soundVolume, soundPitch);
            }
            if (caught != null) {
                var name = stateSection.getString("caught.name");
                if (name != null && !name.isBlank()) {
                    caught.setCustomName(getInstance().getMessage().addColor(stateSection.getString("caught.name")));
                    caught.setCustomNameVisible(true);
                }
            }
        }
        if (caught instanceof Item item) {
            var player = event.getPlayer();
            if (!player.getGameMode().equals(getGameModeHandler().get("survival")))return;
            var itemStack = item.getItemStack();
            var material = itemStack.getType();
            if (!getFisher().isEnabled(material))return;
            if (!player.hasPermission("jobs.job.fisher"))return;
            if (!getRandomHandler().isTrue(getFisher().getChance(material)))return;
            var job = Jobs.jobs.fisher;
            var money = getRandomHandler().nextDouble(getFisher().getMoneyMin(material), getFisher().getMoneyMax(material));
            getEconomy().depositPlayer(player, money);
            getMessage().sendActionBar(player, getMessage().get("event.money.message", getEconomy().currencyNameSingular() + getMessage().format(money)));
            var exp = getRandomHandler().nextDouble(getFisher().getExpMin(material), getFisher().getExpMax(material));
            var result = getUserdata().addExp(player, job, exp);
            var lvl = getUserdata().getLvl(player, job);
            if (result >= lvl) {
                getPluginManager().callEvent(new JobLvlChangeEvent(player, job, lvl + 1, lvl));
            }
        }
    }
}