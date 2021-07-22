package de.Initium.Gilden.Commands.Tag;

import de.Initium.Gilden.Commands.gilde_Main;
import de.Initium.Gilden.Main.Main;
import de.Initium.Gilden.Main.ToolBox;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class response extends JavaPlugin {

    public static void execute(Integer nr, String arg) {
        Player p = gilde_Main.getPlayer(nr);
        if(arg.equalsIgnoreCase("responsedelet")) {
            String gilde = ToolBox.getGildeNameOfPlayer(p);
            DateTimeFormatter dtF  = DateTimeFormatter.ofPattern("dd.MM.yyy");
            LocalDate Next = LocalDate.now().plusMonths(1);
            String NextString = dtF.format(Next);
            LocalDate current = LocalDate.now();
            String currentString = dtF.format(current);
            ToolBox.DelTag(ToolBox.getTagbyGilde(gilde));
            ToolBox.DelGildeToTag(gilde);
            Main.getSaves().set("gilden." + gilde + ".Information." + "TagDeleteDate", currentString);
            Main.getSaves().set("gilden." + gilde + ".Information." + "EstimatedNextSet", NextString);
            Main.saveSaves();
            p.sendMessage("§cDein Gilden-Tag wurde erfolgreich gelöscht!");
        }
        if(arg.equalsIgnoreCase("responsecancle")) {
            p.sendMessage("§aVorgang erfolgreich abgebrochen!");
        }

    }
}
