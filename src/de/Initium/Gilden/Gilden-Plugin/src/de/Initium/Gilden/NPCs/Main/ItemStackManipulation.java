package de.Initium.Gilden.NPCs.Main;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

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

    public static ItemStack getLeaveBarrier()
    {
        ItemStack leaveBarrier = new ItemStack(Material.BARRIER);

        ItemMeta leaveBarrier_meta = leaveBarrier.getItemMeta();
        leaveBarrier_meta.setDisplayName("Schlieﬂen");
        leaveBarrier_meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        leaveBarrier.setItemMeta(leaveBarrier_meta);

        return leaveBarrier;
    }
}