package ru.ritefreak.myplugin.utils;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class ChatUtils {

    public static String colorize(String str) {
        return ChatColor.translateAlternateColorCodes('&', str);
    }

    public static void sendActionBar(Player player, String message) {
        player.sendActionBar(colorize(message));
    }

    public static void sendMessage(Player player, String message) {
        player.sendMessage(colorize(message));
    }
}
