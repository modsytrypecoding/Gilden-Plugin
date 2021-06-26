package de.Initium.Gilden.Commands;

import de.Initium.Gilden.Main.Timer;
import de.Initium.Gilden.Main.ToolBox;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Collection;

public class gilde_invite extends JavaPlugin
{
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
            //return;
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
        if(!(ToolBox.getallPlayers().contains(target.getUniqueId().toString())))
        {
            pl.sendMessage("Der Spieler " + target.getName() + " ist bereits in einer Gilde");
            return;
        }

        Timer.setTimer(pl, target);
        target.sendMessage("Du wurdest von " + pl.getName() + " in die Gilde " + ToolBox.getGildeNameOfPlayer(pl) + " eingeladen");
        pl.sendMessage("Du hast " + target.getName() + " in deine Gilde eingeladen");
    }
}
