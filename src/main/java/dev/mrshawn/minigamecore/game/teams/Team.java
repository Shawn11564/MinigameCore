package dev.mrshawn.minigamecore.game.teams;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.ChatColor;
import org.bukkit.Location;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class Team {

	private final String name;
	private ChatColor teamColor;
	private final List<UUID> players = new ArrayList<>();

	private Location spawnLocation;

	public Team(String name, ChatColor teamColor) {
		this.name = name;
		this.teamColor = teamColor;
	}

	public void addPlayer(UUID uuid) {
		players.add(uuid);
	}

	public void removePlayer(UUID uuid) {
		players.remove(uuid);
	}

}
