package de.Initium.Gilden.Commands.SignMethod;


import org.bukkit.Bukkit;

import org.bukkit.entity.Player;




public class SignGUI {

	public static void sendPacket(Player player, Object packet) {
        try {
            Object handle = player.getClass().getMethod("getHandle").invoke(player);
            Object playerConnection = handle.getClass().getField("playerConnection").get(handle);
            playerConnection.getClass().getMethod("sendPacket", getNMSClass("Packet")).invoke(playerConnection, packet);
        } catch (Exception ex) {
        }
    }

    public static  Class<?> getNMSClass(String name) {
        try {
            return Class.forName("net.minecraft.server"
                    + Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3] + "." + name);
        } catch (ClassNotFoundException ex) {
        }
        return null;
    }
}