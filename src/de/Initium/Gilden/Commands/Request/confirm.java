package de.Initium.Gilden.Commands.Request;

import de.Initium.Gilden.Main.ToolBox;
import de.Initium.Gilden.Main.UUIDManipulation;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class confirm implements CommandExecutor {
    public static ArrayList<Player> hasconfirmed = new ArrayList<>();
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if(sender instanceof Player) {
            Player p = (Player) sender;

            if(ToolBox.getallPlayers().contains(p.getUniqueId().toString())) {
                String gilde = ToolBox.getGildeNameOfPlayer(p);
                Player t = Bukkit.getPlayer(args[0]);

                    if(!(ToolBox.unserializeArrayList(ToolBox.getallPlayersinGilde(gilde)).contains(t.getUniqueId().toString()))) {
                        if(!deny.hasDenied.contains(p.getPlayer())) {
                        ToolBox.addPlayertoGilde(t.getUniqueId().toString(), gilde, "Mitglieder");
                        t.sendMessage("Deine Anfrage wurde angenommen!\nDu bist jetzt Mitglied der Gilde " + gilde);
                        p.sendMessage("Du hast " + t.getName() + " erfolgreich in die Gilde aufgenommen!");
                        gilde_request.awaiting.remove(t.getPlayer());
                        hasconfirmed.add(p.getPlayer());
                            ArrayList<String> playersofGilde = ToolBox.unserializeArrayList(ToolBox.getallPlayersinGilde(ToolBox.getGildeNameOfPlayer(Bukkit.getPlayerExact(p.getName()))));
                            for (String all : playersofGilde) {
                                if (!(p.getUniqueId().toString().equals(all))) {
                                    String temp = UUIDManipulation.getOnlinePlayerByUUID(all);
                                    if (!(temp.equals(""))) {
                                        (Bukkit.getPlayerExact(temp)).sendMessage("§a[§6" + gilde + "§a]" + " §aDer Spieler §6" + t.getName() + " §aist der Gilde beigetreten!");
                                    }
                                }
                            }

                     }else {
                        p.sendMessage("Du hast diesen Spieler bereits abgelehnt!");
                    }

                    }else {
                        p.sendMessage("Du hast diesen Spieler bereits angenommen!");
                    }

            }
        }


        return false;
    }
}
