package de.Initium.Gilden.Commands.Chat;
import java.util.ArrayList;

import de.Initium.Gilden.Commands.gilde_Main;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import de.Initium.Gilden.Main.Main;
import de.Initium.Gilden.Main.ToolBox;

public class gilde_chat_join extends JavaPlugin{
	public static ArrayList<String> watcher = new ArrayList<String>();
	
	public static void execute(Integer nr, String[] args) {
		Player pl = gilde_Main.getPlayer(nr);
		ConfigurationSection gilde = Main.getSaves().getConfigurationSection("gilden." + args[1]);
		if(pl.hasPermission("Gilde.Watcher")) {
			if(gilde == null) {
				pl.sendMessage("§cDie Gilde §6" + args[1] + " §cexistiert nicht!");
			}else {
				if(!watcher.contains(pl.getName())) {
					watcher.add(pl.getName());
					pl.sendMessage("§aWatcher aktiviert!");
					ToolBox.addPlayertoGilde(pl.getUniqueId().toString(), args[1]);
					pl.sendMessage("Du liest jetzt den Gilden-Chat der Gilde §6" + args[1] + " §rmit!");
				}else {
					watcher.remove(pl.getName());
					pl.sendMessage("§cWatcher deaktiviert!");
				}
				
			}
		}else {
			pl.sendMessage("§cDazu hast du keine Berechtigungen!");
		}
		
		
	}

}
