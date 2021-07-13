package de.Initium.Gilden.NPCs.Commands;

import de.Initium.Gilden.Commands.gilde_Main;
import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.event.SpawnReason;
import net.citizensnpcs.api.npc.NPC;
import net.citizensnpcs.trait.SkinTrait;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class gilde_setnpc extends JavaPlugin
{
    static Player executor;
    public static void execute(Integer nr, String arg)
    {
        executor = gilde_Main.getPlayer(nr);
        if(!(executor.hasPermission("citizenstest.use")))
        {
            executor.sendMessage("Keine Perms");
            return;
        }
        if(arg.length() <= 3)
        {
            executor.sendMessage("Zu kurzer Name");
            return;
        }
        set(arg);
    }

    public static void set(String NPC_name)
    {
        NPC npc = CitizensAPI.getNPCRegistry().createNPC(EntityType.PLAYER, NPC_name);

        //Design
        npc.getOrAddTrait(SkinTrait.class).setSkinName(NPC_name, false);

        //Location
        Location l = executor.getLocation();
        SpawnReason sr = SpawnReason.CREATE;
        npc.spawn(l, sr);
    }
}
