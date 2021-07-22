package de.Initium.Gilden.Commands;

import java.util.List;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import de.Initium.Gilden.Main.Main;
import de.Initium.Gilden.Main.ToolBox;

public class gilde_SetHome extends JavaPlugin {
	public static void execute(Integer nr) {
		Player player = gilde_Main.getPlayer(nr);
		String gilde = ToolBox.getGildeNameOfPlayer(player);
		if(ToolBox.getallPlayers().contains(player.getUniqueId().toString())) {
			if(ToolBox.getGildeRankByPlayer(gilde, player.getUniqueId().toString()).equalsIgnoreCase("Leiter") || ToolBox.getGildeRankByPlayer(gilde, player.getUniqueId().toString()).equalsIgnoreCase("Fositzender")) {
				FileConfiguration saves = Main.getPlugin().getConfig();
				List<String> newlist = Main.getSaves().getStringList("gilden." + gilde + ".Infos." + "Home.");
				newlist.add( player.getWorld().getName());
				newlist.add( player.getWorld().getName());
				newlist.add( player.getWorld().getName());
				newlist.add( player.getWorld().getName());
				newlist.add( player.getWorld().getName());
				saves.set("gilden." + gilde + ".Information." + " Location. " + "Home.World", player.getWorld().getName());
				saves.set("gilden." + gilde + ".Information." + " Location. " + "Home.X", player.getLocation().getX());
				saves.set("gilden." + gilde + ".Information." + " Location. " + "Home.Y", player.getLocation().getY());
				saves.set("gilden." + gilde + ".Information." + " Location. " + "Home.Z", player.getLocation().getZ());
				saves.set("gilden." + gilde + ".Information." + " Location. " + "Home.Yaw", player.getLocation().getYaw());
				saves.set("gilden." + gilde + ".Information." + " Location. " + "Home.Pitch", player.getLocation().getPitch());
				Main.saveSaves();
				player.sendMessage("§aDu hast dein Gilden-Home erfolgreich gesetzt!");
			}else {
				player.sendMessage("§cDu kannst kein Gilden-Home setzten!");
			}
		}else {
			player.sendMessage("§cDu kannst diesen Befehl nicht nutzen da du in keiner Gilde bist!");
		}
	}
}
