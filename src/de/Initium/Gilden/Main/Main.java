package de.Initium.Gilden.Main;

import de.Initium.Gilden.Commands.gilde_Main;
import de.Initium.Gilden.Commands.Chat.GildenChat_Short;
import de.Initium.Gilden.NPCs.Listener.Bukkit_ChatEvent;
import de.Initium.Gilden.NPCs.Listener.Bukkit_InteractInventory;
import de.Initium.Gilden.NPCs.Listener.Bukkit_JoinLeave;
import de.Initium.Gilden.NPCs.Listener.NPC_RightClick;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public class Main extends JavaPlugin {
	private static Main plugin;
	private static final File savesfile = new File("plugins//Gilde-Plugin//saves.yml");
	private static final YamlConfiguration savefileConfiguration = YamlConfiguration.loadConfiguration(savesfile);
	private static final File configfile = new File("plugins//Gilde-Plugin//config.yml");
	private static final YamlConfiguration configfileConfiguration = YamlConfiguration.loadConfiguration(configfile);

	public static Economy eco = null;

	public void onEnable() {

		plugin = this;
		PluginManager pl = Bukkit.getPluginManager();

		pl.registerEvents(new NPC_RightClick(), this);
		pl.registerEvents(new Bukkit_InteractInventory(), this);
		pl.registerEvents(new Bukkit_ChatEvent(), this);
		pl.registerEvents(new Bukkit_JoinLeave(), this);

		getCommand("gilde").setExecutor(new gilde_Main());
		getCommand("gctest").setExecutor(new GildenChat_Short());





		//Creation of the saves.yml
		if(!savesfile.exists() || !savefileConfiguration.isSet("gilden")) {
			savefileConfiguration.createSection("gilden");

			try {
				savefileConfiguration.save(savesfile);
			} catch (IOException e) {
				getLogger().info("Fehler beim Erstellen der saves.yml: " + e);
			}
		}
		//Creation of the config.yml
		if(!configfile.exists() || !configfileConfiguration.isSet("settings")) {
			configfileConfiguration.createSection("settings");
			configfileConfiguration.set("settings.NPC_NAME", "&bTest");

			try {
				configfileConfiguration.save(configfile);
			} catch (IOException e) {
				getLogger().info("Fehler beim Erstellen der config.yml: " + e);
			}
		}
		if(setupEconomy()) {
			Bukkit.getConsoleSender().sendMessage("Vault wurde initialisiert!");
		}else {
			Bukkit.getConsoleSender().sendMessage("Vault nicht gefunden!");
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
	public static YamlConfiguration getConfiguration() {
		return configfileConfiguration;
	}
	public static void saveConfiguration()
	{
		try {
			configfileConfiguration.save(configfile);
		} catch (IOException e) {
			getPlugin().getLogger().info("Fehler beim Speichern der config.yml: " + e);
		}
	}


	private boolean setupEconomy() {
		if (getServer().getPluginManager().getPlugin("Vault") == null) {
			Bukkit.getConsoleSender().sendMessage("Fehler 0");
			return false;
		}

		RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
		if (rsp == null) {
			Bukkit.getConsoleSender().sendMessage("Fehler 1");
			return false;
		}
		eco = rsp.getProvider();

		return eco != null;
	}

}
