package dev.mrshawn.minigamecore.game.teams;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerTeleportEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
public class Team {

	private final String name;
	private ChatColor teamColor;
	private final List<UUID> players = new ArrayList<>();

	private Location spawnLocation;

	public Team(String name) {
		this(name, ChatColor.WHITE);
	}

	public Team(String name, ChatColor teamColor) {
		this(name, teamColor, null);
	}

	public Team(String name, ChatColor teamColor, Location spawnLocation) {
		this.name = name;
		this.teamColor = teamColor;
		this.spawnLocation = spawnLocation;
	}

	public Team addPlayer(UUID uuid) {
		players.add(uuid);
		return this;
	}

	public Team removePlayer(Player player) {
		return removePlayer(player.getUniqueId());
	}

	public Team removePlayer(UUID uuid) {
		players.remove(uuid);
		return this;
	}

	public void clearTeamPlayers() {
		players.clear();
	}

	public void teleportPlayerToSpawn(Player player) {
		player.teleport(spawnLocation, PlayerTeleportEvent.TeleportCause.PLUGIN);
	}

	public void teleportPlayersToSpawn() {
		players.stream()
				.map(Bukkit::getPlayer)
				.filter(Objects::nonNull)
				.forEach(player -> player.teleport(spawnLocation, PlayerTeleportEvent.TeleportCause.PLUGIN));
	}

}
