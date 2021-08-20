package de.Initium.Gilden.Listener;

import de.Initium.Gilden.Main.ToolBox;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.ArrayList;

public class InventoryClick implements Listener {
    public static ArrayList<Player> manually = new ArrayList<>();

    @EventHandler
    public static void onClick(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();
        if(e.getView().getTitle().equals("Leiter Auswahl")) {
            if(e.getClickedInventory() != null) {
                if(e.getCurrentItem() != null) {
                    switch (e.getCurrentItem().getType()) {
                        case SKELETON_SKULL:
                            OfflinePlayer t = Bukkit.getOfflinePlayer(e.getCurrentItem().getItemMeta().getDisplayName());
                            ToolBox.removePlayerfromGilde(t.getUniqueId().toString(), ToolBox.getGildeNameOfPlayer(p));
                            ToolBox.addPlayertoGilde(t.getUniqueId().toString(), ToolBox.getGildeNameOfPlayer(p), "Leiter");
                            ToolBox.removePlayerfromGilde(p.getUniqueId().toString(), ToolBox.getGildeNameOfPlayer(p));
                            p.sendMessage("Du hast deine Gilde erfolgreich verlassen!");
                            manually.add(p.getPlayer());
                            p.closeInventory();
                            break;
                        case PLAYER_HEAD:
                            Player online = Bukkit.getPlayer(e.getCurrentItem().getItemMeta().getDisplayName());
                            ToolBox.removePlayerfromGilde(online.getUniqueId().toString(), ToolBox.getGildeNameOfPlayer(p));
                            ToolBox.addPlayertoGilde(online.getUniqueId().toString(), ToolBox.getGildeNameOfPlayer(p), "Leiter");
                            ToolBox.removePlayerfromGilde(p.getUniqueId().toString(), ToolBox.getGildeNameOfPlayer(p));
                            online.sendMessage("Dein Leiter hat die Gilde verlassen und hat dich als neuen Leiter ausgewählt!");
                            p.sendMessage("Du hast deine Gilde erfolgreich verlassen!");
                            manually.add(p.getPlayer());
                            p.closeInventory();
                            break;
                        default:
                            e.setCancelled(true);
                    }
                }
            }
        }
    }
}
