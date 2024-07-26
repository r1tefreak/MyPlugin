package ru.ritefreak.myplugin.listener;

import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import ru.ritefreak.myplugin.utils.ChatUtils;

public class PluginListener implements Listener {
    private final Economy economy;

    public PluginListener(Economy economy) {
        this.economy = economy;
    }

    @EventHandler
    private void on(BlockPlaceEvent event) {
        final Block placedBlock = event.getBlockPlaced();
        if (placedBlock.getType().name().contains("_ORE")) {
            PlayerPlaceOre playerPlaceOre = new PlayerPlaceOre(event.getPlayer(), placedBlock);
            Bukkit.getPluginManager().callEvent(playerPlaceOre);
        }
    }

    @EventHandler
    private void on(PlayerPlaceOre event) {
        final Player player = event.getPlayer();
        final Block block = event.getBlock();
        double randomSum = Math.random() + 1;
        String salary = String.format("%.2f", randomSum);
        ChatUtils.sendActionBar
                (player,
                        "&b⚡ &fВы поставили руду " + block.getType().name() + ", и заработали &a" + salary + "&a$");
        economy.depositPlayer(player, randomSum);
    }
}
