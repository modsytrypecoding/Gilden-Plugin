package de.Initium.Gilden.Commands;

import de.Initium.Gilden.Main.CommandDispatcher;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class gilde_Main implements CommandExecutor, TabCompleter {
    public static HashMap<Integer, ArrayList<Object>> hm = new HashMap<>();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("//Per Console");
            return true;
        }
        Player pl = (Player) sender;
        if (!(pl.hasPermission("gilde.CMD.main")) /* /gilde */) {
            pl.sendMessage("");
            return true;
        }

        ArrayList<Object> inf = new ArrayList<>();
        inf.add(pl);
        inf.add(cmd);

        Integer newnr = hm.size() + 1;
        hm.put(newnr, inf);
        CommandDispatcher.Main_dispatch(newnr, args);
        return true;
    }



    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String s, String[] args) {
        List<String> list = new ArrayList<>();
        ArrayList<String> commands = new ArrayList<>();
        if (sender.hasPermission("gilde.admin")) {
            commands.add("top");
            commands.add("chat");
            commands.add("chat-join");
            commands.add("kick");
            commands.add("rank");
            commands.add("rename");
            commands.add("pay");
            commands.add("take");
            commands.add("home");
            commands.add("setHome");
            commands.add("delHome");
            commands.add("spawn");
            commands.add("setspawn");
            commands.add("setTag");
            commands.add("reTag");
            commands.add("renameTag");
            commands.add("delTag");
            commands.add("leave");
            if (s.equalsIgnoreCase("gilde")) {
                List<String> afterCommands = commands;
                for (String autocomplete : afterCommands) {
                    if (args.length > 1) {

                    } else {
                        if(!autocomplete.toLowerCase().startsWith(args[0].toLowerCase())) continue;
                        list.add(autocomplete);
                    }
                }
            }


            return list;
        } else {
            commands.add("top");
            commands.add("chat");
            commands.add("kick");
            commands.add("rank");
            commands.add("rename");
            commands.add("bank");
            commands.add("home");
            commands.add("setHome");
            commands.add("delHome");
            commands.add("spawn");
            commands.add("setspawn");
            commands.add("setTag");
            commands.add("reTag");
            commands.add("renameTag");
            commands.add("delTag");
            commands.add("leave");

            if (s.equalsIgnoreCase("gilde")) {
                List<String> afterCommands = commands;
                for (String autocomplete : afterCommands) {
                    if (args.length > 1) {

                    } else {
                        if(!autocomplete.toLowerCase().startsWith(args[0].toLowerCase())) continue;
                        list.add(autocomplete);
                    }
                }

            }
            return list;

        }

    }
    public static Player getPlayer(Integer nr) {
        ArrayList<Object> temp_list = hm.get(nr);
        return (Player) temp_list.get(0);
    }
}