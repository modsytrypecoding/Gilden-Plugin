package de.Initium.Gilden.NPCs.Main.Creation;

import de.Initium.Gilden.Main.ToolBox;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.hover.content.Text;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.HashMap;

public class InventoryInteraction extends JavaPlugin
{
    static ArrayList<Player> awaitingNewGildename = new ArrayList<>();
    static HashMap<Player, String> awaitingConfirmation = new HashMap<>();

    public static void clickedItemDecision(InventoryClickEvent e)
    {
        switch (e.getCurrentItem().getType())
        {
            case DIAMOND:
                diamondClicked((Player) e.getWhoClicked());
            default:
                return;
        }
    }

    //Gilde Erstellen
    public static void diamondClicked(Player pl)
    {
        pl.closeInventory();
        pl.sendMessage("Gebe den Namen deiner neuen Gilde ein:");
        awaitingNewGildename.add(pl);
    }

    public static void diamondClicked(Player pl, String MSG)
    {
        //"".equalsIgnoreCase("");
        if(!ToolBox.validateGildeName(MSG))
        {
            pl.sendMessage(
                    "Der Gildenname \"" + MSG + "\" ist ungültig." +
                            "Er muss folgende Kriterien erfüllen:\n" +
                            "- Länge: Mindestens 3 Buchstaben\n" +
                            "- Nur folgender Character dürfen enthalten sein: [A-Z], [a-z]");
            pl.sendMessage(ChatColor.RED + "Versuche es erneut");
            return;
        }

        String gildenname = MSG;
        if(ToolBox.checkGildeExists(gildenname))
        {
            pl.sendMessage("Die Gilde " + gildenname + " existiert bereits");
            pl.sendMessage(ChatColor.RED + "Versuche es erneut");
            return;
        }

        awaitingNewGildename.remove(pl);
        awaitingConfirmation.put(pl, gildenname);

        TextComponent tc = new TextComponent();
        tc.setText(ChatColor.GREEN + "BESTÄTIGEN");
        tc.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/gilde create-confirm " + pl.getName() + "_" + gildenname));
        tc.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new Text("Bestätige den eingegeben Gildennamen")));
        TextComponent tc2 = new TextComponent();
        tc2.setText(ChatColor.RED + "ABBRECHEN"); // /gilde create-quit doppelkool_TesTGilde
        tc2.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/gilde create-quit " + pl.getName() + "_" + gildenname));
        tc2.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new Text("Breche das Erstellen der Gilde ab")));

        pl.sendMessage("Möchtest du den Namen " + gildenname + " verwenden?");
        pl.spigot().sendMessage(tc);
        pl.spigot().sendMessage(tc2);
    }

    public static ArrayList<Player> getAwaitingNewGildename()
    {
        return awaitingNewGildename;
    }
    public static HashMap<Player, String> getAwaitingConfirmation()
    {
        return awaitingConfirmation;
    }
}