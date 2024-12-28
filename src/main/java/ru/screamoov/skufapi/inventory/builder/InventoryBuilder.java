package ru.screamoov.skufapi.inventory.builder;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import ru.screamoov.skufapi.ISkufPlugin;
import ru.screamoov.skufapi.inventory.builder.action.InventoryClickAction;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static ru.screamoov.skufapi.colorize.Hex.color;

public class InventoryBuilder {
    public FileConfiguration config;
    public File inventoryConfigFile;
    public Inventory inventory;
    public List<InventoryClickAction> clickActions = new ArrayList<>();
    public final ISkufPlugin plugin;

    public InventoryBuilder(File inventoryConfigFile, ISkufPlugin plugin) {
        this.inventoryConfigFile = inventoryConfigFile;
        this.plugin = plugin;
        try {
            this.config = YamlConfiguration.loadConfiguration(inventoryConfigFile);
            inventory = Bukkit.createInventory(
                    null,
                    config.getInt("inventory.size"),
                    color(config.getString("inventory.name"))
            );

            int[] contentsAmount = new int[]{0};
            config.getConfigurationSection("inventory.contents").getKeys(false).forEach(key -> contentsAmount[0]++);

            ItemStack[] contents = new ItemStack[contentsAmount[0]];
            for (String key : config.getConfigurationSection("inventory.contents").getKeys(false)) {

            }
        } catch (Exception e) {
            plugin.getLog().fine("Failed to load inventory config file: " + inventoryConfigFile);
        }
    }
}
