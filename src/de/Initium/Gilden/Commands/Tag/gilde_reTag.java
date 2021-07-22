package de.Initium.Gilden.Commands.Tag;

import de.Initium.Gilden.Commands.gilde_Main;
import de.Initium.Gilden.Main.Main;
import de.Initium.Gilden.Main.ToolBox;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

public class gilde_reTag extends JavaPlugin {
    static LocalDate EstimatedChange = null;


    public static void execute(Integer nr, String[] args) {

        Player p = gilde_Main.getPlayer(nr);

            if(ToolBox.getallPlayers().contains(p.getUniqueId().toString())) {
                String gilde = ToolBox.getGildeNameOfPlayer(p);
                if(ToolBox.getGildeRankByPlayer(gilde, p.getUniqueId().toString()).equalsIgnoreCase("Leiter")) {
                    if(Main.getSaves().get("gilden." + gilde + ".Information." + "TagChangeDate") == null) {
                        if(ToolBox.checkTagExists(args[1])) {
                            p.sendMessage("§cDeine Gilde hat noch keinen Tag!");
                        }else {
                            if(ToolBox.validateTagName(args[1])) {
                                p.sendMessage("Der GildenTag \"" + args[1] + "\" ist ungültig. \n" +
                                        "Er muss folgende Kriterien erfüllen:\n" +
                                        "- Länge: genau 3 Ziffern\n" +
                                        "- Nur folgender Character dürfen enthalten sein: \n[A-Z], [a-z], [1-9]");
                            }else {
                                DateTimeFormatter dtF  = DateTimeFormatter.ofPattern("dd.MM.yyy");
                                LocalDate changeDate = LocalDate.now();
                                String change = dtF.format(changeDate);
                                EstimatedChange = LocalDate.now().plusMonths(1);
                                String NextChange = dtF.format(EstimatedChange);



                                ToolBox.DelTag(ToolBox.getTagbyGilde(gilde));
                                Main.getSaves().set("Tags." + "GildeToTag." + gilde, args[1]);
                                Main.getSaves().set("Tags." + "TagToGilde." + args[1], gilde);
                                Main.saveSaves();
                                p.sendMessage("§aDein Gilden Tag wurde erfolgreich geändert!");
                                Main.getSaves().set("gilden." + gilde + ".Information." + "TagChangeDate", change);
                                Main.getSaves().set("gilden." + gilde + ".Information." + "EstimatedNextChange", NextChange);
                                Main.saveSaves();
                            }

                        }
                    }else {




                        //25.07.21
                        LocalDate current = LocalDate.now();


                        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyy");
                        dtf = dtf.withLocale(Locale.GERMAN);
                        LocalDate needed = LocalDate.parse(Main.getSaves().getString("gilden." + gilde + ".Information." + "EstimatedNextChange"), dtf);

                        if(current.isBefore(needed)) {

                            p.sendMessage("§cDu kannst den Gilden Tag erst wieder am §6" + Main.getSaves().get("gilden." + gilde + ".Information." + "EstimatedNextChange").toString() + " §cändern!");


                        }else if(current.isAfter(needed) || current.isEqual(needed)){
                            DateTimeFormatter dtF  = DateTimeFormatter.ofPattern("dd.MM.yyy");
                            LocalDate now = LocalDate.now();
                            LocalDate CalcNext = LocalDate.now().plusMonths(1);
                            String CalcNextString = dtF.format(CalcNext);
                            String NowString = dtF.format(now);

                            ToolBox.DelTag(ToolBox.getTagbyGilde(gilde));
                            Main.getSaves().set("Tags." + "GildeToTag." + gilde, args[1]);
                            Main.getSaves().set("Tags." + "TagToGilde." + args[1], gilde);
                            Main.saveSaves();
                            p.sendMessage("§aDein Gilden Tag wurde erfolgreich geändert!");
                            Main.getSaves().set("gilden." + gilde + ".Information." + "TagChangeDate", NowString);
                            Main.getSaves().set("gilden." + gilde + ".Information." + "EstimatedNextChange", CalcNextString);
                            Main.saveSaves();
                        }
                    }





                }else {
                    p.sendMessage("§cDu hast keine Rechte diesen Befehl auszuführen!");
                }
            }else {
                p.sendMessage("§cDu kannst diesen Befehl nicht ausführen!");
            }


    }
}
