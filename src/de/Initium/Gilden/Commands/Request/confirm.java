package de.Initium.Gilden.Commands.Request;

import de.Initium.Gilden.Commands.gilde_remove;
import de.Initium.Gilden.Main.Main;
import de.Initium.Gilden.Main.ToolBox;
import de.Initium.Gilden.Main.UUIDManipulation;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class confirm implements CommandExecutor {
    public static ArrayList<Player> hasconfirmed = new ArrayList<>();
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if(sender instanceof Player) {
            Player p = (Player) sender;
                if(args.length == 0) {
                    if(ToolBox.getallPlayers().contains(p.getUniqueId().toString())) {
                        if(!deny.hasCanceled.contains(p.getPlayer())) {
                            String gilde = ToolBox.getGildeNameOfPlayer(p);
                            ArrayList<String> playersofGilde = ToolBox.unserializeArrayList(ToolBox.getallPlayersinGilde(gilde));
                            if(Main.getSaves().get("Tags.GildeToTag." + gilde) == null) {
                                ToolBox.DelGilde(ToolBox.getGildeNameOfPlayer(p));
                                for (String all : playersofGilde) {
                                    if (!(p.getUniqueId().toString().equals(all))) {
                                        String temp = UUIDManipulation.getOnlinePlayerByUUID(all);
                                        if (!(temp.equals(""))) {
                                            (Bukkit.getPlayerExact(temp)).sendMessage("§6[GC] §6Der Leiter hat die Gilde verlassen!\nDie Gilde wurde aufgelöst");
                                                                          }
                                    }
                                }
                                p.sendMessage("§aDu hast die Gilde erfolgreich verlassen");
                            }else {
                                for (String all : playersofGilde) {
                                    if (!(p.getUniqueId().toString().equals(all))) {
                                        String temp = UUIDManipulation.getOnlinePlayerByUUID(all);
                                        if (!(temp.equals(""))) {
                                            (Bukkit.getPlayerExact(temp)).sendMessage("§6[GC] §6Der Leiter hat die Gilde verlassen!\nDie Gilde wurde aufgelöst");

                                        }
                                    }
                                }
                                p.sendMessage("§aDu hast die Gilde erfolgreich verlassen");
                                ToolBox.DelGildeToTag(ToolBox.getGildeNameOfPlayer(p));
                                ToolBox.DelTag(ToolBox.getTagbyGilde(ToolBox.getGildeNameOfPlayer(p)));
                                ToolBox.DelGilde(ToolBox.getGildeNameOfPlayer(p));
                            }
                        }else {
                            p.sendMessage("Du hast diesen Vorgang bereits abgebrochen!");
                        }

                    }else {
                        p.sendMessage("§cDu kannst diesen Befehl nicht ausführen!");
                    }

                }


        }


        return false;
    }
}
