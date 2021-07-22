package de.Initium.Gilden.Commands;


import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import de.Initium.Gilden.Main.Main;
import de.Initium.Gilden.Main.ToolBox;
import de.Initium.Gilden.Main.UUIDManipulation;

public class gilde_kick extends JavaPlugin{
	
	public static void execute(Integer nr, String[] args ) {
		Player p = gilde_Main.getPlayer(nr);
		Player t = Bukkit.getPlayer(args[1]);
		String gilde2 = ToolBox.getGildeNameOfPlayer(p);
		if(args.length == 2) {
			ArrayList<String> playersinlist = ToolBox.getallPlayers();
			if(playersinlist.contains(p.getUniqueId().toString())) {
				if(ToolBox.unserializeArrayList(ToolBox.getallPlayersinGilde(ToolBox.getGildeNameOfPlayer(p))).contains(t.getUniqueId().toString())) {
					if(ToolBox.getGildeRankByPlayer(gilde2, p.getUniqueId().toString()).equalsIgnoreCase("Leiter")) {
						if(!(ToolBox.getGildeRankByPlayer(gilde2, t.getUniqueId().toString()).equalsIgnoreCase("Leiter"))) {
							ToolBox.removePlayerfromGilde(t.getUniqueId().toString(), ToolBox.getGildeNameOfPlayer(p));
							ArrayList<String> playersofGilde = ToolBox.unserializeArrayList(ToolBox.getallPlayersinGilde(ToolBox.getGildeNameOfPlayer(Bukkit.getPlayerExact(p.getName()))));
							p.sendMessage("Du hast den Spieler "+ t.getName() + " aus der Gilde entfernt!");
							t.sendMessage("Du wurdest von dem Spieler " + p.getName() + " aus der Gilde entfernt!");
							
							p.sendMessage("[§a" + ToolBox.getGildeNameOfPlayer(Bukkit.getPlayerExact(p.getName())) + "§r] §rDer Spieler " + p.getName() + " hat den Spieler " + t.getName() + " aus der Gilde geworfen!");
			                for (String all : playersofGilde) {
			                    if (!(p.getUniqueId().toString().equals(all))) {
			                        String temp = UUIDManipulation.getOnlinePlayerByUUID(all);
			                        if (!(temp.equals(""))) {
			                            (Bukkit.getPlayerExact(temp)).sendMessage("[§a" + ToolBox.getGildeNameOfPlayer(Bukkit.getPlayerExact(p.getName())) + "§r] §rDer Spieler §6" + p.getName() + " §rhat den Spieler §6" + t.getName() + " §raus der Gilde geworfen!");

			                        }
			                    }
			                }
						}else {
							p.sendMessage("§cDu kannst keine Leiter aus der Gilde kicken!");
						}
					}else if(ToolBox.getGildeRankByPlayer(gilde2, p.getUniqueId().toString()).equalsIgnoreCase("Forsitzender")) {
						if(!(ToolBox.getGildeRankByPlayer(gilde2, t.getUniqueId().toString()).equalsIgnoreCase("Leiter") || ToolBox.getGildeRankByPlayer(gilde2, t.getUniqueId().toString()).equalsIgnoreCase("Forsitzender"))) {
							ToolBox.removePlayerfromGilde(t.getUniqueId().toString(), ToolBox.getGildeNameOfPlayer(p));
							ArrayList<String> playersofGilde = ToolBox.unserializeArrayList(ToolBox.getallPlayersinGilde(ToolBox.getGildeNameOfPlayer(Bukkit.getPlayerExact(p.getName()))));
							p.sendMessage("Du hast den Spieler "+ t.getName() + " aus der Gilde entfernt!");
							t.sendMessage("Du wurdest von dem Spieler " + p.getName() + " aus der Gilde entfernt!");
							
							p.sendMessage("[§a" + ToolBox.getGildeNameOfPlayer(Bukkit.getPlayerExact(p.getName())) + "§r] §rDer Spieler " + p.getName() + " hat den Spieler " + t.getName() + " aus der Gilde geworfen!");
			                for (String all : playersofGilde) {
			                    if (!(p.getUniqueId().toString().equals(all))) {
			                        String temp = UUIDManipulation.getOnlinePlayerByUUID(all);
			                        if (!(temp.equals(""))) {
			                            (Bukkit.getPlayerExact(temp)).sendMessage("[§a" + ToolBox.getGildeNameOfPlayer(Bukkit.getPlayerExact(p.getName())) + "§r] §rDer Spieler §6" + p.getName() + " §rhat den Spieler §6" + t.getName() + " §raus der Gilde geworfen!");
			                        }
			                    }
			                }
						}else {
							p.sendMessage("§cDu darfst diesen Spieler nicht kicken");
						}
					}else {
						p.sendMessage("§cDu darfst keine Spieler kicken!");
					}
					
				}else {
					p.sendMessage("§cDieser Spieler ist nicht in deiner Gilde!");
				}
			}else {
				p.sendMessage("§cDu kannst diesen Befehl nicht benutzen, da du in keiner Gilde bist!");
			}
		}
		if(args.length == 3) {
			if(p.hasPermission("Gilde.Kick.team")) {
				if(ToolBox.checkGildeExists(args[0])) {
					p.sendMessage("Test1");
					Player t2 = Bukkit.getPlayer(args[2]);
					if(ToolBox.unserializeArrayList(ToolBox.getallPlayersinGilde(args[0])).contains(t.getUniqueId().toString())) {
						p.sendMessage("Test2");
						ToolBox.removePlayerfromGilde(t2.getUniqueId().toString(), args[0]);
						p.sendMessage("§a Der Spieler §6" + args[1] + " §awurde erfolgreich aus der Gilde entfernt!");
					}else {
						p.sendMessage("§cDer Spieler ist nicht in der von dir angegebenen Gilde!");
					}
				}else {
					p.sendMessage("§cDie Gilde §6" + args[0] + " §cexistiert nicht!");
				}
			}else {
				p.sendMessage("§cDu hast keine Rechte Spieler aus fremden Gilden zu kicken");
			}
		}
	}
}


