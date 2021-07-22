package de.Initium.Gilden.Commands.Tag;

import de.Initium.Gilden.Commands.gilde_Main;
import de.Initium.Gilden.Main.Main;
import de.Initium.Gilden.Main.ToolBox;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class gilde_delTagTeam extends JavaPlugin {

    public static void execute(Integer nr, String[] args) {

        Player p = gilde_Main.getPlayer(nr);
        if(p.hasPermission("gilde.Tag.admin")) {
            String gildeTeam = args[1];
            if(ToolBox.checkGildeExists(gildeTeam)) {
                if(Main.getSaves().get("Tags." + "GildeToTag." +  gildeTeam)== null) {
                    ToolBox.DelTag(ToolBox.getTagbyGilde(gildeTeam));
                    ToolBox.DelGildeToTag(gildeTeam);
                    p.sendMessage("§aDer Gilden-Tag wurde erfolgreich gelöscht!");
                }else {
                    p.sendMessage("§cDiese Gilde hat keinen Tag!");
                }
            }else {
                p.sendMessage("§cDie von dir gewählte Gilde existiert nicht!");
            }
        }else  {
            p.sendMessage("§cDazu fehlend dir die Berechtigungen!");
        }
    }
}
