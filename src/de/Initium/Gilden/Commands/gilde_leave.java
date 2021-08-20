package de.Initium.Gilden.Commands;

import de.Initium.Gilden.Commands.Request.deny;
import de.Initium.Gilden.Main.Main;
import de.Initium.Gilden.Main.ToolBox;
import de.Initium.Gilden.Main.UUIDManipulation;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.hover.content.Text;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.HashMap;

public class gilde_leave extends JavaPlugin{
    public static HashMap<Player, Inventory> CustomInv = new HashMap<>();

    public static void execute(Integer nr) {

        Player p = gilde_Main.getPlayer(nr);
        String gilde = ToolBox.getGildeNameOfPlayer(p);
        if (ToolBox.getallPlayers().contains(p.getUniqueId().toString())) {
                ArrayList<String> playersofGilde = ToolBox.unserializeArrayList(ToolBox.getallPlayersinGilde(gilde));
                deny.hasCanceled.remove(p.getPlayer());
                if(ToolBox.getGildeRankByPlayer(gilde, p.getUniqueId().toString()).equalsIgnoreCase("Leiter")) {
                    if(Main.getSaves().getStringList("gilden." + gilde + ".raenge.Leiter").size() > 1) {
                        ToolBox.removeWorldGuardPerms(p);
                        ToolBox.removePlayerfromGilde(p.getUniqueId().toString(), gilde);

                        p.sendMessage("§aDu hast die Gilde erfolgreich verlassen!");
                    }else {
                        if(Main.getSaves().getStringList("gilden." + gilde + ".raenge.Stellvertreter").isEmpty()) {
                            p.sendMessage("Löst du die Gilde jetzt auf wird diese gelöscht da kein Stellvertreter gefunden werden konnte.");
                            p.sendMessage("Willst du die Gilde wirklich auflösen ohne einen Stellvertreter zu ernennen?");

                            TextComponent tc = new TextComponent();
                            TextComponent tc2 = new TextComponent();

                            tc.setText("§7[§cJa§7] ");
                            tc.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/confirm"));
                            tc.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new Text("§cBestätigen")));

                            tc2.setText("§7[§aNein§7] ");
                            tc2.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/deny"));
                            tc2.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new Text("§aAbbrechen")));
                            p.spigot().sendMessage(tc, tc2);
                        }else {
                            if(Main.getSaves().getStringList("gilden." + gilde + ".raenge.Stellvertreter").size() == 1) {
                                OfflinePlayer t = Bukkit.getOfflinePlayer(UUIDManipulation.getPlayernameByUUID(Main.getSaves().getStringList("gilden." + gilde + ".raenge.Stellvertreter").get(0)));
                                if(!t.isOnline()) {
                                    ToolBox.removePlayerfromGilde(t.getUniqueId().toString(), gilde);
                                    ToolBox.addPlayertoGilde(t.getUniqueId().toString(), gilde, "Leiter");
                                    ToolBox.removeWorldGuardPerms(p);
                                    ToolBox.removePlayerfromGilde(p.getUniqueId().toString(), gilde);
                                    p.sendMessage("§aDu hast die Gilde erfolgreich verlassen!");
                                }else {
                                    Player online = t.getPlayer();
                                    ToolBox.removePlayerfromGilde(online.getUniqueId().toString(), gilde);
                                    ToolBox.addPlayertoGilde(online.getUniqueId().toString(), gilde, "Leiter");
                                    ToolBox.removeWorldGuardPerms(p);
                                    ToolBox.removePlayerfromGilde(p.getUniqueId().toString(), gilde);
                                    online.sendMessage("Dein Leiter hat die Gilde verlassen\nDu bist nun der neue Leiter!");
                                    p.sendMessage("§aDu hast die Gilde erfolgreich verlassen!");
                                }

                            }
                            if(Main.getSaves().getStringList("gilden." + gilde + ".raenge.Stellvertreter").size() == 2) {
                                Inventory inv =  Bukkit.createInventory(null, 9*1, "Leiter Auswahl");

                                for(String s : Main.getSaves().getStringList("gilden." + gilde + ".raenge.Stellvertreter")) {
                                    OfflinePlayer offline = Bukkit.getOfflinePlayer(UUIDManipulation.getPlayernameByUUID(s));
                                    if(!offline.isOnline()) {
                                        ItemStack skull = new ItemStack(Material.SKELETON_SKULL);
                                        SkullMeta sm = (SkullMeta) skull.getItemMeta();
                                        sm.setDisplayName(offline.getName());
                                        skull.setItemMeta(sm);

                                        ItemStack PlaceHolder = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
                                        ItemMeta pm =  PlaceHolder.getItemMeta();
                                        pm.setDisplayName("");
                                        PlaceHolder.setItemMeta(pm);
                                        inv.setItem(0, PlaceHolder);
                                        inv.setItem(1, PlaceHolder);
                                        inv.setItem(2, PlaceHolder);
                                        inv.setItem(4, PlaceHolder);
                                        inv.setItem(6, PlaceHolder);
                                        inv.setItem(7, PlaceHolder);
                                        inv.setItem(8, PlaceHolder);
                                        inv.addItem(skull);
                                    }else {
                                        Player online = offline.getPlayer();
                                        ItemStack skull = new ItemStack(Material.PLAYER_HEAD);
                                        SkullMeta sm = (SkullMeta) skull.getItemMeta();
                                        sm.setDisplayName(online.getName());
                                        sm.setOwningPlayer(online);
                                        skull.setItemMeta(sm);

                                        ItemStack PlaceHolder = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
                                        ItemMeta pm =  PlaceHolder.getItemMeta();
                                        pm.setDisplayName("");
                                        PlaceHolder.setItemMeta(pm);
                                        inv.setItem(0, PlaceHolder);
                                        inv.setItem(1, PlaceHolder);
                                        inv.setItem(2, PlaceHolder);
                                        inv.setItem(4, PlaceHolder);
                                        inv.setItem(6, PlaceHolder);
                                        inv.setItem(7, PlaceHolder);
                                        inv.setItem(8, PlaceHolder);
                                        inv.addItem(skull);
                                    }



                                }
                                p.openInventory(inv);


                            }
                            if(Main.getSaves().getStringList("gilden." + gilde + ".raenge.Stellvertreter").size() == 3) {
                                Inventory inv =  Bukkit.createInventory(null, 9*1, "Leiter Auswahl");

                                for(String s : Main.getSaves().getStringList("gilden." + gilde + ".raenge.Stellvertreter")) {
                                    OfflinePlayer offline = Bukkit.getOfflinePlayer(UUIDManipulation.getPlayernameByUUID(s));
                                    if(!offline.isOnline()) {
                                        ItemStack skull = new ItemStack(Material.SKELETON_SKULL);
                                        SkullMeta sm = (SkullMeta) skull.getItemMeta();
                                        sm.setDisplayName(offline.getName());
                                        skull.setItemMeta(sm);

                                        ItemStack PlaceHolder = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
                                        ItemMeta pm =  PlaceHolder.getItemMeta();
                                        pm.setDisplayName("");
                                        PlaceHolder.setItemMeta(pm);
                                        inv.setItem(0, PlaceHolder);
                                        inv.setItem(1, PlaceHolder);
                                        inv.setItem(3, PlaceHolder);
                                        inv.setItem(5, PlaceHolder);
                                        inv.setItem(7, PlaceHolder);
                                        inv.setItem(8, PlaceHolder);
                                        inv.addItem(skull);
                                    }else {
                                        Player online = offline.getPlayer();
                                        ItemStack skull = new ItemStack(Material.PLAYER_HEAD);
                                        SkullMeta sm = (SkullMeta) skull.getItemMeta();
                                        sm.setDisplayName(online.getName());
                                        sm.setOwningPlayer(online);
                                        skull.setItemMeta(sm);

                                        ItemStack PlaceHolder = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
                                        ItemMeta pm =  PlaceHolder.getItemMeta();
                                        pm.setDisplayName("");
                                        PlaceHolder.setItemMeta(pm);
                                        inv.setItem(0, PlaceHolder);
                                        inv.setItem(1, PlaceHolder);
                                        inv.setItem(3, PlaceHolder);
                                        inv.setItem(5, PlaceHolder);
                                        inv.setItem(7, PlaceHolder);
                                        inv.setItem(8, PlaceHolder);
                                        inv.addItem(skull);
                                    }



                                }
                                p.openInventory(inv);
                            }
                        }
                    }
                }else {
                    ToolBox.removeWorldGuardPerms(p);
                    ToolBox.removePlayerfromGilde(p.getUniqueId().toString(), gilde);
                    p.sendMessage("Du hast die Gilde erfolgreich verlassen!");
                }


        }else {
            p.sendMessage("§cDu kannst diesen Befehl nicht aus führen, da du dich in keiner Gilde befindest!");
        }

    }

}
