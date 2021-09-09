package de.Initium.Gilden.Commands.Request;

import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import com.sk89q.worldguard.protection.regions.RegionContainer;
import de.Initium.Gilden.Commands.gilde_remove;
import de.Initium.Gilden.Main.Main;
import de.Initium.Gilden.Main.ToolBox;
import de.Initium.Gilden.Main.UUIDManipulation;
import me.xanium.gemseconomy.api.GemsEconomyAPI;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.UUID;

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

                                    GemsEconomyAPI api = new GemsEconomyAPI();
                                    Double Value = Double.parseDouble(ToolBox.getBankValue(gilde));
                                    Double Purse = api.getBalance(p.getUniqueId());
                                p.sendMessage("Purse:" + Purse);
                                p.sendMessage("Value: " + Value);
                                double deposit = Value + Purse;
                                api.deposit(p.getUniqueId(), deposit);

                                ToolBox.DelGilde(ToolBox.getGildeNameOfPlayer(p));
                                for (String all : playersofGilde) {
                                    if (!(p.getUniqueId().toString().equals(all))) {
                                        String temp = UUIDManipulation.getOnlinePlayerByUUID(all);
                                        if (!(temp.equals(""))) {
                                            (Bukkit.getPlayerExact(temp)).sendMessage("§6[GC] §6Der Leiter hat die Gilde verlassen!\nDie Gilde wurde aufgelöst");
                                            break;
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
                                            break;

                                        }
                                    }
                                }
                                p.sendMessage("§aDu hast die Gilde erfolgreich verlassen");
                                ToolBox.removeWorldGuardPerms(p);
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
