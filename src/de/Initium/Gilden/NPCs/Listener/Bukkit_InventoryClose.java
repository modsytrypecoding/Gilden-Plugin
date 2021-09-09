package de.Initium.Gilden.NPCs.Listener;

import de.Initium.Gilden.NPCs.Main.InventoryDispatcher;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;

import java.util.ArrayList;
import java.util.HashMap;

public class Bukkit_InventoryClose implements Listener {
    public static HashMap<Player, Inventory> lastInv = new HashMap<>();


    @EventHandler
    public static void onInvClose(InventoryCloseEvent e) {
        Player pl = (Player) e.getPlayer();
        ArrayList<Player> active = InventoryDispatcher.getActivePlayers();
        HashMap<Player, Boolean> inInv = InventoryDispatcher.getInInventory();

        if(active.contains(pl) && inInv.containsKey(pl) && !e.getView().getTitle().equals("§bGilden"))
        {
            active.remove(pl);
            inInv.remove(pl);
        }
        lastInv.put(pl, e.getInventory());

    }
}
