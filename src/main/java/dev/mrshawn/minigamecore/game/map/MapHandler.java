package dev.mrshawn.minigamecore.game.map;

import dev.mrshawn.minigamecore.game.map.impl.LocalGameMap;
import lombok.Getter;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

public final class MapHandler {

	private final List<String> mapNames = new ArrayList<>();
	@Getter
	private LocalGameMap map;
	private final File gameMapsFolder;

	@SuppressWarnings("ResultOfMethodCallIgnored")
	public MapHandler(String mapsFolderPath) {
		this.gameMapsFolder = new File(mapsFolderPath);
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
