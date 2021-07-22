package de.Initium.Gilden.Commands.Home;

import de.Initium.Gilden.Commands.gilde_Main;
import de.Initium.Gilden.Main.Main;
import de.Initium.Gilden.Main.ToolBox;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class gilde_home extends JavaPlugin {

    public static void execute(Integer nr) {
        Player p = gilde_Main.getPlayer(nr);
        String gilde = ToolBox.getGildeNameOfPlayer(p);

        if(ToolBox.getallPlayers().contains(p.getUniqueId().toString())) {
            if(Main.getSaves().get("gilden." + gilde + ".Information." + "hasSetHome").equals(true)) {

                if(ToolBox.getGildenHome(gilde).getBlock().getType().equals(Material.AIR)) {
                    p.teleport(ToolBox.getGildenHome(gilde));
                    p.sendMessage("�aDu hast dich zu deinem Gilden Home teleportiert!");
                }else {
                    p.sendMessage("Du kannst dich nicht zu deinem Home teleportieren!");
                }


            }else {
                p.sendMessage("�cDeine Gilde hat keinen Home-Punkt gesetzt!");
            }
        }else {
            p.sendMessage("Du kannst diesen Befehl nicht ausf�hren, da du dich in keiner Gilde befindest!");
        }

    }

}
