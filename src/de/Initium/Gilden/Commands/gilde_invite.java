package de.Initium.Gilden.Commands;

import de.Initium.Gilden.Main.Timer;
import de.Initium.Gilden.Main.ToolBox;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.hover.content.Text;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class gilde_invite extends JavaPlugin
{
    static ArrayList<Player> openResponses = new ArrayList<>();

    public static void execute(Integer nr, String target_name)
    {
        Player pl = gilde_Main.getPlayer(nr);

        if(Timer.getGildenTimer().containsKey(pl))
        {
            Object restzeit = Timer.getGildenTimer().get(pl);
            pl.sendMessage("Du kannst erst wieder in " + restzeit + " Sekunden eine weitere Anfragen schicken");
        }

        if(pl.getName().equals(target_name))
        {
            pl.sendMessage("Du kannst dich nicht selber inviten");
            return;
        }

        boolean targetonline = false;
        Collection<? extends Player> onlinePlayers = Bukkit.getOnlinePlayers();
        for(Player temp : onlinePlayers)
        {
            if(!(temp.getName().equals(target_name))) continue;
            targetonline = true;
        }
        if(!targetonline)
        {
            pl.sendMessage("Der angegebene Spieler ist nicht online");
            return;
        }

        Player target = Bukkit.getPlayerExact(target_name);
        if(ToolBox.getallPlayers().contains(target.getUniqueId().toString()))
        {
            pl.sendMessage("Der Spieler " + target.getName() + " ist bereits in einer Gilde");
            return;
        }

        Timer.setTimer(pl, target);

        String gildename_inv = ToolBox.getGildeNameOfPlayer(pl);

        TextComponent c = new TextComponent("Annehmen!");
        c.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/gilde accept " + gildename_inv));
        c.setUnderlined(true);
        c.setColor(ChatColor.GREEN);
        c.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new Text("Du nimmst diese Einladung an")));

        TextComponent c2 = new TextComponent("Ablehnen!");
        c2.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/gilde deny " + gildename_inv));
        c2.setUnderlined(true);
        c2.setColor(ChatColor.RED);
        c2.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new Text("Du lehnst diese Einladung ab")));

        String Target_MSG =
                "§2Du wurdest von §6§l" + pl.getName() + "§r§2 in die Gilde §6§l" + gildename_inv + "§r§2 eingeladen.\n\n" +
                        "§2Achtung! Deine persönliche Insel wird beim übernächsten Serverrestart (um 5 Uhr) aufgegeben.\n" +
                        "Sichere bis dahin deine Items, damit es nicht zu Verlusten kommt.\n" +
                        "Du hast §4§n90 §r§2Sekunden um diese Einladung anzunehmen. Danach wird sie automatisch §4§nabgelehnt\n" +
                        "§r§2Du kannst entweder per Click auf die folgenden Button annehmen, oder per /gilde accept|deny " + gildename_inv;
        target.sendMessage(Target_MSG);
        target.spigot().sendMessage(c);
        target.spigot().sendMessage(c2);
        pl.sendMessage("Du hast " + target.getName() + " in deine Gilde eingeladen");

        Timer.getGilde_invitation_Mapping().put(gildename_inv, pl);
        openResponses.add(target);
    }

    public static ArrayList<Player> getOpenResponses()
    {
        return openResponses;
    }
}