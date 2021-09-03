package dev.mrshawn.minigamecore.game.teams;

import de.leonhard.storage.Json;
import lombok.Getter;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.*;

public class TeamHandler {

	@Getter
	protected final Json configFile;
	private final Map<String, Team> teams = new HashMap<>();

	public TeamHandler(String filePath) {
		configFile = new Json("teams", filePath);
	}

	public Team createTeam(String name, ChatColor color) {
		return teams.put(name, new Team(name, color));
	}

	public Team createTeam(String name, ChatColor color, Location spawnLocation) {
		return teams.put(name, new Team(name, color, spawnLocation));
	}

	public Team getTeam(String name) {
		return teams.get(name);
	}

	public Team getTeam(Player player) {
		return getTeam(player.getUniqueId());
	}

	public Team getTeam(UUID uuid) {
		for (Team team : teams.values()) {
			if (team.getPlayers().contains(uuid)) {
				return team;
			}
		}
		return null;
	}

	public boolean hasTeam(Player player) {
		return hasTeam(player.getUniqueId());
	}

	public boolean hasTeam(UUID uuid) {
		return getTeam(uuid) != null;
	}

	public Team setTeam(UUID uuid, Team team) {
		return team.addPlayer(uuid);
	}

	public Team setTeam(Player player, Team team) {
		return team.addPlayer(player.getUniqueId());
	}

	public Team setTeam(UUID uuid, String teamName) {
		return teams.get(teamName).addPlayer(uuid);
	}

	public Team setTeam(Player player, String teamName) {
		return teams.get(teamName).addPlayer(player.getUniqueId());
	}

	public Team addPlayer(UUID uuid) {
		return getLowestPlayers().addPlayer(uuid);
	}

	public Team addPlayer(Player player) {
		return getLowestPlayers().addPlayer(player.getUniqueId());
	}

	public List<Team> getTeams() {
		return new ArrayList<>(this.teams.values());
	}

	public Team clearTeamPlayers(Team team) {
		team.clearTeamPlayers();
		return team;
	}

	public void clearTeamPlayers() {
		teams.values().forEach(Team::clearTeamPlayers);
	}

	public Team getLowestPlayers() {
		return teams.values()
				.stream()
				.min(Comparator.comparing(team -> team.getPlayers().size()))
				.orElseThrow(NoSuchElementException::new);
	}

}
