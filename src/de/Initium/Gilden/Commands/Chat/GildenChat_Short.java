  
package de.Initium.Gilden.Commands.Chat;



import de.Initium.Gilden.Main.ToolBox;
import de.Initium.Gilden.Main.UUIDManipulation;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class GildenChat_Short implements CommandExecutor
{
    @Override
    public boolean onCommand(CommandSender sender, Command command, String lable, String[] args) {
        if(sender instanceof Player) {
            Player pl = (Player) sender;
            ArrayList<String> playersinlist = ToolBox.getallPlayers();
            if (gilde_chat_join.watcher.contains(pl.getName())) {
                pl.sendMessage("�cDu bist lediglich Zuschauer und kannst keine Nachrichten schreiben!");
            } else {
            	if(command.getName().equalsIgnoreCase("gctest")) {
                    if(playersinlist.contains(pl.getUniqueId().toString())) {
                    	ArrayList<String> playersofGilde = ToolBox.unserializeArrayList(ToolBox.getallPlayersinGilde(ToolBox.getGildeNameOfPlayer(Bukkit.getPlayerExact(pl.getName()))));
                        //Creation of the Message
                        String message = "";
                        for(int i = 0; i !=args.length; i++) {
                            message += args[i] + " ";
                        }
                        //Sending Message
                        String gilde = ToolBox.getGildeNameOfPlayer(pl);
                        if(ToolBox.getGildeRankByPlayer(gilde, pl.getUniqueId().toString()).equalsIgnoreCase("Leiter")) {
                            pl.sendMessage("�6[GC] [L] �f" + pl.getName() + ": �6" + message);
                        }
                        if(ToolBox.getGildeRankByPlayer(gilde, pl.getUniqueId().toString()).equalsIgnoreCase("Stellvertreter")) {
                            pl.sendMessage("�6[GC] [S] �f" + pl.getName() + ": �6" + message);
                        }
                        if(ToolBox.getGildeRankByPlayer(gilde, pl.getUniqueId().toString()).equalsIgnoreCase("Mitglieder")) {
                            pl.sendMessage("�6[GC] �f" + pl.getName() + ": �6" + message);
                        }
                        for(String all : playersofGilde) {
                            if(!(pl.getUniqueId().toString().equals(all))) {
                                String temp = UUIDManipulation.getOnlinePlayerByUUID(all);
                                if(!(temp.equals("")))
                                {
                                    if(ToolBox.getGildeRankByPlayer(gilde, pl.getUniqueId().toString()).equalsIgnoreCase("Leiter")) {
                                        (Bukkit.getPlayerExact(temp)).sendMessage("�6[GC] [L] �f" + pl.getName() + ": �6" + message);
                                    }
                                    if(ToolBox.getGildeRankByPlayer(gilde, pl.getUniqueId().toString()).equalsIgnoreCase("Stellvertreter")) {
                                        (Bukkit.getPlayerExact(temp)).sendMessage("�6[GC] [S] �f" + pl.getName() + ": �6" + message);
                                    }
                                    if(ToolBox.getGildeRankByPlayer(gilde, pl.getUniqueId().toString()).equalsIgnoreCase("Mitglieder")) {
                                        (Bukkit.getPlayerExact(temp)).sendMessage("�6[GC] �f" + pl.getName() + ": �6" + message);
                                    }
                                }
                            }
                        }
                    }else {
                        pl.sendMessage("�cDu kannst diesen Befehl nicht beznutzen, da du in keiner Gilde bist!");
                    }
                }
            }
            
        }
        return true;
    }
}