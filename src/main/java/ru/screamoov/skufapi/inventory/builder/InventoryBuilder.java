package ru.screamoov.skufapi.inventory.builder;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import ru.screamoov.skufapi.ISkufPlugin;
import ru.screamoov.skufapi.inventory.builder.action.InventoryClickAction;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static ru.screamoov.skufapi.colorize.Hex.color;

public class InventoryBuilder implements Listener {
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
                ItemStack item = new ItemStack(Material.valueOf(config.getString("inventory.contents." + key + ".material").toUpperCase()));
                ItemMeta meta = item.getItemMeta();

            }
        } catch (Exception e) {
            plugin.getLog().fine("Failed to load inventory config file: " + inventoryConfigFile);
        }
    }

    /*
        Делаем так, что-бы при нажатии по хуйне оно чет делало
     */
    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        clickActions.forEach(action -> {
            if (event.getClick().equals(action.clickButton)) {
                action.execute((Player) event.getWhoClicked());
            }
        });
    }
}
