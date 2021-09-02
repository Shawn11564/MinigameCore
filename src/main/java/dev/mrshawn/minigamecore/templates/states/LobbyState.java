package dev.mrshawn.minigamecore.templates.states;

import dev.mrshawn.minigamecore.game.state.State;
import dev.mrshawn.minigamecore.game.teams.TeamHandler;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerTeleportEvent;

public class LobbyState extends State {

	private final TeamHandler teamHandler;
	private final Location lobbyLocation;

	public LobbyState(int duration, TeamHandler teamHandler, Location lobbyLocation) {
		super(duration);
		this.teamHandler = teamHandler;
		this.lobbyLocation = lobbyLocation;
	}

	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		teamHandler.addPlayer(event.getPlayer());
		event.getPlayer().teleport(lobbyLocation, PlayerTeleportEvent.TeleportCause.PLUGIN);
	}

	@EventHandler
	public void onQuit(PlayerQuitEvent event) {
		if (teamHandler.hasTeam(event.getPlayer())) {
			teamHandler.getTeam(event.getPlayer()).removePlayer(event.getPlayer());
		}
	}

}
