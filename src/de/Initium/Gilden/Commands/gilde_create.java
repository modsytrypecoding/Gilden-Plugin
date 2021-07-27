package de.Initium.Gilden.Commands;


import de.Initium.Gilden.Main.ToolBox;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class gilde_create extends JavaPlugin
{
    public static void execute(Integer nr, String temp_gilden_name)
    {
        Player pl = gilde_Main.getPlayer(nr);

        if(!(ToolBox.getGildeNameOfPlayer(pl).equals("")))
        {
            pl.sendMessage("Du bist bereits in einer Gilde und kannst aufgrunddessen keine gründen.");
            return;
        }

        //Check if Gilde already exists
        if(ToolBox.checkGildeExists(temp_gilden_name))
        {
            pl.sendMessage("Die Gilde mit dem Namen \"" + temp_gilden_name + "\" ist bereits vorhanden");
            return;
        }

        //Check if Gilde-Name is valid
        if(ToolBox.validateGildeName(temp_gilden_name))
        {
            pl.sendMessage(
                    "Der Gildenname \"" + temp_gilden_name + "\" ist ungültig." +
                    "Er muss folgende Kriterien erfüllen:\n" +
                    "- Länge: Mindestens 3 Buchstaben\n" +
                    "- Nur folgender Character dürfen enthalten sein: [A-Z], [a-z]");
            return;
        }

        ToolBox.createGilde(temp_gilden_name, pl.getUniqueId().toString(), pl.getPlayer());
        pl.sendMessage("Die Gilde wurde erfolgreich erstellt. Du bist der Gildenleiter von " + temp_gilden_name);
    }
}