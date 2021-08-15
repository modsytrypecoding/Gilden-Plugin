package de.Initium.Gilden.Commands.Request;

import de.Initium.Gilden.Main.ToolBox;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class deny implements CommandExecutor {
    public static ArrayList<Player> hasDenied = new ArrayList<>();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if(sender instanceof Player) {
            Player p = (Player) sender;
            if(!hasDenied.contains(p.getPlayer())) {
                if(!confirm.hasconfirmed.contains(p.getPlayer())) {
                    if(ToolBox.getallPlayers().contains(p.getUniqueId().toString())) {
                        Player t = Bukkit.getPlayer(args[0]);

                        t.sendMessage("Deine Anfrage wurde abgelehnt!");
                        p.sendMessage("Du hast die Anfrage von " + t.getName() + " erfolgreich abgelehnt!");
                        gilde_request.awaiting.remove(t.getPlayer());
                        hasDenied.add(p.getPlayer());
                    }
                }else {
                    p.sendMessage("Du hast diesen Spieler bereits angenommen!");
                }

            }else {
                p.sendMessage("Du hast diesen Anfrage bereits abgelehnt!");
            }

        }

        return false;
    }
}
