package de.Initium.Gilden.Commands.Tag;

import de.Initium.Gilden.Commands._show;
import de.Initium.Gilden.Commands.gilde_Main;
import de.Initium.Gilden.Main.ToolBox;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class gilde_tag extends JavaPlugin {

    public static void execute(Integer nr, String args) {
        Player p = gilde_Main.getPlayer(nr);
        if(ToolBox.checkTagExists(args)) {
            _show.execute(nr, ToolBox.getgildebyTag(args));
        }else {
            p.sendMessage("§cDieser Tag existiert nicht");

        }

    }
}
