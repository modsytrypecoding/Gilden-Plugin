package de.Initium.Gilden.NPCs.Listener;

import de.Initium.Gilden.Main.Main;
import de.Initium.Gilden.NPCs.Main.InventoryDispatcher;
import net.citizensnpcs.api.event.NPCRightClickEvent;
import net.citizensnpcs.api.npc.NPC;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class NPC_RightClick implements Listener
{
    static NPC clickedNPC;

    @EventHandler
    public static void onRightClick(NPCRightClickEvent e)
    {
        clickedNPC = e.getNPC();
        Player pl = e.getClicker();

        String expected_npc_name = Main.getConfiguration().getString("settings.NPC.NPC_NAME");
        if(!clickedNPC.getName().equals(expected_npc_name)) return;

        InventoryDispatcher.caseDecision(pl, clickedNPC);
    }
}
