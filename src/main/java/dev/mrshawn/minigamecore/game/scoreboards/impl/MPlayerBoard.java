package dev.mrshawn.minigamecore.game.scoreboards.impl;

import dev.mrshawn.minigamecore.game.scoreboards.PBoard;
import org.apache.commons.lang.StringUtils;
import org.bukkit.entity.Player;

public class MPlayerBoard extends PBoard {

	public MPlayerBoard(Player player, String name) {
		super(StringUtils.left(player.getName(), 14), name);
		player.setScoreboard(this.scoreboard);
	}

}
