package de.Initium.Gilden.Commands;

import de.Initium.Gilden.Main.ToolBox;
import de.Initium.Gilden.Main.UUIDManipulation;
import de.doppelkool.playerLogger.API.MainAPI;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.hover.content.Text;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class gilde_check extends JavaPlugin
{
    public static void execute(Integer nr, String arg)
    {
        Player pl = gilde_Main.getPlayer(nr);

        //Get Storage of PlayerLogger to check every Playername equalsIgnoreCase arg
        // to find the original Playername if arg is Case-False
        MainAPI.PlayerLogger playerLogger = new MainAPI.PlayerLogger();
        HashMap<String, String> hm = playerLogger.getPlayernameToUUIDStorage();

        String orig_playername = "";
        String target_UUID = "";
        for(Map.Entry<String, String> entry : hm.entrySet())
        {
            if(entry.getKey().equalsIgnoreCase(arg))
            {
                orig_playername = entry.getKey();
                target_UUID = entry.getValue();
                break;
            }
        }

        boolean inAllPlayers_FOUND = ToolBox.getallPlayers().contains(target_UUID);
        if(target_UUID.equals("") || orig_playername.equals("") || !inAllPlayers_FOUND)
        {
            pl.sendMessage("§cDer Spieler " + arg + " ist kein Mitglied einer Gilde");
            return;
        }

        String gildenname = ToolBox.getGildeNameOfPlayer(target_UUID);
        String rang = ToolBox.getGildeRankByPlayer(gildenname, target_UUID);

        TextComponent message = new TextComponent("§aDer Spieler " + orig_playername + " ist " + rang + " der Gilde §7'§b" + gildenname +
                "§7'\n§aLass dir die Gilde anzeigen ->");
        TextComponent Klick = new TextComponent(" §bKlick!");
        Klick.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/gilde " + gildenname));
        Klick.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new Text("§aSuche nach Gilde " + gildenname)));

        message.addExtra(Klick);
        pl.spigot().sendMessage(message);
    }
}
