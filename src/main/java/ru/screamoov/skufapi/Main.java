package ru.screamoov.skufapi;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import ru.screamoov.skufapi.logger.Logger;

import java.util.ArrayList;
import java.util.List;

public final class Main extends JavaPlugin {
    static Main api;
    static Logger log;
    static List<ISkufPlugin> connectedPlugins = new ArrayList<>();

    @Override
    public void onEnable() {
        api = this;
        log = new Logger(
                "SkufAPI",
                getConfig().getBoolean("log.show-time")
        );
    }

    @Override
    public void onDisable() {
        for (ISkufPlugin plugin : connectedPlugins) {
            plugin.disable();
        }
    }

    public static Main getApi() {
        return api;
    }

    public static Logger getLog() {
        return log;
    }

    public static void registerPlugin(ISkufPlugin plugin) {
        connectedPlugins.add(plugin);
        plugin.enable();
    }

    public static List<ISkufPlugin> getConnectedPlugins() {
        return connectedPlugins;
    }
}
