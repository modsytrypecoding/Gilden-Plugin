package de.Initium.Gilden.Commands;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import de.Initium.Gilden.Main.ToolBox;
import de.Initium.Gilden.Main.UUIDManipulation;

public class gilden_chat extends JavaPlugin{
	
	public static void execute(Integer nr, String[] args) {
		


		            
		                //Casting Player from gilde_Main
		
		                Player pl = gilde_Main.getPlayer(nr);
		                if(gilde_chat_join.watcher.contains(pl.getName())) {
		                	pl.sendMessage("§cDu bist lediglich Zuschauer und kannst keine Nachrichten schreiben!");
		                }else {
		                	 ArrayList<String> playersinlist = ToolBox.getallPlayers();
					            ArrayList<String> playersofGilde = ToolBox.getallPlayersinGilde(ToolBox.getGildeNameOfPlayer(Bukkit.getPlayerExact(pl.getName())));
				                if(playersinlist.contains(pl.getUniqueId().toString())) {
				                    //Creation of the Message
				                    String message = "";
				                    for(int i = 1; i !=args.length; i++) {
				                        message += args[i] + " ";
				                    }

				                    //Sending Message
				                    pl.sendMessage("[§a" + ToolBox.getGildeNameOfPlayer(Bukkit.getPlayerExact(pl.getName())) + "§r] §6" + pl.getName() + "§r: " + message);
				                    for(String all : playersofGilde) {
				                        if(!(pl.getUniqueId().toString().equals(all))) {
				                            Object temp = UUIDManipulation.getOnlinePlayerByUUID(all);
				                            if(temp instanceof Player)
				                            {
				                                ((Player) temp).sendMessage("[§a" + ToolBox.getGildeNameOfPlayer(Bukkit.getPlayerExact(pl.getName())) + "§r] §6" + pl.getName() + "§r: " + message);
				                            }
				                        }
				                    }
				                }else {
				                    pl.sendMessage("§cDu kannst diesen Befehl nicht beznutzen, da du in keiner Gilde bist!");
				                } 
		                }
		                
		}
	}

