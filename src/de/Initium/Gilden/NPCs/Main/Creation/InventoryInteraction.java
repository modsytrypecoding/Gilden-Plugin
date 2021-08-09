package de.Initium.Gilden.NPCs.Main.Creation;

import de.Initium.Gilden.Commands.Home.gilde_SetHome;
import de.Initium.Gilden.Commands.gilde_Main;
import de.Initium.Gilden.Commands.gilde_leave;
import de.Initium.Gilden.Main.Main;
import de.Initium.Gilden.Main.ToolBox;
import de.Initium.Gilden.NPCs.Main.InventoryDispatcher;
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
                break;
            case NAME_TAG:
                Player p = (Player) e.getWhoClicked();
                TagClick(p, ToolBox.getGildeNameOfPlayer(p));
                CreationResponse.cleanup(p);
                break;
            case GRASS_BLOCK:
                Player p1 = (Player) e.getWhoClicked();
                GrassClick(p1, ToolBox.getGildeNameOfPlayer(p1));
                CreationResponse.cleanup(p1);
                break;
            case BARRIER:
                Player p2 = (Player) e.getWhoClicked();
                p2.closeInventory();
                CreationResponse.cleanup(p2);
                break;
            case IRON_DOOR:
                Player p3 = (Player) e.getWhoClicked();
                ToolBox.removePlayerfromGilde(p3.getUniqueId().toString(), ToolBox.getGildeNameOfPlayer(p3));
                p3.sendMessage("§aDu hast die Gilde erfolgreich verlassen");
                CreationResponse.cleanup(p3);
                p3.closeInventory();
                break;

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
    public static void TagClick(Player pl, String gildenname) {
        Integer Cost = Integer.parseInt(Main.getConfiguration().get("settings.Gilden.TagCost").toString());
        Double Purse = Double.parseDouble(Main.getSaves().get("gilden." + gildenname + ".Information.Bank-Wert").toString());

        if(Main.getSaves().getBoolean("gilden." + gildenname + ".Information.hasBoughtTag")) {
            pl.sendMessage("§cDeine Gilde hat bereits Tag-Rechte!");
            pl.closeInventory();
        }else {
            if(ToolBox.getPlayerNumber(gildenname) >= Integer.parseInt(Main.getConfiguration().get("settings.Gilden.MinPlayersForTag").toString())) {
                if(Purse >= Cost) {
                    Double newPurse = Purse - Cost;
                    Main.getSaves().set("gilden." + gildenname + ".Information.Bank-Wert", newPurse);
                    Main.saveSaves();
                    pl.closeInventory();
                    pl.sendMessage("Du kannst jetzt einen Tag für deine Gilde setzen!");
                    Main.getSaves().set("gilden." + gildenname + ".Information." + "hasBoughtTag", true);
                    Main.saveSaves();
                }else {
                    pl.sendMessage("§cDer Gilden-Wert deiner Gilde ist zu niedrig!\nPreis: " + Cost + " Kronen\nGilden-Wert: " + Purse + " Kronen");
                    pl.closeInventory();
                }
            }else {
                pl.sendMessage("Deine Gilde hat zu wenig Mitglieder!\nBenötigte Mitgliederanzahl: "  + Main.getConfiguration().get("settings.Gilden.MinPlayersForTag").toString() + "\nMomentane Mitgliederanzahl: " + ToolBox.getPlayerNumber(gildenname));
                pl.closeInventory();
            }
        }
    }

    public static void GrassClick(Player pl, String gildenname) {
        Integer Cost = Integer.parseInt(Main.getConfiguration().get("settings.Gilden.HomePunktCost").toString());
        Double Purse = Double.parseDouble(Main.getSaves().get("gilden." + gildenname + ".Information.Bank-Wert").toString());
        if(Main.getSaves().getBoolean("gilden." + gildenname + ".Information.hasBoughtHome")) {
            pl.sendMessage("§cDeine Gilde hat bereits Home-Rechte!");
            pl.closeInventory();
        }else {
            if(Purse >= Cost) {
                Double newPurse = Purse - Cost;
                Main.getSaves().set("gilden." + gildenname + ".Information.Bank-Wert", newPurse);
                Main.saveSaves();
                pl.closeInventory();
                pl.sendMessage("Du kannst jetzt einen Home-Punkt für deine Gilde setzten!");
                Main.getSaves().set("gilden." + gildenname + ".Information." + "hasBoughtHome", true);
                Main.saveSaves();
            }else {
                pl.sendMessage("§cDer Gilden-Wert deiner Gilde ist zu niedrig!\nPreis: " + Cost + " Kronen\nGilden-Wert: " + Purse + " Kronen");
            }
        }
    }



    public static void diamondClicked(Player pl, String MSG)
    {
        //"".equalsIgnoreCase("");
        if(ToolBox.validateGildeName(MSG))
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