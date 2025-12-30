package org.achymake.jobs;

import net.milkbowl.vault.economy.Economy;
import org.achymake.jobs.commands.*;
import org.achymake.jobs.data.*;
import org.achymake.jobs.handlers.*;
import org.achymake.jobs.job.*;
import org.achymake.jobs.listeners.*;
import org.achymake.jobs.providers.PlaceholderProvider;
import org.bukkit.NamespacedKey;
import org.bukkit.OfflinePlayer;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

import java.io.File;
import java.util.UUID;

public final class Jobs extends JavaPlugin {
    private static Jobs instance;
    private Message message;
    private Userdata userdata;
    private BlockHandler blockHandler;
    private RandomHandler randomHandler;
    private ScheduleHandler scheduleHandler;
    private WorldHandler worldHandler;
    private Breeder breeder;
    private Enchanter enchanter;
    private Farmer farmer;
    private Fisher fisher;
    private Hunter hunter;
    private Lumberjack lumberjack;
    private Miner miner;
    private Shepherd shepherd;
    private UpdateChecker updateChecker;
    private BukkitScheduler bukkitScheduler;
    private PluginManager pluginManager;
    private Economy economy = null;
    @Override
    public void onEnable() {
        instance = this;
        message = new Message();
        userdata = new Userdata();
        blockHandler = new BlockHandler();
        randomHandler = new RandomHandler();
        scheduleHandler = new ScheduleHandler();
        worldHandler = new WorldHandler();
        breeder = new Breeder();
        enchanter = new Enchanter();
        farmer = new Farmer();
        fisher = new Fisher();
        hunter = new Hunter();
        lumberjack = new Lumberjack();
        miner = new Miner();
        shepherd = new Shepherd();
        updateChecker = new UpdateChecker();
        bukkitScheduler = getServer().getScheduler();
        pluginManager = getServer().getPluginManager();
        var economyServices = getServer().getServicesManager().getRegistration(Economy.class);
        if (economyServices != null) {
            economy = economyServices.getProvider();
        } else getPluginManager().disablePlugin(this);
        commands();
        events();
        reload();
        new PlaceholderProvider().register();
        sendInfo("Enabled for " + getMinecraftProvider() + " " + getMinecraftVersion());
        getUpdateChecker().getUpdate();
    }
    @Override
    public void onDisable() {
        new PlaceholderProvider().unregister();
        getScheduleHandler().disable();
        sendInfo("Disabled for " + getMinecraftProvider() + " " + getMinecraftVersion());
    }
    private void commands() {
        new JobCommand();
        new JobsCommand();
    }
    private void events() {
        new BlockBreak();
        new BlockPlace();
        new EnchantItem();
        new EntityBreed();
        new EntityDeath();
        new JobLvlChange();
        new PlayerFish();
        new PlayerJoin();
        new PlayerLogin();
        new PlayerShearEntity();
        if (getPluginManager().isPluginEnabled("Replant")) {
            new PlayerReplant();
        }
    }
    public void reload() {
        if (!new File(getDataFolder(), "config.yml").exists()) {
            getConfig().options().copyDefaults(true);
            saveConfig();
        } else reloadConfig();
        getMessage().reload();
        getBreeder().reload();
        getEnchanter().reload();
        getFarmer().reload();
        getFisher().reload();
        getHunter().reload();
        getLumberjack().reload();
        getMiner().reload();
        getShepherd().reload();
    }
    public Economy getEconomy() {
        return economy;
    }
    public PluginManager getPluginManager() {
        return pluginManager;
    }
    public BukkitScheduler getBukkitScheduler() {
        return bukkitScheduler;
    }
    public UpdateChecker getUpdateChecker() {
        return updateChecker;
    }
    public Shepherd getShepherd() {
        return shepherd;
    }
    public Miner getMiner() {
        return miner;
    }
    public Lumberjack getLumberjack() {
        return lumberjack;
    }
    public Hunter getHunter() {
        return hunter;
    }
    public Fisher getFisher() {
        return fisher;
    }
    public Farmer getFarmer() {
        return farmer;
    }
    public Enchanter getEnchanter() {
        return enchanter;
    }
    public Breeder getBreeder() {
        return breeder;
    }
    public WorldHandler getWorldHandler() {
        return worldHandler;
    }
    public ScheduleHandler getScheduleHandler() {
        return scheduleHandler;
    }
    public RandomHandler getRandomHandler() {
        return randomHandler;
    }
    public BlockHandler getBlockHandler() {
        return blockHandler;
    }
    public Userdata getUserdata() {
        return userdata;
    }
    public Message getMessage() {
        return message;
    }
    public static Jobs getInstance() {
        return instance;
    }
    public void sendInfo(String message) {
        getLogger().info(message);
    }
    public void sendWarning(String message) {
        getLogger().warning(message);
    }
    public String name() {
        return getDescription().getName();
    }
    public String version() {
        return getDescription().getVersion();
    }
    public String getMinecraftVersion() {
        return getServer().getBukkitVersion();
    }
    public String getMinecraftProvider() {
        return getServer().getName();
    }
    public enum jobs {
        breeder,
        enchanter,
        farmer,
        fisher,
        hunter,
        lumberjack,
        miner,
        shepherd
    }
    public OfflinePlayer getOfflinePlayer(UUID uuid) {
        return getServer().getOfflinePlayer(uuid);
    }
    public NamespacedKey getKey(String key) {
        return new NamespacedKey(this, key);
    }
}