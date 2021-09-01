package dev.mrshawn.minigamecore.game.manager.state;

import dev.mrshawn.minigamecore.MinigameCore;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitTask;

import java.util.HashSet;
import java.util.Set;

public abstract class State implements Listener {

	@Getter
	private boolean started, ended;

	private int duration;
	private long startTime;
	private long endTime;

	protected final Set<Listener> listeners = new HashSet<>();
	protected final Set<BukkitTask> tasks = new HashSet<>();

	public State(int duration) {
		this.duration = duration;
	}

	protected abstract void onStart();

	protected abstract void onUpdate();

	protected abstract void onEnd();

	protected void start() {
		register();
		startTime = System.currentTimeMillis();
		endTime = System.currentTimeMillis() + (1000L * duration);
		started = true;

		onStart();
	}

	protected void update() {

		onUpdate();
	}

	protected void end() {
		unregister();
		listeners.forEach(HandlerList::unregisterAll);
		listeners.clear();
		tasks.forEach(BukkitTask::cancel);
		tasks.clear();
		ended = true;

		onEnd();
	}

	protected void schedule(Runnable runnable, long delay) {
		BukkitTask task = MinigameCore.getProvidingPlugin().getServer().getScheduler().runTaskLater(MinigameCore.getProvidingPlugin(), runnable, delay);
		tasks.add(task);
	}

	protected void scheduleRepeating(Runnable runnable, long delay, long interval) {
		BukkitTask task = MinigameCore.getProvidingPlugin().getServer().getScheduler().runTaskTimer(MinigameCore.getProvidingPlugin(), runnable, delay, interval);
		tasks.add(task);
	}

	protected void register(Listener listener) {
		listeners.add(listener);
		MinigameCore.getProvidingPlugin().getServer().getPluginManager().registerEvents(listener, MinigameCore.getProvidingPlugin());
	}

	protected void register() {
		Bukkit.getServer().getPluginManager().registerEvents(this, MinigameCore.getProvidingPlugin());
	}

	protected void unregister() {
		HandlerList.unregisterAll(this);
	}

	protected void cancelTask(BukkitTask task) {
		task.cancel();
		tasks.remove(task);
	}

	public boolean isReadyToEnd() {
		return ended || getRemainingSeconds() <= 0;
	}

	public int getRemainingSeconds() {
		int timeRemaining = (int) ((endTime - System.currentTimeMillis()) / 1000);
		return Math.max(timeRemaining, 0);
	}

}
