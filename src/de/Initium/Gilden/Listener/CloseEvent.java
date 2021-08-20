package de.Initium.Gilden.Listener;

import de.Initium.Gilden.Commands.gilde_leave;
import de.Initium.Gilden.Main.Main;
import de.Initium.Gilden.Main.ToolBox;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;

import javax.tools.Tool;

public class CloseEvent implements Listener {

    @EventHandler
    public void onClose(InventoryCloseEvent e) {
        if(e.getView().getTitle().equals("Leiter Auswahl")) {
            Player p = (Player) e.getPlayer();
            if(InventoryClick.manually.contains(p.getPlayer())) {

            }else {
                p.sendMessage("Bist du keinen neuen Leiter ausgewählt hast, kannst du deine Gilde nicht verlassen!");
            }

        }

        if(e.getView().getTitle().equalsIgnoreCase("Item-Auswahl")) {
            if(e.getInventory().getItem(4) != null) {
                if(!(e.getInventory().getItem(4).getType().equals(Material.AIR))) {
                    Main.getSaves().set("gilden." + ToolBox.getGildeNameOfPlayer((Player) e.getPlayer()) + ".Information.GuiBlock", e.getInventory().getItem(4));
                    Main.saveSaves();
                }
            }

        }
    }
}
