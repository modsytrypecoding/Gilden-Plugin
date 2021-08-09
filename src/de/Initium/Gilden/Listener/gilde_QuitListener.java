package de.Initium.Gilden.Listener;

import de.Initium.Gilden.Main.Main;
import de.Initium.Gilden.Main.ToolBox;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class gilde_QuitListener implements Listener {

    @EventHandler
    public static void onQuit(PlayerQuitEvent e) {
        Player p = e.getPlayer();

        Bukkit.broadcastMessage("Test");

        LocalDate Beitritt = LocalDate.now();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyy");
        String now = dtf.format(Beitritt);

        if(ToolBox.getallPlayers().contains(p.getUniqueId().toString())) {
            Main.getSaves().set("Playerdaten." + p.getUniqueId().toString() + ".QuitDate", now);
            Main.saveSaves();
        }

    }
}
