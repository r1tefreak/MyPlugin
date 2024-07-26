package ru.ritefreak.myplugin.listener;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;
import org.jetbrains.annotations.NotNull;

public class PlayerPlaceOre extends PlayerEvent {
    private static final HandlerList handlerList = new HandlerList();

    private final Block block;

    public PlayerPlaceOre(@NotNull Player who, Block block) {
        super(who);
        this.block = block;
    }

    public Block getBlock() {
        return block;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return handlerList;
    }

    public static HandlerList getHandlerList() {
        return handlerList;
    }
}
