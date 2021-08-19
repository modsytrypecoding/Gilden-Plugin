package de.Initium.Gilden.NPCs.Listener;

import de.Initium.Gilden.NPCs.Main.InventoryDispatcher;
import de.Initium.Gilden.NPCs.Main.Creation.InventoryInteraction;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class Bukkit_InteractInventory implements Listener
{
    @EventHandler
    public static void onInteract(InventoryClickEvent e)
    {
        Player pl = (Player) e.getWhoClicked();
        if(e.getCurrentItem() != null &&
                InventoryDispatcher.getActivePlayers().contains(pl) &&
                    InventoryDispatcher.getInInventory().containsKey(pl))
        {
            e.setCancelled(true);
            if(e.getClickedInventory().getSize() == 9*3)
                InventoryInteraction.clickedItemDecision(e);
        }
    }
}