package de.Initium.Gilden.Commands;

import de.Initium.Gilden.Main.Main;
import de.Initium.Gilden.Main.ToolBox;
import de.Initium.Gilden.Main.UUIDManipulation;
import de.malteee.inselshopsystem.core.KleineInselnPlugin;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;

public class _show extends JavaPlugin
{
    public static void execute(Integer nr)
    {
        Player pl = gilde_Main.getPlayer(nr);
        ArrayList<String> allPlayers = ToolBox.getallPlayers();
        if(!(allPlayers).contains(pl.getUniqueId().toString()))
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
                ArrayList<String> temp = UUIDManipulation.getPlayernameByUUID__LIST(UUIDs.get(i));
                for(String playername : temp)
                {
                    if(i==0 && temp.indexOf(playername) == 0) MSG += "\nLeiter:";
                    else if(i==1 && temp.indexOf(playername) == 0) MSG += "\nStellvertreter:";
                    else if(i==2 && temp.indexOf(playername) == 0) MSG += "\nMitglieder:";
                    MSG += "\n- " + playername;
                }

            }
            if(ToolBox.hasInsel().contains(gilde_name)) {
                pl.sendMessage(MSG + "\nGildenWert: " + ToolBox.getBankValue(gilde_name) + " Kronen" + "\nInsel-ID: " + ToolBox.getGildenInselID(gilde_name));
            }else {
                pl.sendMessage(MSG + "\nGildenWert: " + ToolBox.getBankValue(gilde_name) + " Kronen");
            }
        }else {
            String MSG = "Gilde " + gilde_name + " (" + ToolBox.getTagbyGilde(gilde_name) + ")" + ":";
            for(int i = 0; i < 3; i++)
            {
                ArrayList<String> temp = UUIDManipulation.getPlayernameByUUID__LIST(UUIDs.get(i));
                for(String playername : temp)
                {
                    if(i==0 && temp.indexOf(playername) == 0) MSG += "\nLeiter:";
                    else if(i==1 && temp.indexOf(playername) == 0) MSG += "\nStellvertreter:";
                    else if(i == 2 && temp.indexOf(playername) == 0) MSG += "\nMitglieder:";
                    MSG += "\n- " + playername;
                }
            }
            if(ToolBox.hasInsel().contains(gilde_name)) {
                pl.sendMessage(MSG + "\nGildenWert: " + ToolBox.getBankValue(gilde_name) + " Kronen" + "\nInsel-ID: " + ToolBox.getGildenInselID(gilde_name));
            }else {
                pl.sendMessage(MSG + "\nGildenWert: " + ToolBox.getBankValue(gilde_name) + " Kronen");
            }

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