package org.achymake.jobs.providers;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.achymake.jobs.Jobs;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

public class PlaceholderProvider extends PlaceholderExpansion {
    @Override
    public String getIdentifier() {
        return Jobs.getInstance().name().toLowerCase();
    }
    @Override
    public String getAuthor() {
        return "AchyMake";
    }
    @Override
    public String getVersion() {
        return Jobs.getInstance().version();
    }
    @Override
    public boolean canRegister() {
        return true;
    }
    @Override
    public boolean register() {
        return super.register();
    }
    @Override
    public boolean persist() {
        return true;
    }
    @Override
    public @Nullable String onPlaceholderRequest(Player player, @NotNull String params) {
        if (player == null) {
            return "";
        } else {
            var instance = Jobs.getInstance();
            var userdata = instance.getUserdata();
            switch (params) {
                case "breeder" -> {
                    return String.valueOf(userdata.getLvl(player, Jobs.jobs.breeder));
                }
                case "enchanter" -> {
                    return String.valueOf(userdata.getLvl(player, Jobs.jobs.enchanter));
                }
                case "farmer" -> {
                    return String.valueOf(userdata.getLvl(player, Jobs.jobs.farmer));
                }
                case "fisher" -> {
                    return String.valueOf(userdata.getLvl(player, Jobs.jobs.fisher));
                }
                case "hunter" -> {
                    return String.valueOf(userdata.getLvl(player, Jobs.jobs.hunter));
                }
                case "lumberjack" -> {
                    return String.valueOf(userdata.getLvl(player, Jobs.jobs.lumberjack));
                }
                case "miner" -> {
                    return String.valueOf(userdata.getLvl(player, Jobs.jobs.miner));
                }
                case "shepherd" -> {
                    return String.valueOf(userdata.getLvl(player, Jobs.jobs.shepherd));
                }
                case "total" -> {
                    return String.valueOf(userdata.getTotal(player));
                }
            }
        }
        return super.onPlaceholderRequest(player, params);
    }
}