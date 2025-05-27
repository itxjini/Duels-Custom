package com.meteordevelopments.duels.gui;

import com.meteordevelopments.duels.kits.Kit;
import com.meteordevelopments.duels.kits.KitManager;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class KitEditorGUI {

    public static void openEditor(Player player, KitManager kitManager, Kit kit) {
        Inventory gui = Bukkit.createInventory(null, 27, "§aKit Editor: §e" + kit.getName());

        ItemStack pane = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
        ItemMeta paneMeta = pane.getItemMeta();
        paneMeta.setDisplayName(" ");
        pane.setItemMeta(paneMeta);

        for (int i = 0; i < 27; i++) {
            gui.setItem(i, pane);
        }

        gui.setItem(11, kit.getHelmet());
        gui.setItem(12, kit.getChestplate());
        gui.setItem(13, kit.getLeggings());
        gui.setItem(14, kit.getBoots());
        gui.setItem(15, kit.getOffhand());

        gui.setItem(21, createButton(Material.RED_CANDLE, "§cLeave Editor"));
        gui.setItem(22, createButton(Material.YELLOW_CANDLE, "§6Reset Kit"));
        gui.setItem(23, createButton(Material.GREEN_CANDLE, "§aSave Kit"));

        player.openInventory(gui);
    }

    private static ItemStack createButton(Material material, String name) {
        ItemStack button = new ItemStack(material);
        ItemMeta meta = button.getItemMeta();
        meta.setDisplayName(name);
        button.setItemMeta(meta);
        return button;
    }
}
