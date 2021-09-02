package dev.mrshawn.minigamecore.templates.states.series;

import dev.mrshawn.minigamecore.MinigameCore;
import dev.mrshawn.minigamecore.game.state.State;
import dev.mrshawn.minigamecore.game.state.StateSeries;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

public class AutomaticStateSeries extends StateSeries {

	private long startDelay = 0L;
	private long updatePeriod = 20L;
	private BukkitTask updateTask;

	public AutomaticStateSeries(long startDelay, long updatePeriod) {
		this.startDelay = startDelay;
		this.updatePeriod = updatePeriod;
	}

	public AutomaticStateSeries(long startDelay, long updatePeriod, State state) {
		this.startDelay = startDelay;
		this.updatePeriod = updatePeriod;
		addNext(state);
	}

	@Override
	public void start() {
		super.start();
		startTask();
	}

	public void startTask() {
		updateTask = new BukkitRunnable() {
			@Override
			public void run() {
				update();
			}
		}.runTaskTimer(MinigameCore.getProvidingPlugin(), startDelay, updatePeriod);
	}

	public void cancelTask() {
		if (updateTask != null) {
			updateTask.cancel();
		}
	}

}
