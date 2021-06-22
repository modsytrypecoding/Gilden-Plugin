package de.Initium.Gilden.Commands;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class invite extends JavaPlugin
{
    public static void execute(Integer nr, String target_name)
    {
        Player pl = gilde_Main.getPlayer(nr);

        if(pl.getName().equals(target_name))
        {
            pl.sendMessage("Du kannst dich nicht selber inviten");
            //return;
        }

        Player target = Bukkit.getPlayerExact(target_name);
        if(!(Bukkit.getOnlinePlayers().contains(target)))
        {
            pl.sendMessage("Der angegebene Spieler ist nicht online");
            return;
        }

        target.sendMessage("Du wurdest von " + pl.getName() + " in eine Gilde eingeladen");
    }
}
