package de.Initium.Gilden.Commands;

import de.Initium.Gilden.Main.ToolBox;
import org.bukkit.plugin.java.JavaPlugin;

public class gilde_testforToolBox extends JavaPlugin {

    public static void execute(String[] args) {
        ToolBox.getgildebyTag(args[1]);
    }
}
