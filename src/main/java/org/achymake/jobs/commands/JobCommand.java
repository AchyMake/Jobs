package org.achymake.jobs.commands;

import org.achymake.jobs.Jobs;
import org.achymake.jobs.data.Message;
import org.achymake.jobs.data.Userdata;
import org.bukkit.command.*;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class JobCommand implements CommandExecutor, TabCompleter {
    private Jobs getInstance() {
        return Jobs.getInstance();
    }
    private Userdata getUserdata() {
        return getInstance().getUserdata();
    }
    private Message getMessage() {
        return getInstance().getMessage();
    }
    public JobCommand() {
        getInstance().getCommand("job").setExecutor(this);
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player player) {
            if (args.length == 0) {
                player.sendMessage(getMessage().get("commands.job.title"));
                for (var job : Jobs.jobs.values()) {
                    player.sendMessage(getMessage().get("commands.job.listed", job.name(), String.valueOf(getUserdata().getLvl(player, job))));
                }
                return true;
            } else if (args.length == 1) {
                if (args[0].equalsIgnoreCase("breeder")) {
                    var job = Jobs.jobs.breeder;
                    player.sendMessage(getMessage().get("commands.job.lvl", getMessage().toTitleCase(job.name()), String.valueOf(getUserdata().getLvl(player, job)), String.valueOf(getMessage().getFormatted(getUserdata().getExp(player, job)))));
                } else if (args[0].equalsIgnoreCase("enchanter")) {
                    var job = Jobs.jobs.enchanter;
                    player.sendMessage(getMessage().get("commands.job.lvl", getMessage().toTitleCase(job.name()), String.valueOf(getUserdata().getLvl(player, job)), String.valueOf(getMessage().getFormatted(getUserdata().getExp(player, job)))));
                } else if (args[0].equalsIgnoreCase("farmer")) {
                    var job = Jobs.jobs.farmer;
                    player.sendMessage(getMessage().get("commands.job.lvl", getMessage().toTitleCase(job.name()), String.valueOf(getUserdata().getLvl(player, job)), String.valueOf(getMessage().getFormatted(getUserdata().getExp(player, job)))));
                } else if (args[0].equalsIgnoreCase("fisher")) {
                    var job = Jobs.jobs.fisher;
                    player.sendMessage(getMessage().get("commands.job.lvl", getMessage().toTitleCase(job.name()), String.valueOf(getUserdata().getLvl(player, job)), String.valueOf(getMessage().getFormatted(getUserdata().getExp(player, job)))));
                } else if (args[0].equalsIgnoreCase("hunter")) {
                    var job = Jobs.jobs.hunter;
                    player.sendMessage(getMessage().get("commands.job.lvl", getMessage().toTitleCase(job.name()), String.valueOf(getUserdata().getLvl(player, job)), String.valueOf(getMessage().getFormatted(getUserdata().getExp(player, job)))));
                } else if (args[0].equalsIgnoreCase("lumberjack")) {
                    var job = Jobs.jobs.lumberjack;
                    player.sendMessage(getMessage().get("commands.job.lvl", getMessage().toTitleCase(job.name()), String.valueOf(getUserdata().getLvl(player, job)), String.valueOf(getMessage().getFormatted(getUserdata().getExp(player, job)))));
                } else if (args[0].equalsIgnoreCase("miner")) {
                    var job = Jobs.jobs.miner;
                    player.sendMessage(getMessage().get("commands.job.lvl", getMessage().toTitleCase(job.name()), String.valueOf(getUserdata().getLvl(player, job)), String.valueOf(getMessage().getFormatted(getUserdata().getExp(player, job)))));
                } else if (args[0].equalsIgnoreCase("shepherd")) {
                    var job = Jobs.jobs.shepherd;
                    player.sendMessage(getMessage().get("commands.job.lvl", getMessage().toTitleCase(job.name()), String.valueOf(getUserdata().getLvl(player, job)), String.valueOf(getMessage().getFormatted(getUserdata().getExp(player, job)))));
                } else if (args[0].equalsIgnoreCase("top")) {
                    player.sendMessage(getMessage().get("commands.job.top.title"));
                    var list = new ArrayList<>(getInstance().getUserdata().getTopJobs());
                    for (int i = 0; i < list.size(); i++) {
                        player.sendMessage(getMessage().get("commands.job.top.listed", String.valueOf(i + 1), list.get(i).getKey().getName(), String.valueOf(list.get(i).getValue())));
                    }
                    return true;
                }
            }
        } else if (sender instanceof ConsoleCommandSender consoleCommandSender) {
            if (args.length == 0) {
                return true;
            } else if (args.length == 1) {
                if (args[0].equalsIgnoreCase("top")) {
                    consoleCommandSender.sendMessage(getMessage().get("commands.job.top.title"));
                    var list = new ArrayList<>(getInstance().getUserdata().getTopJobs());
                    for (int i = 0; i < list.size(); i++) {
                        consoleCommandSender.sendMessage(getMessage().get("commands.job.top.listed", String.valueOf(i + 1), list.get(i).getKey().getName(), String.valueOf(list.get(i).getValue())));
                    }
                    return true;
                }
            }
        }
        return false;
    }
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        var commands = new ArrayList<String>();
        if (sender instanceof Player) {
            if (args.length == 1) {
                for (var tab : tabs_1.values()) {
                    if (tab.name().startsWith(args[0])) {
                        commands.add(tab.name());
                    }
                }
            }
        }
        return commands;
    }
    private enum tabs_1 {
        breeder,
        enchanter,
        farmer,
        fisher,
        hunter,
        lumberjack,
        miner,
        shepherd,
        top
    }
}