package dev.mrshawn.minigamecore.game.teams;

import lombok.Getter;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.*;

public final class TeamHandler {

	@Getter
	private final Map<String, Team> teams = new HashMap<>();

	public void createTeam(String name, ChatColor color) {
		teams.put(name, new Team(name, color));
	}

	public Team getTeam(String name) {
		return teams.get(name);
	}

	public void setTeam(UUID uuid, Team team) {
		team.addPlayer(uuid);
	}

	public void setTeam(Player player, Team team) {
		team.addPlayer(player.getUniqueId());
	}

	public void setTeam(UUID uuid, String teamName) {
		teams.get(teamName).addPlayer(uuid);
	}

	public void setTeam(Player player, String teamName) {
		teams.get(teamName).addPlayer(player.getUniqueId());
	}

	public void addPlayer(UUID uuid) {
		getLowestPlayers().addPlayer(uuid);
	}

	public void addPlayer(Player player) {
		getLowestPlayers().addPlayer(player.getUniqueId());
	}

	public Team getLowestPlayers() {
		return teams.values()
				.stream()
				.min(Comparator.comparing(team -> team.getPlayers().size()))
				.orElseThrow(NoSuchElementException::new);
	}

}
