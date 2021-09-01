package dev.mrshawn.minigamecore.game.manager;

import dev.mrshawn.minigamecore.game.map.settings.impl.LocalMapSettings;
import dev.mrshawn.minigamecore.game.teams.TeamManager;
import lombok.Getter;

public final class GameManager {

	@Getter
	private LocalMapSettings mapSettings;
	@Getter
	private TeamManager teamManager;

	public GameManager() {

	}

}
