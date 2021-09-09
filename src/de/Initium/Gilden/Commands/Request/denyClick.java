package de.Initium.Gilden.Commands.Request;

import de.Initium.Gilden.Main.ToolBox;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class denyClick implements CommandExecutor {
    public static ArrayList<Player> hasCanceled = new ArrayList<>();
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (args.length == 0) {
                if (ToolBox.getallPlayers().contains(p.getUniqueId().toString())) {
                    if (hasCanceled.contains(p.getPlayer())) {
                        p.sendMessage("Du hast diesen Vorgang bereits abgebrochen!");
                    } else {
                        p.sendMessage("§cAbbruch!");
                        hasCanceled.add(p.getPlayer());
                    }
                }
            }

        }
        return false;
    }
}
