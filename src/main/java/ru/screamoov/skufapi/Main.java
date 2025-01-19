package ru.screamoov.skufapi;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import ru.screamoov.skufapi.logger.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static ru.screamoov.skufapi.colorize.Hex.color;

public final class Main extends JavaPlugin {
    static Main api;
    static Logger log = new Logger(
                "SkufAPI",
                true
        );
    static List<ISkufPlugin> connectedPlugins = new ArrayList<>();

    @Override
    public void onEnable() {
        api = this;

        getCommand("skufapi").setExecutor(((commandSender, command, s, strings) -> {
            if (strings.length == 0) {
                List<String> list = color(Arrays.asList(
                        "&#FB2608&lS&#E82007&lk&#D51905&lu&#C21304&lf&#AE0D03&lA&#9B0601&lP&#880000&lI &8- &fРазработан &#797979l&#727272x&#6B6B6Bc&#656565k&#5E5E5ES&#575757c&#505050r&#4A4A4Ae&#434343a&#3C3C3Cm",
                        "&fМощная библиотека. Требуется ко всем плагинам с началом на 'Skuf'",
                        "&8=-=-=-=-=  &7Список команд:  &8=-=-=-=-=",
                        "&#FB2608/&#F32307s&#EC2107k&#E41E06u&#DC1C06f&#D51905a&#CD1705p&#C51404i &#B60F03p&#AE0D03l&#A70A02u&#9F0802g&#970501i&#900301n&#880000s &8- &fСписок плагинов, подключенных на данный момент к SkufAPI."
                ));
                for (String ln : list) {
                    commandSender.sendMessage(color(ln));
                }
                return true;
            }
            if (strings.length == 1) {
                if (strings[0].equalsIgnoreCase("plugins")) {
                    if (connectedPlugins.isEmpty()) commandSender.sendMessage(color("&#FB2608О&#E82007ш&#D51905и&#C21304б&#AE0D03к&#9B0601а&#880000! &7На данный момент нет плагинов, подключенных к SkufAPI."));
                    for (ISkufPlugin plugin : connectedPlugins) {
                        commandSender.sendMessage(color("#FB2608" + plugin.getBukkitPlugin().getName() + "&7, ver - #880000" + plugin.getBukkitPlugin().getDescription().getVersion()));
                    }
                }
            }
            return true;
        }));
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

    public static void unregisterPlugin(ISkufPlugin plugin) {
        connectedPlugins.remove(plugin);
        getLog().info("Анхукнул плагин " + plugin.getBukkitPlugin().getName());
        plugin.disable();
    }

    public static void registerPlugin(ISkufPlugin plugin) {
        connectedPlugins.add(plugin);
        getLog().info("Хукнул плагин " + plugin.getBukkitPlugin().getName());
        plugin.enable();
    }

    public static List<ISkufPlugin> getConnectedPlugins() {
        return connectedPlugins;
    }
}
