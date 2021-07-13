package de.Initium.Gilden.NPCs.Listener;

import de.Initium.Gilden.NPCs.Main.Creation.CreationResponse;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class Bukkit_JoinLeave implements Listener
{
    @EventHandler
    public static void onJoin(PlayerJoinEvent e)
    {

    }

    @EventHandler
    public static void onQuit(PlayerQuitEvent e)
    {
        onLeaveServer(e);
    }

    @EventHandler
    public static void onKick(PlayerKickEvent e)
    {
        onLeaveServer(e);
    }

    public static void onLeaveServer(PlayerEvent e)
    {
        CreationResponse.cleanup(e.getPlayer());
    }
}
