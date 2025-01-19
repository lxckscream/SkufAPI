package ru.screamoov.skufapi.configuration.v2;

import org.bukkit.configuration.file.FileConfiguration;

import java.io.File;

public interface IConfiguration {
    FileConfiguration getConfig();
    void saveConfig();
    void loadConfig();
    void reloadConfig();
    File getFile();
}
