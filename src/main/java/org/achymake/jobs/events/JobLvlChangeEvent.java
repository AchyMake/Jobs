package org.achymake.jobs.events;

import org.achymake.jobs.Jobs;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.checkerframework.checker.nullness.qual.NonNull;

public class JobLvlChangeEvent extends Event {
    private static final HandlerList handlers = new HandlerList();
    private final Player player;
    private final Jobs.jobs job;
    private final int newLevel;
    private final int oldLevel;
    private boolean cancelled;
    public JobLvlChangeEvent(Player player, Jobs.jobs job, int newLevel, int oldLevel) {
        this.player = player;
        this.job = job;
        this.newLevel = newLevel;
        this.oldLevel = oldLevel;
    }
    public void setCancelled(boolean cancel) {
        this.cancelled = cancel;
    }
    public boolean isCancelled() {
        return cancelled;
    }
    public Player getPlayer() {
        return player;
    }
    public Jobs.jobs getJob() {
        return job;
    }
    public int getNewLevel() {
        return newLevel;
    }
    public int getOldLevel() {
        return oldLevel;
    }
    public @NonNull HandlerList getHandlers() {
        return handlers;
    }
    public static HandlerList getHandlerList() {
        return handlers;
    }
}
