package de.Initium.Gilden.Commands;

import de.Initium.Gilden.Main.Main;
import de.Initium.Gilden.Main.ToolBox;
import de.Initium.Gilden.Main.UUIDManipulation;
import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;

public class _show extends JavaPlugin
{
    public static void execute(Integer nr)
    {
        Player pl = gilde_Main.getPlayer(nr);
        Bukkit.getConsoleSender().sendMessage("AllPlayers: " + ToolBox.getallPlayers());
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

        if(!ToolBox.checkGildeExists(gilde_name))
        {
            pl.sendMessage("Die Gilde " + gilde_name + " existiert nicht");
            return;
        }

        ArrayList<ArrayList<String>> UUIDs = ToolBox.getallPlayersinGilde(gilde_name);
        if(Main.getSaves().get("Tags." + "GildeToTag." + gilde_name) == null) {
            String MSG = "Gilde " + gilde_name + ":";
            for(int i = 0; i < 3; i++)
            {
                ArrayList<String> temp = UUIDManipulation.getPlayernameByUUID_1(UUIDs.get(i));
                for(String playername : temp)
                {
                    if(i==0 && temp.indexOf(playername) == 0) MSG += "\nLeiter:";
                    else if(i==1 && temp.indexOf(playername) == 0) MSG += "\nForsitzender:";
                    else if(temp.indexOf(playername) == 0) MSG += "\nMitglieder:";
                    MSG += "\n- " + playername;
                }
            }
            pl.sendMessage(MSG);
        }else {
            String MSG = "Gilde " + gilde_name + " (" + ToolBox.getTagbyGilde(gilde_name) + ")" + ":";
            for(int i = 0; i < 3; i++)
            {
                ArrayList<String> temp = UUIDManipulation.getPlayernameByUUID_1(UUIDs.get(i));
                for(String playername : temp)
                {
                    if(i==0 && temp.indexOf(playername) == 0) MSG += "\nLeiter:";
                    else if(i==1 && temp.indexOf(playername) == 0) MSG += "\nForsitzender:";
                    else if(temp.indexOf(playername) == 0) MSG += "\nMitglieder:";
                    MSG += "\n- " + playername;
                }
            }
            pl.sendMessage(MSG);
        }


        /*
        *
        Object GildeInformations = ToolBox.getGildeInformationsByName(ToolBox.getGildeNameOfPlayer(pl));
        //ToDo: ToolBox.getGildeOfGildeName()
        //ToDo: Format GildeInformations
        *
        * */
    }


}