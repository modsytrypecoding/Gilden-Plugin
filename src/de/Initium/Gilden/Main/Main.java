package de.Initium.Gilden.Main;

import de.Initium.Gilden.Commands.Gc;
import de.Initium.Gilden.Commands.gilde_Main;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public class Main extends JavaPlugin {
	private static Main plugin;
	private static final File savesfile = new File("plugins//Gilde-Plugin//saves.yml");
	private static final YamlConfiguration savefileConfiguration = YamlConfiguration.loadConfiguration(savesfile);
	
	
	public void onEnable() {
		plugin = this;
		PluginManager pl = Bukkit.getPluginManager();

		getCommand("gilde").setExecutor(new gilde_Main());
		getCommand("gctest").setExecutor(new Gc());


		//Creation of the saves.yml
		if(!savesfile.exists() || !savefileConfiguration.isSet("gilden")) {
			savefileConfiguration.createSection("gilden");
			//savefileConfiguration.set("gilden.block_nether_teleportation", true);

			try {
				savefileConfiguration.save(savesfile);
			} catch (IOException e) {
				getLogger().info("Fehler beim Erstellen der saves.yml: " + e);
			}
		}
	}
	
	public static Main getPlugin() {
		return plugin;
	}
	public static YamlConfiguration getSaves() {
		return savefileConfiguration;
	}
	public static void saveSaves()
	{
		try {
			savefileConfiguration.save(savesfile);
		} catch (IOException e) {
			getPlugin().getLogger().info("Fehler beim Speichern der saves.yml: " + e);
		}
	}
}
