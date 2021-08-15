package de.Initium.Gilden.Commands.Request;

import de.Initium.Gilden.Commands.gilde_Main;
import de.Initium.Gilden.Main.Main;
import de.Initium.Gilden.Main.ToolBox;
import de.Initium.Gilden.Main.UUIDManipulation;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.hover.content.Text;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;


public class gilde_request extends JavaPlugin {
    public static HashMap<Player, String> awaiting = new HashMap<>();

    public static void execute(Integer nr, String[] args) {



        Player p = gilde_Main.getPlayer(nr);
        if(ToolBox.getallPlayers().contains(p.getUniqueId().toString())) {
            p.sendMessage("§cDu kannst diesen Befehel nicht nutzen!");
        }else {
            String gilde = args[1];
            if(ToolBox.checkGildeExists(args[1])) {
                if(!(awaiting.containsKey(p.getPlayer()) && awaiting.containsValue(gilde))) {
                    for (String allLeiter : Main.getSaves().getStringList("gilden." + gilde + ".raenge.Leiter")) {
                        p.sendMessage("Deine Anfrage wurde erfolgreich gestellt!");
                        OfflinePlayer Leiteroff = Bukkit.getOfflinePlayer(UUIDManipulation.getPlayernameByUUID(allLeiter));
                        if (Leiteroff.isOnline()) {
                            awaiting.put(p.getPlayer(), gilde);
                            TextComponent tc = new TextComponent();
                            TextComponent tc2 = new TextComponent();
                            TextComponent tc3 = new TextComponent();
                            TextComponent tc4 = new TextComponent();
                            TextComponent tc5 = new TextComponent();


                            tc.setText("[§aJA§r]");
                            tc.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/confirm " + p.getName()));
                            tc.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new Text("§aAnnehmen!")));

                            tc2.setText("[§cNEIN§r]");
                            tc2.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/deny " + p.getName()));
                            tc2.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new Text("§cAblehnen!")));
                            tc3.setText("Klicke auf ");
                            tc4.setText(" um ihn anzunehmen oder auf ");
                            tc5.setText(" um ihn\nabzulehnen");


                            Player Leiter = Leiteroff.getPlayer();
                            Leiter.sendMessage("_______________________________________________");
                            Leiter.sendMessage(" ");
                            Leiter.sendMessage("Der Spieler " + p.getName() + " möchte der Gilde beitreten!");
                            Leiter.spigot().sendMessage(tc3, tc, tc4, tc2, tc5);
                            Leiter.sendMessage("_______________________________________________");
                        } else {
                            for (String allStellvertreter : Main.getSaves().getStringList("gilden." + gilde + ".raenge.Stellvertreter")) {
                                OfflinePlayer Stellvertreteroff = Bukkit.getOfflinePlayer(UUIDManipulation.getPlayernameByUUID(allStellvertreter));
                                if (Stellvertreteroff.isOnline()) {
                                    Player Stellvertreter = Stellvertreteroff.getPlayer();
                                    awaiting.put(p.getPlayer(), gilde);
                                    TextComponent tc = new TextComponent();
                                    TextComponent tc2 = new TextComponent();
                                    TextComponent tc3 = new TextComponent();
                                    TextComponent tc4 = new TextComponent();
                                    TextComponent tc5 = new TextComponent();


                                    tc.setText("[§aJA§r]");
                                    tc.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/confirm " + p.getName()));
                                    tc.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new Text("§aAnnehmen!")));

                                    tc2.setText("[§cNEIN§r]");
                                    tc2.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/deny " + p.getName()));
                                    tc2.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new Text("§cAblehnen!")));
                                    tc3.setText("Klicke auf ");
                                    tc4.setText(" um ihn anzunehmen oder auf ");
                                    tc5.setText(" um ihn\nabzulehnen");


                                    Stellvertreter.sendMessage("_______________________________________________");
                                    Stellvertreter.sendMessage(" ");
                                    Stellvertreter.sendMessage("Der Spieler " + p.getName() + " möchte der Gilde beitreten!");
                                    Stellvertreter.spigot().sendMessage(tc3, tc, tc4, tc2, tc5);
                                    Stellvertreter.sendMessage("_______________________________________________");
                                }
                            }
                            p.sendMessage("Deine Anfrage kann momentan nicht entgegen genommen werden\nda keine Leiter oder Stellvertreter dieser Gilde online ist!");
                        }
                    }
                } else {
                    p.sendMessage("Du hast dieser Gilde bereits eine Anfrage gestellt!");
                }


            }else {
                p.sendMessage("Die von dir angegebene Gilde existiert nicht!");
            }
        }

    }
}
