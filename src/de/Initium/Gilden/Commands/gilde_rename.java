package de.Initium.Gilden.Commands;

import de.Initium.Gilden.Commands.gilde_Main;
import de.Initium.Gilden.Main.Main;
import de.Initium.Gilden.Main.ToolBox;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class gilde_rename extends JavaPlugin {

    public static void execute(Integer nr, String[] Input)
    {
        Player p = gilde_Main.getPlayer(nr);
        if(ToolBox.getallPlayers().contains(p.getUniqueId().toString())) {
            String gilde = ToolBox.getGildeNameOfPlayer(p);

            if(gilde.equals(Input[1])) {
                p.sendMessage("§cDeine Gilde hat bereits diesen Namen!");
            }else {
                if(ToolBox.getGildeRankByPlayer(gilde, p.getUniqueId().toString()).equalsIgnoreCase("Leiter")) {
                    if(ToolBox.validateGildeName(Input[1])) {
                        p.sendMessage("Der Gildenname \"" + gilde + "\" ist ungültig." +
                                "Er muss folgende Kriterien erfüllen:\n" +
                                "- Länge: Mindestens 3 Buchstaben\n" +
                                "- Nur folgender Character dürfen enthalten sein: [A-Z], [a-z]");
                        return;
                    }

                    FileConfiguration config = Main.getSaves();
                    Map<String, Object> vals = config.getConfigurationSection("gilden." + gilde).getValues(true);
                    String toDot = ("gilden." + Input[1]).equals("") ? "" : ".";
                    for (String s : vals.keySet()){
                        System.out.println(s);
                        Object val = vals.get(s);
                        if (val instanceof List)
                            val = new ArrayList<>((List)val);
                        config.set("gilden." + Input[1] + toDot + s, val);
                    }
                    config.set("gilden." + gilde, null);
                    Main.saveSaves();
                    p.sendMessage("§aDu hast deine Gilde erfolgreich umbenannt!");
                }else {
                    p.sendMessage("§cDu darfst den Namen der Gilde nicht bearbeiten!");
                }

            }



            }else {
            p.sendMessage("§cDu kannst diesen Befehl nicht ausführen, da du in keiner Gilde bist!");
        }


        }
    }

