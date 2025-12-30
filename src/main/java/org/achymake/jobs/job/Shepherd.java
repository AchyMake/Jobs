package org.achymake.jobs.job;

import org.achymake.jobs.Jobs;
import org.achymake.jobs.data.Userdata;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Shepherd {
    private Jobs getInstance() {
        return Jobs.getInstance();
    }
    private Userdata getUserdata() {
        return getInstance().getUserdata();
    }
    private final File file = new File(getInstance().getDataFolder(), "job/shepherd.yml");
    private FileConfiguration config = YamlConfiguration.loadConfiguration(file);
    public boolean isEnabled(EntityType entityType) {
        return config.getBoolean(entityType.name().toUpperCase() + ".enable");
    }
    public double getChance(EntityType entityType) {
        return config.getDouble(entityType.name().toUpperCase() + ".chance");
    }
    public double getMoneyMin(EntityType entityType) {
        return config.getDouble(entityType.name().toUpperCase() + ".money.min");
    }
    public double getMoneyMax(EntityType entityType) {
        return config.getDouble(entityType.name().toUpperCase() + ".money.max");
    }
    public double getExpMin(EntityType entityType) {
        return config.getDouble(entityType.name().toUpperCase() + ".exp.min");
    }
    public double getExpMax(EntityType entityType) {
        return config.getDouble(entityType.name().toUpperCase() + ".exp.max");
    }
    public Set<Map.Entry<OfflinePlayer, Integer>> getTopShepherd() {
        var accounts = new HashMap<OfflinePlayer, Integer>();
        for (var offlinePlayer : getUserdata().getOfflinePlayers()) {
            accounts.put(offlinePlayer, getUserdata().getLvl(offlinePlayer, Jobs.jobs.shepherd));
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
        config.set("SHEEP.enable", true);
        config.set("SHEEP.exp.min", 0.05);
        config.set("SHEEP.exp.max", 0.75);
        config.set("SHEEP.money.min", 0.05);
        config.set("SHEEP.money.max", 0.25);
        config.set("SHEEP.chance", 1.0);
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