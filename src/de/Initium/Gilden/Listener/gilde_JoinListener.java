package de.Initium.Gilden.Listener;

import de.Initium.Gilden.Main.Main;
import de.Initium.Gilden.Main.ToolBox;
import de.Initium.Gilden.Main.UUIDManipulation;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class gilde_JoinListener implements Listener {

    @EventHandler
    public static void onJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();

        //check if Player is in Gilde
        if(ToolBox.getallPlayers().contains(p.getUniqueId().toString())) {
            String gilde = ToolBox.getGildeNameOfPlayer(p);
            //check if there is only one Leiter
            if(Main.getSaves().getStringList("gilden." + gilde + ".raenge.Leiter").size() == 1) {
                //get Leiter
                Player t = Bukkit.getPlayer(UUIDManipulation.getPlayernameByUUID(Main.getSaves().getStringList("gilden." + gilde + ".raenge.Leiter").get(0)));
                //check if there is a Stellvertreter
                if(Main.getSaves().getStringList("gilden." + gilde + ".raenge.Stellvertreter").isEmpty()) {
                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyy");
                    dtf = dtf.withLocale(Locale.GERMAN);
                    LocalDate timeQUit = LocalDate.parse((Main.getSaves().getString("Playerdaten." + t.getUniqueId().toString() + ".QuitDate")), dtf);
                    LocalDate now = LocalDate.now();
                    //check if 83 Days have pasted
                    if(now.isEqual(timeQUit.plusDays(83))) {
                        p.sendMessage("§cDer Leiter deiner Gilde ist seit 83 Tagen inaktiv!\nKommt er innerhalb der nächsten 7 Tage nicht zurück,\nwird die Gilde aufgelöst, da kein Forsitzender in der Gilde gefunden werden konnte!");
                    }else if(now.isEqual(timeQUit.plusDays(90))) {
                        ToolBox.DelTag(ToolBox.getTagbyGilde(gilde));
                        ToolBox.DelGildeToTag(gilde);
                        ToolBox.DelGilde(gilde);
                        p.sendMessage("§cDie Gilde in der du dich befindest wurde aufgelöst!");
                    }
                }else {
                    if(Main.getSaves().getStringList("gilden." + gilde + ".raenge.Stellvertreter").size() == 1) {
                        Player nextLeiter = Bukkit.getPlayer(UUIDManipulation.getPlayernameByUUID(Main.getSaves().getStringList("gilden." + gilde + ".raenge.Stellvertreter").get(0)));
                        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyy");
                        dtf = dtf.withLocale(Locale.GERMAN);
                        LocalDate timeQUit = LocalDate.parse((Main.getSaves().getString("Playerdaten." + t.getUniqueId().toString() + ".QuitDate")), dtf);
                        LocalDate now = LocalDate.now();
                        //check if 83 Days have pasted
                        if(now.isEqual(timeQUit.plusDays(83))) {
                            p.sendMessage("§cDer Leiter deiner Gilde ist seit 83 Tagen inaktiv!\nKommt er innerhalb der nächsten 7 Tage nicht zurück,\nwird der Spieler " + nextLeiter.getName() + " der nächste Leiter!");
                        }else if(now.isEqual(timeQUit.plusDays(90))) {
                            ToolBox.removePlayerfromGilde(nextLeiter.getUniqueId().toString(), gilde);
                            ToolBox.addPlayertoGilde(nextLeiter.getUniqueId().toString(), gilde, "Leiter");
                            p.sendMessage("§aDer Spieler " + nextLeiter.getName() + " ist der neue Leiter.");
                        }
                    }
                    if(Main.getSaves().getStringList("gilden." + gilde + ".raenge.Stellvertreter").size() == 2) {

                        Player possible1 = Bukkit.getPlayer(UUIDManipulation.getPlayernameByUUID(Main.getSaves().getStringList("gilden." + gilde + ".raenge.Stellvertreter").get(0)));
                        Player possible2 = Bukkit.getPlayer(UUIDManipulation.getPlayernameByUUID(Main.getSaves().getStringList("gilden." + gilde + ".raenge.Stellvertreter").get(1)));
                        Main.getSaves().get("Playerdaten." + possible1.getUniqueId().toString() + ".Beitrittsdatum");
                        Main.getSaves().get("Playerdaten." + possible2.getUniqueId().toString() + ".Beitrittsdatum");

                        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyy");
                        dtf = dtf.withLocale(Locale.GERMAN);
                        LocalDate Date1 = LocalDate.parse((Main.getSaves().getString("Playerdaten." + possible1.getUniqueId().toString() + ".Beitrittsdatum")), dtf);
                        LocalDate Date2 = LocalDate.parse((Main.getSaves().getString("Playerdaten." + possible2.getUniqueId().toString() + ".Beitrittsdatum")), dtf);

                        if(Date1.isBefore(Date2)) {
                            Player nextLeiter = Bukkit.getPlayer(possible1.getName());
                            DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("dd.MM.yyy");
                            dtf2 = dtf2.withLocale(Locale.GERMAN);
                            LocalDate timeQUit = LocalDate.parse((Main.getSaves().getString("Playerdaten." + t.getUniqueId().toString() + ".QuitDate")), dtf2);
                            LocalDate now = LocalDate.now();
                            //check if 83 Days have pasted
                            if(now.isEqual(timeQUit.plusDays(83))) {
                                p.sendMessage("§cDer Leiter deiner Gilde ist seit 83 Tagen inaktiv!\nKommt er innerhalb der nächsten 7 Tage nicht zurück,\nwird der Spieler " + nextLeiter.getName() + " der nächste Leiter!");
                            }else if(now.isEqual(timeQUit.plusDays(90))) {
                                ToolBox.removePlayerfromGilde(nextLeiter.getUniqueId().toString(), gilde);
                                ToolBox.addPlayertoGilde(nextLeiter.getUniqueId().toString(), gilde, "Leiter");
                                p.sendMessage("§aDer Spieler " + nextLeiter.getName() + " ist der neue Leiter.");
                            }
                        }
                        if(Date1.isAfter(Date2)) {
                            Player nextLeiter = Bukkit.getPlayer(possible2.getName());
                            DateTimeFormatter dtf3 = DateTimeFormatter.ofPattern("dd.MM.yyy");
                            dtf3 = dtf3.withLocale(Locale.GERMAN);
                            LocalDate timeQUit = LocalDate.parse((Main.getSaves().getString("Playerdaten." + t.getUniqueId().toString() + ".QuitDate")), dtf3);
                            LocalDate now = LocalDate.now();
                            //check if 83 Days have pasted
                            if(now.isEqual(timeQUit.plusDays(83))) {
                                p.sendMessage("§cDer Leiter deiner Gilde ist seit 83 Tagen inaktiv!\nKommt er innerhalb der nächsten 7 Tage nicht zurück,\nwird der Spieler " + nextLeiter.getName() + " der nächste Leiter!");
                            }else if(now.isEqual(timeQUit.plusDays(90))) {
                                ToolBox.removePlayerfromGilde(nextLeiter.getUniqueId().toString(), gilde);
                                ToolBox.addPlayertoGilde(nextLeiter.getUniqueId().toString(), gilde, "Leiter");
                                p.sendMessage("§aDer Spieler " + nextLeiter.getName() + " ist der neue Leiter.");
                            }
                        }
                        if(Date1.isEqual(Date2)) {
                            DateTimeFormatter dtf4 = DateTimeFormatter.ofPattern("HH:mm");
                            dtf4 = dtf4.withLocale(Locale.GERMAN);
                            LocalDate time1 = LocalDate.parse((Main.getSaves().getString("Playerdaten." + possible1.getUniqueId().toString() + ".Beitrittsuhrzeit")), dtf4);
                            LocalDate time2 = LocalDate.parse((Main.getSaves().getString("Playerdaten." + possible1.getUniqueId().toString() + ".Beitrittsuhrzeit")), dtf4);

                            if(time1.isBefore(time2)) {
                                Player nextLeiter = Bukkit.getPlayer(possible1.getName());
                                DateTimeFormatter dtf5 = DateTimeFormatter.ofPattern("dd.MM.yyy");
                                dtf5 = dtf5.withLocale(Locale.GERMAN);
                                LocalDate timeQUit = LocalDate.parse((Main.getSaves().getString("Playerdaten." + t.getUniqueId().toString() + ".QuitDate")), dtf5);
                                LocalDate now = LocalDate.now();
                                //check if 83 Days have pasted
                                if(now.isEqual(timeQUit.plusDays(83))) {
                                    p.sendMessage("§cDer Leiter deiner Gilde ist seit 83 Tagen inaktiv!\nKommt er innerhalb der nächsten 7 Tage nicht zurück,\nwird der Spieler " + nextLeiter.getName() + " der nächste Leiter!");
                                }else if(now.isEqual(timeQUit.plusDays(90))) {
                                    ToolBox.removePlayerfromGilde(nextLeiter.getUniqueId().toString(), gilde);
                                    ToolBox.addPlayertoGilde(nextLeiter.getUniqueId().toString(), gilde, "Leiter");
                                    p.sendMessage("§aDer Spieler " + nextLeiter.getName() + " ist der neue Leiter.");
                                }
                            }
                            if(time1.isAfter(time2)) {
                                Player nextLeiter = Bukkit.getPlayer(possible2.getName());
                                DateTimeFormatter dtf6 = DateTimeFormatter.ofPattern("dd.MM.yyy");
                                dtf6 = dtf6.withLocale(Locale.GERMAN);
                                LocalDate timeQUit = LocalDate.parse((Main.getSaves().getString("Playerdaten." + t.getUniqueId().toString() + ".QuitDate")), dtf6);
                                LocalDate now = LocalDate.now();
                                //check if 83 Days have pasted
                                if(now.isEqual(timeQUit.plusDays(83))) {
                                    p.sendMessage("§cDer Leiter deiner Gilde ist seit 83 Tagen inaktiv!\nKommt er innerhalb der nächsten 7 Tage nicht zurück,\nwird der Spieler " + nextLeiter.getName() + " der nächste Leiter!");
                                }else if(now.isEqual(timeQUit.plusDays(90))) {
                                    ToolBox.removePlayerfromGilde(nextLeiter.getUniqueId().toString(), gilde);
                                    ToolBox.addPlayertoGilde(nextLeiter.getUniqueId().toString(), gilde, "Leiter");
                                    p.sendMessage("§aDer Spieler " + nextLeiter.getName() + " ist der neue Leiter.");
                                }

                            }
                            if(time1.isEqual(time2)) {
                                ArrayList<Player> random = new ArrayList<>();
                                random.add(possible1);
                                random.add(possible2);
                                Random r  = new Random();
                                int i = r.nextInt(random.size());

                                Player nextLeiter = random.get(i);
                                DateTimeFormatter dtf6 = DateTimeFormatter.ofPattern("dd.MM.yyy");
                                dtf6 = dtf6.withLocale(Locale.GERMAN);
                                LocalDate timeQUit = LocalDate.parse((Main.getSaves().getString("Playerdaten." + t.getUniqueId().toString() + ".QuitDate")), dtf6);
                                LocalDate now = LocalDate.now();
                                //check if 83 Days have pasted
                                if(now.isEqual(timeQUit.plusDays(83))) {
                                    p.sendMessage("§cDer Leiter deiner Gilde ist seit 83 Tagen inaktiv!\nKommt er innerhalb der nächsten 7 Tage nicht zurück,\nwird der Spieler " + nextLeiter.getName() + " der nächste Leiter!");
                                }else if(now.isEqual(timeQUit.plusDays(90))) {
                                    ToolBox.removePlayerfromGilde(nextLeiter.getUniqueId().toString(), gilde);
                                    ToolBox.addPlayertoGilde(nextLeiter.getUniqueId().toString(), gilde, "Leiter");
                                    p.sendMessage("§aDer Spieler " + nextLeiter.getName() + " ist der neue Leiter.");
                                }
                            }
                        }



                    }
                }

            }else {
                return;
            }
        }
    }
}
