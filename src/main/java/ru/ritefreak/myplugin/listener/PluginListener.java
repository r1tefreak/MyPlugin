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
        PlayerPlaceOre playerPlaceOre = new PlayerPlaceOre(event.getPlayer(), placedBlock);

        switch (placedBlock.getType()) {
            case COAL_ORE:
                Bukkit.getPluginManager().callEvent(playerPlaceOre);
                break;

            case IRON_ORE:
                Bukkit.getPluginManager().callEvent(playerPlaceOre);
                break;

            case GOLD_ORE:
                Bukkit.getPluginManager().callEvent(playerPlaceOre);
                break;

            case DIAMOND_ORE:
                Bukkit.getPluginManager().callEvent(playerPlaceOre);
                break;

            case EMERALD_ORE:
                Bukkit.getPluginManager().callEvent(playerPlaceOre);
                break;

            case ANCIENT_DEBRIS:
                Bukkit.getPluginManager().callEvent(playerPlaceOre);
                break;

            default:
                break;
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
