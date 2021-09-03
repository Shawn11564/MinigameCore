package dev.mrshawn.minigamecore.templates.states;

import dev.mrshawn.minigamecore.game.state.State;
import dev.mrshawn.minigamecore.game.teams.TeamHandler;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerTeleportEvent;

public class LobbyUntimedState extends State {

	private final TeamHandler teamHandler;
	private final Location lobbyLocation;
	private int minPlayersNeeded;
	private int playerCount = 0;

	public LobbyUntimedState(TeamHandler teamHandler, Location lobbyLocation, int minPlayersNeeded) {
		super(0);
		this.teamHandler = teamHandler;
		this.lobbyLocation = lobbyLocation;
		this.minPlayersNeeded = minPlayersNeeded;
	}

	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		playerCount++;
		teamHandler.addPlayer(event.getPlayer());
		event.getPlayer().teleport(lobbyLocation, PlayerTeleportEvent.TeleportCause.PLUGIN);
	}

	@EventHandler
	public void onQuit(PlayerQuitEvent event) {
		playerCount--;
		if (teamHandler.hasTeam(event.getPlayer())) {
			teamHandler.getTeam(event.getPlayer()).removePlayer(event.getPlayer());
		}
	}

	@Override
	public boolean isReadyToEnd() {
		return playerCount >= minPlayersNeeded;
	}
}
