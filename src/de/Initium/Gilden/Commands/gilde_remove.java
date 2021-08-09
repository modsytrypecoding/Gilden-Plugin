package de.Initium.Gilden.Commands;

import de.Initium.Gilden.Main.Main;
import de.Initium.Gilden.Main.ToolBox;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class gilde_remove extends JavaPlugin {

    public static void execute(Integer nr, String[] args ) {
        Player p = gilde_Main.getPlayer(nr);
        if(p.hasPermission("gilde.remove.admin")) {
            if(ToolBox.checkGildeExists(args[1])) {
                if(Main.getSaves().get("Tags.GildeToTag." + args[1]) == null) {
                    ToolBox.DelGilde(args[1]);
                }else {
                    ToolBox.DelTag(ToolBox.getTagbyGilde(args[1]));
                    ToolBox.DelGildeToTag(args[1]);
                    ToolBox.DelGilde(args[1]);
                }


                p.sendMessage("§aDu hast die Gilde erfolgreich entfernt!");
            }else {
                p.sendMessage("§cDiese Gilde existiert nicht!");
            }

        }
    }
}
