package dev.mrshawn.minigamecore.game.scoreboards;

import dev.mrshawn.minigamecore.utils.Chat;
import org.bukkit.Bukkit;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

import java.util.HashMap;
import java.util.Map;

public abstract class PBoard {

	protected Scoreboard scoreboard;

	protected String name;

	protected Objective objective;

	protected Map<Integer, String> lines = new HashMap<>();

	public PBoard(String sbID, String name) {
		this.name = name;

		this.scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();

		this.objective = this.scoreboard.registerNewObjective(sbID, "dummy", colorize(name));
		this.objective.setDisplaySlot(DisplaySlot.SIDEBAR);
	}

	public void set(String text, Integer slot) {
		final String line = colorize(text);

		String oldLine = colorize(this.lines.get(slot));
		if (line.equals(oldLine))
			return;

		this.lines.entrySet()
				.removeIf(entry -> entry.getValue().equals(line));

		this.lines.put(slot, colorize(text));

		this.objective.getScore(colorize(text)).setScore(slot);
	}

	public String get(Integer slot) {
		return this.lines.get(slot);
	}

	public void remove(Integer slot) {
		String line = this.lines.get(slot);
		if (line == null)
			return;

		this.scoreboard.resetScores(line);
		this.lines.remove(slot);
	}

	public void delete() {
		this.objective.unregister();
	}

	protected String colorize(String message) {
		return Chat.colorize(message);
	}

}
