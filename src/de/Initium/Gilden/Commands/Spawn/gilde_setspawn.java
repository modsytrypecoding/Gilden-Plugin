package de.Initium.Gilden.Commands.Spawn;

import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.util.Location;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import com.sk89q.worldguard.protection.regions.RegionContainer;
import com.sk89q.worldguard.protection.regions.RegionQuery;
import de.Initium.Gilden.Commands.gilde_Main;
import de.Initium.Gilden.Main.Main;
import de.Initium.Gilden.Main.ToolBox;
import de.Initium.Gilden.Main.UUIDManipulation;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class gilde_setspawn extends JavaPlugin {

    public static void execute(Integer nr, String[] args) {
        Player player = gilde_Main.getPlayer(nr);
        if (args.length == 1) {
            if (ToolBox.getallPlayers().contains(player.getUniqueId().toString())) {
                String gilde = ToolBox.getGildeNameOfPlayer(player);
                if (ToolBox.getGildeRankByPlayer(gilde, player.getUniqueId().toString()).equalsIgnoreCase("Leiter") || ToolBox.getGildeRankByPlayer(gilde, player.getUniqueId().toString()).equalsIgnoreCase("Stellvertreter")) {
                    if (ToolBox.hasInsel().contains(gilde)) {
                        if (Main.getSaves().getBoolean("gilden." + gilde + ".Information." + "hasSetSpawn") == true) {

                            String message = "ßaDer Spieler ß6" + player.getName() + " ßahat den Gilden-Spawn neu gesetzt!";
                            RegionContainer container = WorldGuard.getInstance().getPlatform().getRegionContainer();
                            RegionManager regions = container.get(BukkitAdapter.adapt(player.getWorld()));
                            ProtectedRegion region = regions.getRegion("insel-" + ToolBox.getGildenInselID(gilde));

                            Location Loc = BukkitAdapter.adapt(player.getLocation());
                            RegionQuery query = container.createQuery();
                            ApplicableRegionSet set = query.getApplicableRegions(Loc);
                            for (ProtectedRegion region1 : set) {
                                if (region1.equals(region)) {
                                    Main.getInselConfig().set("Inseln." + ToolBox.getInselType(Integer.parseInt(ToolBox.getGildenInselID(gilde))) + "." + ToolBox.getGildenInselID(gilde) + ".InselSpawnLocation." + "Spawn.World", player.getWorld().getName());
                                    Main.getInselConfig().set("Inseln." + ToolBox.getInselType(Integer.parseInt(ToolBox.getGildenInselID(gilde))) + "." + ToolBox.getGildenInselID(gilde) + ".InselSpawnLocation." + "Spawn.X", (player.getLocation().getX()));
                                    Main.getInselConfig().set("Inseln." + ToolBox.getInselType(Integer.parseInt(ToolBox.getGildenInselID(gilde))) + "." + ToolBox.getGildenInselID(gilde) + ".InselSpawnLocation." + "Spawn.Y", (player.getLocation().getY()));
                                    Main.getInselConfig().set("Inseln." + ToolBox.getInselType(Integer.parseInt(ToolBox.getGildenInselID(gilde))) + "." + ToolBox.getGildenInselID(gilde) + ".InselSpawnLocation." + "Spawn.Z", (player.getLocation().getZ()));
                                    Main.getInselConfig().set("Inseln." + ToolBox.getInselType(Integer.parseInt(ToolBox.getGildenInselID(gilde))) + "." + ToolBox.getGildenInselID(gilde) + ".InselSpawnLocation." + "Spawn.Yaw", (player.getLocation().getYaw()));
                                    Main.getInselConfig().set("Inseln." + ToolBox.getInselType(Integer.parseInt(ToolBox.getGildenInselID(gilde))) + "." + ToolBox.getGildenInselID(gilde) + ".InselSpawnLocation." + "Spawn.Pitch", (player.getLocation().getPitch()));
                                    Main.saveInselConfig();
                                    player.sendMessage("Du hast erfolgreich dein Insel-Spawn gesetzt!");

                                    ArrayList<String> playersofGilde = ToolBox.unserializeArrayList(ToolBox.getallPlayersinGilde(ToolBox.getGildeNameOfPlayer(Bukkit.getPlayerExact(player.getName()))));
                                    player.sendMessage("[ßa" + ToolBox.getGildeNameOfPlayer(Bukkit.getPlayerExact(player.getName())) + "ßr] " + message);
                                    for (String all : playersofGilde) {
                                        if (!(player.getUniqueId().toString().equals(all))) {
                                            String temp = UUIDManipulation.getOnlinePlayerByUUID(all);
                                            if (!(temp.equals(""))) {
                                                (Bukkit.getPlayerExact(temp)).sendMessage("[ßa" + ToolBox.getGildeNameOfPlayer(Bukkit.getPlayerExact(player.getName())) + "ßr] " + message);
                                            }
                                        }
                                    }
                                } else {
                                    player.sendMessage("Du befindest dich auﬂerhalb deiner Insel.");
                                }

                            }

                            //Sending Message


                        }
                        if (Main.getSaves().getBoolean("gilden." + gilde + ".Information." + "hasSetSpawn") == false) {
                            String message2 = "ßaDer Spieler ß6" + player.getName() + " ßahat den Gilden-Spawn gesetzt!";
                            RegionContainer container = WorldGuard.getInstance().getPlatform().getRegionContainer();
                            RegionManager regions = container.get(BukkitAdapter.adapt(player.getWorld()));
                            ProtectedRegion region = regions.getRegion("insel-" + ToolBox.getGildenInselID(gilde));

                            Location Loc = BukkitAdapter.adapt(player.getLocation());
                            RegionQuery query = container.createQuery();
                            ApplicableRegionSet set = query.getApplicableRegions(Loc);
                            for (ProtectedRegion region1 : set) {
                                if (region1.equals(region)) {
                                    Main.getInselConfig().set("Inseln." + ToolBox.getInselType(Integer.parseInt(ToolBox.getGildenInselID(gilde))) + "." + ToolBox.getGildenInselID(gilde) + ".InselSpawnLocation." + "Spawn.World", player.getWorld().getName());
                                    Main.getInselConfig().set("Inseln." + ToolBox.getInselType(Integer.parseInt(ToolBox.getGildenInselID(gilde))) + "." + ToolBox.getGildenInselID(gilde) + ".InselSpawnLocation." + "Spawn.X", (player.getLocation().getX()));
                                    Main.getInselConfig().set("Inseln." + ToolBox.getInselType(Integer.parseInt(ToolBox.getGildenInselID(gilde))) + "." + ToolBox.getGildenInselID(gilde) + ".InselSpawnLocation." + "Spawn.Y", (player.getLocation().getY()));
                                    Main.getInselConfig().set("Inseln." + ToolBox.getInselType(Integer.parseInt(ToolBox.getGildenInselID(gilde))) + "." + ToolBox.getGildenInselID(gilde) + ".InselSpawnLocation." + "Spawn.Z", (player.getLocation().getZ()));
                                    Main.getInselConfig().set("Inseln." + ToolBox.getInselType(Integer.parseInt(ToolBox.getGildenInselID(gilde))) + "." + ToolBox.getGildenInselID(gilde) + ".InselSpawnLocation." + "Spawn.Yaw", (player.getLocation().getYaw()));
                                    Main.getInselConfig().set("Inseln." + ToolBox.getInselType(Integer.parseInt(ToolBox.getGildenInselID(gilde))) + "." + ToolBox.getGildenInselID(gilde) + ".InselSpawnLocation." + "Spawn.Pitch", (player.getLocation().getPitch()));
                                    Main.getSaves().set("gilden." + gilde + ".Information." + "hasSetSpawn", true);
                                    Main.saveSaves();
                                    Main.saveInselConfig();
                                    player.sendMessage("Du hast erfolgreich dein Insel-Spawn gesetzt!");

                                    ArrayList<String> playersofGilde = ToolBox.unserializeArrayList(ToolBox.getallPlayersinGilde(ToolBox.getGildeNameOfPlayer(Bukkit.getPlayerExact(player.getName()))));
                                    player.sendMessage("[ßa" + ToolBox.getGildeNameOfPlayer(Bukkit.getPlayerExact(player.getName())) + "ßr] " + message2);
                                    for (String all : playersofGilde) {
                                        if (!(player.getUniqueId().toString().equals(all))) {
                                            String temp = UUIDManipulation.getOnlinePlayerByUUID(all);
                                            if (!(temp.equals(""))) {
                                                (Bukkit.getPlayerExact(temp)).sendMessage("[ßa" + ToolBox.getGildeNameOfPlayer(Bukkit.getPlayerExact(player.getName())) + "ßr] " + message2);
                                            }
                                        }
                                    }
                                } else {
                                    player.sendMessage("Du befindest dich auﬂerhalb deiner Insel.");
                                }

                            }

                            //Sending Message


                        }
                    } else {
                        player.sendMessage("ßcDeine Gilde hat noch keine Insel");
                    }


                } else {
                    player.sendMessage("ßcDu kannst kein Gilden-Spawn setzten!");
                }
            } else {
                player.sendMessage("ßcDu kannst diesen Befehl nicht nutzen da du in keiner Gilde bist!");
            }
        }
        if (args.length == 2) {
            String gilde = args[1];
            if(ToolBox.checkGildeExists(args[1])) {
                RegionContainer container = WorldGuard.getInstance().getPlatform().getRegionContainer();
                RegionManager regions = container.get(BukkitAdapter.adapt(player.getWorld()));
                ProtectedRegion region = regions.getRegion("insel-" + ToolBox.getGildenInselID(gilde));

                Location Loc = BukkitAdapter.adapt(player.getLocation());
                RegionQuery query = container.createQuery();
                ApplicableRegionSet set = query.getApplicableRegions(Loc);
                for (ProtectedRegion region1 : set) {
                    if (region1.equals(region)) {
                        Main.getInselConfig().set("Inseln." + ToolBox.getInselType(Integer.parseInt(ToolBox.getGildenInselID(gilde))) + "." + ToolBox.getGildenInselID(gilde) + ".InselSpawnLocation." + "Spawn.World", player.getWorld().getName());
                        Main.getInselConfig().set("Inseln." + ToolBox.getInselType(Integer.parseInt(ToolBox.getGildenInselID(gilde))) + "." + ToolBox.getGildenInselID(gilde) + ".InselSpawnLocation." + "Spawn.X", (player.getLocation().getX()));
                        Main.getInselConfig().set("Inseln." + ToolBox.getInselType(Integer.parseInt(ToolBox.getGildenInselID(gilde))) + "." + ToolBox.getGildenInselID(gilde) + ".InselSpawnLocation." + "Spawn.Y", (player.getLocation().getY()));
                        Main.getInselConfig().set("Inseln." + ToolBox.getInselType(Integer.parseInt(ToolBox.getGildenInselID(gilde))) + "." + ToolBox.getGildenInselID(gilde) + ".InselSpawnLocation." + "Spawn.Z", (player.getLocation().getZ()));
                        Main.getInselConfig().set("Inseln." + ToolBox.getInselType(Integer.parseInt(ToolBox.getGildenInselID(gilde))) + "." + ToolBox.getGildenInselID(gilde) + ".InselSpawnLocation." + "Spawn.Yaw", (player.getLocation().getYaw()));
                        Main.getInselConfig().set("Inseln." + ToolBox.getInselType(Integer.parseInt(ToolBox.getGildenInselID(gilde))) + "." + ToolBox.getGildenInselID(gilde) + ".InselSpawnLocation." + "Spawn.Pitch", (player.getLocation().getPitch()));
                        Main.getSaves().set("gilden." + gilde + ".Information." + "hasSetSpawn", true);
                        Main.saveSaves();
                        Main.saveInselConfig();
                        player.sendMessage("Du hast erfolgreich den Insel-Spawn der Gilde gesetzt!");
                    }else {
                        player.sendMessage("ßcBitte begebe dich zuerst auf die Insel!");
                    }

                }
            }else {
                player.sendMessage("Diese Gilde existiert nicht!");
            }


        }
    }
}
