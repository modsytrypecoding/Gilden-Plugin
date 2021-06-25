package de.Initium.Gilden.Main;
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

    //ToDo:
    public static Object getGildeOfGildeName(String gildenname)
    {
        //Get all Information about a Gilde with its name.
        //Check: checkGildeExists() or getGildeNameOfPlayer()
    	return Main.getSaves().getConfigurationSection("gilden." + gildenname) != null;
    }

    //ToDo:
    public static String getGildeNameOfPlayer(Player pl)
    {
        String uuid = pl.getUniqueId().toString();
        pl.sendMessage(getallPlayers(pl).toString());
        if(!(getallPlayers(pl).contains(uuid))) return "";

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
    public static ArrayList<String> getallPlayers()
    {
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
    
    public static ArrayList<String> getallPlayers(Player p)
    {
    	ArrayList<String> allUUIDs = new ArrayList<>();
        for(String key : Main.getSaves().getConfigurationSection("gilden").getKeys(true)) 
        {
            if(key.contains("players"))
            {
                //gilden.test_gilde.players
            	Object test = Main.getSaves().get("gilden." + key);
            	if(test instanceof String)
                {
                    allUUIDs.add((String) test);
                }
            	else if(test instanceof Collection<?>)
                {
                    allUUIDs.addAll((Collection<String>) test);
                }
                p.sendMessage("Key \"" + key + "\":" + test.toString());
            }
        }
        return allUUIDs;
    }
    //ToDo:
    public static ArrayList<String> getallPlayersinGilde(String gildenname)
    {
        return new ArrayList<>(Main.getSaves().getStringList("gilden." + gildenname + ".players"));
    }
}
