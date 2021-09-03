package dev.mrshawn.minigamecore.game.scoreboards;

import dev.mrshawn.minigamecore.game.scoreboards.impl.GlobalPlayerBoard;
import dev.mrshawn.minigamecore.game.scoreboards.impl.MPlayerBoard;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class MPlayerBoards {

	private final Map<Player, MPlayerBoard> playerBoards = new HashMap<>();
	private final Map<String, GlobalPlayerBoard> globalBoards = new HashMap<>();

	public MPlayerBoard createNewPlayerBoard(Player player, String title) {
		return playerBoards.put(player, new MPlayerBoard(player, title));
	}

	public GlobalPlayerBoard createNewGlobalBoard(String id, String title) {
		return globalBoards.put(id, new GlobalPlayerBoard(id, title));
	}

	public MPlayerBoard getBoard(Player player) {
		return playerBoards.get(player);
	}

	public boolean hasBoard(Player player) {
		return playerBoards.containsKey(player);
	}

	public void deletePlayerBoard(Player player) {
		if (hasBoard(player)) {
			getBoard(player).delete();
			playerBoards.remove(player);
		}
	}

	public void deleteGlobalBoard(String id) {
		globalBoards.remove(id).removeAllViewers();
	}

}
