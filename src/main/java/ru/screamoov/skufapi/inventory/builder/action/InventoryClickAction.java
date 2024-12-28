package ru.screamoov.skufapi.inventory.builder.action;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import ru.screamoov.skufapi.inventory.builder.InventoryBuilder;

import java.util.List;

import static ru.screamoov.skufapi.colorize.Hex.color;

public class InventoryClickAction {
    public InventoryBuilder inventory;
    public int slot;
    public List<String> actions;
    public ClickType clickButton;

    public InventoryClickAction(int slot, List<String> actions, ClickType clickButton) {
        this.slot = slot;
        this.actions = actions;
        this.clickButton = clickButton;
    }

    public void execute(Player whoClicked) {
        actions.forEach(action -> {
            String[] split = action.split("->>");
            String actionName = split[0].toUpperCase();
            try {
                ActionType actionType = ActionType.valueOf(actionName);
                String actionValue = split[1];
                switch (actionType) {
                    case BROADCAST:
                        Bukkit.broadcastMessage(color(actionValue));
                        break;
                    case CONSOLE:
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), color(actionValue));
                        break;
                    case PLAYER:
                        Bukkit.dispatchCommand(whoClicked, color(actionValue));
                        break;
                }
            } catch (Exception exception) {
                inventory.plugin.getLog().error("Issue: " + actionName);
            }
        });
    }
}
