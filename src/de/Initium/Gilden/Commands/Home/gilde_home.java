package de.Initium.Gilden.Commands.Home;

import de.Initium.Gilden.Commands.gilde_Main;
import de.Initium.Gilden.Main.Main;
import de.Initium.Gilden.Main.ToolBox;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.text.DecimalFormat;

public class gilde_home extends JavaPlugin {

    public static void execute(Integer nr) {
        Player p = gilde_Main.getPlayer(nr);


        if(ToolBox.getallPlayers().contains(p.getUniqueId().toString())) {
            String gilde = ToolBox.getGildeNameOfPlayer(p);
            if(Main.getSaves().get("gilden." + gilde + ".Information." + "hasSetHome").equals(true)) {

                Double x = Double.parseDouble(Main.getSaves().get("gilden." + gilde + ".Information." + "SpawnLocation." + "Spawn.X").toString());
                Double y = Double.parseDouble(Main.getSaves().get("gilden." + gilde + ".Information." + "SpawnLocation." + "Spawn.Y").toString());
                Double z = Double.parseDouble(Main.getSaves().get("gilden." + gilde + ".Information." + "SpawnLocation." + "Spawn.Z").toString());

                if(ToolBox.getGildenHome(gilde).getBlock().getType().equals(Material.AIR)) {
                    p.teleport(ToolBox.getGildenHome(gilde));
                    p.sendMessage("§aDu hast dich zu deinem Gilden Home teleportiert!");
                }else {
                    p.sendMessage("§cDer Gilden-Home ist besetzt.\nDu kannst nicht teleportiert werden!");
                    p.sendMessage("Der Home befindet sich in der Welt §6" + Main.getSaves().get("gilden." + gilde + ".Information." + "HomeLocation." +"Home.World"));
                    p.sendMessage("Der Home befindet sich bei folgenden Koordinate:");
                    DecimalFormat f = new DecimalFormat();
                    f.setMaximumFractionDigits(2);
                    p.sendMessage("§6X§r: " + f.format(x));
                    p.sendMessage("§6Y§r: " + f.format(y));
                    p.sendMessage("§6Z§r: " + f.format(z));
                }


            }else {
                p.sendMessage("§cDeine Gilde hat keinen Home-Punkt gesetzt!");
            }
        }else {
            p.sendMessage("Du kannst diesen Befehl nicht ausführen, da du dich in keiner Gilde befindest!");
        }

    }

}
