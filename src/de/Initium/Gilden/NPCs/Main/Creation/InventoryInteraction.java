package de.Initium.Gilden.NPCs.Main.Creation;

import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldguard.LocalPlayer;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.domains.DefaultDomain;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.GlobalProtectedRegion;
import com.sk89q.worldguard.protection.regions.ProtectedCuboidRegion;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import com.sk89q.worldguard.protection.regions.RegionContainer;
import de.Initium.Gilden.Main.Main;
import de.Initium.Gilden.Main.ToolBox;
import de.Initium.Gilden.Main.UUIDManipulation;
import de.Initium.Gilden.NPCs.Main.InventoryDispatcher;
import de.Initium.Gilden.NPCs.Main.ItemStackManipulation;
import me.arcaniax.hdb.api.HeadDatabaseAPI;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.hover.content.Text;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class InventoryInteraction extends JavaPlugin
{
    static ArrayList<Player> awaitingNewGildename = new ArrayList<>();
    static HashMap<Player, String> awaitingConfirmation = new HashMap<>();

    public static void clickedItemDecision(InventoryClickEvent e)
    {

        if(e.getCursor().equals(ItemStackManipulation.BackArrow())) {
            Player player = (Player) e.getWhoClicked();
            player.openInventory(ToolBox.getLastInv(player));
        }
        if(e.getView().getTitle().equalsIgnoreCase("Item-Auswahl")) {
                if(e.getView().getTopInventory().getItem(4) == null) {
                    if(!(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(" ")))   {
                        if(!(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§fZurück"))) {
                            e.getInventory().setItem(4, e.getCurrentItem());
                            e.getInventory().getItem(4).setAmount(1);
                        }else {
                            Player player = (Player) e.getWhoClicked();
                            player.openInventory(ToolBox.getLastInv(player));
                        }
                    }else {
                        e.setCancelled(true);
                    }

                }else {
                    Main.getSaves().set("gilden." + ToolBox.getGildeNameOfPlayer((Player) e.getWhoClicked()) + ".Information.GuiBlock", null);
                    Main.saveSaves();
                    e.getInventory().setItem(4, null);
                }
        }
        if(e.getView().getTitle().equalsIgnoreCase("Gilde-Liste")) {
            if (e.getClickedInventory().equals(e.getView().getTopInventory())) {
                Player p4 = (Player) e.getWhoClicked();
                if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§fZurück")) {
                    Player player = (Player) e.getWhoClicked();
                    player.openInventory(ToolBox.getLastInv(player));
                }else {
                    Request((Player) e.getWhoClicked(), e.getCurrentItem().getItemMeta().getDisplayName());
                    p4.closeInventory();
                }

            }

        }
        if(e.getClickedInventory() != null) {
            if (e.getClickedInventory().equals(e.getView().getTopInventory())) {
                if(e.getCurrentItem() != null) {
                    if(!e.getView().getTitle().equalsIgnoreCase("Gilde-Liste")) {
                        switch (e.getCurrentItem().getType()) {
                            case DIAMOND:
                                diamondClicked((Player) e.getWhoClicked());
                                break;
                            case NAME_TAG:
                                Player p = (Player) e.getWhoClicked();
                                TagClick(p, ToolBox.getGildeNameOfPlayer(p));
                                CreationResponse.cleanup(p);
                                break;
                            case GRASS_BLOCK:
                                Player p1 = (Player) e.getWhoClicked();
                                GrassClick(p1, ToolBox.getGildeNameOfPlayer(p1));
                                CreationResponse.cleanup(p1);
                                break;
                            case BARRIER:
                                Player p2 = (Player) e.getWhoClicked();
                                p2.closeInventory();
                                CreationResponse.cleanup(p2);
                                break;
                            case IRON_DOOR:
                                Player p3 = (Player) e.getWhoClicked();
                                p3.closeInventory();
                                p3.sendMessage("Willst du die Gilde wirklich verlassen?");
                                TextComponent tc = new TextComponent();
                                TextComponent tc2 = new TextComponent();
                                tc.setText("§7[§cJa§7] ");
                                tc.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/confirmClick"));
                                tc.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new Text("§cBestätigen")));

                                tc2.setText("§7[§aNein§7] ");
                                tc2.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/denyClick"));
                                tc2.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new Text("§aAbbrechen")));
                                p3.spigot().sendMessage(tc, tc2);
                                CreationResponse.cleanup(p3);

                                break;
                            case WRITABLE_BOOK:
                                WrittenBookClick((Player) e.getWhoClicked());
                                break;
                            case LIGHT_GRAY_STAINED_GLASS_PANE:
                                e.setCancelled(true);
                                break;
                            case GRAY_STAINED_GLASS_PANE:
                                e.setCancelled(true);
                                break;
                            case BOOK:
                                BookClick((Player) e.getWhoClicked(), ToolBox.getGildeNameOfPlayer((Player) e.getWhoClicked()));
                                break;
                            case PLAYER_HEAD:
                                if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§fInsel-Shop")) {
                                    if(ToolBox.hasInsel().contains(ToolBox.getGildeNameOfPlayer((Player) e.getWhoClicked()))) {
                                        e.getWhoClicked().sendMessage("§cDeine Gilde hat bereits eine Insel");
                                        e.getWhoClicked().closeInventory();
                                    }else {
                                        clickedGlobe((Player) e.getWhoClicked());
                                    }

                                }
                                if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§fZurück")) {
                                    Player player = (Player) e.getWhoClicked();
                                    player.openInventory(ToolBox.getLastInv(player));
                                }
                                if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§fMittlere Insel")) {

                                    Integer needed = Integer.parseInt(Main.getConfiguration().get("settings.Inseln.Groß").toString());
                                    Double Purse = Double.parseDouble(ToolBox.getBankValue(ToolBox.getGildeNameOfPlayer((Player) e.getWhoClicked())));


                                    RegionContainer container = WorldGuard.getInstance().getPlatform().getRegionContainer();
                                    RegionManager regions = container.get(BukkitAdapter.adapt(e.getWhoClicked().getWorld()));


                                    if(ToolBox.getPlayerNumber(ToolBox.getGildeNameOfPlayer((Player) e.getWhoClicked())) >=7) {
                                    if(Purse >= needed) {
                                        double remaining = Purse - needed;
                                        Main.getSaves().set("gilden." + ToolBox.getGildeNameOfPlayer((Player) e.getWhoClicked()) + ".Information." + "Bank-Wert", remaining);
                                        Main.saveSaves();
                                        Random r = new Random();
                                        int Inselgröße = 22;

                                        int i = r.nextInt(Inselgröße);

                                        Main.getInselConfig().set("Inseln.Mittel." + ToolBox.getFreeMittel().get(i) + ".owner", ToolBox.getGildeNameOfPlayer((Player) e.getWhoClicked()));
                                        Main.saveInselConfig();
                                        e.getWhoClicked().closeInventory();

                                        TextComponent tcr = new TextComponent();
                                        TextComponent tcr2 = new TextComponent();

                                        tcr.setText("Der Kauf war erfolgreich!\nKlicke hier um der neuen Insel deiner Gilde zu teleportieren ");
                                        tcr2.setText("[Klick]");
                                        tcr2.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/region tp insel-" + ToolBox.getFreeMittel().get(i)));
                                        ProtectedRegion region = regions.getRegion("insel-" + ToolBox.getFreeMittel().get(i));
                                        if(region != null) {
                                            DefaultDomain regionOwner = region.getOwners();
                                            regionOwner.addPlayer(e.getWhoClicked().getName());
                                            region.setOwners(regionOwner);
                                            e.getWhoClicked().spigot().sendMessage(tcr, tcr2);

                                            DefaultDomain regionMember = region.getMembers();
                                            for(String s  : Main.getSaves().getStringList("gilden." + ToolBox.getGildeNameOfPlayer((Player) e.getWhoClicked()) + ".raenge.Mitglieder")) {
                                               Player pl = Bukkit.getPlayer(UUIDManipulation.getPlayernameByUUID(s));
                                               regionMember.addPlayer(pl.getName());
                                               region.setMembers(regionMember);
                                               break;
                                            }
                                        }

                                    }else {
                                        e.getWhoClicked().sendMessage("Deine Gilde hat zu wenig Kronen!");
                                    }

                                    }else {
                                        e.getWhoClicked().sendMessage("Du kannst noch keine Mittlere Insel Kaufen!");
                                    }



                                }
                                if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§fGroße Insel")) {
                                    if(ToolBox.getPlayerNumber(ToolBox.getGildeNameOfPlayer((Player) e.getWhoClicked())) >=10) {
                                        Integer needed = Integer.parseInt(Main.getConfiguration().get("settings.Inseln.Groß").toString());
                                        Double Purse = Double.parseDouble(ToolBox.getBankValue(ToolBox.getGildeNameOfPlayer((Player) e.getWhoClicked())));

                                        if(Purse >= needed) {
                                            double remaining = Purse - needed;
                                            Main.getSaves().set("gilden." + ToolBox.getGildeNameOfPlayer((Player) e.getWhoClicked()) + ".Information." + "Bank-Wert", remaining);
                                            Main.saveSaves();
                                            RegionContainer container = WorldGuard.getInstance().getPlatform().getRegionContainer();
                                            RegionManager regions = container.get(BukkitAdapter.adapt(e.getWhoClicked().getWorld()));
                                            Random r = new Random();
                                            int Inselgröße = 10;

                                            int i = r.nextInt(Inselgröße);
                                            ToolBox.getFreeGross().get(i);

                                            Main.getInselConfig().set("Inseln.Groß." + ToolBox.getFreeGross().get(i) + ".owner", ToolBox.getGildeNameOfPlayer((Player) e.getWhoClicked()));
                                            Main.saveInselConfig();
                                            e.getWhoClicked().closeInventory();

                                            TextComponent tc0 = new TextComponent();
                                            TextComponent tc3 = new TextComponent();

                                            tc0.setText("Der Kauf war erfolgreich!\nKlicke hier um der neuen Insel deiner Gilde zu teleportieren ");
                                            tc3.setText("[Klick]");
                                            tc3.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/region tp insel-" + ToolBox.getFreeGross().get(i)));
                                            ProtectedRegion region = regions.getRegion("insel-" + ToolBox.getFreeMittel().get(i));
                                            DefaultDomain regionOwner = region.getOwners();
                                            regionOwner.addPlayer(WorldGuardPlugin.inst().wrapPlayer((Player) e.getWhoClicked()));
                                            region.setOwners(regionOwner);
                                            e.getWhoClicked().spigot().sendMessage(tc0, tc3);
                                        }else {
                                            e.getWhoClicked().sendMessage("Deine Gilde hat zu wenig Kronen!");
                                        }

                                    }else {
                                        e.getWhoClicked().sendMessage("Du kannst noch keine Große Insel Kaufen!");
                                    }

                                }
                                if(e.getView().getTitle().equals("Gilde-Anfragen")) {
                                    Player p5 = (Player) e.getWhoClicked();
                                    Inventory inv = Bukkit.createInventory(null, 9, e.getCurrentItem().getItemMeta().getDisplayName());

                                    ItemStack green = new ItemStack(Material.GREEN_WOOL);
                                    ItemMeta gm = green.getItemMeta();

                                    gm.setDisplayName("§aAnfrage annehmen");


                                    green.setItemMeta(gm);

                                    ItemStack red = new ItemStack(Material.RED_WOOL);
                                    ItemMeta rm = red.getItemMeta();

                                    rm.setDisplayName("§cAnfrage ablehnen");

                                    red.setItemMeta(rm);
                                    inv.setItem(0, ItemStackManipulation.getPlaceholder2());
                                    inv.setItem(1, ItemStackManipulation.getPlaceholder2());
                                    inv.setItem(2, ItemStackManipulation.getPlaceholder2());
                                    inv.setItem(3, green);
                                    inv.setItem(4, ItemStackManipulation.getPlaceholder2());
                                    inv.setItem(5, red);
                                    inv.setItem(6, ItemStackManipulation.getPlaceholder2());
                                    inv.setItem(7, ItemStackManipulation.getPlaceholder2());
                                    inv.setItem(8, ItemStackManipulation.getPlaceholder2());
                                    p5.openInventory(inv);

                                    InventoryDispatcher.getInInventory().put(p5, true);
                                    InventoryDispatcher.getActivePlayers().add(p5);
                                }

                                break;
                            case RED_WOOL:
                                OfflinePlayer t = Bukkit.getOfflinePlayer(e.getView().getTitle());

                                if (!t.isOnline()) {
                                    e.getWhoClicked().sendMessage("Du hast den Spieler erfolgreich abgelehnt!");
                                    Main.getSaves().set("PlayerDaten." + t.getUniqueId().toString() + ".RequestDate", null);
                                    Main.getSaves().set("PlayerDaten." + t.getUniqueId().toString() + ".RequestTime", null);
                                    Main.getSaves().set("PlayerDaten." + t.getUniqueId().toString() + ".WaitingForReturn", "Deine Anfrage an die Gilde  " + ToolBox.getGildeNameOfPlayer((Player) e.getWhoClicked()) + " wurde abgelehnt!");
                                    List<String> newList = Main.getSaves().getStringList("gilden." + e.getCurrentItem().getItemMeta().getDisplayName() + ".OpenRequest");
                                    newList.remove(t.getUniqueId().toString());
                                    Main.getSaves().set("gilden." + ToolBox.getGildeNameOfPlayer((Player) e.getWhoClicked()) + ".OpenRequest", newList);
                                    Main.saveSaves();

                                } else {

                                    Player online = t.getPlayer();
                                    Main.getSaves().set("PlayerDaten." + online.getUniqueId().toString() + ".RequestDate", null);
                                    Main.getSaves().set("PlayerDaten." + online.getUniqueId().toString() + ".RequestTime", null);
                                    e.getWhoClicked().sendMessage("Du hast den Spieler erfolgreich abgelehnt!");
                                    online.sendMessage("§cDeine Anfrage an die Gilde §r" + ToolBox.getGildeNameOfPlayer((Player) e.getWhoClicked()) + " §cwurde abgelehnt!");
                                    List<String> newList = Main.getSaves().getStringList("gilden." + e.getCurrentItem().getItemMeta().getDisplayName() + ".OpenRequest");
                                    newList.remove(online.getUniqueId().toString());
                                    Main.getSaves().set("gilden." + ToolBox.getGildeNameOfPlayer((Player) e.getWhoClicked()) + ".OpenRequest" , newList);
                                    Main.saveSaves();

                                }
                                e.getWhoClicked().closeInventory();
                                break;
                            case GREEN_WOOL:
                                OfflinePlayer t2 = Bukkit.getOfflinePlayer(e.getView().getTitle());

                                if (!t2.isOnline()) {
                                    if(!ToolBox.getallPlayers().contains(t2.getUniqueId().toString())) {
                                        e.getWhoClicked().sendMessage("Du hast den Spieler erfolgreich angenommen!");
                                        Main.getSaves().set("PlayerDaten." + t2.getUniqueId().toString() + ".RequestDate", null);
                                        Main.getSaves().set("PlayerDaten." + t2.getUniqueId().toString() + ".RequestTime", null);
                                        Main.getSaves().set("PlayerDaten." + t2.getUniqueId().toString() + ".WaitingForReturn", "Deine Anfrage an die Gilde  " + ToolBox.getGildeNameOfPlayer((Player) e.getWhoClicked()) + " wurde angenommen!");
                                        ToolBox.addPlayertoGilde(t2.getUniqueId().toString(), ToolBox.getGildeNameOfPlayer((Player) e.getWhoClicked()), "Mitglieder");
                                        List<String> newList = Main.getSaves().getStringList("gilden." + e.getCurrentItem().getItemMeta().getDisplayName() + ".OpenRequest");
                                        newList.remove(t2.getUniqueId().toString());
                                        Main.getSaves().set("gilden." + ToolBox.getGildeNameOfPlayer((Player) e.getWhoClicked()) + ".OpenRequest", newList);
                                        Main.saveSaves();
                                    }else {
                                        e.getWhoClicked().sendMessage("Dieser Spieler ist bereits in einer Gilde");
                                        List<String> newList = Main.getSaves().getStringList("gilden." + e.getCurrentItem().getItemMeta().getDisplayName() + ".OpenRequest");
                                        newList.remove(t2.getUniqueId().toString());
                                        Main.getSaves().set("gilden." + ToolBox.getGildeNameOfPlayer((Player) e.getWhoClicked()) + ".OpenRequest", newList);
                                        Main.saveSaves();
                                    }

                                } else {
                                    Player online = t2.getPlayer();
                                    if(!ToolBox.getallPlayers().contains(online.getUniqueId().toString())) {
                                        Main.getSaves().set("PlayerDaten." + online.getUniqueId().toString() + ".RequestDate", null);
                                        Main.getSaves().set("PlayerDaten." + online.getUniqueId().toString() + ".RequestTime", null);
                                        e.getWhoClicked().sendMessage("Du hast den Spieler erfolgreich angenommen!");
                                        ToolBox.addPlayertoGilde(t2.getUniqueId().toString(), ToolBox.getGildeNameOfPlayer((Player) e.getWhoClicked()), "Mitglieder");
                                        online.sendMessage("§aDeine Anfrage an die Gilde §r" + ToolBox.getGildeNameOfPlayer((Player) e.getWhoClicked()) + " §awurde angenommen!");
                                        List<String> newList = Main.getSaves().getStringList("gilden." + e.getCurrentItem().getItemMeta().getDisplayName() + ".OpenRequest");
                                        newList.remove(online.getUniqueId().toString());
                                        Main.getSaves().set("gilden." + ToolBox.getGildeNameOfPlayer((Player) e.getWhoClicked()) + ".OpenRequest", newList);
                                        Main.saveSaves();
                                    }else  {
                                        e.getWhoClicked().sendMessage("Dieser Spieler ist bereits in einer Gilde");
                                        List<String> newList = Main.getSaves().getStringList("gilden." + e.getCurrentItem().getItemMeta().getDisplayName() + ".OpenRequest");
                                        newList.remove(online.getUniqueId().toString());
                                        Main.getSaves().set("gilden." + ToolBox.getGildeNameOfPlayer((Player) e.getWhoClicked()) + ".OpenRequest" , newList);
                                        Main.saveSaves();
                                    }

                                }
                                e.getWhoClicked().closeInventory();
                                break;

                            case CHEST:
                                if(Main.getSaves().get("gilden." + ToolBox.getGildeNameOfPlayer((Player) e.getWhoClicked()) + ".Information." + "hasBoughtItemSelect").equals(false)) {
                                    Integer Cost = Integer.parseInt(Main.getConfiguration().get("settings.Gilden.TagCost").toString());
                                    Double Purse = Double.parseDouble(Main.getSaves().get("gilden." + ToolBox.getGildeNameOfPlayer((Player) e.getWhoClicked()) + ".Information.Bank-Wert").toString());

                                    if(Purse >= Cost) {
                                        double remaining = Purse - Cost;
                                        Main.getSaves().set("gilden." + ToolBox.getGildeNameOfPlayer((Player) e.getWhoClicked()) + ".Information.Bank-Wert", remaining);
                                        Main.getSaves().set("gilden." + ToolBox.getGildeNameOfPlayer((Player) e.getWhoClicked()) + ".Information." + "hasBoughtItemSelect", true);
                                        Main.getSaves();
                                        e.getWhoClicked().closeInventory();
                                        e.getWhoClicked().sendMessage("Du kannst deiner Gilde nun ein Item auswählen!");


                                    }else {
                                        e.getWhoClicked().closeInventory();
                                        e.getWhoClicked().sendMessage("Der Wert deiner Gilden-Kasse ist zu niedrig!");
                                    }
                                }else {
                                    Inventory inv2 = Bukkit.createInventory(null, 9*2, "Item-Auswahl");
                                    inv2.setItem(0, ItemStackManipulation.getPlaceholder2());
                                    inv2.setItem(1, ItemStackManipulation.getPlaceholder2());
                                    inv2.setItem(2, ItemStackManipulation.getPlaceholder2());
                                    inv2.setItem(3, ItemStackManipulation.getPlaceholder2());
                                    inv2.setItem(5, ItemStackManipulation.getPlaceholder2());
                                    inv2.setItem(6, ItemStackManipulation.getPlaceholder2());
                                    inv2.setItem(7, ItemStackManipulation.getPlaceholder2());
                                    inv2.setItem(8, ItemStackManipulation.getPlaceholder2());
                                    inv2.setItem(13, ItemStackManipulation.BackArrow());
                                    inv2.setItem(9, ItemStackManipulation.getPlaceholder2());
                                    inv2.setItem(10, ItemStackManipulation.getPlaceholder2());
                                    inv2.setItem(11, ItemStackManipulation.getPlaceholder2());
                                    inv2.setItem(12, ItemStackManipulation.getPlaceholder2());
                                    inv2.setItem(14, ItemStackManipulation.getPlaceholder2());
                                    inv2.setItem(15, ItemStackManipulation.getPlaceholder2());
                                    inv2.setItem(16, ItemStackManipulation.getPlaceholder2());
                                    inv2.setItem(17, ItemStackManipulation.getPlaceholder2());

                                    if(Main.getSaves().get("gilden." + ToolBox.getGildeNameOfPlayer((Player) e.getWhoClicked()) + ".Information.GuiBlock") != null) {
                                        inv2.setItem(4, Main.getSaves().getItemStack("gilden." + ToolBox.getGildeNameOfPlayer((Player) e.getWhoClicked()) + ".Information.GuiBlock"));
                                    }

                                    e.getWhoClicked().openInventory(inv2);

                                }
                                break;


                            default:
                                e.getWhoClicked().sendMessage("HIER NICHT");
                        }
                    }

                }

            }
        }
    }

    //Gilde Erstellen
    public static void diamondClicked(Player pl)
    {
        pl.closeInventory();
        InventoryDispatcher.getInInventory().remove(pl);
        pl.sendMessage("Gebe den Namen deiner neuen Gilde ein:");
        awaitingNewGildename.add(pl);
    }

    public static void Request(Player p, String Input) {

        if(ToolBox.getallPlayers().contains(p.getUniqueId().toString())) {
            p.sendMessage("§cDu kannst diesen Befehel nicht nutzen!");
        }else {
            String gilde = Input;
            if(ToolBox.checkGildeExists(gilde)) {
                if(!(Main.getSaves().getStringList("gilden." + gilde + ".OpenRequest").contains(p.getUniqueId().toString()))) {
                    for (String allLeiter : Main.getSaves().getStringList("gilden." + gilde + ".raenge.Leiter")) {
                        p.sendMessage("Deine Anfrage wurde erfolgreich gestellt!");
                        List<String> newList = Main.getSaves().getStringList("gilden." + gilde + ".OpenRequest");
                        newList.add(p.getUniqueId().toString());
                        Main.getSaves().set("gilden." + gilde + ".OpenRequest", newList);
                        DateTimeFormatter dtF = DateTimeFormatter.ofPattern("dd.MM.yyy");
                        String Date = dtF.format(LocalDate.now());
                        java.util.Date Uhrzeit = new Date();
                        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
                        String time = sdf.format(Uhrzeit);
                        Main.getSaves().set("PlayerDaten." + p.getUniqueId().toString() + ".RequestDate", Date);
                        Main.getSaves().set("PlayerDaten." + p.getUniqueId().toString() + ".RequestTime", time);
                        Main.saveSaves();
                        OfflinePlayer Leiteroff = Bukkit.getOfflinePlayer(UUIDManipulation.getPlayernameByUUID(allLeiter));
                        if (Leiteroff.isOnline()) {
                            TextComponent tc = new TextComponent();
                            TextComponent t2 = new TextComponent();


                            Player Leiter = Leiteroff.getPlayer();
                            tc.setText("Der Spieler §6" + p.getName() + " §rhat deiner Gilde\neine Beitrittsanfrage gestellt.\nUm das Anfrage-Menu zu öffnen klicke ");
                            t2.setText("§c[Hier]");
                            t2.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new Text("§aAnfrage-Menu")));
                            t2.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/gilde Requests"));

                            Leiter.spigot().sendMessage(tc, t2);
                        } else {
                            for (String allStellvertreter : Main.getSaves().getStringList("gilden." + gilde + ".raenge.Stellvertreter")) {
                                OfflinePlayer Stellvertreteroff = Bukkit.getOfflinePlayer(UUIDManipulation.getPlayernameByUUID(allStellvertreter));
                                if (Stellvertreteroff.isOnline()) {
                                    Player Stellvertreter = Stellvertreteroff.getPlayer();
                                    TextComponent tc = new TextComponent();
                                    TextComponent t2 = new TextComponent();
                                    tc.setText("Der Spieler §6" + p.getName() + " §rhat deiner Gilde eine Beitrittsanfrage gestellt.\nUm das Anfrage-Menu zu öffnen klicke ");
                                    t2.setText("§c[Hier]");
                                    t2.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new Text("§aAnfrage-Menu")));
                                    t2.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/gilde Requests"));

                                    Stellvertreter.spigot().sendMessage(tc, t2);

                                }
                            }
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

    public static void clickedGlobe(Player p) {
        Inventory inv = Bukkit.createInventory(null, 9*3, "Insel-Shop");
        HeadDatabaseAPI api = new HeadDatabaseAPI();
        if(ToolBox.getPlayerNumber(ToolBox.getGildeNameOfPlayer(p)) >= 7) {
            ItemStack Mittel = api.getItemHead("17006");
            ItemMeta m = Mittel.getItemMeta();
            m.setDisplayName("§fMittlere Insel");
            ArrayList<String> Lore = new ArrayList<>();
            Lore.add("§fKaufe eine Mittlere Insel für deine Gilde");
            Integer needed = Integer.parseInt(Main.getConfiguration().get("settings.Inseln.Mittel").toString());
            Lore.add("§aPreis: §6" + needed.toString() + " §aKronen");
            m.setLore(Lore);
            Mittel.setItemMeta(m);
            inv.setItem(3, Mittel);
        }else {
            ItemStack Mittel = api.getItemHead("17006");
            ItemMeta m = Mittel.getItemMeta();
            m.setDisplayName("§fMittlere Insel");
            ArrayList<String> Lore = new ArrayList<>();
            Integer missing = 7 - ToolBox.getPlayerNumber(ToolBox.getGildeNameOfPlayer(p));
            Lore.add("§cKaufe Gesperrt!");
            Lore.add("§cFehlende Mitglieder: §6" + missing);
            m.setLore(Lore);
            Mittel.setItemMeta(m);
            inv.setItem(3, Mittel);
        }

        if(ToolBox.getPlayerNumber(ToolBox.getGildeNameOfPlayer(p)) >= 10) {
            ItemStack Groß = api.getItemHead("25842");
            ItemMeta g = Groß.getItemMeta();
            g.setDisplayName("§fGroße Insel");
            ArrayList<String> Lore2 = new ArrayList<>();
            Lore2.add("§fKaufe eine Große Insel für deine Gilde");
            Integer needed = Integer.parseInt(Main.getConfiguration().get("settings.Inseln.Groß").toString());
            Lore2.add("§aPreis: §6" + needed.toString() + " §aKronen");
            g.setLore(Lore2);
            Groß.setItemMeta(g);
            inv.setItem(5, Groß);

        }else {
            ItemStack Groß = api.getItemHead("25842");
            ItemMeta g = Groß.getItemMeta();
            g.setDisplayName("§fGroße Insel");
            ArrayList<String> Lore2 = new ArrayList<>();
            Integer missing = 10 - ToolBox.getPlayerNumber(ToolBox.getGildeNameOfPlayer(p));
            Lore2.add("§cKaufe Gesperrt!");
            Lore2.add("§cFehlende Mitglieder: §6" + missing);
            g.setLore(Lore2);
            Groß.setItemMeta(g);
            inv.setItem(5, Groß);
        }
        inv.setItem(22, ItemStackManipulation.BackArrow());
        p.openInventory(inv);

    }

    public static void TagClick(Player pl, String gildenname) {
        Integer Cost = Integer.parseInt(Main.getConfiguration().get("settings.Gilden.TagCost").toString());
        Double Purse = Double.parseDouble(Main.getSaves().get("gilden." + gildenname + ".Information.Bank-Wert").toString());

        if(Main.getSaves().getBoolean("gilden." + gildenname + ".Information.hasBoughtTag")) {
            pl.sendMessage("§cDeine Gilde hat bereits Tag-Rechte!");
            pl.closeInventory();
        }else {
            if(ToolBox.getPlayerNumber(gildenname) >= Integer.parseInt(Main.getConfiguration().get("settings.Gilden.MinPlayersForTag").toString())) {
                if(Purse >= Cost) {
                    Double newPurse = Purse - Cost;
                    Main.getSaves().set("gilden." + gildenname + ".Information.Bank-Wert", newPurse);
                    Main.saveSaves();
                    pl.closeInventory();
                    pl.sendMessage("Du kannst jetzt einen Tag für deine Gilde setzen!");
                    Main.getSaves().set("gilden." + gildenname + ".Information." + "hasBoughtTag", true);
                    Main.saveSaves();
                }else {
                    pl.sendMessage("§cDer Gilden-Wert deiner Gilde ist zu niedrig!\nPreis: " + Cost + " Kronen\nGilden-Wert: " + Purse + " Kronen");
                    pl.closeInventory();
                }
            }else {
                pl.sendMessage("Deine Gilde hat zu wenig Mitglieder!\nBenötigte Mitgliederanzahl: "  + Main.getConfiguration().get("settings.Gilden.MinPlayersForTag").toString() + "\nMomentane Mitgliederanzahl: " + ToolBox.getPlayerNumber(gildenname));
                pl.closeInventory();
            }
        }
    }

    public static void GrassClick(Player pl, String gildenname) {
        Integer Cost = Integer.parseInt(Main.getConfiguration().get("settings.Gilden.HomePunktCost").toString());
        Double Purse = Double.parseDouble(Main.getSaves().get("gilden." + gildenname + ".Information.Bank-Wert").toString());
        if(Main.getSaves().getBoolean("gilden." + gildenname + ".Information.hasBoughtHome")) {
            pl.sendMessage("§cDeine Gilde hat bereits Home-Rechte!");
            pl.closeInventory();
        }else {
            if(Purse >= Cost) {
                Double newPurse = Purse - Cost;
                Main.getSaves().set("gilden." + gildenname + ".Information.Bank-Wert", newPurse);
                Main.saveSaves();
                pl.closeInventory();
                pl.sendMessage("Du kannst jetzt einen Home-Punkt für deine Gilde setzten!");
                Main.getSaves().set("gilden." + gildenname + ".Information." + "hasBoughtHome", true);
                Main.saveSaves();
            }else {
                pl.sendMessage("§cDer Gilden-Wert deiner Gilde ist zu niedrig!\nPreis: " + Cost + " Kronen\nGilden-Wert: " + Purse + " Kronen");
            }
        }
    }
    public static void WrittenBookClick(Player p) {
        Inventory inv = Bukkit.createInventory(null, 9*4, "Gilde-Liste");

        for(String allGilden : ToolBox.getAllGildennamen()) {
            if(Main.getSaves().getBoolean("gilden." + allGilden + ".Information.RequestOn")) {
                if(Main.getSaves().get("gilden." + allGilden + ".Information.GuiBlock") == null) {
                    String s1 = Main.getConfiguration().get("settings.Gilden.defaultGuiBlock").toString();
                    Material m1 = Material.matchMaterial(s1);
                    ItemStack item = new ItemStack(m1);
                    ItemMeta im = item.getItemMeta();
                    im.setDisplayName(allGilden);
                    im.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                    item.setItemMeta(im);
                    inv.addItem(item);

                }else {
                    ItemStack item = Main.getSaves().getItemStack("gilden." + allGilden + ".Information.GuiBlock");
                    ItemMeta im = item.getItemMeta();

                    im.setDisplayName(allGilden);

                    item.setItemMeta(im);


                    inv.addItem(item);
                }
            }
        }
        inv.setItem(35, ItemStackManipulation.BackArrow());
        p.openInventory(inv);
    }
    public static void BookClick(Player p, String gildenname) {
        Inventory inv = Bukkit.createInventory(null, 9*4, "Gilde-Anfragen");

        for (String all : Main.getSaves().getStringList("gilden." + gildenname + ".OpenRequest")) {
            OfflinePlayer offline = Bukkit.getOfflinePlayer(UUIDManipulation.getPlayernameByUUID(all));
            if(!offline.isOnline()) {
                ItemStack Skull = new ItemStack(Material.PLAYER_HEAD);
                SkullMeta sm = (SkullMeta) Skull.getItemMeta();

                sm.setDisplayName(offline.getName());
                sm.setOwningPlayer(offline);
                ArrayList<String> lore = new ArrayList<>();
                lore.add("§fAnfrageDatum: §6" + Main.getSaves().get("PlayerDaten." + offline.getUniqueId().toString() + ".RequestDate"));
                lore.add("§fAnrfrageZeitPunkt: §6" + Main.getSaves().get("PlayerDaten." + offline.getUniqueId().toString() + ".RequestTime"));
                sm.setLore(lore);
                Skull.setItemMeta(sm);
                inv.addItem(Skull);
            }else {
                Player online = offline.getPlayer();
                ItemStack Skull = new ItemStack(Material.PLAYER_HEAD);
                SkullMeta sm = (SkullMeta) Skull.getItemMeta();

                sm.setDisplayName(online.getName());
                sm.setOwningPlayer(online);
                ArrayList<String> lore = new ArrayList<>();
                lore.add("§fAnfrageDatum: §6" + Main.getSaves().get("PlayerDaten." + online.getUniqueId().toString() + ".RequestDate"));
                lore.add("§fAnfrageZeitPunkt: §6" + Main.getSaves().get("PlayerDaten." + online.getUniqueId().toString() + ".RequestTime"));
                sm.setLore(lore);


                Skull.setItemMeta(sm);

                inv.addItem(Skull);
            }
        }
        inv.setItem(35, ItemStackManipulation.BackArrow());
        p.openInventory(inv);
    }

    public static void diamondClicked(Player pl, String MSG)
    {
        //"".equalsIgnoreCase("");
        if(ToolBox.validateGildeName(MSG))
        {
            pl.sendMessage(
                    "Der Gildenname \"" + MSG + "\" ist ungültig.\n" +
                            "Er muss folgende Kriterien erfüllen:\n" +
                            "- Länge: Mindestens 4 Buchstaben\n" +
                            "- Nur folgender Character dürfen enthalten sein: [A-Z], [a-z]");
            pl.sendMessage(ChatColor.RED + "Versuche es erneut");
            return;
        }

        String gildenname = MSG;
        if(ToolBox.checkGildeExists(gildenname))
        {
            pl.sendMessage("Die Gilde " + gildenname + " existiert bereits");
            pl.sendMessage(ChatColor.RED + "Versuche es erneut");
            return;
        }

        awaitingNewGildename.remove(pl);
        awaitingConfirmation.put(pl, gildenname);

        TextComponent tc = new TextComponent();
        tc.setText(ChatColor.GREEN + "BESTÄTIGEN");
        tc.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/gilde create-confirm " + pl.getName() + "_" + gildenname));
        tc.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new Text("Bestätige den eingegeben Gildennamen")));
        TextComponent tc2 = new TextComponent();
        tc2.setText(ChatColor.RED + "ABBRECHEN"); // /gilde create-quit doppelkool_TesTGilde
        tc2.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/gilde create-quit " + pl.getName() + "_" + gildenname));
        tc2.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new Text("Breche das Erstellen der Gilde ab")));

        pl.sendMessage("Möchtest du den Namen " + gildenname + " verwenden?");
        pl.spigot().sendMessage(tc);
        pl.spigot().sendMessage(tc2);
    }

    public static ArrayList<Player> getAwaitingNewGildename()
    {
        return awaitingNewGildename;
    }
    public static HashMap<Player, String> getAwaitingConfirmation()
    {
        return awaitingConfirmation;
    }
}