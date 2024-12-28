package ru.screamoov.skufapi;

import org.bukkit.plugin.Plugin;
import ru.screamoov.skufapi.logger.Logger;

public interface ISkufPlugin {
    void disable();
    void enable();
    void shutdown();
    Logger getLog();
    Plugin getBukkitPlugin();
}
