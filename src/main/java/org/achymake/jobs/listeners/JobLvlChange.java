package org.achymake.jobs.listeners;

import org.achymake.jobs.Jobs;
import org.achymake.jobs.data.Message;
import org.achymake.jobs.data.Userdata;
import org.achymake.jobs.events.JobLvlChangeEvent;
import org.achymake.jobs.handlers.WorldHandler;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;

public class JobLvlChange implements Listener {
    private Jobs getInstance() {
        return Jobs.getInstance();
    }
    private FileConfiguration getConfig() {
        return getInstance().getConfig();
    }
    private Message getMessage() {
        return getInstance().getMessage();
    }
    private Userdata getUserdata() {
        return getInstance().getUserdata();
    }
    private WorldHandler getWorldHandler() {
        return getInstance().getWorldHandler();
    }
    private PluginManager getPluginManager() {
        return getInstance().getPluginManager();
    }
    public JobLvlChange() {
        getPluginManager().registerEvents(this, getInstance());
    }
    @EventHandler(priority = EventPriority.MONITOR)
    public void onJobLvlChange(JobLvlChangeEvent event) {
        if (event.isCancelled())return;
        var player = event.getPlayer();
        var job = event.getJob();
        var lvlResult = getUserdata().addLvl(player, job, 1);
        player.sendMessage(getMessage().get("event.lvl.message", getMessage().toTitleCase(job.name()), String.valueOf(lvlResult)));
        if (getConfig().getBoolean("levels.particle.enable")) {
            var particleType = getConfig().getString("levels.particle.type");
            if (particleType == null)return;
            var count = getConfig().getInt("levels.particle.count");
            var offsetX = getConfig().getDouble("levels.particle.offsetX");
            var offsetY = getConfig().getDouble("levels.particle.offsetY");
            var offsetZ = getConfig().getDouble("levels.particle.offsetZ");
            var location = player.getLocation().add(0.0, 0.7, 0.0);
            getWorldHandler().spawnParticle(location, particleType, count, offsetX, offsetY, offsetZ);
        }
        if (getConfig().getBoolean("levels.sound.enable")) {
            var soundType = getConfig().getString("levels.sound.type");
            if (soundType == null)return;
            var volume = getConfig().getDouble("levels.sound.volume");
            var pitch = getConfig().getDouble("levels.sound.pitch");
            getWorldHandler().playSound(player, soundType, volume, pitch);
        }
        if (getUserdata().removeExp(player, job, lvlResult) >= lvlResult) {
            getPluginManager().callEvent(new JobLvlChangeEvent(player, job, lvlResult + 1, lvlResult));
        }
    }
}