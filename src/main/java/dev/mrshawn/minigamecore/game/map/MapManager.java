package dev.mrshawn.minigamecore.game.map;

import dev.mrshawn.minigamecore.MinigameCore;
import dev.mrshawn.minigamecore.game.map.impl.LocalGameMap;
import org.bukkit.Bukkit;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

public final class MapManager {

	private final MinigameCore main;
	private final List<String> mapNames = new ArrayList<>();
	private LocalGameMap map;
	private final File gameMapsFolder;

	@SuppressWarnings("ResultOfMethodCallIgnored")
	public MapManager(MinigameCore main) {
		this.main = main;

		this.gameMapsFolder = new File(
				Bukkit.getWorldContainer().getParentFile(),
				"maps"
		);
		if (!this.gameMapsFolder.exists()) {
			this.gameMapsFolder.mkdir();
		}
		if (this.gameMapsFolder.listFiles() != null) {
			for (File file : Objects.requireNonNull(this.gameMapsFolder.listFiles())) {
				mapNames.add(file.getName());
			}
		}
	}

	public void loadMap(String mapName) {
		map = new LocalGameMap(gameMapsFolder, mapName, true);
	}

	public String pickRandomMap() {
		return mapNames.get(ThreadLocalRandom.current().nextInt(mapNames.size()));
	}

	public void unloadMap() {
		if (this.map != null)
			map.unload();
	}
}
