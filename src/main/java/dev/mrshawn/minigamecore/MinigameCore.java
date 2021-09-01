package dev.mrshawn.minigamecore;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.plugin.java.JavaPlugin;

public final class MinigameCore extends JavaPlugin {

	@Getter
	private static MinigameCore instance;

	@Setter
	private static JavaPlugin providingPlugin = null;

	@Override
	public void onEnable() {
		instance = this;

	}

	@Override
	public void onDisable() {
		// Plugin shutdown logic
	}

	/*
	 * @return Returns providing plugin or MinigameCore's instance if providing plugin is null
	 */
	public static JavaPlugin getProvidingPlugin() {
		return providingPlugin != null ? providingPlugin : getInstance();
	}

}
