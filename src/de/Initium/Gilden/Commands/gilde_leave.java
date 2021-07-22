package de.Initium.Gilden.Commands;

import de.Initium.Gilden.Main.ToolBox;
import de.Initium.Gilden.Main.UUIDManipulation;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;

public class gilde_leave extends JavaPlugin {

    public static void execute(Integer nr) {
        Player p = gilde_Main.getPlayer(nr);
        String gilde = ToolBox.getGildeNameOfPlayer(p);
        if (ToolBox.getallPlayers().contains(p.getUniqueId().toString())) {
                ArrayList<String> playersofGilde = ToolBox.unserializeArrayList(ToolBox.getallPlayersinGilde(gilde));
                for (String all : playersofGilde) {
                    if (!(p.getUniqueId().toString().equals(all))) {
                        String temp = UUIDManipulation.getOnlinePlayerByUUID(all);
                        if (!(temp.equals(""))) {
                            (Bukkit.getPlayerExact(temp)).sendMessage("[§a" + ToolBox.getGildeNameOfPlayer(Bukkit.getPlayerExact(p.getName())) + "§r] §rDer Spieler §6" + p.getName() + " §rhat die Gilde verlassen!");
                        }
                    }
                }
                ToolBox.removePlayerfromGilde(p.getUniqueId().toString(), gilde);
                p.sendMessage("§aDu hast die Gilde erfolgreich verlassen.");
        }else {
            p.sendMessage("§cDu kannst diesen Befehl nicht aus führen, da du dich in keiner Gilde befindest!");
        }

    }
}
