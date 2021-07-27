package de.Initium.Gilden.API;

import de.Initium.Gilden.Main.UUIDManipulation;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class MainAPI extends JavaPlugin
{
    public static class Gilde
    {
        public void removePlayerNameFromGilde(String GildenName, String PlayerName)
        {
            de.doppelkool.playerLogger.API.MainAPI.PlayerLogger pl = new de.doppelkool.playerLogger.API.MainAPI.PlayerLogger();
            HashMap<String, String> hm = pl.getPlayernameToUUIDStorage();

            String target_UUID = "";
            for(Map.Entry<String, String> entry : hm.entrySet())
            {
                if(entry.getKey().equalsIgnoreCase(PlayerName))
                {
                    target_UUID = entry.getValue();
                    break;
                }
            }
            de.Initium.Gilden.Commands.gilde_kick.execute(GildenName, target_UUID);
        }

        public void removePlayerUUIDFromGilde(String GildenName, String UUID)
        {
            de.Initium.Gilden.Commands.gilde_kick.execute(GildenName, UUID);
        }
    }
}
