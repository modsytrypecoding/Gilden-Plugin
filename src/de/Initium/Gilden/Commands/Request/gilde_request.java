package de.Initium.Gilden.Commands.Request;

import de.Initium.Gilden.Commands.gilde_Main;
import de.Initium.Gilden.Main.ToolBox;
import de.Initium.Gilden.NPCs.Main.Creation.InventoryInteraction;

import de.Initium.Gilden.NPCs.Main.InventoryDispatcher;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;


public class gilde_request extends JavaPlugin {

    public static void execute(Integer nr) {
        Player p = gilde_Main.getPlayer(nr);
        if(ToolBox.getallPlayers().contains(p.getUniqueId().toString())) {
            String gilde = ToolBox.getGildeNameOfPlayer(p);
            if(ToolBox.getGildeRankByPlayer(gilde, p.getUniqueId().toString()).equalsIgnoreCase("Leiter") || ToolBox.getGildeRankByPlayer(gilde, p.getUniqueId().toString()).equalsIgnoreCase("Stellvertreter")) {
                InventoryDispatcher.getInInventory().put(p, true);
                InventoryDispatcher.getActivePlayers().add(p);
                InventoryInteraction.BookClick(p, gilde);
            }else {
                p.sendMessage("§cDu kannst diesen Befehl nicht nutzen!");
            }
        }else {
            p.sendMessage("§cDu kannst diesen Befehl nicht nutzen!");
        }

    }
}
