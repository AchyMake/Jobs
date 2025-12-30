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

public class Hunter {
    private Jobs getInstance() {
        return Jobs.getInstance();
    }
    private Userdata getUserdata() {
        return getInstance().getUserdata();
    }
    private final File file = new File(getInstance().getDataFolder(), "job/hunter.yml");
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
    public Set<Map.Entry<OfflinePlayer, Integer>> getTopHunter() {
        var accounts = new HashMap<OfflinePlayer, Integer>();
        for (var offlinePlayer : getUserdata().getOfflinePlayers()) {
            accounts.put(offlinePlayer, getUserdata().getLvl(offlinePlayer, Jobs.jobs.hunter));
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
        config.set("CAVE_SPIDER.enable", true);
        config.set("CAVE_SPIDER.exp.min", 0.05);
        config.set("CAVE_SPIDER.exp.max", 0.75);
        config.set("CAVE_SPIDER.money.min", 0.05);
        config.set("CAVE_SPIDER.money.max", 0.25);
        config.set("CAVE_SPIDER.chance", 1.0);
        config.set("DROWNED.enable", true);
        config.set("DROWNED.exp.min", 0.05);
        config.set("DROWNED.exp.max", 0.75);
        config.set("DROWNED.money.min", 0.05);
        config.set("DROWNED.money.max", 0.25);
        config.set("DROWNED.chance", 1.0);
        config.set("ENDERMAN.enable", true);
        config.set("ENDERMAN.exp.min", 0.05);
        config.set("ENDERMAN.exp.max", 0.75);
        config.set("ENDERMAN.money.min", 0.05);
        config.set("ENDERMAN.money.max", 0.25);
        config.set("ENDERMAN.chance", 1.0);
        config.set("SPIDER.enable", true);
        config.set("SPIDER.exp.min", 0.05);
        config.set("SPIDER.exp.max", 0.75);
        config.set("SPIDER.money.min", 0.05);
        config.set("SPIDER.money.max", 0.25);
        config.set("SPIDER.chance", 1.0);
        config.set("BLAZE.enable", true);
        config.set("BLAZE.exp.min", 0.05);
        config.set("BLAZE.exp.max", 0.75);
        config.set("BLAZE.money.min", 0.05);
        config.set("BLAZE.money.max", 0.25);
        config.set("BLAZE.chance", 1.0);
        config.set("BOGGED.enable", true);
        config.set("BOGGED.exp.min", 0.05);
        config.set("BOGGED.exp.max", 0.75);
        config.set("BOGGED.money.min", 0.05);
        config.set("BOGGED.money.max", 0.25);
        config.set("BOGGED.chance", 1.0);
        config.set("BREEZE.enable", true);
        config.set("BREEZE.exp.min", 0.05);
        config.set("BREEZE.exp.max", 0.75);
        config.set("BREEZE.money.min", 0.05);
        config.set("BREEZE.money.max", 0.25);
        config.set("BREEZE.chance", 1.0);
        config.set("CREAKING.enable", true);
        config.set("CREAKING.exp.min", 0.05);
        config.set("CREAKING.exp.max", 0.75);
        config.set("CREAKING.money.min", 0.05);
        config.set("CREAKING.money.max", 0.25);
        config.set("CREAKING.chance", 1.0);
        config.set("CREEPER.enable", true);
        config.set("CREEPER.exp.min", 0.05);
        config.set("CREEPER.exp.max", 0.75);
        config.set("CREEPER.money.min", 0.05);
        config.set("CREEPER.money.max", 0.25);
        config.set("CREEPER.chance", 1.0);
        config.set("ELDER_GUARDIAN.enable", true);
        config.set("ELDER_GUARDIAN.exp.min", 0.05);
        config.set("ELDER_GUARDIAN.exp.max", 0.75);
        config.set("ELDER_GUARDIAN.money.min", 0.05);
        config.set("ELDER_GUARDIAN.money.max", 0.25);
        config.set("ELDER_GUARDIAN.chance", 1.0);
        config.set("ENDERMITE.enable", true);
        config.set("ENDERMITE.exp.min", 0.05);
        config.set("ENDERMITE.exp.max", 0.75);
        config.set("ENDERMITE.money.min", 0.05);
        config.set("ENDERMITE.money.max", 0.25);
        config.set("ENDERMITE.chance", 1.0);
        config.set("EVOKER.enable", true);
        config.set("EVOKER.exp.min", 0.05);
        config.set("EVOKER.exp.max", 0.75);
        config.set("EVOKER.money.min", 0.05);
        config.set("EVOKER.money.max", 0.25);
        config.set("EVOKER.chance", 1.0);
        config.set("GHAST.enable", true);
        config.set("GHAST.exp.min", 0.05);
        config.set("GHAST.exp.max", 0.75);
        config.set("GHAST.money.min", 0.05);
        config.set("GHAST.money.max", 0.25);
        config.set("GHAST.chance", 1.0);
        config.set("GUARDIAN.enable", true);
        config.set("GUARDIAN.exp.min", 0.05);
        config.set("GUARDIAN.exp.max", 0.75);
        config.set("GUARDIAN.money.min", 0.05);
        config.set("GUARDIAN.money.max", 0.25);
        config.set("GUARDIAN.chance", 1.0);
        config.set("HOGLIN.enable", true);
        config.set("HOGLIN.exp.min", 0.05);
        config.set("HOGLIN.exp.max", 0.75);
        config.set("HOGLIN.money.min", 0.05);
        config.set("HOGLIN.money.max", 0.25);
        config.set("HOGLIN.chance", 1.0);
        config.set("HUSK.enable", true);
        config.set("HUSK.exp.min", 0.05);
        config.set("HUSK.exp.max", 0.75);
        config.set("HUSK.money.min", 0.05);
        config.set("HUSK.money.max", 0.25);
        config.set("HUSK.chance", 1.0);
        config.set("MAGMA_CUBE.enable", true);
        config.set("MAGMA_CUBE.exp.min", 0.05);
        config.set("MAGMA_CUBE.exp.max", 0.75);
        config.set("MAGMA_CUBE.money.min", 0.05);
        config.set("MAGMA_CUBE.money.max", 0.25);
        config.set("MAGMA_CUBE.chance", 1.0);
        config.set("PARCHED.enable", true);
        config.set("PARCHED.exp.min", 0.05);
        config.set("PARCHED.exp.max", 0.75);
        config.set("PARCHED.money.min", 0.05);
        config.set("PARCHED.money.max", 0.25);
        config.set("PARCHED.chance", 1.0);
        config.set("PHANTOM.enable", true);
        config.set("PHANTOM.exp.min", 0.05);
        config.set("PHANTOM.exp.max", 0.75);
        config.set("PHANTOM.money.min", 0.05);
        config.set("PHANTOM.money.max", 0.25);
        config.set("PHANTOM.chance", 1.0);
        config.set("PILGIN_BRUTE.enable", true);
        config.set("PILGIN_BRUTE.exp.min", 0.05);
        config.set("PILGIN_BRUTE.exp.max", 0.75);
        config.set("PILGIN_BRUTE.money.min", 0.05);
        config.set("PILGIN_BRUTE.money.max", 0.25);
        config.set("PILGIN_BRUTE.chance", 1.0);
        config.set("PILLAGER.enable", true);
        config.set("PILLAGER.exp.min", 0.05);
        config.set("PILLAGER.exp.max", 0.75);
        config.set("PILLAGER.money.min", 0.05);
        config.set("PILLAGER.money.max", 0.25);
        config.set("PILLAGER.chance", 1.0);
        config.set("RAVAGER.enable", true);
        config.set("RAVAGER.exp.min", 0.05);
        config.set("RAVAGER.exp.max", 0.75);
        config.set("RAVAGER.money.min", 0.05);
        config.set("RAVAGER.money.max", 0.25);
        config.set("RAVAGER.chance", 1.0);
        config.set("SHULKER.enable", true);
        config.set("SHULKER.exp.min", 0.05);
        config.set("SHULKER.exp.max", 0.75);
        config.set("SHULKER.money.min", 0.05);
        config.set("SHULKER.money.max", 0.25);
        config.set("SHULKER.chance", 1.0);
        config.set("SILVERFISH.enable", true);
        config.set("SILVERFISH.exp.min", 0.05);
        config.set("SILVERFISH.exp.max", 0.75);
        config.set("SILVERFISH.money.min", 0.05);
        config.set("SILVERFISH.money.max", 0.25);
        config.set("SILVERFISH.chance", 1.0);
        config.set("SKELETON.enable", true);
        config.set("SKELETON.exp.min", 0.05);
        config.set("SKELETON.exp.max", 0.75);
        config.set("SKELETON.money.min", 0.05);
        config.set("SKELETON.money.max", 0.25);
        config.set("SKELETON.chance", 1.0);
        config.set("SLIME.enable", true);
        config.set("SLIME.exp.min", 0.05);
        config.set("SLIME.exp.max", 0.75);
        config.set("SLIME.money.min", 0.05);
        config.set("SLIME.money.max", 0.25);
        config.set("SLIME.chance", 1.0);
        config.set("STRAY.enable", true);
        config.set("STRAY.exp.min", 0.05);
        config.set("STRAY.exp.max", 0.75);
        config.set("STRAY.money.min", 0.05);
        config.set("STRAY.money.max", 0.25);
        config.set("STRAY.chance", 1.0);
        config.set("VEX.enable", true);
        config.set("VEX.exp.min", 0.05);
        config.set("VEX.exp.max", 0.75);
        config.set("VEX.money.min", 0.05);
        config.set("VEX.money.max", 0.25);
        config.set("VEX.chance", 1.0);
        config.set("VINDICATOR.enable", true);
        config.set("VINDICATOR.exp.min", 0.05);
        config.set("VINDICATOR.exp.max", 0.75);
        config.set("VINDICATOR.money.min", 0.05);
        config.set("VINDICATOR.money.max", 0.25);
        config.set("VINDICATOR.chance", 1.0);
        config.set("WARDEN.enable", true);
        config.set("WARDEN.exp.min", 0.05);
        config.set("WARDEN.exp.max", 0.75);
        config.set("WARDEN.money.min", 0.05);
        config.set("WARDEN.money.max", 0.25);
        config.set("WARDEN.chance", 1.0);
        config.set("WITCH.enable", true);
        config.set("WITCH.exp.min", 0.05);
        config.set("WITCH.exp.max", 0.75);
        config.set("WITCH.money.min", 0.05);
        config.set("WITCH.money.max", 0.25);
        config.set("WITCH.chance", 1.0);
        config.set("WITHER_SKELETON.enable", true);
        config.set("WITHER_SKELETON.exp.min", 0.05);
        config.set("WITHER_SKELETON.exp.max", 0.75);
        config.set("WITHER_SKELETON.money.min", 0.05);
        config.set("WITHER_SKELETON.money.max", 0.25);
        config.set("WITHER_SKELETON.chance", 1.0);
        config.set("ZOGLIN.enable", true);
        config.set("ZOGLIN.exp.min", 0.05);
        config.set("ZOGLIN.exp.max", 0.75);
        config.set("ZOGLIN.money.min", 0.05);
        config.set("ZOGLIN.money.max", 0.25);
        config.set("ZOGLIN.chance", 1.0);
        config.set("ZOMBIE.enable", true);
        config.set("ZOMBIE.exp.min", 0.05);
        config.set("ZOMBIE.exp.max", 0.75);
        config.set("ZOMBIE.money.min", 0.05);
        config.set("ZOMBIE.money.max", 0.25);
        config.set("ZOMBIE.chance", 1.0);
        config.set("ZOMBIE_VILLAGER.enable", true);
        config.set("ZOMBIE_VILLAGER.exp.min", 0.05);
        config.set("ZOMBIE_VILLAGER.exp.max", 0.75);
        config.set("ZOMBIE_VILLAGER.money.min", 0.05);
        config.set("ZOMBIE_VILLAGER.money.max", 0.25);
        config.set("ZOMBIE_VILLAGER.chance", 1.0);
        config.set("ENDER_DRAGON.enable", true);
        config.set("ENDER_DRAGON.exp.min", 0.05);
        config.set("ENDER_DRAGON.exp.max", 0.75);
        config.set("ENDER_DRAGON.money.min", 0.05);
        config.set("ENDER_DRAGON.money.max", 0.25);
        config.set("ENDER_DRAGON.chance", 1.0);
        config.set("WITHER.enable", true);
        config.set("WITHER.exp.min", 0.05);
        config.set("WITHER.exp.max", 0.75);
        config.set("WITHER.money.min", 0.05);
        config.set("WITHER.money.max", 0.25);
        config.set("WITHER.chance", 1.0);
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
