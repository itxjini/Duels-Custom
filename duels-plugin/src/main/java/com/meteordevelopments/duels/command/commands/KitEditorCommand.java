package com.meteordevelopments.duels.command.commands;

import com.meteordevelopments.duels.gui.KitEditorGUI;
import com.meteordevelopments.duels.kits.Kit;
import com.meteordevelopments.duels.kits.KitManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class KitsEditCommand implements CommandExecutor {

    private final KitManager kitManager;

    public KitsEditCommand(KitManager kitManager) {
        this.kitManager = kitManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Only players can use this command.");
            return true;
        }

        Player executor = (Player) sender;

        if (!executor.hasPermission("warfury.kit.editor")) {
            executor.sendMessage("\u00a78unknown command '/kit'\u00a7f");
            return true;
        }

        if (args.length == 0) {
            // No arguments: open GUI for sender
            Kit kit = kitManager.getDefaultKit();
            if (kit == null) {
                executor.sendMessage("\u00a7cNo default kit found.");
                return true;
            }
            KitEditorGUI.openEditor(executor, kitManager, kit);
            executor.sendMessage("\u00a7aOpening Kit Editor for kit \u00a7e" + kit.getName() + "\u00a7a.");
            return true;
        }

        if (args.length >= 1) {
            String kitName = args[0];
            Player target = executor;

            if (args.length >= 2) {
                target = Bukkit.getPlayer(args[1]);
                if (target == null) {
                    executor.sendMessage("\u00a7cPlayer not found.");
                    return true;
                }
            }

            Kit kit = kitManager.getKit(kitName);
            if (kit == null) {
                executor.sendMessage("\u00a7cNo kit found with the name " + kitName + ".");
                return true;
            }

            KitEditorGUI.openEditor(target, kitManager, kit);
            executor.sendMessage("\u00a7aOpening Kit Editor for kit \u00a7e" + kitName + "\u00a7a for \u00a7e" + target.getName() + "\u00a7a.");
            return true;
        }

        return false;
    }
}
