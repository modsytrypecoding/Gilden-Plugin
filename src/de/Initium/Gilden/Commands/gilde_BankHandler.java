package de.Initium.Gilden.Commands;

import de.Initium.Gilden.Main.Main;
import me.xanium.gemseconomy.api.GemsEconomyAPI;
import net.minecraft.server.v1_16_R3.EntityPlayer;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class gilde_BankHandler extends JavaPlugin {

    public static Player Dummy = (Player) EntityPlayer.DUMMY;

    public static void Handler(String Gilde)
    {
        GemsEconomyAPI api = new GemsEconomyAPI();
        api.deposit(Dummy.getUniqueId(), 1000);
        Main.getSaves().set("gilden." + Gilde + ".Information." + "Gilden Balance", api.getBalance(Dummy.getUniqueId()));
        Main.saveSaves();

    }
}
