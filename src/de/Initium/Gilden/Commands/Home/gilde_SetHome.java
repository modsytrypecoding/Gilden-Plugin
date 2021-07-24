package de.Initium.Gilden.Commands.Home;

import java.text.DecimalFormat;
import java.util.List;

import de.Initium.Gilden.Commands.gilde_Main;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import de.Initium.Gilden.Main.Main;
import de.Initium.Gilden.Main.ToolBox;



public class gilde_SetHome extends JavaPlugin{
	
	public static void execute(Integer nr) {
		
		Player player = gilde_Main.getPlayer(nr);
		String gilde = ToolBox.getGildeNameOfPlayer(player);
		if(ToolBox.getallPlayers().contains(player.getUniqueId().toString())) {
			//check Rank
			if(ToolBox.getGildeRankByPlayer(gilde, player.getUniqueId().toString()).equalsIgnoreCase("Leiter") || ToolBox.getGildeRankByPlayer(gilde, player.getUniqueId().toString()).equalsIgnoreCase("Fositzender")) {

				//set the Location in the Config
				FileConfiguration saves = Main.getSaves();
				saves.set("gilden." + gilde + ".Information." + "HomeLocation." +"Home.World", player.getWorld().getName());
				saves.set("gilden." + gilde + ".Information." + "HomeLocation." +"Home.X", (player.getLocation().getX()));
				saves.set("gilden." + gilde + ".Information." + "HomeLocation." +"Home.Y", (player.getLocation().getY()));
				saves.set("gilden." + gilde + ".Information." + "HomeLocation." +"Home.Z", (player.getLocation().getZ()));
				saves.set("gilden." + gilde + ".Information." + "HomeLocation." +"Home.Yaw", (player.getLocation().getYaw()));
				saves.set("gilden." + gilde + ".Information." + "HomeLocation." +"Home.Pitch", (player.getLocation().getPitch()));
				Main.getSaves().set("gilden." + gilde + ".Information." + "hasSetHome", true);
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
