package de.Initium.Gilden.Commands;


import de.Initium.Gilden.Main.Main;
import de.Initium.Gilden.Main.ToolBox;
import me.xanium.gemseconomy.api.GemsEconomyAPI;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class gilden_Bank extends JavaPlugin {

    public static boolean isDouble(String s) {
        try {
            Double.parseDouble(s);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }


    public static void execute(Integer nr, String[] args) {
        GemsEconomyAPI api = new GemsEconomyAPI();
        Player p = gilde_Main.getPlayer(nr);

        if(args[0].equalsIgnoreCase("pay")) {
            if (isDouble(args[1])) {
                if (ToolBox.getallPlayers().contains(p.getUniqueId().toString())) {
                    String gilde = ToolBox.getGildeNameOfPlayer(p);
                    if (api.getBalance(p.getUniqueId()) > Double.parseDouble(args[1])) {
                        api.withdraw(p.getUniqueId(), Double.parseDouble(args[1]));
                        Double befor = Double.parseDouble(Main.getSaves().get("gilden." + gilde + ".Information." + "Bank-Wert").toString());
                        Double after = Double.parseDouble(args[1]) + befor;
                        Main.getSaves().set("gilden." + gilde + ".Information." + "Bank-Wert", after.toString());
                        Main.saveSaves();
                        p.sendMessage("§aDu hast deiner Gilde §6" + args[1] + " §aKronen überwiesen!");
                    } else {
                        p.sendMessage("§cDu hast zu wenig Kronen!\nDeinen Kontostand kannst du mit §a/money §ceinsehen!");
                    }


                } else {
                    p.sendMessage("§cDu kannst diesen Befehl nicht benutzten");
                }

            } else {
                p.sendMessage("Der von dir angegebene Wert ist ungültig!");
            }


        }
        if(args[0].equalsIgnoreCase("take")) {
            if(args.length == 2) {
                if (ToolBox.getallPlayers().contains(p.getUniqueId().toString())) {
                    String gilde = ToolBox.getGildeNameOfPlayer(p);
                    if (ToolBox.getGildeRankByPlayer(gilde, p.getUniqueId().toString()).equalsIgnoreCase("Leiter") || ToolBox.getGildeRankByPlayer(gilde, p.getUniqueId().toString()).equalsIgnoreCase("Stellvertreter")) {
                        if (isDouble(args[1])) {
                            if (Double.parseDouble(Main.getSaves().get("gilden." + gilde + ".Information." + "Bank-Wert").toString()) > Double.parseDouble(args[1])) {
                                api.deposit(p.getUniqueId(), Double.parseDouble(args[1]));
                                Double befor = Double.parseDouble(Main.getSaves().get("gilden." + gilde + ".Information." + "Bank-Wert").toString());
                                Double after = befor - Double.parseDouble(args[1]);
                                Main.getSaves().set("gilden." + gilde + ".Information." + "Bank-Wert", after.toString());
                                Main.saveSaves();
                                p.sendMessage("§aDu hast erfolgreich §6" + args[1] + " §aKronen von der Gilden-Kasse abgehoben!");
                            } else {
                                p.sendMessage("Der eingegebene Betrag übersteigt den Betrag der Gildenkasse.");
                            }
                        } else {
                            p.sendMessage("Der von dir angegebene Wert ist ungültig!");
                        }
                    } else {
                        p.sendMessage("§cDu hast keine Rechte Kronen aus der Gildenbank zu nehmen");
                    }

                } else {
                    p.sendMessage("§cDu kannst diesen Befehl nicht benutzten");
                }
            }
            if(args.length == 3) {
                if(p.hasPermission("gilde.Take.Admin")) {
                    String gildeTeam = args[1];
                    if (isDouble(args[2])) {
                        if(ToolBox.checkGildeExists(gildeTeam)) {
                            if(Double.parseDouble(Main.getSaves().get("gilden." + gildeTeam + ".Information." + "Bank-Wert").toString()) > Double.parseDouble(args[2])) {
                                Double befor = Double.parseDouble(Main.getSaves().get("gilden." + gildeTeam + ".Information." + "Bank-Wert").toString());
                                Double after =  befor - Double.parseDouble(args[2]);
                                Main.getSaves().set("gilden." + gildeTeam + ".Information." + "Bank-Wert", after.toString());
                                Main.saveSaves();
                                p.sendMessage("§aDu hast erfolgreich §6" + args[2] + " §aKronen von der Gilden-Kasse abgehoben!");
                            }else {
                                p.sendMessage("Der eingegebene Betrag übersteigt den Betrag der Gildenkasse.");
                            }

                        }else {
                            p.sendMessage("Diese Gilde existiert nicht!");
                        }
                    }else {
                        p.sendMessage("Der von dir angegebene Wert ist ungültig!");
                    }


                }else {
                    p.sendMessage("§cDir fehlen die Berechtigungen!");
                }
            }


        }



    }
}
