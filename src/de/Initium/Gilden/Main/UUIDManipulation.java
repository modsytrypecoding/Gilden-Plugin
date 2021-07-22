package de.Initium.Gilden.Main;

import jdk.nashorn.api.scripting.URLReader;
import org.apache.logging.log4j.core.util.IOUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class UUIDManipulation extends JavaPlugin
{
    public static String getPlayernameByUUID(String UUID)
    {
        for(Player onlinePlayer : Bukkit.getOnlinePlayers())
        {
            if(onlinePlayer.getUniqueId().toString().equals(UUID))
            {
                return getOnlinePlayerByUUID(UUID);
            }
        }
        return getOfflinePlayerByUUID(UUID);
    }

    public static ArrayList<String> getPlayernameByUUID_1(ArrayList<String> UUIDs)
    {
        ArrayList<String> playernames = new ArrayList<>();
        for(String UUID : UUIDs)
        {
            boolean found = false;
            for(Player onlinePlayer : Bukkit.getOnlinePlayers())
            {
                if(onlinePlayer.getUniqueId().toString().equals(UUID))
                {
                    playernames.add(getOnlinePlayerByUUID(UUID).toString());
                    found = true;
                }
            }

            if(!found)
            {
                playernames.add(getOfflinePlayerByUUID(UUID).toString());
            }
        }
        return playernames;
    }

    public static ArrayList<String> getPlayernameByUUID_2(ArrayList<ArrayList<String>> UUIDs)
    {
        ArrayList<String> playernames = new ArrayList<>();
        for(List<String> asd : UUIDs)
        {
            for(String UUID : asd)
            {
                boolean found = false;
                for(Player onlinePlayer : Bukkit.getOnlinePlayers())
                {
                    if(onlinePlayer.getUniqueId().toString().equals(UUID))
                    {
                        playernames.add(getOnlinePlayerByUUID(UUID).toString());
                        found = true;
                    }
                }

                if(!found)
                {
                    playernames.add(getOfflinePlayerByUUID(UUID).toString());
                }
            }
        }
        return playernames;
    }

    public static String getOnlinePlayerByUUID(String UUID)
    {
        for(Player p : Main.getPlugin().getServer().getOnlinePlayers())
            if(p.getUniqueId().toString().equals(UUID)) return p.getName();
        return "";
    }

    public static String getOfflinePlayerByUUID(String UUID)
    {
         try {
            URL myURL = new URL("https://api.mojang.com/user/profiles/"+UUID+"/names");
            String nameJson = IOUtils.toString(new URLReader(myURL));
            JSONArray nameValue = (JSONArray) JSONValue.parseWithException(nameJson);
            String playerSlot = nameValue.get(nameValue.size()-1).toString();
            JSONObject nameObject = (JSONObject) JSONValue.parseWithException(playerSlot);
            return nameObject.get("name").toString();
         }
         catch (IOException | ParseException e)
         {
             e.printStackTrace();
         }
         return "";
    }
}