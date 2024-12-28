package ru.screamoov.skufapi.configuration;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import ru.screamoov.skufapi.ISkufPlugin;
import ru.screamoov.skufapi.Main;

import java.io.File;

public class Configuration {
    public FileConfiguration config;
    public File configFile;
    public ISkufPlugin plugin;

    public void load() {
        try {
            config = YamlConfiguration.loadConfiguration(configFile);
        } catch (Exception e) {
            plugin.getLog().error("Issue on configuration load: " + e.getMessage());
        }
    }
}
