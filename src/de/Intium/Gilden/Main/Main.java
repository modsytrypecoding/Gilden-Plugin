package de.Intium.Gilden.Main;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
	private static Main plugin;
	
	
	public void onEnable() {
		plugin = this;
		
		
		PluginManager pl = Bukkit.getPluginManager();
	}
	
	public static Main getPlugin() {
		return plugin;
	}

}

