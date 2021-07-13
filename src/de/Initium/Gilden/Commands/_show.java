  
package de.Initium.Gilden.Commands;

import de.Initium.Gilden.Main.Main;
import de.Initium.Gilden.Main.ToolBox;
import de.Initium.Gilden.Main.UUIDManipulation;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public class _show extends JavaPlugin
{
    public static void execute(Integer nr)
    {
        Player pl = gilde_Main.getPlayer(nr);
        if(!(ToolBox.getallPlayers().contains(pl.getUniqueId().toString())))
        {
            pl.sendMessage("Du kannst dir deine eigene Gilde nicht anzeigen, da du in keiner bist");
            return;
        }

        execute(nr, ToolBox.getGildeNameOfPlayer(pl));
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

        ArrayList<String> UUIDs = ToolBox.getallPlayersinGilde(gilde_name);
        String MSG = "Gilde " + gilde_name + ":";
        for(String playername : UUIDManipulation.getPlayernameByUUID(UUIDs))
        {
            MSG += "\n- " + playername;
        }
        pl.sendMessage(MSG);

        /*
        *
        Object GildeInformations = ToolBox.getGildeInformationsByName(ToolBox.getGildeNameOfPlayer(pl));
        //ToDo: ToolBox.getGildeOfGildeName()
        //ToDo: Format GildeInformations
        *
        * */
    }

    public static void set(Integer nr, String arg)
    {
        Player pl = gilde_Main.getPlayer(nr);
        pl.sendMessage("" + ToolBox.checkGildeExists(arg));
    }
}