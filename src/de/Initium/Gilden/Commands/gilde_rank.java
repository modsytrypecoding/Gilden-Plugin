package de.Initium.Gilden.Commands;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import de.Initium.Gilden.Main.Main;
import de.Initium.Gilden.Main.ToolBox;

public class gilde_rank extends JavaPlugin{
	
	public static void execute(Integer nr, String[] args) {
		Player p = gilde_Main.getPlayer(nr);
		if(args.length == 3) {
			if(args[2].equalsIgnoreCase("Gildenleiter") || args[2].equalsIgnoreCase("Forsitzender") || args[2].equalsIgnoreCase("Member")) {
				//Leiter
				Player t = Bukkit.getPlayer(args[1]);
				if(Main.getSaves().getStringList("gilden." + ToolBox.getGildeNameOfPlayer(p) + ".Leiter").contains(p.getUniqueId().toString())) {
					if(ToolBox.getallPlayersinGilde(ToolBox.getGildeNameOfPlayer(p)).contains(t.getUniqueId().toString())) {
							
					}else {
						p.sendMessage("§cDieser Spieler ist nicht in deiner Gilde!");
					}
					
				}else {
					p.sendMessage("§cDu kannst die Ränge anderer Gildenmitglieder nicht ändern!");
				}
				
			}else {
				p.sendMessage("Der von dir eingegebene Rang funktioniert nicht! /nVerfügbare Ränge: /n-§6Gildenleiter /n§r-§6Forsitzender /n§r-§6Member");
			}
		}
	}
 
}
