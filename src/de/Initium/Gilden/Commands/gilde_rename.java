package de.Initium.Gilden.Commands;

import de.Initium.Gilden.Commands.gilde_Main;
import de.Initium.Gilden.Main.Main;
import de.Initium.Gilden.Main.ToolBox;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class gilde_rename extends JavaPlugin {

    public static void execute(Integer nr, String[] Input)
    {
        Player p = gilde_Main.getPlayer(nr);
        if(ToolBox.getallPlayers().contains(p.getUniqueId().toString())) {
            String gilde = ToolBox.getGildeNameOfPlayer(p);

                if(ToolBox.validateGildeName(Input[1])) {
                    p.sendMessage("§cDieser Name ist nicht gültig!");
                    return;
                }
                    FileConfiguration getConfig = Main.getSaves();
                    for (String s : getConfig.getConfigurationSection("gilden").getKeys(false)) {
                     getConfig.set("gilden."+ s, getConfig.get("gilden." + Input[1]));
                     p.sendMessage("key " + s );
                     Main.saveSaves();
            }


                p.sendMessage("§aDu hast deine Gilde erfolgreich umbenannt!");

        }else {
            p.sendMessage("§cDu kannst diesen Befehl nicht ausführen, da du in keiner Gilde bist!");
        }
    }





}
