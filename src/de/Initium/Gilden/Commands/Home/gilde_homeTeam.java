package de.Initium.Gilden.Commands.Home;

import de.Initium.Gilden.Commands.gilde_Main;
import de.Initium.Gilden.Main.Main;
import de.Initium.Gilden.Main.ToolBox;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class gilde_homeTeam extends JavaPlugin {

    public static void execute(Integer nr, String[] args) {
        Player p = gilde_Main.getPlayer(nr);
        if(p.hasPermission("gilde.Home.admin")) {
            String gildeTeam = args[1];

            //returns != null
            if(ToolBox.checkGildeExists(args[1])) {

                // check if Home already exists
                if(Main.getSaves().get("gilden." + gildeTeam + ".Information." + "hasSetHome").equals(true)) {
                    if(ToolBox.getGildenHome(gildeTeam).getBlock().getType().equals(Material.AIR)) {
                        p.teleport(ToolBox.getGildenHome(gildeTeam));
                    }else {

                        //check Gamemode
                        if(p.getGameMode().equals(GameMode.SPECTATOR) || p.getGameMode().equals(GameMode.CREATIVE)) {
                            p.teleport(ToolBox.getGildenHome(gildeTeam));
                            p.sendMessage("§aDu wurdest erfolgreich teleportiert!");
                        }else {
                            p.sendMessage("§cDu kannst dich nicht zu diesem Home teleportieren!");
                            p.sendMessage("Der Home Punkt scheint besetzt zu sein");
                            p.sendMessage("Begebe dich in Creative oder Spectator Mode\num dich trotzdem zu teleportieren!");
                        }
                    }
                }else {
                    p.sendMessage("§cKein Gilden Home Punkt vorhanden!");
                }
            }else {
                p.sendMessage("§cDie von dir gewählte Gilde existiert nicht!");
            }
        }else {
            p.sendMessage("§cDir fehlen dir Berechtigungen!");
        }
    }
        }
