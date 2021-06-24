package de.Initium.Gilden.Main;

import de.Initium.Gilden.Commands.gilde_Main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ToolBox extends JavaPlugin
{
    public static ArrayList<String> parseAllUUIDsToPlayerNames(List<String> UUIDs)
    {
        //Result: Returns an ArrayList within Playernames that were parsed from the given UUIDs

        ArrayList<String> playernames = new ArrayList<>();
        for(String UUID : UUIDs)
            playernames.add(Bukkit.getPlayer(UUID).getName());
        return playernames;
    }

    //ToDo:
    public static boolean checkGildeExists(String gildenname)
    {
        //Result:
        //- true -> Gilde exists
        //- false -> Gilde does not exist

        return Main.getSaves().getConfigurationSection("gilden." + gildenname) != null;
    }

    //ToDo:
    public static String getGildeNameOfPlayer(Player pl)
    {
        //Result: Returns:
        //- Gildenname (String), if given Player is in a gilde
        //- Empty String, if given Player is not in a gilde
        //- "_" only if there is an error

        String uuid = pl.getUniqueId().toString();
        if(!(getallPlayers().contains(uuid))) return "";

        for(String key : Main.getSaves().getConfigurationSection("gilden").getKeys(true))
        {
            if(!(key.contains("players"))) continue;

            List<String> asd = Main.getSaves().getStringList("gilden." + key);
            if(asd.contains(uuid))
            {
                return key.replace("players", "");
            }
        }
        return "_";
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
        //Result: Adds a Player to the gilde

        List<String> newList = Main.getSaves().getStringList("gilden." + gildenname + ".players");
        newList.add(uuid);
        Main.getSaves().set("gilden." + gildenname + ".players", newList);
        Main.saveSaves();
    }

    //ToDo:
    public static ArrayList<String> getallPlayers()
    {
        //Result: All Players that are in the config (in any gilde) inside of an ArrayList<String>

        ArrayList<String> allUUIDs = new ArrayList<>();
        for(String key : Main.getSaves().getConfigurationSection("gilden").getKeys(true))
        {
            if(key.contains("players"))
            {
                //gilden.test_gilde.players
                allUUIDs.addAll(Main.getSaves().getStringList("gilden." + key));
            }
        }
        return allUUIDs;
    }

    //ToDo:
    public static ArrayList<String> getallPlayersinGilde(String gildenname)
    {
        //Only execute this method if checked:
        // - Gilde exists
        //Result: ArrayList<String> within all Players that are in the given Gilde

        return new ArrayList<>(Main.getSaves().getStringList("gilden." + gildenname + ".players"));
    }
}
