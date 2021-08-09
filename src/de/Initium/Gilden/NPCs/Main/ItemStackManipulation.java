package de.Initium.Gilden.NPCs.Main;

import de.Initium.Gilden.Main.Main;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;

public class ItemStackManipulation extends JavaPlugin
{
    public static ItemStack getCreatGildeDiamond()
    {
        ItemStack buyDiamond =  new ItemStack(Material.DIAMOND);
        buyDiamond.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 1);

        ItemMeta buyDiamond_meta = buyDiamond.getItemMeta();
        buyDiamond_meta.setDisplayName(ChatColor.AQUA + "Erstelle eine neue Gilde");
        buyDiamond_meta.setLore(Collections.singletonList("Kostenlos"));
        buyDiamond_meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        buyDiamond.setItemMeta(buyDiamond_meta);

        return buyDiamond;
    }

    public static ItemStack getPlaceholder()
    {
        ItemStack placeholder = new ItemStack(Material.LIGHT_GRAY_STAINED_GLASS_PANE);
        placeholder.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 1);

        ItemMeta placeholder_meta = placeholder.getItemMeta();
        placeholder_meta.setDisplayName(" ");
        placeholder_meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        placeholder_meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        placeholder.setItemMeta(placeholder_meta);

        return placeholder;
    }

    public static ItemStack getCloseBarrier()
    {
        ItemStack leaveBarrier = new ItemStack(Material.BARRIER);

        ItemMeta leaveBarrier_meta = leaveBarrier.getItemMeta();
        leaveBarrier_meta.setDisplayName("Schlieﬂen");
        leaveBarrier_meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        leaveBarrier.setItemMeta(leaveBarrier_meta);

        return leaveBarrier;
    }
    public static ItemStack getLeaveDoor()
    {
        ItemStack leaveDoor = new ItemStack(Material.IRON_DOOR);

        ItemMeta leaveDoormeta = leaveDoor.getItemMeta();
        leaveDoormeta.setDisplayName("Verlassen");
        leaveDoormeta.setLore(Collections.singletonList("Verlasse deine Gilde"));
        leaveDoormeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        leaveDoor.setItemMeta(leaveDoormeta);
        return leaveDoor;
    }

    public static ItemStack getTagItem(String gildenName) {
        ItemStack Tag = new ItemStack(Material.NAME_TAG);
        ItemMeta tagmeta = Tag.getItemMeta();
        if(!Main.getSaves().getBoolean("gilden." + gildenName + ".Information.hasBoughtTag")) {

            tagmeta.setDisplayName("Gilden-Tag");
            tagmeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
            ArrayList<String> lore = new ArrayList<>();
            lore.add("Kaufe deiner Gilde das Recht auf den Tag-Befehl");
            lore.add("Preis: " + Main.getConfiguration().get("settings.Gilden.TagCost") + " Kronen");
            tagmeta.setLore(lore);
            Tag.setItemMeta(tagmeta);
            return Tag;
        }
        tagmeta.setDisplayName("Gilden-Tag");
        tagmeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        tagmeta.setLore(Collections.singletonList("Deine Gilde besitzt bereits das Recht auf den Tag-Befehl"));
        Tag.setItemMeta(tagmeta);
        return Tag;



    }
    public static ItemStack getHomeItem(String gildenName) {
        ItemStack Grass = new ItemStack(Material.GRASS_BLOCK);
        ItemMeta grassmeta = Grass.getItemMeta();
        if(!Main.getSaves().getBoolean("gilden." + gildenName + ".Information.hasBoughtHome")) {

            grassmeta.setDisplayName("Gilden-Home");
            grassmeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
            ArrayList<String> lore = new ArrayList<>();
            lore.add("Kaufe deiner Gilde das Recht auf den Home-Befehl");
            lore.add("Preis: " + Main.getConfiguration().get("settings.Gilden.HomePunktCost") + " Kronen");
            grassmeta.setLore(lore);

            Grass.setItemMeta(grassmeta);
            return Grass;
        }
            grassmeta.setDisplayName("Gilden-Home");
            grassmeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
            grassmeta.setLore(Collections.singletonList("Deine Gilde hat bereits ein Home-Rechte gekauft!"));
            Grass.setItemMeta(grassmeta);
            return Grass;


    }
}