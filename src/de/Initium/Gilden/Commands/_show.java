package de.Initium.Gilden.Commands;

import de.Initium.Gilden.Main.Main;
import de.Initium.Gilden.Main.ToolBox;
import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class _show extends JavaPlugin
{
    public static void execute(Integer nr)
    {
        //Check if Player is in any Gilde
        Player pl = gilde_Main.getPlayer(nr);

        ArrayList<String> gilden = new ArrayList<>();
        for(String key : Main.getSaves().getConfigurationSection("gilden").getKeys(true))
        {
            if(!(key.contains("players")))
            {
                Main.getSaves().get("gilden." + key + ".players");
            }
        }




        //Zeige eigene Gilde an
    }

    public static void execute(Integer nr, String gilde_name)
    {
        Player pl = gilde_Main.getPlayer(nr);

        ConfigurationSection gilden_sec = Main.getSaves().getConfigurationSection("gilden." + gilde_name);
        if(gilden_sec == null)
        {
            pl.sendMessage("Die Gilde " + gilde_name + " existiert nicht");
            return;
        }
        List<String> UUIDs = Main.getSaves().getStringList("gilden." + gilde_name + ".players");
        pl.sendMessage("Gilde " + gilde_name + ":\n" + ToolBox.parseAllUUIDsToPlayerNames(UUIDs));
    }

    public static void set(Integer nr, String uuid)
    {
        Player pl = gilde_Main.getPlayer(nr);
        pl.sendMessage(ToolBox.getallPlayers(pl).toString());
    }
}
