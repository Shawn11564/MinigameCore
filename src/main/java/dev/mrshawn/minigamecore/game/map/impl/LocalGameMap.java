package dev.mrshawn.minigamecore.game.map.impl;

import dev.mrshawn.minigamecore.game.map.GameMap;
import dev.mrshawn.minigamecore.utils.Files;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldCreator;

import java.io.File;

public class LocalGameMap implements GameMap {

	private final File sourceWorldFolder;
	private File activeWorldFile;

	private World bukkitWorld;

	public LocalGameMap(File worldFolder, String worldName, boolean loadOnInit) {
		this.sourceWorldFolder = new File(
				worldFolder,
				worldName
		);

		if (loadOnInit)
			load();

	}

	@Override
	public boolean load() {
		this.activeWorldFile = new File(
				Bukkit.getWorldContainer().getParentFile(), // Root server folder
				sourceWorldFolder.getName() + "_active_" + System.currentTimeMillis()
		);

		try {
			Files.copy(sourceWorldFolder.getPath(), activeWorldFile.getPath());
		} catch (Exception ex) {
			Bukkit.getLogger().severe("Failed to load GameMap from source folder " + sourceWorldFolder.getName());
			ex.printStackTrace();
			return false;
		}

		this.bukkitWorld = Bukkit.createWorld(
				new WorldCreator(activeWorldFile.getName())
		);

		if (bukkitWorld != null) {
			this.bukkitWorld.setAutoSave(false);
		}

		return isLoaded();
	}

	@Override
	public void unload() {
		if (bukkitWorld != null)
			Bukkit.unloadWorld(bukkitWorld, false);
		if (activeWorldFile != null)
			Files.delete(activeWorldFile);

		bukkitWorld = null;
		activeWorldFile = null;
	}

	@Override
	public boolean restoreFromSource() {
		unload();
		return load();
	}

	@Override
	public boolean isLoaded() {
		return this.bukkitWorld != null;
	}

	@Override
	public World getWorld() {
		return this.bukkitWorld;
	}

	@Override
	public String getName() {
		return this.sourceWorldFolder.getName();
	}
}
