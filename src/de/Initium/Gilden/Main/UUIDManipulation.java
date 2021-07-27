package de.Initium.Gilden.Main;

import de.doppelkool.playerLogger.API.MainAPI;
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
    static MainAPI.PlayerLogger pl;

    public static String getPlayernameByUUID(String UUID)
    {
        for(Player onlinePlayer : Bukkit.getOnlinePlayers())
        {
            if(onlinePlayer.getUniqueId().toString().equals(UUID))
            {
                return onlinePlayer.getName();
            }
        }
        return getOfflinePlayernameByUUID_PlayerLogger(UUID);
    }

    public static ArrayList<String> getPlayernameByUUID__LIST(ArrayList<String> UUIDs)
    {
        ArrayList<String> playernames = new ArrayList<>();
        for(String UUID : UUIDs)
        {
            boolean found = false;
            for(Player onlinePlayer : Bukkit.getOnlinePlayers())
            {
                if(onlinePlayer.getUniqueId().toString().equals(UUID))
                {
                    playernames.add(onlinePlayer.getName());
                    found = true;
                }
            }

            if(!found)
            {
                playernames.add(getOfflinePlayernameByUUID_PlayerLogger(UUID));
            }
        }
        return playernames;
    }

    public static ArrayList<String> getPlayernameByUUID__LIST_LIST(ArrayList<ArrayList<String>> UUIDs)
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
                        playernames.add(onlinePlayer.getName());
                        found = true;
                    }
                }

                if(!found)
                {
                    playernames.add(getOfflinePlayernameByUUID_PlayerLogger(UUID));
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

    public static String getOfflinePlayernameByUUID_PlayerLogger(String UUID)
    {
        pl = new MainAPI.PlayerLogger();
        if(pl.IsUUIDToPlayernameSet(UUID)) return pl.getPlayername(UUID);
        return getOfflinePlayerByUUID(UUID);
    }

    public static String getOfflineUUIDByPlayername_PlayerLogger(String playername)
    {
        pl = new MainAPI.PlayerLogger();
        if(pl.IsPlayernameToUUIDSet(playername)) return pl.getUUID(playername);
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