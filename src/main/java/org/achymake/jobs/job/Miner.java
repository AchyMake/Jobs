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

public class Miner {
    private Jobs getInstance() {
        return Jobs.getInstance();
    }
    private Userdata getUserdata() {
        return getInstance().getUserdata();
    }
    private final File file = new File(getInstance().getDataFolder(), "job/miner.yml");
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
    public Set<Map.Entry<OfflinePlayer, Integer>> getTopMiner() {
        var accounts = new HashMap<OfflinePlayer, Integer>();
        for (var offlinePlayer : getUserdata().getOfflinePlayers()) {
            accounts.put(offlinePlayer, getUserdata().getLvl(offlinePlayer, Jobs.jobs.miner));
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
        config.set("STONE.enable", true);
        config.set("STONE.exp.min", 0.01);
        config.set("STONE.exp.max", 0.15);
        config.set("STONE.money.min", 0.01);
        config.set("STONE.money.max", 0.15);
        config.set("STONE.chance", 1.0);
        config.set("DEEPSLATE.enable", true);
        config.set("DEEPSLATE.exp.min", 0.01);
        config.set("DEEPSLATE.exp.max", 0.15);
        config.set("DEEPSLATE.money.min", 0.01);
        config.set("DEEPSLATE.money.max", 0.15);
        config.set("DEEPSLATE.chance", 1.0);
        config.set("COAL_ORE.enable", true);
        config.set("COAL_ORE.exp.min", 0.05);
        config.set("COAL_ORE.exp.max", 0.75);
        config.set("COAL_ORE.money.min", 0.05);
        config.set("COAL_ORE.money.max", 0.25);
        config.set("COAL_ORE.chance", 1.0);
        config.set("DEEPSLATE_COAL_ORE.enable", true);
        config.set("DEEPSLATE_COAL_ORE.exp.min", 0.05);
        config.set("DEEPSLATE_COAL_ORE.exp.max", 0.75);
        config.set("DEEPSLATE_COAL_ORE.money.min", 0.05);
        config.set("DEEPSLATE_COAL_ORE.money.max", 0.25);
        config.set("DEEPSLATE_COAL_ORE.chance", 1.0);
        config.set("IRON_ORE.enable", true);
        config.set("IRON_ORE.exp.min", 0.05);
        config.set("IRON_ORE.exp.max", 0.75);
        config.set("IRON_ORE.money.min", 0.05);
        config.set("IRON_ORE.money.max", 0.25);
        config.set("IRON_ORE.chance", 1.0);
        config.set("DEEPSLATE_IRON_ORE.enable", true);
        config.set("DEEPSLATE_IRON_ORE.exp.min", 0.05);
        config.set("DEEPSLATE_IRON_ORE.exp.max", 0.75);
        config.set("DEEPSLATE_IRON_ORE.money.min", 0.05);
        config.set("DEEPSLATE_IRON_ORE.money.max", 0.25);
        config.set("DEEPSLATE_IRON_ORE.chance", 1.0);
        config.set("COPPER_ORE.enable", true);
        config.set("COPPER_ORE.exp.min", 0.05);
        config.set("COPPER_ORE.exp.max", 0.75);
        config.set("COPPER_ORE.money.min", 0.05);
        config.set("COPPER_ORE.money.max", 0.25);
        config.set("COPPER_ORE.chance", 1.0);
        config.set("DEEPSLATE_COPPER_ORE.enable", true);
        config.set("DEEPSLATE_COPPER_ORE.exp.min", 0.05);
        config.set("DEEPSLATE_COPPER_ORE.exp.max", 0.75);
        config.set("DEEPSLATE_COPPER_ORE.money.min", 0.05);
        config.set("DEEPSLATE_COPPER_ORE.money.max", 0.25);
        config.set("DEEPSLATE_COPPER_ORE.chance", 1.0);
        config.set("GOLD_ORE.enable", true);
        config.set("GOLD_ORE.exp.min", 0.05);
        config.set("GOLD_ORE.exp.max", 0.75);
        config.set("GOLD_ORE.money.min", 0.05);
        config.set("GOLD_ORE.money.max", 0.25);
        config.set("GOLD_ORE.chance", 1.0);
        config.set("DEEPSLATE_GOLD_ORE.enable", true);
        config.set("DEEPSLATE_GOLD_ORE.exp.min", 0.05);
        config.set("DEEPSLATE_GOLD_ORE.exp.max", 0.75);
        config.set("DEEPSLATE_GOLD_ORE.money.min", 0.05);
        config.set("DEEPSLATE_GOLD_ORE.money.max", 0.25);
        config.set("DEEPSLATE_GOLD_ORE.chance", 1.0);
        config.set("REDSTONE_ORE.enable", true);
        config.set("REDSTONE_ORE.exp.min", 0.05);
        config.set("REDSTONE_ORE.exp.max", 0.75);
        config.set("REDSTONE_ORE.money.min", 0.05);
        config.set("REDSTONE_ORE.money.max", 0.25);
        config.set("REDSTONE_ORE.chance", 1.0);
        config.set("DEEPSLATE_REDSTONE_ORE.enable", true);
        config.set("DEEPSLATE_REDSTONE_ORE.exp.min", 0.05);
        config.set("DEEPSLATE_REDSTONE_ORE.exp.max", 0.75);
        config.set("DEEPSLATE_REDSTONE_ORE.money.min", 0.05);
        config.set("DEEPSLATE_REDSTONE_ORE.money.max", 0.25);
        config.set("DEEPSLATE_REDSTONE_ORE.chance", 1.0);
        config.set("EMERALD_ORE.enable", true);
        config.set("EMERALD_ORE.exp.min", 0.05);
        config.set("EMERALD_ORE.exp.max", 0.75);
        config.set("EMERALD_ORE.money.min", 0.05);
        config.set("EMERALD_ORE.money.max", 0.25);
        config.set("EMERALD_ORE.chance", 1.0);
        config.set("DEEPSLATE_EMERALD_ORE.enable", true);
        config.set("DEEPSLATE_EMERALD_ORE.exp.min", 0.05);
        config.set("DEEPSLATE_EMERALD_ORE.exp.max", 0.75);
        config.set("DEEPSLATE_EMERALD_ORE.money.min", 0.05);
        config.set("DEEPSLATE_EMERALD_ORE.money.max", 0.25);
        config.set("DEEPSLATE_EMERALD_ORE.chance", 1.0);
        config.set("LAPIS_ORE.enable", true);
        config.set("LAPIS_ORE.exp.min", 0.05);
        config.set("LAPIS_ORE.exp.max", 0.75);
        config.set("LAPIS_ORE.money.min", 0.05);
        config.set("LAPIS_ORE.money.max", 0.25);
        config.set("LAPIS_ORE.chance", 1.0);
        config.set("DEEPSLATE_LAPIS_ORE.enable", true);
        config.set("DEEPSLATE_LAPIS_ORE.exp.min", 0.05);
        config.set("DEEPSLATE_LAPIS_ORE.exp.max", 0.75);
        config.set("DEEPSLATE_LAPIS_ORE.money.min", 0.05);
        config.set("DEEPSLATE_LAPIS_ORE.money.max", 0.25);
        config.set("DEEPSLATE_LAPIS_ORE.chance", 1.0);
        config.set("DIAMOND_ORE.enable", true);
        config.set("DIAMOND_ORE.exp.min", 0.05);
        config.set("DIAMOND_ORE.exp.max", 0.75);
        config.set("DIAMOND_ORE.money.min", 0.05);
        config.set("DIAMOND_ORE.money.max", 0.25);
        config.set("DIAMOND_ORE.chance", 1.0);
        config.set("DEEPSLATE_DIAMOND_ORE.enable", true);
        config.set("DEEPSLATE_DIAMOND_ORE.exp.min", 0.05);
        config.set("DEEPSLATE_DIAMOND_ORE.exp.max", 0.75);
        config.set("DEEPSLATE_DIAMOND_ORE.money.min", 0.05);
        config.set("DEEPSLATE_DIAMOND_ORE.money.max", 0.25);
        config.set("DEEPSLATE_DIAMOND_ORE.chance", 1.0);
        config.set("NETHER_GOLD_ORE.enable", true);
        config.set("NETHER_GOLD_ORE.exp.min", 0.05);
        config.set("NETHER_GOLD_ORE.exp.max", 0.75);
        config.set("NETHER_GOLD_ORE.money.min", 0.05);
        config.set("NETHER_GOLD_ORE.money.max", 0.25);
        config.set("NETHER_GOLD_ORE.chance", 1.0);
        config.set("NETHER_QUARTZ_ORE.enable", true);
        config.set("NETHER_QUARTZ_ORE.exp.min", 0.05);
        config.set("NETHER_QUARTZ_ORE.exp.max", 0.75);
        config.set("NETHER_QUARTZ_ORE.money.min", 0.05);
        config.set("NETHER_QUARTZ_ORE.money.max", 0.25);
        config.set("NETHER_QUARTZ_ORE.chance", 1.0);
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
