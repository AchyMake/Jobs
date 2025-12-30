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

public class Enchanter {
    private Jobs getInstance() {
        return Jobs.getInstance();
    }
    private Userdata getUserdata() {
        return getInstance().getUserdata();
    }
    private final File file = new File(getInstance().getDataFolder(), "job/enchanter.yml");
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
    public Set<Map.Entry<OfflinePlayer, Integer>> getTopEnchanter() {
        var accounts = new HashMap<OfflinePlayer, Integer>();
        for (var offlinePlayer : getUserdata().getOfflinePlayers()) {
            accounts.put(offlinePlayer, getUserdata().getLvl(offlinePlayer, Jobs.jobs.enchanter));
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
        config.set("WOODEN_SWORD.enable", true);
        config.set("WOODEN_SWORD.exp.min", 0.05);
        config.set("WOODEN_SWORD.exp.max", 0.75);
        config.set("WOODEN_SWORD.money.min", 0.05);
        config.set("WOODEN_SWORD.money.max", 0.25);
        config.set("WOODEN_SWORD.chance", 1.0);
        config.set("WOODEN_PICKAXE.enable", true);
        config.set("WOODEN_PICKAXE.exp.min", 0.05);
        config.set("WOODEN_PICKAXE.exp.max", 0.75);
        config.set("WOODEN_PICKAXE.money.min", 0.05);
        config.set("WOODEN_PICKAXE.money.max", 0.25);
        config.set("WOODEN_PICKAXE.chance", 1.0);
        config.set("WOODEN_AXE.enable", true);
        config.set("WOODEN_AXE.exp.min", 0.05);
        config.set("WOODEN_AXE.exp.max", 0.75);
        config.set("WOODEN_AXE.money.min", 0.05);
        config.set("WOODEN_AXE.money.max", 0.25);
        config.set("WOODEN_AXE.chance", 1.0);
        config.set("WOODEN_SHOVEL.enable", true);
        config.set("WOODEN_SHOVEL.exp.min", 0.05);
        config.set("WOODEN_SHOVEL.exp.max", 0.75);
        config.set("WOODEN_SHOVEL.money.min", 0.05);
        config.set("WOODEN_SHOVEL.money.max", 0.25);
        config.set("WOODEN_SHOVEL.chance", 1.0);
        config.set("WOODEN_HOE.enable", true);
        config.set("WOODEN_HOE.exp.min", 0.05);
        config.set("WOODEN_HOE.exp.max", 0.75);
        config.set("WOODEN_HOE.money.min", 0.05);
        config.set("WOODEN_HOE.money.max", 0.25);
        config.set("WOODEN_HOE.chance", 1.0);
        config.set("WOODEN_SPEAR.enable", true);
        config.set("WOODEN_SPEAR.exp.min", 0.05);
        config.set("WOODEN_SPEAR.exp.max", 0.75);
        config.set("WOODEN_SPEAR.money.min", 0.05);
        config.set("WOODEN_SPEAR.money.max", 0.25);
        config.set("WOODEN_SPEAR.chance", 1.0);

        config.set("LEATHER_HELMET.enable", true);
        config.set("LEATHER_HELMET.exp.min", 0.05);
        config.set("LEATHER_HELMET.exp.max", 0.75);
        config.set("LEATHER_HELMET.money.min", 0.05);
        config.set("LEATHER_HELMET.money.max", 0.25);
        config.set("LEATHER_HELMET.chance", 1.0);
        config.set("LEATHER_CHESTPLATE.enable", true);
        config.set("LEATHER_CHESTPLATE.exp.min", 0.05);
        config.set("LEATHER_CHESTPLATE.exp.max", 0.75);
        config.set("LEATHER_CHESTPLATE.money.min", 0.05);
        config.set("LEATHER_CHESTPLATE.money.max", 0.25);
        config.set("LEATHER_CHESTPLATE.chance", 1.0);
        config.set("LEATHER_LEGGINGS.enable", true);
        config.set("LEATHER_LEGGINGS.exp.min", 0.05);
        config.set("LEATHER_LEGGINGS.exp.max", 0.75);
        config.set("LEATHER_LEGGINGS.money.min", 0.05);
        config.set("LEATHER_LEGGINGS.money.max", 0.25);
        config.set("LEATHER_LEGGINGS.chance", 1.0);
        config.set("LEATHER_BOOTS.enable", true);
        config.set("LEATHER_BOOTS.exp.min", 0.05);
        config.set("LEATHER_BOOTS.exp.max", 0.75);
        config.set("LEATHER_BOOTS.money.min", 0.05);
        config.set("LEATHER_BOOTS.money.max", 0.25);
        config.set("LEATHER_BOOTS.chance", 1.0);

        config.set("STONE_SWORD.enable", true);
        config.set("STONE_SWORD.exp.min", 0.05);
        config.set("STONE_SWORD.exp.max", 0.75);
        config.set("STONE_SWORD.money.min", 0.05);
        config.set("STONE_SWORD.money.max", 0.25);
        config.set("STONE_SWORD.chance", 1.0);
        config.set("STONE_PICKAXE.enable", true);
        config.set("STONE_PICKAXE.exp.min", 0.05);
        config.set("STONE_PICKAXE.exp.max", 0.75);
        config.set("STONE_PICKAXE.money.min", 0.05);
        config.set("STONE_PICKAXE.money.max", 0.25);
        config.set("STONE_PICKAXE.chance", 1.0);
        config.set("STONE_AXE.enable", true);
        config.set("STONE_AXE.exp.min", 0.05);
        config.set("STONE_AXE.exp.max", 0.75);
        config.set("STONE_AXE.money.min", 0.05);
        config.set("STONE_AXE.money.max", 0.25);
        config.set("STONE_AXE.chance", 1.0);
        config.set("STONE_SHOVEL.enable", true);
        config.set("STONE_SHOVEL.exp.min", 0.05);
        config.set("STONE_SHOVEL.exp.max", 0.75);
        config.set("STONE_SHOVEL.money.min", 0.05);
        config.set("STONE_SHOVEL.money.max", 0.25);
        config.set("STONE_SHOVEL.chance", 1.0);
        config.set("STONE_HOE.enable", true);
        config.set("STONE_HOE.exp.min", 0.05);
        config.set("STONE_HOE.exp.max", 0.75);
        config.set("STONE_HOE.money.min", 0.05);
        config.set("STONE_HOE.money.max", 0.25);
        config.set("STONE_HOE.chance", 1.0);
        config.set("STONE_SPEAR.enable", true);
        config.set("STONE_SPEAR.exp.min", 0.05);
        config.set("STONE_SPEAR.exp.max", 0.75);
        config.set("STONE_SPEAR.money.min", 0.05);
        config.set("STONE_SPEAR.money.max", 0.25);
        config.set("STONE_SPEAR.chance", 1.0);

        config.set("CHAINMAIL_HELMET.enable", true);
        config.set("CHAINMAIL_HELMET.exp.min", 0.05);
        config.set("CHAINMAIL_HELMET.exp.max", 0.75);
        config.set("CHAINMAIL_HELMET.money.min", 0.05);
        config.set("CHAINMAIL_HELMET.money.max", 0.25);
        config.set("CHAINMAIL_HELMET.chance", 1.0);
        config.set("CHAINMAIL_CHESTPLATE.enable", true);
        config.set("CHAINMAIL_CHESTPLATE.exp.min", 0.05);
        config.set("CHAINMAIL_CHESTPLATE.exp.max", 0.75);
        config.set("CHAINMAIL_CHESTPLATE.money.min", 0.05);
        config.set("CHAINMAIL_CHESTPLATE.money.max", 0.25);
        config.set("CHAINMAIL_CHESTPLATE.chance", 1.0);
        config.set("CHAINMAIL_LEGGINGS.enable", true);
        config.set("CHAINMAIL_LEGGINGS.exp.min", 0.05);
        config.set("CHAINMAIL_LEGGINGS.exp.max", 0.75);
        config.set("CHAINMAIL_LEGGINGS.money.min", 0.05);
        config.set("CHAINMAIL_LEGGINGS.money.max", 0.25);
        config.set("CHAINMAIL_LEGGINGS.chance", 1.0);
        config.set("CHAINMAIL_BOOTS.enable", true);
        config.set("CHAINMAIL_BOOTS.exp.min", 0.05);
        config.set("CHAINMAIL_BOOTS.exp.max", 0.75);
        config.set("CHAINMAIL_BOOTS.money.min", 0.05);
        config.set("CHAINMAIL_BOOTS.money.max", 0.25);
        config.set("CHAINMAIL_BOOTS.chance", 1.0);

        config.set("COPPER_SWORD.enable", true);
        config.set("COPPER_SWORD.exp.min", 0.05);
        config.set("COPPER_SWORD.exp.max", 0.75);
        config.set("COPPER_SWORD.money.min", 0.05);
        config.set("COPPER_SWORD.money.max", 0.25);
        config.set("COPPER_SWORD.chance", 1.0);
        config.set("COPPER_PICKAXE.enable", true);
        config.set("COPPER_PICKAXE.exp.min", 0.05);
        config.set("COPPER_PICKAXE.exp.max", 0.75);
        config.set("COPPER_PICKAXE.money.min", 0.05);
        config.set("COPPER_PICKAXE.money.max", 0.25);
        config.set("COPPER_PICKAXE.chance", 1.0);
        config.set("COPPER_AXE.enable", true);
        config.set("COPPER_AXE.exp.min", 0.05);
        config.set("COPPER_AXE.exp.max", 0.75);
        config.set("COPPER_AXE.money.min", 0.05);
        config.set("COPPER_AXE.money.max", 0.25);
        config.set("COPPER_AXE.chance", 1.0);
        config.set("COPPER_SHOVEL.enable", true);
        config.set("COPPER_SHOVEL.exp.min", 0.05);
        config.set("COPPER_SHOVEL.exp.max", 0.75);
        config.set("COPPER_SHOVEL.money.min", 0.05);
        config.set("COPPER_SHOVEL.money.max", 0.25);
        config.set("COPPER_SHOVEL.chance", 1.0);
        config.set("COPPER_HOE.enable", true);
        config.set("COPPER_HOE.exp.min", 0.05);
        config.set("COPPER_HOE.exp.max", 0.75);
        config.set("COPPER_HOE.money.min", 0.05);
        config.set("COPPER_HOE.money.max", 0.25);
        config.set("COPPER_HOE.chance", 1.0);
        config.set("COPPER_SPEAR.enable", true);
        config.set("COPPER_SPEAR.exp.min", 0.05);
        config.set("COPPER_SPEAR.exp.max", 0.75);
        config.set("COPPER_SPEAR.money.min", 0.05);
        config.set("COPPER_SPEAR.money.max", 0.25);
        config.set("COPPER_SPEAR.chance", 1.0);

        config.set("COPPER_HELMET.enable", true);
        config.set("COPPER_HELMET.exp.min", 0.05);
        config.set("COPPER_HELMET.exp.max", 0.75);
        config.set("COPPER_HELMET.money.min", 0.05);
        config.set("COPPER_HELMET.money.max", 0.25);
        config.set("COPPER_HELMET.chance", 1.0);
        config.set("COPPER_CHESTPLATE.enable", true);
        config.set("COPPER_CHESTPLATE.exp.min", 0.05);
        config.set("COPPER_CHESTPLATE.exp.max", 0.75);
        config.set("COPPER_CHESTPLATE.money.min", 0.05);
        config.set("COPPER_CHESTPLATE.money.max", 0.25);
        config.set("COPPER_CHESTPLATE.chance", 1.0);
        config.set("COPPER_LEGGINGS.enable", true);
        config.set("COPPER_LEGGINGS.exp.min", 0.05);
        config.set("COPPER_LEGGINGS.exp.max", 0.75);
        config.set("COPPER_LEGGINGS.money.min", 0.05);
        config.set("COPPER_LEGGINGS.money.max", 0.25);
        config.set("COPPER_LEGGINGS.chance", 1.0);
        config.set("COPPER_BOOTS.enable", true);
        config.set("COPPER_BOOTS.exp.min", 0.05);
        config.set("COPPER_BOOTS.exp.max", 0.75);
        config.set("COPPER_BOOTS.money.min", 0.05);
        config.set("COPPER_BOOTS.money.max", 0.25);
        config.set("COPPER_BOOTS.chance", 1.0);

        config.set("IRON_SWORD.enable", true);
        config.set("IRON_SWORD.exp.min", 0.05);
        config.set("IRON_SWORD.exp.max", 0.75);
        config.set("IRON_SWORD.money.min", 0.05);
        config.set("IRON_SWORD.money.max", 0.25);
        config.set("IRON_SWORD.chance", 1.0);
        config.set("IRON_PICKAXE.enable", true);
        config.set("IRON_PICKAXE.exp.min", 0.05);
        config.set("IRON_PICKAXE.exp.max", 0.75);
        config.set("IRON_PICKAXE.money.min", 0.05);
        config.set("IRON_PICKAXE.money.max", 0.25);
        config.set("IRON_PICKAXE.chance", 1.0);
        config.set("IRON_AXE.enable", true);
        config.set("IRON_AXE.exp.min", 0.05);
        config.set("IRON_AXE.exp.max", 0.75);
        config.set("IRON_AXE.money.min", 0.05);
        config.set("IRON_AXE.money.max", 0.25);
        config.set("IRON_AXE.chance", 1.0);
        config.set("IRON_SHOVEL.enable", true);
        config.set("IRON_SHOVEL.exp.min", 0.05);
        config.set("IRON_SHOVEL.exp.max", 0.75);
        config.set("IRON_SHOVEL.money.min", 0.05);
        config.set("IRON_SHOVEL.money.max", 0.25);
        config.set("IRON_SHOVEL.chance", 1.0);
        config.set("IRON_HOE.enable", true);
        config.set("IRON_HOE.exp.min", 0.05);
        config.set("IRON_HOE.exp.max", 0.75);
        config.set("IRON_HOE.money.min", 0.05);
        config.set("IRON_HOE.money.max", 0.25);
        config.set("IRON_HOE.chance", 1.0);
        config.set("IRON_SPEAR.enable", true);
        config.set("IRON_SPEAR.exp.min", 0.05);
        config.set("IRON_SPEAR.exp.max", 0.75);
        config.set("IRON_SPEAR.money.min", 0.05);
        config.set("IRON_SPEAR.money.max", 0.25);
        config.set("IRON_SPEAR.chance", 1.0);

        config.set("IRON_HELMET.enable", true);
        config.set("IRON_HELMET.exp.min", 0.05);
        config.set("IRON_HELMET.exp.max", 0.75);
        config.set("IRON_HELMET.money.min", 0.05);
        config.set("IRON_HELMET.money.max", 0.25);
        config.set("IRON_HELMET.chance", 1.0);
        config.set("IRON_CHESTPLATE.enable", true);
        config.set("IRON_CHESTPLATE.exp.min", 0.05);
        config.set("IRON_CHESTPLATE.exp.max", 0.75);
        config.set("IRON_CHESTPLATE.money.min", 0.05);
        config.set("IRON_CHESTPLATE.money.max", 0.25);
        config.set("IRON_CHESTPLATE.chance", 1.0);
        config.set("IRON_LEGGINGS.enable", true);
        config.set("IRON_LEGGINGS.exp.min", 0.05);
        config.set("IRON_LEGGINGS.exp.max", 0.75);
        config.set("IRON_LEGGINGS.money.min", 0.05);
        config.set("IRON_LEGGINGS.money.max", 0.25);
        config.set("IRON_LEGGINGS.chance", 1.0);
        config.set("IRON_BOOTS.enable", true);
        config.set("IRON_BOOTS.exp.min", 0.05);
        config.set("IRON_BOOTS.exp.max", 0.75);
        config.set("IRON_BOOTS.money.min", 0.05);
        config.set("IRON_BOOTS.money.max", 0.25);
        config.set("IRON_BOOTS.chance", 1.0);

        config.set("GOLDEN_SWORD.enable", true);
        config.set("GOLDEN_SWORD.exp.min", 0.05);
        config.set("GOLDEN_SWORD.exp.max", 0.75);
        config.set("GOLDEN_SWORD.money.min", 0.05);
        config.set("GOLDEN_SWORD.money.max", 0.25);
        config.set("GOLDEN_SWORD.chance", 1.0);
        config.set("GOLDEN_PICKAXE.enable", true);
        config.set("GOLDEN_PICKAXE.exp.min", 0.05);
        config.set("GOLDEN_PICKAXE.exp.max", 0.75);
        config.set("GOLDEN_PICKAXE.money.min", 0.05);
        config.set("GOLDEN_PICKAXE.money.max", 0.25);
        config.set("GOLDEN_PICKAXE.chance", 1.0);
        config.set("GOLDEN_AXE.enable", true);
        config.set("GOLDEN_AXE.exp.min", 0.05);
        config.set("GOLDEN_AXE.exp.max", 0.75);
        config.set("GOLDEN_AXE.money.min", 0.05);
        config.set("GOLDEN_AXE.money.max", 0.25);
        config.set("GOLDEN_AXE.chance", 1.0);
        config.set("GOLDEN_SHOVEL.enable", true);
        config.set("GOLDEN_SHOVEL.exp.min", 0.05);
        config.set("GOLDEN_SHOVEL.exp.max", 0.75);
        config.set("GOLDEN_SHOVEL.money.min", 0.05);
        config.set("GOLDEN_SHOVEL.money.max", 0.25);
        config.set("GOLDEN_SHOVEL.chance", 1.0);
        config.set("GOLDEN_HOE.enable", true);
        config.set("GOLDEN_HOE.exp.min", 0.05);
        config.set("GOLDEN_HOE.exp.max", 0.75);
        config.set("GOLDEN_HOE.money.min", 0.05);
        config.set("GOLDEN_HOE.money.max", 0.25);
        config.set("GOLDEN_HOE.chance", 1.0);
        config.set("GOLDEN_SPEAR.enable", true);
        config.set("GOLDEN_SPEAR.exp.min", 0.05);
        config.set("GOLDEN_SPEAR.exp.max", 0.75);
        config.set("GOLDEN_SPEAR.money.min", 0.05);
        config.set("GOLDEN_SPEAR.money.max", 0.25);
        config.set("GOLDEN_SPEAR.chance", 1.0);

        config.set("GOLDEN_HELMET.enable", true);
        config.set("GOLDEN_HELMET.exp.min", 0.05);
        config.set("GOLDEN_HELMET.exp.max", 0.75);
        config.set("GOLDEN_HELMET.money.min", 0.05);
        config.set("GOLDEN_HELMET.money.max", 0.25);
        config.set("GOLDEN_HELMET.chance", 1.0);
        config.set("GOLDEN_CHESTPLATE.enable", true);
        config.set("GOLDEN_CHESTPLATE.exp.min", 0.05);
        config.set("GOLDEN_CHESTPLATE.exp.max", 0.75);
        config.set("GOLDEN_CHESTPLATE.money.min", 0.05);
        config.set("GOLDEN_CHESTPLATE.money.max", 0.25);
        config.set("GOLDEN_CHESTPLATE.chance", 1.0);
        config.set("GOLDEN_LEGGINGS.enable", true);
        config.set("GOLDEN_LEGGINGS.exp.min", 0.05);
        config.set("GOLDEN_LEGGINGS.exp.max", 0.75);
        config.set("GOLDEN_LEGGINGS.money.min", 0.05);
        config.set("GOLDEN_LEGGINGS.money.max", 0.25);
        config.set("GOLDEN_LEGGINGS.chance", 1.0);
        config.set("GOLDEN_BOOTS.enable", true);
        config.set("GOLDEN_BOOTS.exp.min", 0.05);
        config.set("GOLDEN_BOOTS.exp.max", 0.75);
        config.set("GOLDEN_BOOTS.money.min", 0.05);
        config.set("GOLDEN_BOOTS.money.max", 0.25);
        config.set("GOLDEN_BOOTS.chance", 1.0);

        config.set("DIAMOND_SWORD.enable", true);
        config.set("DIAMOND_SWORD.exp.min", 0.05);
        config.set("DIAMOND_SWORD.exp.max", 0.75);
        config.set("DIAMOND_SWORD.money.min", 0.05);
        config.set("DIAMOND_SWORD.money.max", 0.25);
        config.set("DIAMOND_SWORD.chance", 1.0);
        config.set("DIAMOND_PICKAXE.enable", true);
        config.set("DIAMOND_PICKAXE.exp.min", 0.05);
        config.set("DIAMOND_PICKAXE.exp.max", 0.75);
        config.set("DIAMOND_PICKAXE.money.min", 0.05);
        config.set("DIAMOND_PICKAXE.money.max", 0.25);
        config.set("DIAMOND_PICKAXE.chance", 1.0);
        config.set("DIAMOND_AXE.enable", true);
        config.set("DIAMOND_AXE.exp.min", 0.05);
        config.set("DIAMOND_AXE.exp.max", 0.75);
        config.set("DIAMOND_AXE.money.min", 0.05);
        config.set("DIAMOND_AXE.money.max", 0.25);
        config.set("DIAMOND_AXE.chance", 1.0);
        config.set("DIAMOND_SHOVEL.enable", true);
        config.set("DIAMOND_SHOVEL.exp.min", 0.05);
        config.set("DIAMOND_SHOVEL.exp.max", 0.75);
        config.set("DIAMOND_SHOVEL.money.min", 0.05);
        config.set("DIAMOND_SHOVEL.money.max", 0.25);
        config.set("DIAMOND_SHOVEL.chance", 1.0);
        config.set("DIAMOND_HOE.enable", true);
        config.set("DIAMOND_HOE.exp.min", 0.05);
        config.set("DIAMOND_HOE.exp.max", 0.75);
        config.set("DIAMOND_HOE.money.min", 0.05);
        config.set("DIAMOND_HOE.money.max", 0.25);
        config.set("DIAMOND_HOE.chance", 1.0);
        config.set("DIAMOND_SPEAR.enable", true);
        config.set("DIAMOND_SPEAR.exp.min", 0.05);
        config.set("DIAMOND_SPEAR.exp.max", 0.75);
        config.set("DIAMOND_SPEAR.money.min", 0.05);
        config.set("DIAMOND_SPEAR.money.max", 0.25);
        config.set("DIAMOND_SPEAR.chance", 1.0);

        config.set("DIAMOND_HELMET.enable", true);
        config.set("DIAMOND_HELMET.exp.min", 0.05);
        config.set("DIAMOND_HELMET.exp.max", 0.75);
        config.set("DIAMOND_HELMET.money.min", 0.05);
        config.set("DIAMOND_HELMET.money.max", 0.25);
        config.set("DIAMOND_HELMET.chance", 1.0);
        config.set("DIAMOND_CHESTPLATE.enable", true);
        config.set("DIAMOND_CHESTPLATE.exp.min", 0.05);
        config.set("DIAMOND_CHESTPLATE.exp.max", 0.75);
        config.set("DIAMOND_CHESTPLATE.money.min", 0.05);
        config.set("DIAMOND_CHESTPLATE.money.max", 0.25);
        config.set("DIAMOND_CHESTPLATE.chance", 1.0);
        config.set("DIAMOND_LEGGINGS.enable", true);
        config.set("DIAMOND_LEGGINGS.exp.min", 0.05);
        config.set("DIAMOND_LEGGINGS.exp.max", 0.75);
        config.set("DIAMOND_LEGGINGS.money.min", 0.05);
        config.set("DIAMOND_LEGGINGS.money.max", 0.25);
        config.set("DIAMOND_LEGGINGS.chance", 1.0);
        config.set("DIAMOND_BOOTS.enable", true);
        config.set("DIAMOND_BOOTS.exp.min", 0.05);
        config.set("DIAMOND_BOOTS.exp.max", 0.75);
        config.set("DIAMOND_BOOTS.money.min", 0.05);
        config.set("DIAMOND_BOOTS.money.max", 0.25);
        config.set("DIAMOND_BOOTS.chance", 1.0);

        config.set("NETHERITE_SWORD.enable", true);
        config.set("NETHERITE_SWORD.exp.min", 0.05);
        config.set("NETHERITE_SWORD.exp.max", 0.75);
        config.set("NETHERITE_SWORD.money.min", 0.05);
        config.set("NETHERITE_SWORD.money.max", 0.25);
        config.set("NETHERITE_SWORD.chance", 1.0);
        config.set("NETHERITE_PICKAXE.enable", true);
        config.set("NETHERITE_PICKAXE.exp.min", 0.05);
        config.set("NETHERITE_PICKAXE.exp.max", 0.75);
        config.set("NETHERITE_PICKAXE.money.min", 0.05);
        config.set("NETHERITE_PICKAXE.money.max", 0.25);
        config.set("NETHERITE_PICKAXE.chance", 1.0);
        config.set("NETHERITE_AXE.enable", true);
        config.set("NETHERITE_AXE.exp.min", 0.05);
        config.set("NETHERITE_AXE.exp.max", 0.75);
        config.set("NETHERITE_AXE.money.min", 0.05);
        config.set("NETHERITE_AXE.money.max", 0.25);
        config.set("NETHERITE_AXE.chance", 1.0);
        config.set("NETHERITE_SHOVEL.enable", true);
        config.set("NETHERITE_SHOVEL.exp.min", 0.05);
        config.set("NETHERITE_SHOVEL.exp.max", 0.75);
        config.set("NETHERITE_SHOVEL.money.min", 0.05);
        config.set("NETHERITE_SHOVEL.money.max", 0.25);
        config.set("NETHERITE_SHOVEL.chance", 1.0);
        config.set("NETHERITE_HOE.enable", true);
        config.set("NETHERITE_HOE.exp.min", 0.05);
        config.set("NETHERITE_HOE.exp.max", 0.75);
        config.set("NETHERITE_HOE.money.min", 0.05);
        config.set("NETHERITE_HOE.money.max", 0.25);
        config.set("NETHERITE_HOE.chance", 1.0);
        config.set("NETHERITE_SPEAR.enable", true);
        config.set("NETHERITE_SPEAR.exp.min", 0.05);
        config.set("NETHERITE_SPEAR.exp.max", 0.75);
        config.set("NETHERITE_SPEAR.money.min", 0.05);
        config.set("NETHERITE_SPEAR.money.max", 0.25);
        config.set("NETHERITE_SPEAR.chance", 1.0);

        config.set("NETHERITE_HELMET.enable", true);
        config.set("NETHERITE_HELMET.exp.min", 0.05);
        config.set("NETHERITE_HELMET.exp.max", 0.75);
        config.set("NETHERITE_HELMET.money.min", 0.05);
        config.set("NETHERITE_HELMET.money.max", 0.25);
        config.set("NETHERITE_HELMET.chance", 1.0);
        config.set("NETHERITE_CHESTPLATE.enable", true);
        config.set("NETHERITE_CHESTPLATE.exp.min", 0.05);
        config.set("NETHERITE_CHESTPLATE.exp.max", 0.75);
        config.set("NETHERITE_CHESTPLATE.money.min", 0.05);
        config.set("NETHERITE_CHESTPLATE.money.max", 0.25);
        config.set("NETHERITE_CHESTPLATE.chance", 1.0);
        config.set("NETHERITE_LEGGINGS.enable", true);
        config.set("NETHERITE_LEGGINGS.exp.min", 0.05);
        config.set("NETHERITE_LEGGINGS.exp.max", 0.75);
        config.set("NETHERITE_LEGGINGS.money.min", 0.05);
        config.set("NETHERITE_LEGGINGS.money.max", 0.25);
        config.set("NETHERITE_LEGGINGS.chance", 1.0);
        config.set("NETHERITE_BOOTS.enable", true);
        config.set("NETHERITE_BOOTS.exp.min", 0.05);
        config.set("NETHERITE_BOOTS.exp.max", 0.75);
        config.set("NETHERITE_BOOTS.money.min", 0.05);
        config.set("NETHERITE_BOOTS.money.max", 0.25);
        config.set("NETHERITE_BOOTS.chance", 1.0);

        config.set("TURTLE_HELMET.enable", true);
        config.set("TURTLE_HELMET.exp.min", 0.05);
        config.set("TURTLE_HELMET.exp.max", 0.75);
        config.set("TURTLE_HELMET.money.min", 0.05);
        config.set("TURTLE_HELMET.money.max", 0.25);
        config.set("TURTLE_HELMET.chance", 1.0);

        config.set("TRIDENT.enable", true);
        config.set("TRIDENT.exp.min", 0.05);
        config.set("TRIDENT.exp.max", 0.75);
        config.set("TRIDENT.money.min", 0.05);
        config.set("TRIDENT.money.max", 0.25);
        config.set("TRIDENT.chance", 1.0);

        config.set("MACE.enable", true);
        config.set("MACE.exp.min", 0.05);
        config.set("MACE.exp.max", 0.75);
        config.set("MACE.money.min", 0.05);
        config.set("MACE.money.max", 0.25);
        config.set("MACE.chance", 1.0);

        config.set("BOW.enable", true);
        config.set("BOW.exp.min", 0.05);
        config.set("BOW.exp.max", 0.75);
        config.set("BOW.money.min", 0.05);
        config.set("BOW.money.max", 0.25);
        config.set("BOW.chance", 1.0);

        config.set("CROSSBOW.enable", true);
        config.set("CROSSBOW.exp.min", 0.05);
        config.set("CROSSBOW.exp.max", 0.75);
        config.set("CROSSBOW.money.min", 0.05);
        config.set("CROSSBOW.money.max", 0.25);
        config.set("CROSSBOW.chance", 1.0);

        config.set("BOOK.enable", true);
        config.set("BOOK.exp.min", 0.05);
        config.set("BOOK.exp.max", 0.75);
        config.set("BOOK.money.min", 0.05);
        config.set("BOOK.money.max", 0.25);
        config.set("BOOK.chance", 1.0);

        config.set("FISHING_ROD.enable", true);
        config.set("FISHING_ROD.exp.min", 0.05);
        config.set("FISHING_ROD.exp.max", 0.75);
        config.set("FISHING_ROD.money.min", 0.05);
        config.set("FISHING_ROD.money.max", 0.25);
        config.set("FISHING_ROD.chance", 1.0);
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
