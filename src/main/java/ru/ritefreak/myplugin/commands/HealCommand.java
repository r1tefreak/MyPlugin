package ru.ritefreak.myplugin.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import ru.ritefreak.myplugin.utils.ChatUtils;

public class HealCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        final Player player = (Player) sender;
        if(!(sender instanceof Player)) {
            ChatUtils.sendMessage(player, "&cДанную команду могут писать только игроки!");
            return false;
        }

        if(args.length > 0) {
            ChatUtils.sendMessage(player, "&cНеверное количество аргументов!");
            return false;
        }

        if(!player.hasPermission("myplugin.heal") || !player.isOp()) {
            ChatUtils.sendMessage(player, "&cУ вас нехватает прав для использования этой комманды!");
            return false;
        }

        double playerMaxHealth = player.getMaxHealth();
        player.setHealth(playerMaxHealth);
        ChatUtils.sendMessage(player, "&6Вы были исцелены!");

        return true;
    }
}
