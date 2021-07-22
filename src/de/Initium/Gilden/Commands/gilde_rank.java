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
		String gilde = ToolBox.getGildeNameOfPlayer(p);
		Player t = Bukkit.getPlayer(args[1]);
		if(args.length == 3) {
			if(args[2].equalsIgnoreCase("Leiter") || args[2].equalsIgnoreCase("Forsitzender") || args[2].equalsIgnoreCase("Mitglied")) {
				//Leiter
				
				if(ToolBox.getallPlayers().contains(t.getUniqueId().toString())) {
					if(p == t) {
						p.sendMessage("§cDu kannst deinen eigenen Rang nicht ändern!");
						return;
					}
					if(ToolBox.getGildeRankByPlayer(ToolBox.getGildeNameOfPlayer(p), p.getUniqueId().toString()).equalsIgnoreCase("Leiter")) {
						
						if(ToolBox.unserializeArrayList(ToolBox.getallPlayersinGilde(gilde)).contains(t.getUniqueId().toString())) {
							if(args[2].equalsIgnoreCase("Forsitzender")) {
								if(ToolBox.getGildeRankByPlayer(gilde, t.getUniqueId().toString()).equalsIgnoreCase("Mitglieder")) {
									ToolBox.removePlayerfromGilde(t.getUniqueId().toString(), ToolBox.getGildeNameOfPlayer(p));
									ToolBox.addPlayertoGilde(t.getUniqueId().toString(), gilde, "Forsitzender");
									Main.saveSaves();
									p.sendMessage("§aDu hast den Rang des Spielers §6" + t.getName() + " §avon Mitglied auf Forsitzender befördert!");
									t.sendMessage("§aDein Rang wurde von §6Member §aauf Rang §6Forsitzender §abefördert!");
								}else if(ToolBox.getGildeRankByPlayer(gilde, t.getUniqueId().toString()).equalsIgnoreCase("Forsitzender")){
									p.sendMessage("Dieser Spieler ist bereits Forsitzender");
								}else {
									p.sendMessage("§c Dieser Spieler ist ein Leiter! Du kannst seinen Rang nicht verändern");
								}
						}
							
							if(args[2].equalsIgnoreCase("Leiter")) {
								if(ToolBox.getGildeRankByPlayer(gilde, t.getUniqueId().toString()).equalsIgnoreCase("Forsitzender")) {
									ToolBox.removePlayerfromGilde(t.getUniqueId().toString(), ToolBox.getGildeNameOfPlayer(p));
										ToolBox.addPlayertoGilde(t.getUniqueId().toString(), gilde, "Leiter");
										p.sendMessage("§aDu hast den Rang des Spielers §6" + t.getName() + " §avon Forsitzender auf Leiter befördert!");
										t.sendMessage("§aDein Rang wurde von §6Forsitzender §aauf Rang §6Leiter §abefördert!");
									
									
									
									
									
								}else if(ToolBox.getGildeRankByPlayer(gilde, t.getUniqueId().toString()).equalsIgnoreCase("Mitglieder")) {
									ToolBox.removePlayerfromGilde(t.getUniqueId().toString(), ToolBox.getGildeNameOfPlayer(p));
									ToolBox.addPlayertoGilde(t.getUniqueId().toString(), gilde, "Leiter");
									t.sendMessage("§aDein Rang wurde von §6Mitglied §aauf Rang §6Leiter §abefördert!");
									p.sendMessage("§aDu hast den Rang des Spielers §6" + t.getName() + " §avon Mitglied auf Leiter befördert!");
								}else {
									p.sendMessage("§c Dieser Spieler ist ein Leiter! Du kannst seinen Rang nicht verändern");
								}
							}
							
						if(args[2].equalsIgnoreCase("Mitglied")) {
							if(ToolBox.getGildeRankByPlayer(gilde, t.getUniqueId().toString()).equalsIgnoreCase("Forsitzender")) {
								ToolBox.removePlayerfromGilde(t.getUniqueId().toString(), ToolBox.getGildeNameOfPlayer(p));
									ToolBox.addPlayertoGilde(t.getUniqueId().toString(), gilde, "Mitglieder");
									p.sendMessage("§aDu hast den Rang des Spielers §6" + t.getName() + " §avon Forsitzender auf Mitglied degradiert!");
									t.sendMessage("§aDein Rang wurde von §6Forsitzender §aauf Rang §6Member §adegradiert!");
								
								
								
								
								
							}else if(ToolBox.getGildeRankByPlayer(gilde, t.getUniqueId().toString()).equalsIgnoreCase("Mitglieder")) {
								p.sendMessage("§cDieser Spieler ist bereits ein Mitglied!");
							}else {
								p.sendMessage("§c Dieser Spieler ist ein Leiter! Du kannst seinen Rang nicht verändern");
							}
						}
						
						
						
					}else {
						p.sendMessage("§cDieser Spieler ist nicht in deiner Gilde!");
					}
					
					
				
			}else {
				p.sendMessage("§cDu kannst die Ränge anderer Gildenmitglieder nicht ändern!");
			}
				}else {
					p.sendMessage("§cDu kannst diesen Befehl nicht ausführen, da du dich in keiner Gilde befindest!");
					return;
				}
					
				
				
		}else {
			p.sendMessage("Der von dir eingegebene Rang funktioniert nicht! \nVerfügbare Ränge: \n-§6Leiter \n§r-§6Forsitzender \n§r-§6Member");
		}
	}
	if(args.length == 4) {
		String gilde2 = args[1];
		Player t2 = Bukkit.getPlayer(args[2]);
		if(p.hasPermission("gilde.rank.admin")) {
			if(args[3].equalsIgnoreCase("Leiter") || args[3].equalsIgnoreCase("Forsitzender") || args[3].equalsIgnoreCase("Mitglied")) {
				if(ToolBox.checkGildeExists(gilde2)) {
					if(ToolBox.unserializeArrayList(ToolBox.getallPlayersinGilde(gilde2)).contains(t2.getUniqueId().toString())) {
						if(args[3].equalsIgnoreCase("Forsitzender")) {
							if(ToolBox.getGildeRankByPlayer(gilde2, t2.getUniqueId().toString()).equalsIgnoreCase("Mitglieder")) {
								ToolBox.removePlayerfromGilde(t2.getUniqueId().toString(), ToolBox.getGildeNameOfPlayer(p));
								ToolBox.addPlayertoGilde(t2.getUniqueId().toString(), gilde2, "Forsitzender");
								Main.saveSaves();
								p.sendMessage("§aDu hast den Rang des Spielers §6" + t2.getName() + " §avon Mitglied auf Forsitzender befördert!");
								t2.sendMessage("§aEin Teammitglied hat deinen Rang von §6Member §aauf Rang §6Forsitzender §abefördert!");
							}else if(ToolBox.getGildeRankByPlayer(gilde2, t2.getUniqueId().toString()).equalsIgnoreCase("Forsitzender")){
								p.sendMessage("Dieser Spieler ist bereits Forsitzender");
							}else {
								ToolBox.removePlayerfromGilde(t2.getUniqueId().toString(), ToolBox.getGildeNameOfPlayer(p));
								ToolBox.addPlayertoGilde(t2.getUniqueId().toString(), gilde2, "Forsitzender");
								p.sendMessage("§aDu hast den Rang des Spielers §6" + t2.getName() + " §avon Leiter auf Forsitzender degradiert!");
								t2.sendMessage("§cEin Teammitglied hat deinen Rang von §6Leiter §cauf Rang §6Forsitzender §cdegradiert!");
							}
					}
						
						if(args[3].equalsIgnoreCase("Leiter")) {
							if(ToolBox.getGildeRankByPlayer(gilde2, t2.getUniqueId().toString()).equalsIgnoreCase("Forsitzender")) {
								ToolBox.removePlayerfromGilde(t2.getUniqueId().toString(), ToolBox.getGildeNameOfPlayer(p));
									ToolBox.addPlayertoGilde(t2.getUniqueId().toString(), gilde2, "Leiter");
									p.sendMessage("§aDu hast den Rang des Spielers §6" + t2.getName() + " §avon Forsitzender auf Leiter befördert!");
									t2.sendMessage("§aEin Teammitglied hat deinen Rang von §6Forsitzender §aauf Rang §6Leiter §abefördert!");
								
								
								
								
								
							}else if(ToolBox.getGildeRankByPlayer(gilde2, t2.getUniqueId().toString()).equalsIgnoreCase("Mitglieder")) {
								ToolBox.removePlayerfromGilde(t2.getUniqueId().toString(), ToolBox.getGildeNameOfPlayer(p));
								ToolBox.addPlayertoGilde(t2.getUniqueId().toString(), gilde2, "Leiter");
								t2.sendMessage("§aEin Teammitglied hat deinen Rang von §6Mitglied §aauf Rang §6Leiter §abefördert!");
								p.sendMessage("§aDu hast den Rang des Spielers §6" + t2.getName() + " §avon Mitglied auf Leiter befördert!");
							}else {
								p.sendMessage("§c Dieser Spieler ist bereits ein Leiter");
							}
						}
						
					if(args[3].equalsIgnoreCase("Mitglied")) {
						if(ToolBox.getGildeRankByPlayer(gilde2, t2.getUniqueId().toString()).equalsIgnoreCase("Forsitzender")) {
							ToolBox.removePlayerfromGilde(t2.getUniqueId().toString(), ToolBox.getGildeNameOfPlayer(p));
								ToolBox.addPlayertoGilde(t2.getUniqueId().toString(), gilde2, "Mitglieder");
								p.sendMessage("§aDu hast den Rang des Spielers §6" + t2.getName() + " §avon Forsitzender auf Mitglied degradiert!");
								t2.sendMessage("§cEin Teammitglied hat deinen Rang von §6Forsitzender §cauf Rang §6Member §cdegradiert!");
							
							
							
							
							
						}else if(ToolBox.getGildeRankByPlayer(gilde, t2.getUniqueId().toString()).equalsIgnoreCase("Mitglieder")) {
							p.sendMessage("§cDieser Spieler ist bereits ein Mitglied!");
						}else {
							ToolBox.removePlayerfromGilde(t2.getUniqueId().toString(), ToolBox.getGildeNameOfPlayer(p));
							ToolBox.addPlayertoGilde(t2.getUniqueId().toString(), gilde2, "Forsitzender");
							p.sendMessage("§aDu hast den Rang des Spielers §6" + t2.getName() + " §avon Leiter auf Mitglied degradiert!");
							t2.sendMessage("§cEin Teammitglied hat deinen Rang von §6Leiter §cauf Rang §6Mitglied §cdegradiert!");
						}
					}
				}else {
					p.sendMessage("§cDieser Spieler ist nicht in der von dir gewählten Gilde!");
				}
				}else {
					p.sendMessage("§cDie von dir gewählte Gilde existiert nicht");
				}
			}else {
				p.sendMessage("Der von dir eingegebene Rang funktioniert nicht! \nVerfügbare Ränge: \n-§6Leiter \n§r-§6Forsitzender \n§r-§6Member");
			}
			
			
		}else {
			p.sendMessage("§cDazu fehlen dir die Berechtigungen!");
		}
	}else {
		p.sendMessage("§cZuviele Args");
	}
 
}
}
