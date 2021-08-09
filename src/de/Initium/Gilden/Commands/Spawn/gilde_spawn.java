package de.Initium.Gilden.Commands.Spawn;

import de.Initium.Gilden.Commands.gilde_Main;
import de.Initium.Gilden.Main.Main;
import de.Initium.Gilden.Main.ToolBox;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.text.DecimalFormat;


public class gilde_spawn extends JavaPlugin {

    public static void execute(Integer nr) {
        Player p = gilde_Main.getPlayer(nr);

        if(ToolBox.getallPlayers().contains(p.getUniqueId().toString())) {
            String gilde = ToolBox.getGildeNameOfPlayer(p);
            if(Main.getSaves().get("gilden." + gilde + ".Information." + "hasSetSpawn").equals(true)) {

                Double x = Double.parseDouble(Main.getSaves().get("gilden." + gilde + ".Information." + "SpawnLocation." + "Spawn.X").toString());
                Double y = Double.parseDouble(Main.getSaves().get("gilden." + gilde + ".Information." + "SpawnLocation." + "Spawn.Y").toString());
                Double z = Double.parseDouble(Main.getSaves().get("gilden." + gilde + ".Information." + "SpawnLocation." + "Spawn.Z").toString());

                if(ToolBox.getGildenSpawn(gilde).getBlock().getType().isTransparent()) {
                    p.teleport(ToolBox.getGildenSpawn(gilde));
                    p.sendMessage("§aDu wurdest zum Gilden-Spawn teleportiert!");
                }else {
                    p.sendMessage("§cDer Gilden-Spawn ist besetzt.\nDu kannst nicht teleportiert werden!");
                    p.sendMessage("Der Spawn befindet sich bei folgenden Koordinate:");
                    DecimalFormat f = new DecimalFormat();
                    f.setMaximumFractionDigits(2);
                    p.sendMessage("§6X§r: " + f.format(x));
                    p.sendMessage("§6Y§r: " + f.format(y));
                    p.sendMessage("§6Z§r: " + f.format(z));


                }
            }else {
                p.sendMessage("§cDeine Gilde hat noch keinen Gilden-Spawn gesetzt!");
            }
        }else {
            p.sendMessage("§cDu kannst diesen Befehl nicht benutzen, da du in keiner Gilde bist!");
        }
    }
}
