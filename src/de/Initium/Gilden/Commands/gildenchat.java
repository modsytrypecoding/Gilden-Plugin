package de.Initium.Gilden.Commands;

import de.Initium.Gilden.Main.ToolBox;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class gildenchat implements CommandExecutor
{
    @Override
    public boolean onCommand(CommandSender sender, Command command, String lable, String[] args) {
        if(sender instanceof Player) {
            Player pl = (Player) sender;
            ArrayList<String> playersinlist = ToolBox.getallPlayers();

            if(command.getName().equalsIgnoreCase("gctest")) {
                ArrayList<String> playersofGilde = ToolBox.getallPlayersinGilde(ToolBox.getGildeNameOfPlayer(Bukkit.getPlayerExact(pl.getName())));

                if(playersinlist.contains(pl.getUniqueId().toString())) {
                    //Creation of the Message
                    String message = "";
                    for(int i = 0; i !=args.length; i++) {
                        message += args[i] + " ";
                    }

                    //Sending Message
                    pl.sendMessage("[§a" + ToolBox.getGildeNameOfPlayer(Bukkit.getPlayerExact(pl.getName())) + "§r] §6" + pl.getName() + "§r: " + message);
                    for(String all : playersofGilde) {
                        if(!(pl.getUniqueId().toString().equals(all))) {
                            Object temp = ToolBox.getOnlinePlayerByUUID(pl, all);
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
        return true;
    }
}
