package de.Initium.Gilden.API;

import de.Initium.Gilden.Main.Main;
import de.Initium.Gilden.Main.ToolBox;
import de.Initium.Gilden.Main.UUIDManipulation;
import org.bukkit.Bukkit;
import de.Initium.Gilden.NPCs.Main.Creation.InventoryInteraction;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;

public class GildeAPI
{
    public static class MainAPI
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
        public String getQuitDateofPlayer(String UUID)
        {
            String QuitDate = Main.getSaves().get("PlayerDaten." + UUID + "QuitDate").toString();
            return QuitDate;
        }
        public String getGildenNameofPlayer(Player p) {
            String gilde = ToolBox.getGildeNameOfPlayer(p);
            return gilde;
        }
        public String getGildenNameofPlayer(String player_uuid) {
            String gilde = ToolBox.getGildeNameOfPlayer(player_uuid);
            return gilde;
        }

        public boolean awaitingResponse(Player pl)
        {
            return InventoryInteraction.getAwaitingNewGildename().contains(pl);
        }

        public boolean awaitingConfirmation(Player pl)
        {
            return InventoryInteraction.getAwaitingConfirmation().containsKey(pl);
        }
    }
}
