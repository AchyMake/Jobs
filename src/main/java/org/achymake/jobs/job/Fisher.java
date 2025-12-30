package org.achymake.jobs.job;

import org.achymake.jobs.Jobs;
import org.achymake.jobs.data.Userdata;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Fisher {
    private Jobs getInstance() {
        return Jobs.getInstance();
    }
    private Userdata getUserdata() {
        return getInstance().getUserdata();
    }
    private final File file = new File(getInstance().getDataFolder(), "job/fisher.yml");
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
    public Set<Map.Entry<OfflinePlayer, Integer>> getTopFisher() {
        var accounts = new HashMap<OfflinePlayer, Integer>();
        for (var offlinePlayer : getUserdata().getOfflinePlayers()) {
            accounts.put(offlinePlayer, getUserdata().getLvl(offlinePlayer, Jobs.jobs.fisher));
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
        config.set("COD.enable", true);
        config.set("COD.exp.min", 0.05);
        config.set("COD.exp.max", 0.5);
        config.set("COD.money.min", 0.05);
        config.set("COD.money.max", 0.25);
        config.set("COD.chance", 1.0);
        config.set("SALMON.enable", true);
        config.set("SALMON.exp.min", 0.05);
        config.set("SALMON.exp.max", 0.75);
        config.set("SALMON.money.min", 0.05);
        config.set("SALMON.money.max", 0.25);
        config.set("SALMON.chance", 1.0);
        config.set("PUFFERFISH.enable", true);
        config.set("PUFFERFISH.exp.min", 0.25);
        config.set("PUFFERFISH.exp.max", 0.75);
        config.set("PUFFERFISH.money.min", 0.05);
        config.set("PUFFERFISH.money.max", 0.25);
        config.set("PUFFERFISH.chance", 1.0);
        config.set("TROPICAL_FISH.enable", true);
        config.set("TROPICAL_FISH.exp.min", 0.5);
        config.set("TROPICAL_FISH.exp.max", 0.75);
        config.set("TROPICAL_FISH.money.min", 0.05);
        config.set("TROPICAL_FISH.money.max", 0.25);
        config.set("TROPICAL_FISH.chance", 1.0);

        config.set("BOW.enable", true);
        config.set("BOW.exp.min", 0.25);
        config.set("BOW.exp.max", 0.5);
        config.set("BOW.money.min", 0.05);
        config.set("BOW.money.max", 0.25);
        config.set("BOW.chance", 1.0);
        config.set("ENCHANTED_BOOD.enable", true);
        config.set("ENCHANTED_BOOD.exp.min", 0.25);
        config.set("ENCHANTED_BOOD.exp.max", 0.5);
        config.set("ENCHANTED_BOOD.money.min", 0.05);
        config.set("ENCHANTED_BOOD.money.max", 0.25);
        config.set("ENCHANTED_BOOD.chance", 1.0);
        config.set("FISHING_ROD.enable", true);
        config.set("FISHING_ROD.exp.min", 0.25);
        config.set("FISHING_ROD.exp.max", 0.5);
        config.set("FISHING_ROD.money.min", 0.05);
        config.set("FISHING_ROD.money.max", 0.25);
        config.set("FISHING_ROD.chance", 1.0);
        config.set("NAME_TAG.enable", true);
        config.set("NAME_TAG.exp.min", 0.25);
        config.set("NAME_TAG.exp.max", 0.5);
        config.set("NAME_TAG.money.min", 0.05);
        config.set("NAME_TAG.money.max", 0.25);
        config.set("NAME_TAG.chance", 1.0);
        config.set("NAUTILUS_SHELL.enable", true);
        config.set("NAUTILUS_SHELL.exp.min", 0.25);
        config.set("NAUTILUS_SHELL.exp.max", 0.5);
        config.set("NAUTILUS_SHELL.money.min", 0.05);
        config.set("NAUTILUS_SHELL.money.max", 0.25);
        config.set("NAUTILUS_SHELL.chance", 1.0);
        config.set("SADDLE.enable", true);
        config.set("SADDLE.exp.min", 0.25);
        config.set("SADDLE.exp.max", 0.5);
        config.set("SADDLE.money.min", 0.05);
        config.set("SADDLE.money.max", 0.25);
        config.set("SADDLE.chance", 1.0);

        config.set("LILY_PAD.enable", true);
        config.set("LILY_PAD.exp.min", 0.05);
        config.set("LILY_PAD.exp.max", 0.25);
        config.set("LILY_PAD.money.min", 0.05);
        config.set("LILY_PAD.money.max", 0.25);
        config.set("LILY_PAD.chance", 1.0);
        config.set("BONE.enable", true);
        config.set("BONE.exp.min", 0.05);
        config.set("BONE.exp.max", 0.25);
        config.set("BONE.money.min", 0.05);
        config.set("BONE.money.max", 0.25);
        config.set("BONE.chance", 1.0);
        config.set("BOWL.enable", true);
        config.set("BOWL.exp.min", 0.05);
        config.set("BOWL.exp.max", 0.25);
        config.set("BOWL.money.min", 0.05);
        config.set("BOWL.money.max", 0.25);
        config.set("BOWL.chance", 1.0);
        config.set("LEATHER.enable", true);
        config.set("LEATHER.exp.min", 0.05);
        config.set("LEATHER.exp.max", 0.25);
        config.set("LEATHER.money.min", 0.05);
        config.set("LEATHER.money.max", 0.25);
        config.set("LEATHER.chance", 1.0);
        config.set("LEATHER_BOOTS.enable", true);
        config.set("LEATHER_BOOTS.exp.min", 0.05);
        config.set("LEATHER_BOOTS.exp.max", 0.25);
        config.set("LEATHER_BOOTS.money.min", 0.05);
        config.set("LEATHER_BOOTS.money.max", 0.25);
        config.set("LEATHER_BOOTS.chance", 1.0);
        config.set("ROTTEN_FLESH.enable", true);
        config.set("ROTTEN_FLESH.exp.min", 0.05);
        config.set("ROTTEN_FLESH.exp.max", 0.25);
        config.set("ROTTEN_FLESH.money.min", 0.05);
        config.set("ROTTEN_FLESH.money.max", 0.25);
        config.set("ROTTEN_FLESH.chance", 1.0);
        config.set("POTION.enable", true);
        config.set("POTION.exp.min", 0.05);
        config.set("POTION.exp.max", 0.25);
        config.set("POTION.money.min", 0.05);
        config.set("POTION.money.max", 0.25);
        config.set("POTION.chance", 1.0);
        config.set("TRIPWIRE_HOOK.enable", true);
        config.set("TRIPWIRE_HOOK.exp.min", 0.05);
        config.set("TRIPWIRE_HOOK.exp.max", 0.25);
        config.set("TRIPWIRE_HOOK.money.min", 0.05);
        config.set("TRIPWIRE_HOOK.money.max", 0.25);
        config.set("TRIPWIRE_HOOK.chance", 1.0);
        config.set("STICK.enable", true);
        config.set("STICK.exp.min", 0.05);
        config.set("STICK.exp.max", 0.25);
        config.set("STICK.money.min", 0.05);
        config.set("STICK.money.max", 0.25);
        config.set("STICK.chance", 1.0);
        config.set("STRING.enable", true);
        config.set("STRING.exp.min", 0.05);
        config.set("STRING.exp.max", 0.25);
        config.set("STRING.money.min", 0.05);
        config.set("STRING.money.max", 0.25);
        config.set("STRING.chance", 1.0);
        config.set("INK_SAC.enable", true);
        config.set("INK_SAC.exp.min", 0.05);
        config.set("INK_SAC.exp.max", 0.25);
        config.set("INK_SAC.money.min", 0.05);
        config.set("INK_SAC.money.max", 0.25);
        config.set("INK_SAC.chance", 1.0);
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