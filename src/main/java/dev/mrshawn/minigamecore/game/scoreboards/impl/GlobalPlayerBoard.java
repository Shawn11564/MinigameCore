package dev.mrshawn.minigamecore.game.scoreboards.impl;

import dev.mrshawn.minigamecore.game.scoreboards.PBoard;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class GlobalPlayerBoard extends PBoard {

	private final List<Player> viewers = new ArrayList<>();

	public GlobalPlayerBoard(String sbID, String name) {
		super(sbID, name);
	}

	public void addViewer(Player player) {
		this.viewers.add(player);
		player.setScoreboard(this.scoreboard);
	}

	public void removeViewer(Player player) {
		this.viewers.remove(player);
	}

	public void removeAllViewers() {
		viewers.clear();
	}

	public boolean isViewing(Player player) {
		return this.viewers.contains(player);
	}

}
