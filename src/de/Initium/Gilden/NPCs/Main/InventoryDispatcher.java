package de.Initium.Gilden.NPCs.Main;

import de.Initium.Gilden.Main.ToolBox;
import de.Initium.Gilden.NPCs.Main.Creation.InventoryInteraction;
import net.citizensnpcs.api.npc.NPC;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.inventory.Inventory;

import java.util.ArrayList;
import java.util.HashMap;

public class InventoryDispatcher extends JavaPlugin
{
    static ArrayList<Player> executors = new ArrayList<>();
    static HashMap<Player, Boolean> inInventory = new HashMap<>();

    public static void caseDecision(Player ex, NPC npc)
    {
        executors.add(ex);
        if(InventoryInteraction.getAwaitingNewGildename().contains(ex))
        {
            ex.sendMessage("TESTNACHTICHT: Bereits im Prozess1");
            return;
        }
        if(InventoryInteraction.getAwaitingConfirmation().containsKey(ex))
        {
            ex.sendMessage("TESTNACHTICHT: Bereits im Prozess2");
            return;
        }
        Boolean Case = ToolBox.getallPlayers().contains(ex.getUniqueId().toString());

        Inventory showInv;
        if(Case)
        {
            //Check Rank of Player in Gilde
            //return if not Leiter

            showInv = Bukkit.createInventory(null, 9*6, ChatColor.AQUA + "Gilden");

            //IF HOMEPUNKT      -> Item mit Koordinaten des Homepunkts
            //IF NO HOMEPUNKT   -> Kaufmöglichkeit für einen Homepunkt

            //IF TAG            -> Item mit TAG Ändern (Countdown = Config)
            //IF NO TAG         -> Kaufmöglichkeit für einen TAG (Preis = Config)

            //IF Gildeninsel    ->
                //Mittlere Insel ->
                    //Spielerzahl >= 10 -> Kaufmöglichkeit große Insel
                    //else Kauf gesperrt
                //Große Insel    -> Größte Insel erreicht (Kein Upgrade möglich)
                //Kleine Insel (bereits vorhanden, nichts tun)
            //IF NO Gildeninsel -> Kaufmenü
                //Kaufmenü:
                //Spielerzahl < 7 -> Kauf einer Mittleren Insel gesperrt
                //Spielerzahl >= 7 -> Kauf einer mittleren Insel möglich
                //Spielerzahl >= 10 -> Kauf einer großen Insel möglich

            //Leave-Barrier (Last-Slot)
            showInv.setItem(9*6-1, ItemStackManipulation.getCloseBarrier());
            showInv.setItem(20, ItemStackManipulation.getTagItem(ToolBox.getGildeNameOfPlayer(ex)));
            showInv.setItem(22, ItemStackManipulation.getLeaveDoor());
            showInv.setItem(24, ItemStackManipulation.getHomeItem(ToolBox.getGildeNameOfPlayer(ex)));
            showInv.setItem(38, ItemStackManipulation.getRequestSeeItem());
            showInv.setItem(40, ItemStackManipulation.InselShop());
            showInv.setItem(42, ItemStackManipulation.ChooseGuiItem(ToolBox.getGildeNameOfPlayer(ex)));
        }
        else
        {
            //Set the ItemStack into the Inv
            showInv = Bukkit.createInventory(null, 9*3, ChatColor.AQUA + "Gilden");

            //Buy-Diamond
            showInv.setItem(12, ItemStackManipulation.getCreatGildeDiamond());

            //Request-Item
            showInv.setItem(14, ItemStackManipulation.getRequestItem());

            //Placeholders
            ItemStack placeholder = ItemStackManipulation.getPlaceholder();
            for(int i = 0; i <= 9; i++)
                showInv.setItem(i, placeholder);
            for(int i = 17; i <= 26; i++)
                showInv.setItem(i, placeholder);
        }
        ex.openInventory(showInv);
        inInventory.put(ex, true);
    }

    public static ArrayList<Player> getActivePlayers() { return executors; }
    public static HashMap<Player, Boolean> getInInventory() { return inInventory; }
}