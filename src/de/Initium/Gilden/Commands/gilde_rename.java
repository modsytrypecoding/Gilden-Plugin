package de.Initium.Gilden.Commands;

import de.Initium.Gilden.Commands.gilde_Main;
import de.Initium.Gilden.Main.Main;
import de.Initium.Gilden.Main.ToolBox;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class gilde_rename extends JavaPlugin {
    static LocalDate EstimatedChange = null;


    public static void execute(Integer nr, String[] Input) {
        Player p = gilde_Main.getPlayer(nr);
        if (Input.length == 2) {
            if (ToolBox.getallPlayers().contains(p.getUniqueId().toString())) {
                String gilde = ToolBox.getGildeNameOfPlayer(p);
                DateTimeFormatter dtF = DateTimeFormatter.ofPattern("dd.MM.yyy");
                LocalDate changeDate = LocalDate.now();
                String change = dtF.format(changeDate);
                EstimatedChange = LocalDate.now().plusMonths(3);
                String NextChange = dtF.format(EstimatedChange);


                if (Main.getSaves().get("gilden." + gilde + ".Information." + "RenameDate") == null) {
                    if (gilde.equals(Input[1])) {
                        p.sendMessage("§cDeine Gilde hat bereits diesen Namen!");
                    } else {
                        if (ToolBox.getGildeRankByPlayer(gilde, p.getUniqueId().toString()).equalsIgnoreCase("Leiter")) {
                            if (ToolBox.validateGildeName(Input[1])) {
                                p.sendMessage("Der Gildenname \"" + gilde + "\" ist ungültig." +
                                        "Er muss folgende Kriterien erfüllen:\n" +
                                        "- Länge: Mindestens 3 Buchstaben\n" +
                                        "- Nur folgender Character dürfen enthalten sein: [A-Z], [a-z]");
                                return;
                            }

                            Map<String, Object> vals = Main.getSaves().getConfigurationSection("gilden." + gilde).getValues(true);
                            String toDot = ("gilden." + Input[1]).equals("") ? "" : ".";
                            for (String s : vals.keySet()) {
                                Object val = vals.get(s);
                                if (val instanceof List)
                                    val = new ArrayList<>((List) val);
                                Main.getSaves().set("gilden." + Input[1] + toDot + s, val);
                            }
                            Main.getSaves().set("gilden." + gilde, null);
                            Main.getSaves().set("gilden." + Input[1] + ".Information." + "RenameDate", change);
                            Main.getSaves().set("gilden." + Input[1] + ".Information." + "NextRenameDate", NextChange);
                            Main.saveSaves();
                            p.sendMessage("§aDu hast deine Gilde erfolgreich umbenannt!");


                        } else {
                            p.sendMessage("§cDu darfst den Namen der Gilde nicht bearbeiten!");
                        }

                    }
                } else {
                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyy");
                    dtf = dtf.withLocale(Locale.GERMAN);
                    LocalDate needed = LocalDate.parse(Main.getSaves().getString("gilden." + gilde + ".Information." + "NextRenameDate"), dtf);
                    if (LocalDate.now().isBefore(needed)) {
                        p.sendMessage("Du kannst deine Gilde erst wieder am §6" + Main.getSaves().get("gilden." + gilde + ".Information." + "NextRenameDate").toString() + " §rumbenennen!");

                    } else if (LocalDate.now().isEqual(needed) || LocalDate.now().isAfter(needed)) {
                        if (ToolBox.getGildeRankByPlayer(gilde, p.getUniqueId().toString()).equalsIgnoreCase("Leiter")) {
                            if (ToolBox.validateGildeName(Input[1])) {
                                p.sendMessage("Der Gildenname \"" + gilde + "\" ist ungültig." +
                                        "Er muss folgende Kriterien erfüllen:\n" +
                                        "- Länge: Mindestens 3 Buchstaben\n" +
                                        "- Nur folgender Character dürfen enthalten sein: [A-Z], [a-z]");
                                return;
                            }

                            Map<String, Object> vals = Main.getSaves().getConfigurationSection("gilden." + gilde).getValues(true);
                            String toDot = ("gilden." + Input[1]).equals("") ? "" : ".";
                            for (String s : vals.keySet()) {
                                Object val = vals.get(s);
                                if (val instanceof List)
                                    val = new ArrayList<>((List) val);
                                Main.getSaves().set("gilden." + Input[1] + toDot + s, val);
                            }
                            Main.getSaves().set("gilden." + gilde, null);
                            Main.getSaves().set("gilden." + Input[1] + ".Information." + "RenameDate", change);
                            Main.getSaves().set("gilden." + Input[1] + ".Information." + "NextRenameDate", NextChange);
                            Main.saveSaves();
                            p.sendMessage("§aDu hast deine Gilde erfolgreich umbenannt!");
                        }
                    }
                }

            } else {
                p.sendMessage("§cDu kannst diesen Befehl nicht ausführen, da du in keiner Gilde bist!");
            }

        }
        if (Input.length == 3) {
            if (p.hasPermission("gilde.rename.admin")) {
                if (ToolBox.checkGildeExists(Input[1])) {
                    if (ToolBox.validateGildeName(Input[1])) {
                        p.sendMessage("Der Gildenname \"" + Input[1] + "\" ist ungültig." +
                                "Er muss folgende Kriterien erfüllen:\n" +
                                "- Länge: Mindestens 3 Buchstaben\n" +
                                "- Nur folgender Character dürfen enthalten sein: [A-Z], [a-z]");
                        return;
                    }
                    if(Input[1].equals(Input[2])) {
                        p.sendMessage("§cDer neue Name darf nicht dem alten entsprechen!");
                    }else {
                        Map<String, Object> vals = Main.getSaves().getConfigurationSection("gilden." + Input[1]).getValues(true);
                        String toDot = ("gilden." + Input[2]).equals("") ? "" : ".";
                        for (String s : vals.keySet()) {
                            Object val = vals.get(s);
                            if (val instanceof List)
                                val = new ArrayList<>((List) val);
                            Main.getSaves().set("gilden." + Input[2] + toDot + s, val);
                        }
                        Main.getSaves().set("gilden." + Input[1], null);
                        Main.saveSaves();
                        p.sendMessage("§aDu hast die Gilde erfolgreich umbenannt!");
                    }


                } else {
                    p.sendMessage("§cDie von dir gewählte Gilde existiert nicht!");
                }
            }
        }

    }
}

