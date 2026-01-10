package org.achymake.jobs.data;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.achymake.jobs.Jobs;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.MessageFormat;

public class Message {
    private Jobs getInstance() {
        return Jobs.getInstance();
    }
    private final File file = new File(getInstance().getDataFolder(), "message.yml");
    private FileConfiguration config = YamlConfiguration.loadConfiguration(file);
    public File getFile() {
        return file;
    }
    public FileConfiguration getConfig() {
        return config;
    }
    public String get(String path) {
        if (config.isString(path)) {
            return addColor(config.getString(path));
        } else return path + ": is missing a value";
    }
    public String get(String path, String... format) {
        if (config.isString(path)) {
            return addColor(MessageFormat.format(config.getString(path), format));
        } else return path + ": is missing a value";
    }
    public String getFormatted(double value) {
        return new DecimalFormat("#,##0.00").format(value);
    }
    private boolean setup() {
        config.options().copyDefaults(true);
        config.set("commands.job.title", "&6Jobs:");
        config.set("commands.job.listed", "- &6{0}&f {1}");
        config.set("commands.job.lvl", "&6Your&f {0}&6 lvl is&f {1}&6 with&f {2}&6 exp");
        config.set("commands.job.top.title", "&6Top 10 Employees:");
        config.set("commands.job.top.listed", "&6{0}&f {1}&a {2}");
        config.set("event.money.message", "&6Money earned&f:&a {0}");
        config.set("event.lvl.message", "{0}&6 reached lvl&f {1}");
        try {
            config.save(file);
            return true;
        } catch (IOException e) {
            getInstance().sendWarning(e.getMessage());
            return false;
        }
    }
    public boolean reload() {
        if (file.exists()) {
            config = YamlConfiguration.loadConfiguration(file);
            return true;
        } else return setup();
    }
    public void sendActionBar(Player player, String message) {
        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(addColor(message)));
    }
    public String addColor(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }
    public String toTitleCase(String string) {
        if (string.contains(" ")) {
            var builder = getBuilder();
            for (var strings : string.split(" ")) {
                builder.append(strings.toUpperCase().charAt(0)).append(strings.substring(1).toLowerCase());
                builder.append(" ");
            }
            return builder.toString().strip();
        } else if (string.contains("_")) {
            var builder = getBuilder();
            for (var strings : string.split("_")) {
                builder.append(strings.toUpperCase().charAt(0)).append(strings.substring(1).toLowerCase());
                builder.append(" ");
            }
            return builder.toString().strip();
        } else return string.toUpperCase().charAt(0) + string.substring(1).toLowerCase();
    }
    public StringBuilder getBuilder() {
        return new StringBuilder();
    }
    public String format(double amount) {
        return new DecimalFormat(getInstance().getConfig().getString("format")).format(amount);
    }
}