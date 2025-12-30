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

public class Farmer {
    private Jobs getInstance() {
        return Jobs.getInstance();
    }
    private Userdata getUserdata() {
        return getInstance().getUserdata();
    }
    private final File file = new File(getInstance().getDataFolder(), "job/farmer.yml");
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
    public Set<Map.Entry<OfflinePlayer, Integer>> getTopFarmer() {
        var accounts = new HashMap<OfflinePlayer, Integer>();
        for (var offlinePlayer : getUserdata().getOfflinePlayers()) {
            accounts.put(offlinePlayer, getUserdata().getLvl(offlinePlayer, Jobs.jobs.farmer));
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
        config.set("CARROTS.enable", true);
        config.set("CARROTS.exp.min", 0.05);
        config.set("CARROTS.exp.max", 0.75);
        config.set("CARROTS.money.min", 0.05);
        config.set("CARROTS.money.max", 0.25);
        config.set("CARROTS.chance", 1.0);
        config.set("POTATOES.enable", true);
        config.set("POTATOES.exp.min", 0.05);
        config.set("POTATOES.exp.max", 0.75);
        config.set("POTATOES.money.min", 0.05);
        config.set("POTATOES.money.max", 0.25);
        config.set("POTATOES.chance", 1.0);
        config.set("WHEAT.enable", true);
        config.set("WHEAT.exp.min", 0.05);
        config.set("WHEAT.exp.max", 0.75);
        config.set("WHEAT.money.min", 0.05);
        config.set("WHEAT.money.max", 0.25);
        config.set("WHEAT.chance", 1.0);
        config.set("BEETROOTS.enable", true);
        config.set("BEETROOTS.exp.min", 0.05);
        config.set("BEETROOTS.exp.max", 0.75);
        config.set("BEETROOTS.money.min", 0.05);
        config.set("BEETROOTS.money.max", 0.25);
        config.set("BEETROOTS.chance", 1.0);
        config.set("COCOA.enable", true);
        config.set("COCOA.exp.min", 0.05);
        config.set("COCOA.exp.max", 0.75);
        config.set("COCOA.money.min", 0.05);
        config.set("COCOA.money.max", 0.25);
        config.set("COCOA.chance", 1.0);
        config.set("NETHER_WART.enable", true);
        config.set("NETHER_WART.exp.min", 0.05);
        config.set("NETHER_WART.exp.max", 0.75);
        config.set("NETHER_WART.money.min", 0.05);
        config.set("NETHER_WART.money.max", 0.25);
        config.set("NETHER_WART.chance", 1.0);
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
