package com.meteordevelopments.duels.gui;

import com.meteordevelopments.duels.kits.Kit;
import com.meteordevelopments.duels.kits.KitManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class KitEditorListener implements Listener {

    private final KitManager kitManager;

    public KitEditorListener(KitManager kitManager) {
        this.kitManager = kitManager;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (!(event.getWhoClicked() instanceof Player)) return;

        Player player = (Player) event.getWhoClicked();
        Inventory clickedInventory = event.getClickedInventory();

        if (clickedInventory == null || !event.getView().getTitle().startsWith("§aKit Editor: §e")) return;

        event.setCancelled(true);

        int slot = event.getRawSlot();
        String kitName = event.getView().getTitle().substring(17);
        Kit kit = kitManager.getKit(kitName);
        if (kit == null) return;

        if (slot == 21) {
            player.closeInventory();
            player.sendMessage("§cLeft Editor ✔");
        } else if (slot == 22) {
            kit.resetToDefault();
            player.sendMessage("§6Kit layout has been reset to the default.");
            KitEditorGUI.openEditor(player, kitManager, kit);
        } else if (slot == 23) {
            Inventory top = event.getView().getTopInventory();
            kit.setHelmet(top.getItem(11));
            kit.setChestplate(top.getItem(12));
            kit.setLeggings(top.getItem(13));
            kit.setBoots(top.getItem(14));
            kit.setOffhand(top.getItem(15));
            kitManager.saveKit(kit);
            player.closeInventory();
            player.sendMessage("§aKit saved ✔");
        }
    }
}
