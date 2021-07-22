package de.Initium.Gilden.Commands.Tag;

import de.Initium.Gilden.Commands.gilde_Main;
import de.Initium.Gilden.Main.Main;
import de.Initium.Gilden.Main.ToolBox;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class gilde_delTag extends JavaPlugin {

    public static void execute(Integer nr) {
        Player p = gilde_Main.getPlayer(nr);
        String gilde = ToolBox.getGildeNameOfPlayer(p);

        if(ToolBox.getallPlayers().contains(p.getUniqueId().toString())) {
            if(ToolBox.getGildeRankByPlayer(gilde, p.getUniqueId().toString()).equalsIgnoreCase("Leiter")) {
                if(Main.getSaves().get("Tags." + "GildeToTag." +  gilde) == null) {
                    p.sendMessage("§cDeine Gilde hat noch kein Tag!");
                }else {
                    if(ToolBox.checkTagExists(ToolBox.getTagbyGilde(gilde))) {
                        ToolBox.DelTag(ToolBox.getTagbyGilde(gilde));
                        ToolBox.DelGildeToTag(gilde);
                        p.sendMessage("§aDer Gilden-Tag wurde erfolgreich gelöscht!");

                    }else {
                        p.sendMessage("§cDeine Gilde hat noch kein Tag!");
                    }
                }

            }
        }else {
            p.sendMessage("§cDu kannst diesen Befehl nicht benutzen!");
        }



    }

}
