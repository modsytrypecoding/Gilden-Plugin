package de.Initium.Gilden.Commands;

import de.Initium.Gilden.Main.ToolBox;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class gilde_testforToolBox extends JavaPlugin {

    public static void execute(Integer nr, String[] args) {
        Player p = gilde_Main.getPlayer(nr);
        Player t = Bukkit.getPlayer(args[1]);

        gilde_kick.execute(args[1], p.getUniqueId().toString());
    }
}
