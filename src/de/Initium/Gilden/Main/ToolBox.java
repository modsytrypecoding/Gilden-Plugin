package de.Initium.Gilden.Main;

import de.Initium.Gilden.Commands.gilde_Main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ToolBox extends JavaPlugin
{
    public static ArrayList<String> parseAllUUIDsToPlayerNames(List<String> UUIDs)
    {
        ArrayList<String> playernames = new ArrayList<>();
        for(String UUID : UUIDs)
            playernames.add(Bukkit.getPlayer(UUID).getName());
        return playernames;
    }

    //ToDo:
    public static boolean checkGildeExists(String gildenname)
    {
        return Main.getSaves().getConfigurationSection("gilden." + gildenname) != null;
    }

    //ToDo:
    public static String getGildeNameOfPlayer(String playername)
    {
        //If Player is in a Gilde -> return Gildenname;
        //If Player is not in a Gilde -> return "";
        return "";
    }

    //ToDo:
    public static Object getGildeOfGildeName(String gildenname)
    {
        //Get all Information about a Gilde with its name.
        //Check: checkGildeExists() or getGildeNameOfPlayer()
        return "";
    }

    //ToDo:
    public static void addPlayertoGilde(String uuid, String gildenname)
    {
        //Only execute this method if checked:
        // - Gilde exists
        // - Player is not in Gilde

        List<String> newList = Main.getSaves().getStringList("gilden." + gildenname + ".players");
        newList.add(uuid);
        Main.getSaves().set("gilden." + gildenname + ".players", newList);
        Main.saveSaves();
    }

    //ToDo:
    public static ArrayList<String> getallPlayers(Player pl)
    {
        ArrayList<String> allUUIDs = new ArrayList<>();
        pl.sendMessage("Erste: " + allUUIDs.toString());
        for(String key : Main.getSaves().getConfigurationSection("gilden").getKeys(true))
        {
            pl.sendMessage("Key: " + key);
            if(key.contains("players"))
            {
                //gilden.test_gilde.players
                allUUIDs.addAll(Main.getSaves().getStringList("gilden." + key));
                pl.sendMessage("Key2: " + key);
            }
        }
        pl.sendMessage("Letzte: " + allUUIDs.toString());
        return allUUIDs;
    }

    //ToDo:
    public static ArrayList<String> getallPlayersinGilde(String gildenname)
    {
        //Only execute this method if checked:
        // - Gilde exists
        return new ArrayList<>(Main.getSaves().getStringList("gilden." + gildenname + ".players"));
    }
}
