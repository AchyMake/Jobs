package org.achymake.jobs.job;

import org.achymake.jobs.Jobs;
import org.achymake.jobs.data.Userdata;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Lumberjack {
    private Jobs getInstance() {
        return Jobs.getInstance();
    }
    private Userdata getUserdata() {
        return getInstance().getUserdata();
    }
    private final File file = new File(getInstance().getDataFolder(), "job/lumberjack.yml");
    private FileConfiguration config = YamlConfiguration.loadConfiguration(file);
    public boolean isEnabled(Material material) {
        return config.getBoolean(material.name().toUpperCase() + ".enable");
    }
    public double getChance(Material material) {
        return config.getDouble(material.name().toUpperCase() + ".chance");
    }
    public double getMoneyMin(Material material) {
        return config.getDouble(material.name().toUpperCase() + ".money.min");
    }
    public double getMoneyMax(Material material) {
        return config.getDouble(material.name().toUpperCase() + ".money.max");
    }
    public double getExpMin(Material material) {
        return config.getDouble(material.name().toUpperCase() + ".exp.min");
    }
    public double getExpMax(Material material) {
        return config.getDouble(material.name().toUpperCase() + ".exp.max");
    }
    public Set<Map.Entry<OfflinePlayer, Integer>> getTopLumberjack() {
        var accounts = new HashMap<OfflinePlayer, Integer>();
        for (var offlinePlayer : getUserdata().getOfflinePlayers()) {
            accounts.put(offlinePlayer, getUserdata().getLvl(offlinePlayer, Jobs.jobs.lumberjack));
        }
        var list = new ArrayList<>(accounts.entrySet());
        list.sort(Collections.reverseOrder(Map.Entry.comparingByValue()));
        var result = new LinkedHashMap<OfflinePlayer, Integer>();
        result.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .limit(10)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
        for (var entry : list) {
            result.put(entry.getKey(), entry.getValue());
        }
        return result.entrySet();
    }
    private void setup() {
        config.options().copyDefaults(true);
        config.set("OAK_LOG.enable", true);
        config.set("OAK_LOG.exp.min", 0.05);
        config.set("OAK_LOG.exp.max", 0.75);
        config.set("OAK_LOG.money.min", 0.05);
        config.set("OAK_LOG.money.max", 0.25);
        config.set("OAK_LOG.chance", 1.0);
        config.set("SPRUCE_LOG.enable", true);
        config.set("SPRUCE_LOG.exp.min", 0.05);
        config.set("SPRUCE_LOG.exp.max", 0.75);
        config.set("SPRUCE_LOG.money.min", 0.05);
        config.set("SPRUCE_LOG.money.max", 0.25);
        config.set("SPRUCE_LOG.chance", 1.0);
        config.set("BIRCH_LOG.enable", true);
        config.set("BIRCH_LOG.exp.min", 0.05);
        config.set("BIRCH_LOG.exp.max", 0.75);
        config.set("BIRCH_LOG.money.min", 0.05);
        config.set("BIRCH_LOG.money.max", 0.25);
        config.set("BIRCH_LOG.chance", 1.0);
        config.set("JUNGLE_LOG.enable", true);
        config.set("JUNGLE_LOG.exp.min", 0.05);
        config.set("JUNGLE_LOG.exp.max", 0.75);
        config.set("JUNGLE_LOG.money.min", 0.05);
        config.set("JUNGLE_LOG.money.max", 0.25);
        config.set("JUNGLE_LOG.chance", 1.0);
        config.set("ACACIA_LOG.enable", true);
        config.set("ACACIA_LOG.exp.min", 0.05);
        config.set("ACACIA_LOG.exp.max", 0.75);
        config.set("ACACIA_LOG.money.min", 0.05);
        config.set("ACACIA_LOG.money.max", 0.25);
        config.set("ACACIA_LOG.chance", 1.0);
        config.set("DARK_OAK_LOG.enable", true);
        config.set("DARK_OAK_LOG.exp.min", 0.05);
        config.set("DARK_OAK_LOG.exp.max", 0.75);
        config.set("DARK_OAK_LOG.money.min", 0.05);
        config.set("DARK_OAK_LOG.money.max", 0.25);
        config.set("DARK_OAK_LOG.chance", 1.0);
        config.set("MANGROVE_LOG.enable", true);
        config.set("MANGROVE_LOG.exp.min", 0.05);
        config.set("MANGROVE_LOG.exp.max", 0.75);
        config.set("MANGROVE_LOG.money.min", 0.05);
        config.set("MANGROVE_LOG.money.max", 0.25);
        config.set("MANGROVE_LOG.chance", 1.0);
        config.set("CHERRY_LOG.enable", true);
        config.set("CHERRY_LOG.exp.min", 0.05);
        config.set("CHERRY_LOG.exp.max", 0.75);
        config.set("CHERRY_LOG.money.min", 0.05);
        config.set("CHERRY_LOG.money.max", 0.25);
        config.set("CHERRY_LOG.chance", 1.0);
        config.set("PALE_OAK_LOG.enable", true);
        config.set("PALE_OAK_LOG.exp.min", 0.05);
        config.set("PALE_OAK_LOG.exp.max", 0.75);
        config.set("PALE_OAK_LOG.money.min", 0.05);
        config.set("PALE_OAK_LOG.money.max", 0.25);
        config.set("PALE_OAK_LOG.chance", 1.0);
        config.set("WARPED_STEM.enable", true);
        config.set("WARPED_STEM.exp.min", 0.05);
        config.set("WARPED_STEM.exp.max", 0.75);
        config.set("WARPED_STEM.money.min", 0.05);
        config.set("WARPED_STEM.money.max", 0.25);
        config.set("WARPED_STEM.chance", 1.0);
        config.set("CRIMSON_STEM.enable", true);
        config.set("CRIMSON_STEM.exp.min", 0.05);
        config.set("CRIMSON_STEM.exp.max", 0.75);
        config.set("CRIMSON_STEM.money.min", 0.05);
        config.set("CRIMSON_STEM.money.max", 0.25);
        config.set("CRIMSON_STEM.chance", 1.0);
        try {
            config.save(file);
        } catch (IOException e) {
            getInstance().sendWarning(e.getMessage());
        }
    }
    public void reload() {
        if (file.exists()) {
            config = YamlConfiguration.loadConfiguration(file);
        } else setup();
    }
}