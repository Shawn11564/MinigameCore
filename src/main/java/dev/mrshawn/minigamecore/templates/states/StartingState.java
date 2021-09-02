package dev.mrshawn.minigamecore.templates.states;

import dev.mrshawn.minigamecore.game.state.State;
import dev.mrshawn.minigamecore.game.teams.Team;
import dev.mrshawn.minigamecore.game.teams.TeamHandler;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class StartingState extends State {

	private final TeamHandler teamHandler;

	public StartingState(int duration, TeamHandler teamHandler) {
		super(duration);
		this.teamHandler = teamHandler;
	}

	@Override
	protected void onStart() {
		teamHandler.getTeams().values().forEach(Team::teleportPlayersToSpawn);
	}

	@Override
	protected void onUpdate() {

	}

	@Override
	protected void onEnd() {

	}

	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		teamHandler
				.addPlayer(event.getPlayer())
				.teleportPlayerToSpawn(event.getPlayer());
	}

	@EventHandler
	public void onQuit(PlayerQuitEvent event) {
		if (teamHandler.hasTeam(event.getPlayer())) {
			teamHandler.getTeam(event.getPlayer()).removePlayer(event.getPlayer());
		}
	}

}
