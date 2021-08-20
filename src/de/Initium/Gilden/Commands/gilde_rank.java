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
			if(args[2].equalsIgnoreCase("Leiter") || args[2].equalsIgnoreCase("Stellvertreter") || args[2].equalsIgnoreCase("Mitglied")) {
				//Leiter
				
				if(ToolBox.getallPlayers().contains(t.getUniqueId().toString())) {
					if(p == t) {
						p.sendMessage("§cDu kannst deinen eigenen Rang nicht ändern!");
						return;
					}
					if(ToolBox.getGildeRankByPlayer(ToolBox.getGildeNameOfPlayer(p), p.getUniqueId().toString()).equalsIgnoreCase("Leiter")) {
						
						if(ToolBox.unserializeArrayList(ToolBox.getallPlayersinGilde(gilde)).contains(t.getUniqueId().toString())) {
							if(args[2].equalsIgnoreCase("Stellvertreter")) {
								if(ToolBox.getGildeRankByPlayer(gilde, t.getUniqueId().toString()).equalsIgnoreCase("Mitglieder")) {

									if(Main.getSaves().getStringList("gilden." + gilde + ".raenge.Stellvertreter").size() == 3) {
										p.sendMessage("§cDie Maximale Anzahl an Stellvertretern ist erreicht!");
									}else {
										ToolBox.removePlayerfromGilde(t.getUniqueId().toString(), ToolBox.getGildeNameOfPlayer(p));
										ToolBox.addPlayertoGilde(t.getUniqueId().toString(), gilde, "Stellvertreter");
										Main.saveSaves();
										p.sendMessage("§aDu hast den Rang des Spielers §6" + t.getName() + " §avon Mitglied auf Stellvertreter befördert!");
										t.sendMessage("§aDein Rang wurde von §6Member §aauf Rang §6Stellvertreter §abefördert!");
									}
								}else if(ToolBox.getGildeRankByPlayer(gilde, t.getUniqueId().toString()).equalsIgnoreCase("Stellvertreter")){
									p.sendMessage("Dieser Spieler ist bereits Stellvertreter");
								}else {
									p.sendMessage("§c Dieser Spieler ist ein Leiter! Du kannst seinen Rang nicht verändern");
								}
						}
							
							if(args[2].equalsIgnoreCase("Leiter")) {
								if(ToolBox.getGildeRankByPlayer(gilde, t.getUniqueId().toString()).equalsIgnoreCase("Stellvertreter")) {

									if(Main.getSaves().getStringList("gilden." + gilde + ".raenge.Leiter").size() == 3) {
										p.sendMessage("§cDie Maximale Anzahl an Stellvertretern ist erreicht!");
									}else {
										ToolBox.removePlayerfromGilde(t.getUniqueId().toString(), ToolBox.getGildeNameOfPlayer(p));
										ToolBox.addPlayertoGilde(t.getUniqueId().toString(), gilde, "Leiter");
										p.sendMessage("§aDu hast den Rang des Spielers §6" + t.getName() + " §avon Stellvertreter auf Leiter befördert!");
										t.sendMessage("§aDein Rang wurde von §6Stellvertreter §aauf Rang §6Leiter §abefördert!");
									}


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
							if(ToolBox.getGildeRankByPlayer(gilde, t.getUniqueId().toString()).equalsIgnoreCase("Stellvertreter")) {
								ToolBox.removePlayerfromGilde(t.getUniqueId().toString(), ToolBox.getGildeNameOfPlayer(p));
									ToolBox.addPlayertoGilde(t.getUniqueId().toString(), gilde, "Mitglieder");
									p.sendMessage("§aDu hast den Rang des Spielers §6" + t.getName() + " §avon Stellvertreter auf Mitglied degradiert!");
									t.sendMessage("§aDein Rang wurde von §6Stellvertreter §aauf Rang §6Member §adegradiert!");
								
								
								
								
								
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
			p.sendMessage("Der von dir eingegebene Rang funktioniert nicht! \nVerfügbare Ränge: \n-§6Leiter \n§r-§6Stellvertreter \n§r-§6Member");
		}
	}
	if(args.length == 4) {
		String gilde2 = args[1];
		Player t2 = Bukkit.getPlayer(args[2]);
		if(p.hasPermission("gilde.rank.admin")) {
			if(args[3].equalsIgnoreCase("Leiter") || args[3].equalsIgnoreCase("Stellvertreter") || args[3].equalsIgnoreCase("Mitglied")) {
				if(ToolBox.checkGildeExists(gilde2)) {
					if(t2 == null) {
						if(ToolBox.unserializeArrayList(ToolBox.getallPlayersinGilde(gilde2)).contains(t2.getUniqueId().toString())) {
							if(args[3].equalsIgnoreCase("Stellvertreter")) {
								if(ToolBox.getGildeRankByPlayer(gilde2, t2.getUniqueId().toString()).equalsIgnoreCase("Mitglieder")) {
									ToolBox.removePlayerfromGilde(t2.getUniqueId().toString(), gilde2);
									ToolBox.addPlayertoGilde(t2.getUniqueId().toString(), gilde2, "Stellvertreter");
									Main.saveSaves();
									p.sendMessage("§aDu hast den Rang des Spielers §6" + t2.getName() + " §avon Mitglied auf Stellvertreter befördert!");
									t2.sendMessage("§aEin Teammitglied hat deinen Rang von §6Member §aauf Rang §6Stellvertreter §abefördert!");
								}else if(ToolBox.getGildeRankByPlayer(gilde2, t2.getUniqueId().toString()).equalsIgnoreCase("Stellvertreter")){
									p.sendMessage("Dieser Spieler ist bereits Stellvertreter");
								}else {
									ToolBox.removePlayerfromGilde(t2.getUniqueId().toString(), gilde2);
									ToolBox.addPlayertoGilde(t2.getUniqueId().toString(), gilde2, "Stellvertreter");
									p.sendMessage("§aDu hast den Rang des Spielers §6" + t2.getName() + " §avon Leiter auf Stellvertreter degradiert!");
									t2.sendMessage("§cEin Teammitglied hat deinen Rang von §6Leiter §cauf Rang §6Stellvertreter §cdegradiert!");
								}
							}

							if(args[3].equalsIgnoreCase("Leiter")) {
								if(ToolBox.getGildeRankByPlayer(gilde2, t2.getUniqueId().toString()).equalsIgnoreCase("Stellvertreter")) {
									ToolBox.removePlayerfromGilde(t2.getUniqueId().toString(), gilde2);
									ToolBox.addPlayertoGilde(t2.getUniqueId().toString(), gilde2, "Leiter");
									p.sendMessage("§aDu hast den Rang des Spielers §6" + t2.getName() + " §avon Stellvertreter auf Leiter befördert!");
									t2.sendMessage("§aEin Teammitglied hat deinen Rang von §6Stellvertreter §aauf Rang §6Leiter §abefördert!");





								}else if(ToolBox.getGildeRankByPlayer(gilde2, t2.getUniqueId().toString()).equalsIgnoreCase("Mitglieder")) {
									ToolBox.removePlayerfromGilde(t2.getUniqueId().toString(), gilde2);
									ToolBox.addPlayertoGilde(t2.getUniqueId().toString(), gilde2, "Leiter");
									t2.sendMessage("§aEin Teammitglied hat deinen Rang von §6Mitglied §aauf Rang §6Leiter §abefördert!");
									p.sendMessage("§aDu hast den Rang des Spielers §6" + t2.getName() + " §avon Mitglied auf Leiter befördert!");
								}else {
									p.sendMessage("§c Dieser Spieler ist bereits ein Leiter");
								}
							}

							if(args[3].equalsIgnoreCase("Mitglied")) {
								if(ToolBox.getGildeRankByPlayer(gilde2, t2.getUniqueId().toString()).equalsIgnoreCase("Stellvertreter")) {
									ToolBox.removePlayerfromGilde(t2.getUniqueId().toString(), gilde2);
									ToolBox.addPlayertoGilde(t2.getUniqueId().toString(), gilde2, "Mitglieder");
									p.sendMessage("§aDu hast den Rang des Spielers §6" + t2.getName() + " §avon Stellvertreter auf Mitglied degradiert!");
									t2.sendMessage("§cEin Teammitglied hat deinen Rang von §6Stellvertreter §cauf Rang §6Member §cdegradiert!");





								}else if(ToolBox.getGildeRankByPlayer(gilde, t2.getUniqueId().toString()).equalsIgnoreCase("Mitglieder")) {
									p.sendMessage("§cDieser Spieler ist bereits ein Mitglied!");
								}else {
									ToolBox.removePlayerfromGilde(t2.getUniqueId().toString(), gilde2);
									ToolBox.addPlayertoGilde(t2.getUniqueId().toString(), gilde2, "Mitglieder");
									p.sendMessage("§aDu hast den Rang des Spielers §6" + t2.getName() + " §avon Leiter auf Mitglied degradiert!");
									t2.sendMessage("§cEin Teammitglied hat deinen Rang von §6Leiter §cauf Rang §6Mitglied §cdegradiert!");
								}
							}
						}else {
							p.sendMessage("§cDieser Spieler ist nicht in der von dir gewählten Gilde!");
						}
					}else {
						p.sendMessage("Der Spieler " + t2.getName() + " ist momentan nicht online!");
					}

				}else {
					p.sendMessage("§cDie von dir gewählte Gilde existiert nicht");
				}
			}else {
				p.sendMessage("Der von dir eingegebene Rang funktioniert nicht! \nVerfügbare Ränge: \n-§6Leiter \n§r-§6Stellvertreter \n§r-§6Member");
			}
			
			
		}else {
			p.sendMessage("§cDazu fehlen dir die Berechtigungen!");
		}
	}
 
}
}
