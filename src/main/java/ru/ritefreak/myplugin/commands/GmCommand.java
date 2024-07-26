package ru.ritefreak.myplugin.commands;

import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.ritefreak.myplugin.utils.ChatUtils;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class GmCommand implements TabExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        final Player player = (Player) sender;

        if(!(sender instanceof Player)) {
            ChatUtils.sendMessage(player, "&cДанную команду могут писать только игроки!");
            return false;
        }

        if(args.length > 1) {
            ChatUtils.sendMessage(player, "&cНеверное количество аргументов!");
            return false;
        }

        if(!player.hasPermission("myplugin.gm") || !player.isOp()) {
            ChatUtils.sendMessage(player, "&cУ вас нехватает прав для использования этой комманды!");
            return false;
        }

        try {
            int mode = Integer.parseInt(args[0]);

            switch (mode) {
                case 0:
                    player.setGameMode(GameMode.SURVIVAL);
                    ChatUtils.sendMessage
                            (player, "&6Установлен режим игры &cвыживание &6для игрока &4" + player.getName());
                    break;
                case 1:
                    player.setGameMode(GameMode.CREATIVE);
                    ChatUtils.sendMessage
                            (player, "&6Установлен режим игры &cтворческий &6для игрока &4" + player.getName());
                    break;
                case 2:
                    player.setGameMode(GameMode.ADVENTURE);
                    ChatUtils.sendMessage
                            (player, "&6Установлен режим игры &cприключенческий &6для игрока &4" + player.getName());
                    break;
                case 3:
                    player.setGameMode(GameMode.SPECTATOR);
                    ChatUtils.sendMessage
                            (player, "&6Установлен режим игры &cнаблюдатель &6для игрока &4" + player.getName());
                    break;
                default:
                    ChatUtils.sendMessage
                            (player, "&4Ошибка: &cНеизвестный режим игры");
                    break;
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if(args.length == 1) {
            final List<String> modes = Arrays.asList("0", "1", "2", "3");
            return modes;
        }
        return Collections.emptyList();
    }
}
