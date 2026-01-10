package org.achymake.jobs.data;

import org.achymake.jobs.Jobs;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class Userdata {
    private Jobs getInstance() {
        return Jobs.getInstance();
    }
    public File getFile(OfflinePlayer offlinePlayer) {
        return new File(getInstance().getDataFolder(), "userdata/" + offlinePlayer.getUniqueId() + ".yml");
    }
    public FileConfiguration getConfig(OfflinePlayer offlinePlayer) {
        return YamlConfiguration.loadConfiguration(getFile(offlinePlayer));
    }
    public boolean exists(OfflinePlayer offlinePlayer) {
        return getFile(offlinePlayer).exists();
    }
    public void setObject(OfflinePlayer offlinePlayer, String path, Object value) {
        var file = getFile(offlinePlayer);
        var config = YamlConfiguration.loadConfiguration(file);
        config.set(path, value);
        try {
            config.save(file);
        } catch (IOException e) {
            getInstance().sendWarning(e.getMessage());
        }
    }
    public void resetExp(OfflinePlayer offlinePlayer, Jobs.jobs jobs) {
        setObject(offlinePlayer, jobs.name() + ".exp", 0.0);
    }
    public double getExp(OfflinePlayer offlinePlayer, Jobs.jobs jobs) {
        return getConfig(offlinePlayer).getDouble(jobs.name() + ".exp");
    }
    public double addExp(Player player, Jobs.jobs jobs, double value) {
        var result = value + getExp(player, jobs);
        setObject(player, jobs.name() + ".exp", result);
        return result;
    }
    public double removeExp(Player player, Jobs.jobs jobs, double value) {
        var result = getExp(player, jobs) - value;
        setObject(player, jobs.name() + ".exp", result);
        return result;
    }
    public int getLvl(OfflinePlayer offlinePlayer, Jobs.jobs jobs) {
        return getConfig(offlinePlayer).getInt(jobs.name() + ".lvl");
    }
    public int addLvl(OfflinePlayer offlinePlayer, Jobs.jobs jobs, int value) {
        var result = getLvl(offlinePlayer, jobs) + value;
        setObject(offlinePlayer, jobs.name() + ".lvl", result);
        return result;
    }
    public int removeLvl(OfflinePlayer offlinePlayer, Jobs.jobs jobs, int value) {
        var result = getLvl(offlinePlayer, jobs) - value;
        setObject(offlinePlayer, jobs.name() + ".lvl", result);
        return result;
    }
    private void setup(OfflinePlayer offlinePlayer) {
        var file = getFile(offlinePlayer);
        var config = YamlConfiguration.loadConfiguration(file);
        try {
            config.save(file);
        } catch (IOException e) {
            getInstance().sendWarning(e.getMessage());
        }
    }
    public void reload(OfflinePlayer offlinePlayer) {
        if (exists(offlinePlayer)) {
            var file = getFile(offlinePlayer);
            var config = YamlConfiguration.loadConfiguration(file);
            try {
                config.load(file);
            } catch (IOException | InvalidConfigurationException e) {
                getInstance().sendWarning(e.getMessage());
            }
        } else setup(offlinePlayer);
    }
    public ArrayList<Map.Entry<OfflinePlayer, Integer>> getTopJobs() {
        var accounts = new HashMap<OfflinePlayer, Integer>();
        for (var offlinePlayer : getOfflinePlayers()) {
            accounts.put(offlinePlayer, getTotal(offlinePlayer));
        }
        var listed = new ArrayList<>(accounts.entrySet());
        listed.sort(Collections.reverseOrder(Map.Entry.comparingByValue()));
        return listed;
    }
    public int getTotal(OfflinePlayer offlinePlayer) {
        return getLvl(offlinePlayer, Jobs.jobs.breeder) + getLvl(offlinePlayer, Jobs.jobs.enchanter) +
        getLvl(offlinePlayer, Jobs.jobs.farmer) + getLvl(offlinePlayer, Jobs.jobs.fisher) +
        getLvl(offlinePlayer, Jobs.jobs.hunter) + getLvl(offlinePlayer, Jobs.jobs.lumberjack) +
        getLvl(offlinePlayer, Jobs.jobs.miner) + getLvl(offlinePlayer, Jobs.jobs.shepherd);
    }
    public Collection<OfflinePlayer> getOfflinePlayers() {
        var listed = new HashSet<OfflinePlayer>();
        var folder = new File(getInstance().getDataFolder(), "userdata");
        if (folder.exists() && folder.isDirectory()) {
            for (var file : folder.listFiles()) {
                if (file.exists() && file.isFile()) {
                    var uuidString = file.getName().replace(".yml", "");
                    listed.add(getInstance().getOfflinePlayer(UUID.fromString(uuidString)));
                }
            }
        }
        return listed;
    }
}