package de.Initium.Gilden.Commands;

import de.Initium.Gilden.Main.Main;
import de.Initium.Gilden.Main.ToolBox;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class toggleRequest extends JavaPlugin {

    public static void execute(Integer nr) {
        Player p = gilde_Main.getPlayer(nr);

        if(ToolBox.getallPlayers().contains(p.getUniqueId().toString())) {
            String Gilde = ToolBox.getGildeNameOfPlayer(p);
            if(ToolBox.getGildeRankByPlayer(Gilde, p.getUniqueId().toString()).equalsIgnoreCase("Leiter")) {
                if(Main.getSaves().getBoolean("gilden." + Gilde + ".Information.RequestOn")) {
                    Main.getSaves().set("gilden." + Gilde + ".Information.RequestOn", false);
                    Main.saveSaves();
                    p.sendMessage("Deine Gilde erhält absofort keine Anfragen mehr!");
                }else {
                    Main.getSaves().set("gilden." + Gilde + ".Information.RequestOn", true);
                    Main.saveSaves();
                    p.sendMessage("Deine Gilde erhält absofort Anfragen!");
                }
            }else {
                p.sendMessage("Du kannst diesen Befehl nicht ausführen");
            }
        }else {
            p.sendMessage("Du kannst diesen Befehl nicht ausführen!");
        }
    }
}
