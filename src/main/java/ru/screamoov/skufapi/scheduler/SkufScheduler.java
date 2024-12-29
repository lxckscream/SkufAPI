package ru.screamoov.skufapi.scheduler;

import ru.screamoov.skufapi.ISkufPlugin;

import java.util.UUID;

public class SkufScheduler {
    public boolean cancelled = false;
    public final Runnable task;
    public final ISkufPlugin plugin;
    public final UUID uuid;
    public final int delay;

    public SkufScheduler(Runnable runnable, ISkufPlugin plugin, int delay) {
        this.task = runnable;
        this.plugin = plugin;
        this.delay = delay;
        uuid = UUID.randomUUID();
    }

    public void start() {
        new Thread(() -> {
            task.run();
            try {
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                plugin.getLog().error("Error on start SkufScheduler: " + this);
            }
        });
    }

    @Override
    public String toString() {
        return plugin.getBukkitPlugin().getDescription().getName() + ":" + uuid.toString();
    }
}
