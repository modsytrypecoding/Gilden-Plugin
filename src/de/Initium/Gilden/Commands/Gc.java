package de.Initium.Gilden.Commands;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.Initium.Gilden.Main.ToolBox;

public class Gc implements CommandExecutor{
	
	

	

	@Override
	public boolean onCommand(CommandSender sender, Command command, String lable, String[] args) {
		ArrayList<String> playersinlist = ToolBox.getallPlayers();
		
		 ToolBox.addPlayertoGilde(Bukkit.getPlayer("doppelkool").getUniqueId().toString(), "AffeemitWaffee");
		
		if(sender instanceof Player) {
			if(command.getName().equalsIgnoreCase("gctest")) {
				Player pl = (Player) sender;
				
				ArrayList<String> playersofGilde = ToolBox.getallPlayersinGilde(ToolBox.getGildeNameOfPlayer(Bukkit.getPlayerExact(pl.getName())));
				pl.sendMessage(playersofGilde.toString());
				if(playersinlist.contains(pl.getUniqueId().toString())) {
					String message = "";
					for(int i = 0; i !=args.length; i++) {
						message += args[i] + " ";
					}
					pl.sendMessage("[§a" + ToolBox.getGildeNameOfPlayer(Bukkit.getPlayerExact(pl.getName())) + "§r] §6" + pl.getName() + "§r: " + message);
					for(String all : playersofGilde) {
						
						
						//gctest Hallo
						
						if(!(pl.getUniqueId().toString().equals(all))) {
							
							ArrayList<String> garkeinBock = new ArrayList<String>();
							garkeinBock.add(all);
							garkeinBock = ToolBox.parseAllUUIDsToPlayerNames(garkeinBock);
							Player fickdich = Bukkit.getPlayerExact(garkeinBock.get(0));
							fickdich.sendMessage("[§a" + ToolBox.getGildeNameOfPlayer(Bukkit.getPlayerExact(pl.getName())) + "§r] §6" + pl.getName() + "§r: " + message);
						}
						
							
						
						
					}
		
				}else {
					pl.sendMessage("§cDu kannst diesen Befehl nicht beznutzen, da du in keiner Gilde bist!");
				}
			}
			
			
			
		}
		
		
		return true;

 }
}
