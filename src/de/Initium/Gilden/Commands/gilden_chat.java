package de.Initium.Gilden.Commands;



import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import de.Initium.Gilden.Main.ToolBox;
import de.Initium.Gilden.Main.UUIDManipulation;

public class gilden_chat
{
    public static void execute(Integer nr, String[] args) {
        //Casting Player from gilde_Main
        Player pl = gilde_Main.getPlayer(nr);
        if (gilde_chat_join.watcher.contains(pl.getName())) {
            pl.sendMessage("§cDu bist lediglich Zuschauer und kannst keine Nachrichten schreiben!");
        } else {
            ArrayList<String> playersinlist = ToolBox.getallPlayers();
            
            if (playersinlist.contains(pl.getUniqueId().toString())) {
            	ArrayList<String> playersofGilde = ToolBox.unserializeArrayList(ToolBox.getallPlayersinGilde(ToolBox.getGildeNameOfPlayer(Bukkit.getPlayerExact(pl.getName()))));
                //Creation of the Message
                String message = "";
                for (int i = 1; i != args.length; i++) {
                    message += args[i] + " ";
                }

                //Sending Message
                pl.sendMessage("[§a" + ToolBox.getGildeNameOfPlayer(Bukkit.getPlayerExact(pl.getName())) + "§r] §6" + pl.getName() + "§r: " + message);
                for (String all : playersofGilde) {
                    if (!(pl.getUniqueId().toString().equals(all))) {
                        String temp = UUIDManipulation.getOnlinePlayerByUUID(all);
                        if (!(temp.equals(""))) {
                            (Bukkit.getPlayerExact(temp)).sendMessage("[§a" + ToolBox.getGildeNameOfPlayer(Bukkit.getPlayerExact(pl.getName())) + "§r] §6" + pl.getName() + "§r: " + message);
                        }
                    }
                }
            } else {
                pl.sendMessage("§cDu kannst diesen Befehl nicht benutzen, da du in keiner Gilde bist!");
            }
        }
    }
}