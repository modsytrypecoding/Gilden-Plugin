package de.Initium.Gilden.Commands;

import de.Initium.Gilden.Main.Main;
import de.Initium.Gilden.Main.ToolBox;
import de.Initium.Gilden.Main.UUIDManipulation;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class gilde_request extends JavaPlugin {

    public static void execute(Integer nr, String[] args) {
        Player p = gilde_Main.getPlayer(nr);
        if(ToolBox.getallPlayers().contains(p.getUniqueId().toString())) {
            p.sendMessage("§cDu kannst diesen Befehel nicht nutzen!");
        }else {
            String gilde = args[1];
            if(ToolBox.checkGildeExists(args[1])) {
                for(String allLeiter : Main.getSaves().getStringList("gilden." + gilde + ".raenge.Leiter")) {
                    p.sendMessage(Main.getSaves().getStringList("gilden." + gilde + ".raenge.Leiter").toString());
                    Player Leiter = Bukkit.getPlayer(UUIDManipulation.getPlayernameByUUID(allLeiter));
                    if(Leiter.isOnline()) {
                        Leiter.sendMessage("Test");
                    }else {
                        for (String allStellvertreter : Main.getSaves().getStringList("gilden." + gilde + ".raenge.Stellvertreter")) {
                            Player Stellvertreter = Bukkit.getPlayer(UUIDManipulation.getPlayernameByUUID(allStellvertreter));
                            if(Stellvertreter.isOnline()) {
                                Stellvertreter.sendMessage("Test");
                            }
                        }
                        p.sendMessage("Deine Anfrage kann momentan nicht entgegen genommen werden\nda keine Leiter oder Stellvertreter dieser Gilde online ist!");
                    }
                }
            }else {
                p.sendMessage("Die von dir angegebene Gilde existiert nicht!");
            }
        }

    }
}
