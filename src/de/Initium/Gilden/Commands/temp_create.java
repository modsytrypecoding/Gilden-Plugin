package de.Initium.Gilden.Commands;

import de.Initium.Gilden.Main.Main;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class temp_create extends JavaPlugin
{
    public static void execute(Integer nr, String temp_gilden_name)
    {
        Player pl = gilde_Main.getPlayer(nr);

        //ToDo: Check if Player is already in a Gilde

        //Check if Gilde already exists
        if(Main.getSaves().isConfigurationSection("gilden." + temp_gilden_name))
        {
            pl.sendMessage("Die Gilde mit dem Namen \"" + temp_gilden_name + "\" ist bereits vorhanden");
            return;
        }

        //Check if Gilde-Name is valid
        if(temp_gilden_name.equals("") || temp_gilden_name.matches("[0-9]") || temp_gilden_name.length() <= 2)
        {
            pl.sendMessage(
                    "Der Gildenname \"" + temp_gilden_name + "\" ist ungültig." +
                    "Er muss folgende Kriterien erfüllen:\n" +
                    "- Länge: Mindestens 3 Buchstaben\n" +
                    "- Nur folgender Character dürfen enthalten sein: [A-Z], [a-z]");
            return;
        }

        Main.getSaves().set("gilden." + temp_gilden_name + ".players", pl.getUniqueId().toString());
        Main.saveSaves();
        pl.sendMessage("Die Gilde wurde erfolgreich erstellt. Du bist der Gildenleiter von " + temp_gilden_name);
    }
}
