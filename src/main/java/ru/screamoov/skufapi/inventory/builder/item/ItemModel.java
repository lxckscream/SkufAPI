package ru.screamoov.skufapi.inventory.builder.item;

import org.bukkit.enchantments.Enchantment;

import java.util.List;

public class ItemModel {
    public String displayName;
    public int slot;
    public List<String> lore;
    public boolean unbreakable;
    public List<Enchantment> enchantments;

    public ItemModel(String displayName, int slot, List<String> lore, boolean unbreakable, List<Enchantment> enchantments) {
        this.displayName = displayName;
        this.slot = slot;
        this.lore = lore;
        this.unbreakable = unbreakable;
        this.enchantments = enchantments;
    }
}
