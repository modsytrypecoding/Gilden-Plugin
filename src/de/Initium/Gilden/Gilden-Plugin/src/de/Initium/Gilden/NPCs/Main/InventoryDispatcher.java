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

public class InventoryDispatcher extends JavaPlugin
{
    static ArrayList<Player> executors = new ArrayList<>();

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
            //IF NO HOMEPUNKT   -> Kaufm�glichkeit f�r einen Homepunkt

            //IF TAG            -> Item mit TAG �ndern (Countdown = Config)
            //IF NO TAG         -> Kaufm�glichkeit f�r einen TAG (Preis = Config)

            //IF Gildeninsel    ->
                //Mittlere Insel ->
                    //Spielerzahl >= 10 -> Kaufm�glichkeit gro�e Insel
                    //else Kauf gesperrt
                //Gro�e Insel    -> Gr��te Insel erreicht (Kein Upgrade m�glich)
                //Kleine Insel (bereits vorhanden, nichts tun)
            //IF NO Gildeninsel -> Kaufmen�
                //Kaufmen�:
                //Spielerzahl < 7 -> Kauf einer kleinen Insel gesperrt
                //Spielerzahl >= 7 -> Kauf einer mittleren Insel m�glich
                //Spielerzahl >= 10 -> Kauf einer gro�en Insel m�glich

            //Leave-Barrier (Last-Slot)
            showInv.setItem(9*6-1, ItemStackManipulation.getLeaveBarrier());
        }
        else
        {
            //Set the ItemStack into the Inv
            showInv = Bukkit.createInventory(null, 9*3, ChatColor.AQUA + "Gilden");

            //Buy-Diamond
            showInv.setItem(9*3/2, ItemStackManipulation.getCreatGildeDiamond());

            //Placeholders
            ItemStack placeholder = ItemStackManipulation.getPlaceholder();
            for(int i = 0; i <= 9; i++)
                showInv.setItem(i, placeholder);
            for(int i = 17; i <= 26; i++)
                showInv.setItem(i, placeholder);
        }
        ex.openInventory(showInv);
    }

    public static ArrayList<Player> getActivePlayers() { return executors; }
}