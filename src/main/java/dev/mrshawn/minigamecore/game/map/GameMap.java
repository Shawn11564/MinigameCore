package dev.mrshawn.minigamecore.game.map;

import org.bukkit.World;

public interface GameMap {

	boolean load();

	void unload();

	boolean restoreFromSource();

	boolean isLoaded();

	World getWorld();

	String getName();

}
