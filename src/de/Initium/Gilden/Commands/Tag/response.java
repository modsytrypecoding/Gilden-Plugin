package de.Initium.Gilden.Commands.Tag;

import de.Initium.Gilden.Commands.gilde_Main;
import de.Initium.Gilden.Main.Main;
import de.Initium.Gilden.Main.ToolBox;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class response extends JavaPlugin {
    public static ArrayList<Player> responsecancel = new ArrayList<>();
    public static ArrayList<Player> responsdelete = new ArrayList<>();

    public static void execute(Integer nr, String arg) {
        Player p = gilde_Main.getPlayer(nr);
        if(arg.equalsIgnoreCase("responsedelet")) {
            if(Main.getSaves().get("Tags.GildeToTag." + ToolBox.getGildeNameOfPlayer(p)) != null) {
                if(!responsdelete.contains(p)) {
                    if(!responsecancel.contains(p)) {
                        responsdelete.add(p);
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
                    }else {
                        p.sendMessage("Du hast das Löschen bereits abgebrochen!");
                    }
                }else {
                    p.sendMessage("Du hast das Löschen bereits bestätigt!");
                }
            }else {
                p.sendMessage("Deine Gilde hat kein Tag!");
            }


        }
        if(arg.equalsIgnoreCase("responsecancle")) {
            if(!responsecancel.contains(p)) {
                if(!responsdelete.contains(p)) {
                    responsecancel.add(p);
                    p.sendMessage("§aVorgang erfolgreich abgebrochen!");
                }else {
                    p.sendMessage("Du hast das Löschen bereits bestätigt!");
                }
            }else {
                p.sendMessage("Du hast das Löschen bereits abgebrochen!");
            }

        }

    }
}
