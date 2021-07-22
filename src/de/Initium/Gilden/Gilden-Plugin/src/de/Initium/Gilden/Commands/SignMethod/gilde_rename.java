package de.Initium.Gilden.Commands.SignMethod;

import java.lang.reflect.Constructor;


import org.bukkit.entity.Player;

import org.bukkit.plugin.java.JavaPlugin;

import de.Initium.Gilden.Commands.gilde_Main;

import de.Initium.Gilden.Main.ToolBox;


public class gilde_rename extends JavaPlugin{
	
	public static void execute(Integer nr, String[] args) {
		Player pl = gilde_Main.getPlayer(nr);
		int x = pl.getLocation().getBlockX();
		int y = pl.getLocation().getBlockY();
		int z = pl.getLocation().getBlockZ();
		
		if(ToolBox.getallPlayers().contains(pl.getUniqueId().toString())) {
			
			try {
				pl.sendMessage("Test1");
	            Class<?> packetClass = SignGUI.getNMSClass("PacketPlayOutOpenSignEditor");
	            Class<?> blockPositionClass = SignGUI.getNMSClass("BlockPosition");
	            Constructor<?> blockPosCon = blockPositionClass.getConstructor(new Class[] { Integer.TYPE, Integer.TYPE, Integer.TYPE });
	            pl.sendMessage("Test2");
	            Object blockPosition = blockPosCon.newInstance(new Object[] { Integer.valueOf(x), Integer.valueOf(y), Integer.valueOf(z) });
	            Constructor<?> packetCon = packetClass.getConstructor(new Class[] { blockPositionClass });
	            pl.sendMessage("Test3");
	            Object packet = packetCon.newInstance(new Object[] { blockPosition });
	            SignGUI.sendPacket(pl, packet);
	            pl.sendMessage(args[1]);

	        } catch (Exception ex) {
	        	System.out.println("Fehler" + ex);
	        }
		}else {
			pl.sendMessage("§cDu befindest dich in keiner Gilde");
		}
	}

}
