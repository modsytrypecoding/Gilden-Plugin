package de.Initium.Gilden.NPCs.Main.Creation;

import de.Initium.Gilden.Commands.gilde_Main;
import de.Initium.Gilden.Main.ToolBox;
import de.Initium.Gilden.NPCs.Main.InventoryDispatcher;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class CreationResponse extends JavaPlugin
{
    public static void execute(Integer nr, String[] args)
    {
        Player pl = gilde_Main.getPlayer(nr);
        String arg_0 = args[0];
        String arg_1 = args[1];

        String playername = "";
        String gildenname = "";
        try
        {
            String[] parts = arg_1.split("_");
            playername = parts[0];
            gildenname = parts[1];
        }
        catch(ArrayIndexOutOfBoundsException e)
        {
            pl.sendMessage("TESTNACHTICHT. Command nicht gefunden");
            return;
        }

        if(!(InventoryInteraction.getAwaitingConfirmation().containsKey(pl))
            || !(pl.getName().equals(playername)))
        {
            pl.sendMessage("TESTNACHTICHT2. Command nicht gefunden");
            return;
        }

        if(!(InventoryInteraction.getAwaitingConfirmation().get(pl).equals(gildenname)))
        {
            pl.sendMessage("TESTNACHTICHT3. Command nicht gefunden");
            return;
        }

        if(arg_0.equals("create-confirm"))
        {
            ToolBox.createGilde(gildenname, pl.getUniqueId().toString(), pl.getPlayer());
            pl.sendMessage("TESTNACHRICHT. Erfolgreich Erstellt");
            Bukkit.getServer().broadcastMessage("TESTNACHRICHT. Die Gilde " + gildenname + " wurde erfolgreich von " + pl.getName() + " gegründet");
        }
        else
        {
            pl.sendMessage(ChatColor.GREEN + "Du hast das Erstellen deiner Gilde erfolgreich abgebrochen");
        }
        cleanup(pl);
    }

    public static void cleanup(Player pl)
    {
        InventoryInteraction.getAwaitingNewGildename().remove(pl);
        InventoryInteraction.getAwaitingConfirmation().remove(pl);
        InventoryDispatcher.getActivePlayers().remove(pl);
    }
}