package de.Initium.Gilden.NPCs.Listener;

import de.Initium.Gilden.NPCs.Main.Creation.InventoryInteraction;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class Bukkit_ChatEvent implements Listener
{
    @EventHandler
    public static void onNewGildeChat(AsyncPlayerChatEvent e)
    {
        if(InventoryInteraction.getAwaitingNewGildename().contains(e.getPlayer())
                && !(e.getMessage().equals("")))
        {
            e.setCancelled(true);
            InventoryInteraction.diamondClicked(e.getPlayer(), e.getMessage());
        }
    }
}
