package de.Initium.Gilden.Commands.Spawn;

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

    public static void execute(Integer nr) {
        Player player = gilde_Main.getPlayer(nr);
        if(ToolBox.getallPlayers().contains(player.getUniqueId().toString())) {
            String gilde = ToolBox.getGildeNameOfPlayer(player);
            if(ToolBox.getGildeRankByPlayer(gilde, player.getUniqueId().toString()).equalsIgnoreCase("Leiter") || ToolBox.getGildeRankByPlayer(gilde, player.getUniqueId().toString()).equalsIgnoreCase("Fositzender")) {


                if(Main.getSaves().getBoolean("gilden." + gilde + ".Information." + "hasSetSpawn") == true)  {

                    String message = "§aDer Spieler §6" + player.getName() + " §ahat den Gilden-Spawn neu gesetzt!";
                    FileConfiguration saves = Main.getSaves();
                    saves.set("gilden." + gilde + ".Information." + "SpawnLocation." +"Spawn.World", player.getWorld().getName());
                    saves.set("gilden." + gilde + ".Information." + "SpawnLocation." +"Spawn.X", (player.getLocation().getX()));
                    saves.set("gilden." + gilde + ".Information." + "SpawnLocation." +"Spawn.Y", (player.getLocation().getY()));
                    saves.set("gilden." + gilde + ".Information." + "SpawnLocation." +"Spawn.Z", (player.getLocation().getZ()));
                    saves.set("gilden." + gilde + ".Information." + "SpawnLocation." +"Spawn.Yaw", (player.getLocation().getYaw()));
                    saves.set("gilden." + gilde + ".Information." + "SpawnLocation." +"Spawn.Pitch", (player.getLocation().getPitch()));
                    Main.saveSaves();

                    //Sending Message

                    ArrayList<String> playersofGilde = ToolBox.unserializeArrayList(ToolBox.getallPlayersinGilde(ToolBox.getGildeNameOfPlayer(Bukkit.getPlayerExact(player.getName()))));
                    player.sendMessage("[§a" + ToolBox.getGildeNameOfPlayer(Bukkit.getPlayerExact(player.getName())) + "§r] "+ message);
                    for (String all : playersofGilde) {
                        if (!(player.getUniqueId().toString().equals(all))) {
                            String temp = UUIDManipulation.getOnlinePlayerByUUID(all);
                            if (!(temp.equals(""))) {
                                (Bukkit.getPlayerExact(temp)).sendMessage("[§a" + ToolBox.getGildeNameOfPlayer(Bukkit.getPlayerExact(player.getName())) + "§r] "+ message);
                            }
                        }
                    }
                }else if(Main.getSaves().getBoolean("gilden." + gilde + ".Information." + "hasSetSpawn") == false){
                    String message2 = "§aDer Spieler §6" + player.getName() + " §ahat den Gilden-Spawn gesetzt!";
                    FileConfiguration saves = Main.getSaves();
                    saves.set("gilden." + gilde + ".Information." + "SpawnLocation." +"Spawn.World", player.getWorld().getName());
                    saves.set("gilden." + gilde + ".Information." + "SpawnLocation." +"Spawn.X", player.getLocation().getX());
                    saves.set("gilden." + gilde + ".Information." + "SpawnLocation." +"Spawn.Y", player.getLocation().getY());
                    saves.set("gilden." + gilde + ".Information." + "SpawnLocation." +"Spawn.Z", player.getLocation().getZ());
                    saves.set("gilden." + gilde + ".Information." + "SpawnLocation." +"Spawn.Yaw", player.getLocation().getYaw());
                    saves.set("gilden." + gilde + ".Information." + "SpawnLocation." +"Spawn.Pitch", player.getLocation().getPitch());
                    Main.getSaves().set("gilden." + gilde + ".Information." + "hasSetSpawn", true);
                    Main.saveSaves();

                    //Sending Message

                    ArrayList<String> playersofGilde = ToolBox.unserializeArrayList(ToolBox.getallPlayersinGilde(ToolBox.getGildeNameOfPlayer(Bukkit.getPlayerExact(player.getName()))));
                    player.sendMessage("[§a" + ToolBox.getGildeNameOfPlayer(Bukkit.getPlayerExact(player.getName())) + "§r] "+ message2);
                    for (String all : playersofGilde) {
                        if (!(player.getUniqueId().toString().equals(all))) {
                            String temp = UUIDManipulation.getOnlinePlayerByUUID(all);
                            if (!(temp.equals(""))) {
                                (Bukkit.getPlayerExact(temp)).sendMessage("[§a" + ToolBox.getGildeNameOfPlayer(Bukkit.getPlayerExact(player.getName())) + "§r] "+ message2);
                            }
                        }
                    }
                }
                player.sendMessage("§aDu hast dein Gilden-Spawn erfolgreich gesetzt!");
            }else {
                player.sendMessage("§cDu kannst kein Gilden-Spawn setzten!");
            }
        }else {
            player.sendMessage("§cDu kannst diesen Befehl nicht nutzen da du in keiner Gilde bist!");
        }
    }
}
