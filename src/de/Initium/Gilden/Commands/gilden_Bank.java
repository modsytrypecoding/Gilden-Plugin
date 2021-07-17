package de.Initium.Gilden.Commands;

import de.Initium.Gilden.Main.Main;
import de.Initium.Gilden.Main.ToolBox;
import me.xanium.gemseconomy.api.GemsEconomyAPI;
import net.minecraft.server.v1_16_R3.EntityPlayer;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class gilden_Bank extends JavaPlugin {

    public static boolean isInt(String s) {
        try {
            Integer.parseInt(s);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public static void execute(Integer nr, String[] args) {
        GemsEconomyAPI api = new GemsEconomyAPI();
        Player p = gilde_Main.getPlayer(nr);
        if(ToolBox.getallPlayers().contains(p.getUniqueId().toString())) {
            if(api.getBalance(p.getUniqueId() ) < Double.parseDouble(args[1])) {
                api.deposit(gilde_BankHandler.Dummy.getUniqueId(), Double.parseDouble(args[1]));
            }else {
                p.sendMessage("§cDu hast zu wenig Kronen!");
            }


        }else {
            p.sendMessage("§cDu kannst diesen Befehl nicht benutzten");
        }

        if (args.length >= 1) {
            if (isInt(args[1])) {
                int num = Integer.parseInt(args[1]);

            }
        }

    }
}
