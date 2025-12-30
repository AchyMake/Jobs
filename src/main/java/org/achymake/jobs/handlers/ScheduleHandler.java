package org.achymake.jobs.handlers;

import org.achymake.jobs.Jobs;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.scheduler.BukkitTask;

public class ScheduleHandler {
    private Jobs getInstance() {
        return Jobs.getInstance();
    }
    private BukkitScheduler getScheduler() {
        return getInstance().getBukkitScheduler();
    }
    public BukkitTask runLater(Runnable runnable, long timer) {
        return getScheduler().runTaskLater(getInstance(), runnable, timer);
    }
    public BukkitTask runAsynchronously(Runnable runnable) {
        return getScheduler().runTaskAsynchronously(getInstance(), runnable);
    }
    public boolean isQueued(int taskID) {
        return getScheduler().isQueued(taskID);
    }
    public void cancel(int taskID) {
        if (!isQueued(taskID))return;
        getScheduler().cancelTask(taskID);
    }
    public void disable() {
        getScheduler().cancelTasks(getInstance());
    }
}