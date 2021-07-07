package de.Initium.Gilden.Commands;

import de.Initium.Gilden.Main.CommandDispatcher;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;

public class gilde_Main implements CommandExecutor
{
    public static HashMap<Integer, ArrayList<Object>> hm = new HashMap<>();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(!(sender instanceof Player))
        {
            sender.sendMessage("//Per Console");
            return true;
        }
        Player pl = (Player) sender;
        if(!(pl.hasPermission("gilde.CMD.main")) /* /gilde */)
        {
            pl.sendMessage("Keine Perms");
            return true;
        }

        ArrayList<Object> inf = new ArrayList<>();
        inf.add(pl);
        inf.add(cmd);

        Integer newnr = hm.size()+1;
        hm.put(newnr, inf);
        CommandDispatcher.Main_dispatch(newnr, args);
        return true;
    }

    public static Player getPlayer(Integer nr)
    {
        ArrayList<Object> temp_list = hm.get(nr);

        return (Player) temp_list.get(0);
    }
}