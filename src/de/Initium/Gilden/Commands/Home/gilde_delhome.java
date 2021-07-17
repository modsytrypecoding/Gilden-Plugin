package de.Initium.Gilden.Commands.Home;

import de.Initium.Gilden.Commands.gilde_Main;
import de.Initium.Gilden.Main.Main;
import de.Initium.Gilden.Main.ToolBox;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class gilde_delhome extends JavaPlugin {

    public static void execute(Integer nr) {
        Player p = gilde_Main.getPlayer(nr);

        if(ToolBox.getallPlayers().contains(p.getUniqueId().toString())) {
            String gilde = ToolBox.getGildeNameOfPlayer(p);

                if(ToolBox.getGildeRankByPlayer(gilde, p.getUniqueId().toString()).equalsIgnoreCase("Leiter") || ToolBox.getGildeRankByPlayer(gilde, p.getUniqueId().toString()).equalsIgnoreCase("Fositzender")) {
                    if(Main.getSaves().get("gilden." + gilde + ".Information." + "hasSetHome").equals(true)) {
                        Main.getSaves().set("gilden." + gilde + ".Information." + " Location", null);
                        Main.getSaves().set("gilden." + gilde + ".Information." + "hasSetHome", false);
                        Main.saveSaves();
                        p.sendMessage("§aDu hast den Home Punkt erfolgreich gelöscht!");
                    }else {
                        p.sendMessage("§cDeine Gilde hat keinen Aktiven Home Punkt!");
                    }

                } else {
                    p.sendMessage("§cDu kannst den Home Punkt nicht löschen!");
                }
            }else {
                p.sendMessage("Du kannst diesen Befehl nicht benutzen!");
            }
            }


    }

