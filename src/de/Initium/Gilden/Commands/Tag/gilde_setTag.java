package de.Initium.Gilden.Commands.Tag;

import de.Initium.Gilden.Commands.gilde_Main;
import de.Initium.Gilden.Main.Main;
import de.Initium.Gilden.Main.ToolBox;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Objects;

public class gilde_setTag extends JavaPlugin {

    public static void execute(Integer nr, String[] args) {
        Player p = gilde_Main.getPlayer(nr);
        if(ToolBox.getallPlayers().contains(p.getUniqueId().toString())) {
            String gilde = ToolBox.getGildeNameOfPlayer(p);
            if(Main.getSaves().getBoolean("gilden." + gilde + ".Information.hasBoughtTag")) {
                if (ToolBox.getGildeRankByPlayer(gilde, p.getUniqueId().toString()).equalsIgnoreCase("Leiter") || ToolBox.getGildeRankByPlayer(gilde, p.getUniqueId().toString()).equalsIgnoreCase("Fositzender")) {
                    if (ToolBox.checkTagExists(args[1])) {
                        p.sendMessage("§cDie Gilde hat bereits einen Tag!");
                    } else {
                        if (ToolBox.validateTagName(args[1])) {

                            p.sendMessage("Der GildenTag \"" + args[1] + "\" ist ungültig. \n" +
                                    "Er muss folgende Kriterien erfüllen:\n" +
                                    "- Länge: genau 3 Ziffern\n" +
                                    "- Nur folgender Character dürfen enthalten sein: \n[A-Z], [a-z], [1-9]");
                        } else {
                            LocalDate current = LocalDate.now();
                            if (Main.getSaves().get("gilden." + gilde + ".Information." + "EstimatedNextSet") == null) {
                                Main.getSaves().getConfigurationSection("Tags.");
                                Main.getSaves().set("Tags." + "GildeToTag." + ToolBox.getGildeNameOfPlayer(p), args[1]);
                                Main.getSaves().set("Tags." + "TagToGilde." + args[1], ToolBox.getGildeNameOfPlayer(p));
                                Main.saveSaves();
                                p.sendMessage("§aDu hast erfolgreich den Gilden-Tag deiner Gilde erstellt");
                            } else {
                                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyy");
                                dtf = dtf.withLocale(Locale.GERMAN);
                                LocalDate needed = LocalDate.parse((Main.getSaves().getString("gilden." + gilde + ".Information." + "EstimatedNextSet")), dtf);
                                if (current.isBefore(needed)) {
                                    p.sendMessage("§cDu kannst erst am §6" + Main.getSaves().get("gilden." + gilde + ".Information." + "EstimatedNextSet") + "§c einen neuen gilden Tag setzten!");
                                } else if (current.isAfter(needed) || current.isEqual(needed)) {
                                    Main.getSaves().getConfigurationSection("Tags.");
                                    Main.getSaves().set("Tags." + "GildeToTag." + ToolBox.getGildeNameOfPlayer(p), args[1]);
                                    Main.getSaves().set("Tags." + "TagToGilde." + args[1], ToolBox.getGildeNameOfPlayer(p));
                                    Main.saveSaves();
                                    p.sendMessage("§aDu hast erfolgreich den Gilden-Tag deiner Gilde erstellt");
                                }

                            }


                        }

                    }
                }
            }else {
                p.sendMessage("Um diesen Befehl zu nutzen, musst du diesen erst kaufen!");
            }
        }else {
            p.sendMessage("§cDu kannst diesen Befehl nicht ausführen!");
        }


    }
}
