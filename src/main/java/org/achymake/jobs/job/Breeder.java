package org.achymake.jobs.job;

import org.achymake.jobs.Jobs;
import org.achymake.jobs.data.Userdata;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.EntityType;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Breeder {
    private Jobs getInstance() {
        return Jobs.getInstance();
    }
    private Userdata getUserdata() {
        return getInstance().getUserdata();
    }
    private final File file = new File(getInstance().getDataFolder(), "job/breeder.yml");
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
    public Set<Map.Entry<OfflinePlayer, Integer>> getTopBreeder() {
        var accounts = new HashMap<OfflinePlayer, Integer>();
        for (var offlinePlayer : getUserdata().getOfflinePlayers()) {
            accounts.put(offlinePlayer, getUserdata().getLvl(offlinePlayer, Jobs.jobs.breeder));
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
        config.set("ARMADILLO.enable", true);
        config.set("ARMADILLO.exp.min", 0.25);
        config.set("ARMADILLO.exp.max", 0.75);
        config.set("ARMADILLO.money.min", 0.05);
        config.set("ARMADILLO.money.max", 0.25);
        config.set("ARMADILLO.chance", 1.0);
        config.set("AXOLOTL.enable", true);
        config.set("AXOLOTL.exp.min", 0.25);
        config.set("AXOLOTL.exp.max", 0.75);
        config.set("AXOLOTL.money.min", 0.05);
        config.set("AXOLOTL.money.max", 0.25);
        config.set("AXOLOTL.chance", 1.0);
        config.set("BEE.enable", true);
        config.set("BEE.exp.min", 0.25);
        config.set("BEE.exp.max", 0.75);
        config.set("BEE.money.min", 0.05);
        config.set("BEE.money.max", 0.25);
        config.set("BEE.chance", 1.0);
        config.set("CAMEL.enable", true);
        config.set("CAMEL.exp.min", 0.25);
        config.set("CAMEL.exp.max", 0.75);
        config.set("CAMEL.money.min", 0.05);
        config.set("CAMEL.money.max", 0.25);
        config.set("CAMEL.chance", 1.0);
        config.set("CAMEL_HUSK.enable", true);
        config.set("CAMEL_HUSK.exp.min", 0.25);
        config.set("CAMEL_HUSK.exp.max", 0.75);
        config.set("CAMEL_HUSK.money.min", 0.05);
        config.set("CAMEL_HUSK.money.max", 0.25);
        config.set("CAMEL_HUSK.chance", 1.0);
        config.set("CAT.enable", true);
        config.set("CAT.exp.min", 0.25);
        config.set("CAT.exp.max", 0.75);
        config.set("CAT.money.min", 0.05);
        config.set("CAT.money.max", 0.25);
        config.set("CAT.chance", 1.0);
        config.set("CHICKEN.enable", true);
        config.set("CHICKEN.exp.min", 0.25);
        config.set("CHICKEN.exp.max", 0.75);
        config.set("CHICKEN.money.min", 0.05);
        config.set("CHICKEN.money.max", 0.25);
        config.set("CHICKEN.chance", 1.0);
        config.set("COW.enable", true);
        config.set("COW.exp.min", 0.25);
        config.set("COW.exp.max", 0.75);
        config.set("COW.money.min", 0.05);
        config.set("COW.money.max", 0.25);
        config.set("COW.chance", 1.0);
        config.set("DONKEY.enable", true);
        config.set("DONKEY.exp.min", 0.25);
        config.set("DONKEY.exp.max", 0.75);
        config.set("DONKEY.money.min", 0.05);
        config.set("DONKEY.money.max", 0.25);
        config.set("DONKEY.chance", 1.0);
        config.set("FOX.enable", true);
        config.set("FOX.exp.min", 0.25);
        config.set("FOX.exp.max", 0.75);
        config.set("FOX.money.min", 0.05);
        config.set("FOX.money.max", 0.25);
        config.set("FOX.chance", 1.0);
        config.set("FROG.enable", true);
        config.set("FROG.exp.min", 0.25);
        config.set("FROG.exp.max", 0.75);
        config.set("FROG.money.min", 0.05);
        config.set("FROG.money.max", 0.25);
        config.set("FROG.chance", 1.0);
        config.set("GOAT.enable", true);
        config.set("GOAT.exp.min", 0.25);
        config.set("GOAT.exp.max", 0.75);
        config.set("GOAT.money.min", 0.05);
        config.set("GOAT.money.max", 0.25);
        config.set("GOAT.chance", 1.0);
        config.set("HAPPY_GHAST.enable", true);
        config.set("HAPPY_GHAST.exp.min", 0.25);
        config.set("HAPPY_GHAST.exp.max", 0.75);
        config.set("HAPPY_GHAST.money.min", 0.05);
        config.set("HAPPY_GHAST.money.max", 0.25);
        config.set("HAPPY_GHAST.chance", 1.0);
        config.set("HOGLIN.enable", true);
        config.set("HOGLIN.exp.min", 0.25);
        config.set("HOGLIN.exp.max", 0.75);
        config.set("HOGLIN.money.min", 0.05);
        config.set("HOGLIN.money.max", 0.25);
        config.set("HOGLIN.chance", 1.0);
        config.set("HORSE.enable", true);
        config.set("HORSE.exp.min", 0.25);
        config.set("HORSE.exp.max", 0.75);
        config.set("HORSE.money.min", 0.05);
        config.set("HORSE.money.max", 0.25);
        config.set("HORSE.chance", 1.0);
        config.set("LLAMA.enable", true);
        config.set("LLAMA.exp.min", 0.25);
        config.set("LLAMA.exp.max", 0.75);
        config.set("LLAMA.money.min", 0.05);
        config.set("LLAMA.money.max", 0.25);
        config.set("LLAMA.chance", 1.0);
        config.set("MULE.enable", true);
        config.set("MULE.exp.min", 0.25);
        config.set("MULE.exp.max", 0.75);
        config.set("MULE.money.min", 0.05);
        config.set("MULE.money.max", 0.25);
        config.set("MULE.chance", 1.0);
        config.set("MOOSHROOM.enable", true);
        config.set("MOOSHROOM.exp.min", 0.25);
        config.set("MOOSHROOM.exp.max", 0.75);
        config.set("MOOSHROOM.money.min", 0.05);
        config.set("MOOSHROOM.money.max", 0.25);
        config.set("MOOSHROOM.chance", 1.0);
        config.set("NAUTILUS.enable", true);
        config.set("NAUTILUS.exp.min", 0.25);
        config.set("NAUTILUS.exp.max", 0.75);
        config.set("NAUTILUS.money.min", 0.05);
        config.set("NAUTILUS.money.max", 0.25);
        config.set("NAUTILUS.chance", 1.0);
        config.set("OCELOT.enable", true);
        config.set("OCELOT.exp.min", 0.25);
        config.set("OCELOT.exp.max", 0.75);
        config.set("OCELOT.money.min", 0.05);
        config.set("OCELOT.money.max", 0.25);
        config.set("OCELOT.chance", 1.0);
        config.set("PANDA.enable", true);
        config.set("PANDA.exp.min", 0.25);
        config.set("PANDA.exp.max", 0.75);
        config.set("PANDA.money.min", 0.05);
        config.set("PANDA.money.max", 0.25);
        config.set("PANDA.chance", 1.0);
        config.set("PARROT.enable", true);
        config.set("PARROT.exp.min", 0.25);
        config.set("PARROT.exp.max", 0.75);
        config.set("PARROT.money.min", 0.05);
        config.set("PARROT.money.max", 0.25);
        config.set("PARROT.chance", 1.0);
        config.set("PIG.enable", true);
        config.set("PIG.exp.min", 0.25);
        config.set("PIG.exp.max", 0.75);
        config.set("PIG.money.min", 0.05);
        config.set("PIG.money.max", 0.25);
        config.set("PIG.chance", 1.0);
        config.set("POLAR_BEAR.enable", true);
        config.set("POLAR_BEAR.exp.min", 0.25);
        config.set("POLAR_BEAR.exp.max", 0.75);
        config.set("POLAR_BEAR.money.min", 0.05);
        config.set("POLAR_BEAR.money.max", 0.25);
        config.set("POLAR_BEAR.chance", 1.0);
        config.set("RABBIT.enable", true);
        config.set("RABBIT.exp.min", 0.25);
        config.set("RABBIT.exp.max", 0.75);
        config.set("RABBIT.money.min", 0.05);
        config.set("RABBIT.money.max", 0.25);
        config.set("RABBIT.chance", 1.0);
        config.set("SHEEP.enable", true);
        config.set("SHEEP.exp.min", 0.25);
        config.set("SHEEP.exp.max", 0.75);
        config.set("SHEEP.money.min", 0.05);
        config.set("SHEEP.money.max", 0.25);
        config.set("SHEEP.chance", 1.0);
        config.set("SKELETON_HORSE.enable", true);
        config.set("SKELETON_HORSE.exp.min", 0.25);
        config.set("SKELETON_HORSE.exp.max", 0.75);
        config.set("SKELETON_HORSE.money.min", 0.05);
        config.set("SKELETON_HORSE.money.max", 0.25);
        config.set("SKELETON_HORSE.chance", 1.0);
        config.set("SNIFFER.enable", true);
        config.set("SNIFFER.exp.min", 0.25);
        config.set("SNIFFER.exp.max", 0.75);
        config.set("SNIFFER.money.min", 0.05);
        config.set("SNIFFER.money.max", 0.25);
        config.set("SNIFFER.chance", 1.0);
        config.set("STRIDER.enable", true);
        config.set("STRIDER.exp.min", 0.25);
        config.set("STRIDER.exp.max", 0.75);
        config.set("STRIDER.money.min", 0.05);
        config.set("STRIDER.money.max", 0.25);
        config.set("STRIDER.chance", 1.0);
        config.set("TRADER_LLAMA.enable", true);
        config.set("TRADER_LLAMA.exp.min", 0.25);
        config.set("TRADER_LLAMA.exp.max", 0.75);
        config.set("TRADER_LLAMA.money.min", 0.05);
        config.set("TRADER_LLAMA.money.max", 0.25);
        config.set("TRADER_LLAMA.chance", 1.0);
        config.set("TURTLE.enable", true);
        config.set("TURTLE.exp.min", 0.25);
        config.set("TURTLE.exp.max", 0.75);
        config.set("TURTLE.money.min", 0.05);
        config.set("TURTLE.money.max", 0.25);
        config.set("TURTLE.chance", 1.0);
        config.set("VILLAGER.enable", true);
        config.set("VILLAGER.exp.min", 0.25);
        config.set("VILLAGER.exp.max", 0.75);
        config.set("VILLAGER.money.min", 0.05);
        config.set("VILLAGER.money.max", 0.25);
        config.set("VILLAGER.chance", 1.0);
        config.set("WANDERING_TRADER.enable", true);
        config.set("WANDERING_TRADER.exp.min", 0.25);
        config.set("WANDERING_TRADER.exp.max", 0.75);
        config.set("WANDERING_TRADER.money.min", 0.05);
        config.set("WANDERING_TRADER.money.max", 0.25);
        config.set("WANDERING_TRADER.chance", 1.0);
        config.set("WOLF.enable", true);
        config.set("WOLF.exp.min", 0.25);
        config.set("WOLF.exp.max", 0.75);
        config.set("WOLF.money.min", 0.05);
        config.set("WOLF.money.max", 0.25);
        config.set("WOLF.chance", 1.0);
        config.set("ZOMBIE_HORSE.enable", true);
        config.set("ZOMBIE_HORSE.exp.min", 0.25);
        config.set("ZOMBIE_HORSE.exp.max", 0.75);
        config.set("ZOMBIE_HORSE.money.min", 0.05);
        config.set("ZOMBIE_HORSE.money.max", 0.25);
        config.set("ZOMBIE_HORSE.chance", 1.0);
        config.set("ZOMBIE_NAUTILUS.enable", true);
        config.set("ZOMBIE_NAUTILUS.exp.min", 0.25);
        config.set("ZOMBIE_NAUTILUS.exp.max", 0.75);
        config.set("ZOMBIE_NAUTILUS.money.min", 0.05);
        config.set("ZOMBIE_NAUTILUS.money.max", 0.25);
        config.set("ZOMBIE_NAUTILUS.chance", 1.0);
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