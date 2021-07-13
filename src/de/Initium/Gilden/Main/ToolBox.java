package de.Initium.Gilden.Main;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.*;
import java.util.stream.Collectors;

public class ToolBox extends JavaPlugin
{
    public static boolean checkGildeExists(String gildenname)
    {
    	 return Main.getSaves().getConfigurationSection("gilden." + gildenname) != null;
    }

    public static boolean validateGildeName(String gildenname)
    {
        if(gildenname.length() <= 2) return false;
        if(gildenname.contains("_")) return false;
        if(gildenname.contains("-")) return false;
        if(gildenname.contains(".")) return false;
        if(gildenname.contains("1")) return false;
        if(gildenname.contains("2")) return false;
        if(gildenname.contains("3")) return false;
        if(gildenname.contains("4")) return false;
        if(gildenname.contains("5")) return false;
        if(gildenname.contains("6")) return false;
        if(gildenname.contains("7")) return false;
        if(gildenname.contains("8")) return false;
        if(gildenname.contains("9")) return false;
        if(gildenname.contains("0")) return false;
        return true;
    }

    public static String getGildeNameOfPlayer(Player pl)
    {
        String uuid = pl.getUniqueId().toString();
        if(!(getallPlayers().contains(uuid))) return "";

        for(String key : Main.getSaves().getConfigurationSection("gilden").getKeys(true))
        {
            if(!(key.contains("players"))) continue;

            String temp_key = key.replace("gilden.", "").replace(".players", "");
            ArrayList<String> temp = getallPlayersinGilde(temp_key);

            if(temp.contains(uuid))
            {
                return temp_key;
            }
        }
        return "_";
    }

    public static void createGilde(String gildenname, String gruender_UUID)
    {
        //Only execute this method if checked:
        // - Gildenname is not used yet
        // - Player is not in any Gilde

        ArrayList<String> temp = new ArrayList<>();
        temp.add(gruender_UUID);
        Main.getSaves().set("gilden." + gildenname + ".players", temp);
        Main.saveSaves();
    }

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
            	Object test = Main.getSaves().get("gilden." + key);
            	if(test instanceof String)
                {
                    allUUIDs.add((String) test);
                }
            	else if(test instanceof Collection<?>)
                {
                    allUUIDs.addAll((Collection<String>) test);
                }
            }
        }
        return allUUIDs;
    }

    public static ArrayList<String> getallPlayersinGilde(String gildenname)
    {
        ArrayList<String> temp_return_list = new ArrayList<>();
        Object temp = Main.getSaves().get("gilden." + gildenname + ".players");
        if(temp instanceof List<?>) return new ArrayList<>((List<String>) temp);
        temp_return_list.add(temp.toString());
        return temp_return_list;
    }

    public static ArrayList<String> getAllGildennamen()
    {
        ArrayList<String> storage = new ArrayList<>();
        for(String key : Main.getSaves().getConfigurationSection("gilden").getKeys(true))
        {
            if(key.contains("players")) continue;
            storage.add(key.replace("gilden.", ""));
        }
        return storage;
    }

    //ToDo:
    public static Object getGildeInformationsByName(String gildenname)
    {
        //Get all Information about a Gilde with its name.
        //Check: checkGildeExists() or getGildeNameOfPlayer()
        return Main.getSaves().getConfigurationSection("gilden." + gildenname) != null;
    }

    public static Object parseUIntOrNull(String value) {
        //Return UINT if Parsable, return null if not
        try {
            Integer temp =  Integer.parseInt(value);
            if(temp > 0) return temp;
            return null;
        } catch (NumberFormatException e) {
            return null;
        }
    }

    public static Map<String, Integer> sort(Map<String, Integer> unsortedMap)
    {
        return unsortedMap.entrySet().stream()
                .sorted(Comparator.comparingInt(e -> -e.getValue()))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (a, b) -> { throw new AssertionError(); },
                        LinkedHashMap::new
                ));
    }

    public static String getGildeRankByPlayer(String gildenname, String UUID)
    {
        //Only execute this method if checked:
        // - Gilde exists
        // - Player is in Gilde

        //if(Rank == "Leiter") return "Leiter";
        //if(Rank == "Stellvertreter") return "Stellvertreter";
        //return "Mitglied";
        return "";
    }
}
