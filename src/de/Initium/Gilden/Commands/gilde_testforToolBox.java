package de.Initium.Gilden.Commands;

import de.Initium.Gilden.Main.ToolBox;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class gilde_testforToolBox extends JavaPlugin {

    public static void execute(Integer nr, String[] args) {
        Player p = gilde_Main.getPlayer(nr);
        Player t = Bukkit.getPlayer(args[1]);
        if(args.length == 2) {
            ToolBox.addPlayertoGilde(t.getUniqueId().toString(), ToolBox.getGildeNameOfPlayer(p),"Mitglieder");
        }



    }

    public static void execute(Integer nr) {
        Player p = gilde_Main.getPlayer(nr);
       ToolBox.removeWorldGuardPerms(p);



    }
}
