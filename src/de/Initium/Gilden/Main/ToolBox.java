package de.Initium.Gilden.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.Array;
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
        if(gildenname.length() <= 2) return true;
        if(gildenname.contains("_")) return true;
        if(gildenname.contains("-")) return true;
        if(gildenname.contains(".")) return true;
        if(gildenname.contains("1")) return true;
        if(gildenname.contains("2")) return true;
        if(gildenname.contains("3")) return true;
        if(gildenname.contains("4")) return true;
        if(gildenname.contains("5")) return true;
        if(gildenname.contains("6")) return true;
        if(gildenname.contains("7")) return true;
        if(gildenname.contains("8")) return true;
        if(gildenname.contains("9")) return true;
        if(gildenname.contains("0")) return true;
        return false;
    }

    public static String getGildeNameOfPlayer(Player pl)
    {
        String uuid = pl.getUniqueId().toString();
        if(!(getallPlayers().contains(uuid))) return "";

        for(String key : Main.getSaves().getConfigurationSection("gilden").getKeys(true))
        {
            if(key.contains(".raenge.")) continue;
            //key: <gildenname.>raenge.Leiter/Mitglieder/Fositzender
            ArrayList<ArrayList<String>> temp = getallPlayersinGilde(key);
            for(ArrayList<String> temp_2 : temp)
            {
                if(temp_2.contains(uuid))
                {
                    return key;
                }
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
        Main.getSaves().set("gilden." + gildenname + ".raenge.Leiter", temp);
        Main.getSaves().set("gilden." + gildenname + ".raenge.Forsitzender", new ArrayList<String>());
        Main.getSaves().set("gilden." + gildenname + ".raenge.Mitglieder", new ArrayList<String>());
        Main.getSaves().set("gilden." + gildenname + ".Information." + "hasSetHome", false);
        Main.saveSaves();
    }

    public static void addPlayertoGilde(String uuid, String gildenname, String rang)
    {
        //Only execute this method if checked:
        // - Gilde exists
        // - Player is not in Gilde

        List<String> newList = Main.getSaves().getStringList("gilden." + gildenname + ".raenge." + rang);
        newList.add(uuid);
        Main.getSaves().set("gilden." + gildenname + ".raenge." + rang, newList);
        Main.saveSaves();
    }

    public static void removePlayerfromGilde(String uuid, String gildenname)
    {
        //Only execute this method if checked:
        // - Gilde exists
        // - Player is not in Gilde

        String rang = getGildeRankByPlayer(gildenname, uuid);
        List<String> newList = Main.getSaves().getStringList("gilden." + gildenname + ".raenge." + rang);
        newList.remove(uuid);
        Main.getSaves().set("gilden." + gildenname + ".raenge." + rang, newList);
        Main.saveSaves();
    }

    public static ArrayList<String> getallPlayers()
    {
    	ArrayList<String> allUUIDs = new ArrayList<>();
        for(String key : Main.getSaves().getConfigurationSection("gilden").getKeys(true)) 
        {
            if(!key.contains(".raenge.")) continue;

            String temp = "gilden." + key;
            //temp: gilden.gildenname.raenge.Leiter
            //temp: gilden.gildenname.raenge.Forsitzender
            //temp: gilden.gildenname.raenge.Mitglieder
        	Object test = Main.getSaves().getStringList(temp);
            allUUIDs.addAll((Collection<String>) test);
        }
        return allUUIDs;
    }

    public static ArrayList<ArrayList<String>> getallPlayersinGilde(String gildenname)
    {
        ArrayList<ArrayList<String>> temp_return_list = new ArrayList<>();
        temp_return_list.add(new ArrayList<>(Main.getSaves().getStringList("gilden." + gildenname + ".raenge.Leiter")));
        temp_return_list.add(new ArrayList<>(Main.getSaves().getStringList("gilden." + gildenname + ".raenge.Forsitzender")));
        temp_return_list.add(new ArrayList<>(Main.getSaves().getStringList("gilden." + gildenname + ".raenge.Mitglieder")));
        return temp_return_list;
    }

    public static ArrayList<String> unserializeArrayList(ArrayList<ArrayList<String>> temp)
    {
        ArrayList<String> result = new ArrayList<>();
        for(ArrayList<String> inner_list : temp)
        {
            for(String result_string : inner_list)
            {
                result.add(result_string);
            }
        }
        return result;
    }

    public static ArrayList<String> getAllGildennamen()
    {
        ArrayList<String> temp_storage = new ArrayList<>();
        for(String key : Main.getSaves().getConfigurationSection("gilden").getKeys(true))
        {
            if(key.contains("raenge")) continue;
            Bukkit.getServer().getConsoleSender().sendMessage("DU MICH AUCH: " + key);
            temp_storage.add(key.replace("gilden.", ""));
        }
        return temp_storage;
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

        ArrayList<String> raenge = new ArrayList<>();
        raenge.add("Leiter");
        raenge.add("Forsitzender");
        raenge.add("Mitglieder");
        String rang_found = "";

        for(String Rang : raenge)
        {
            if(Main.getSaves().getStringList("gilden." + gildenname + ".raenge." + Rang).contains(UUID))
            {
                rang_found = Rang;
                break;
            }
        }
        return rang_found;
    }
    
    public static Location getGildenHome(String gildenName) 
    {
    	//First check if Main.getSaves().getBoolean("gilden." + gildenName + ".Information." + "hasSetHome") == true

    		
    	
    		FileConfiguration save = Main.getSaves();
        	World world = Bukkit.getWorld(save.getString("gilden." + gildenName + ".Information." + " Location. " +"Home.World"));
    		double x = save.getDouble("gilden." + gildenName + ".Information." + " Location. " +"Home.X");
    		double y = save.getDouble("gilden." + gildenName + ".Information." + " Location. " +"Home.Y");
    		double z = save.getDouble("gilden." + gildenName + ".Information." + " Location. " +"Home.Z");
    		float yaw = (float) save.getDouble("gilden." + gildenName + ".Information." + " Location. " +"Home.Yaw");
    		float pitch = (float) save.getDouble("gilden." + gildenName + ".Information." + " Location. " +"Home.Pitch");
    		Location location = new Location(world, x, y, z, yaw, pitch);
    		return location;
    		
    	
    	
    }
}