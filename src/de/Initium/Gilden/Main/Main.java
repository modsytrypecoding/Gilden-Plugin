package de.Initium.Gilden.Main;

import de.Initium.Gilden.Commands.gilde_Main;
import de.Initium.Gilden.Commands.Chat.GildenChat_Short;
import de.Initium.Gilden.Main.MessageControlling.DefaultMessages;
import de.Initium.Gilden.NPCs.Listener.Bukkit_ChatEvent;
import de.Initium.Gilden.NPCs.Listener.Bukkit_InteractInventory;
import de.Initium.Gilden.NPCs.Listener.Bukkit_JoinLeave;
import de.Initium.Gilden.NPCs.Listener.NPC_RightClick;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public class Main extends JavaPlugin {
	private static Main plugin;
	private static final File savesfile = new File("plugins//Gilde-Plugin//saves.yml");
	private static YamlConfiguration savefileConfiguration = YamlConfiguration.loadConfiguration(savesfile);
	private static final File configfile = new File("plugins//Gilde-Plugin//config.yml");
	private static YamlConfiguration configfileConfiguration = YamlConfiguration.loadConfiguration(configfile);
	private static final File messagefile = new File("plugins//Gilde-Plugin//messages.yml");
	private static YamlConfiguration messagefileConfiguration = YamlConfiguration.loadConfiguration(messagefile);

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
			saveSaves();
		}
		//Creation of the config.yml
		if(!configfile.exists() || !configfileConfiguration.isSet("settings")) {
			configfileConfiguration.createSection("settings");
			configfileConfiguration.set("settings.NPC_NAME", "&bTest");
			saveConfiguration();
		}

		//Creation of the messages.yml
		if(!messagefile.exists() || !messagefileConfiguration.isSet("settings")) {
			DefaultMessages.set(messagefileConfiguration);
			saveMessages();
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
			savefileConfiguration = YamlConfiguration.loadConfiguration(savesfile);
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
			configfileConfiguration = YamlConfiguration.loadConfiguration(configfile);
		} catch (IOException e) {
			getPlugin().getLogger().info("Fehler beim Speichern der config.yml: " + e);
		}
	}

	public static YamlConfiguration getMessages() {
		return messagefileConfiguration;
	}
	public static void saveMessages() {
		try {
			messagefileConfiguration.save(messagefile);
			messagefileConfiguration = YamlConfiguration.loadConfiguration(messagefile);
		} catch (IOException e) {
			getPlugin().getLogger().info("Fehler beim Speichern der messages.yml: " + e);
		}
	}
}
