package de.Initium.Gilden.Timer;

import de.Initium.Gilden.Main.Main;
import de.Initium.Gilden.Main.ToolBox;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.util.HashMap;
import java.util.Map;

public class Invitation_Response extends JavaPlugin
{
    static HashMap<Integer, Player> gildentimer = new HashMap<>();
    static HashMap<Player, Player> exTar_Mapping = new HashMap<>();
    static HashMap<String, Player> gilde_invitation_Mapping = new HashMap<>();

    public static void setTimer(Player executor, Player target)
    {
        //Timer von 90 Sekunden und Anfragensteller in die HashMap
        BukkitTask temp_task = new BukkitRunnable()
        {
            int restzeit = 20 * 90;
            @Override
            public void run() {
                if(restzeit == 0)
                {
                    Player exe = gildentimer.get(this.getTaskId());
                    Player tar = exTar_Mapping.get(gildentimer.get(this.getTaskId()));
                    if(exe.isOnline()) exe.sendMessage("Deine Gildenanfrage an " + tar.getName() + " ist ausgelaufen.");
                    if(tar.isOnline()) tar.sendMessage("Die Gildenanfrage von " + ToolBox.getGildeNameOfPlayer(exe) + " ist ausgelaufen.");
                    Invitation_Response.remove(this.getTaskId());
                }
                restzeit -= 20;
            }
        }.runTaskTimerAsynchronously(Main.getPlugin(), 0, 20L);
        gildentimer.put(temp_task.getTaskId(), executor);
        exTar_Mapping.put(executor, target);
    }

    public static void remove(Integer TaskID)
    {
        gildentimer.remove(TaskID);
        Bukkit.getServer().getScheduler().cancelTask(TaskID);
    }

    public static HashMap<Integer, Player> getGildenTimer()
    {
        return gildentimer;
    }

    public static HashMap<Player, Player> getExTar_Mapping()
    {
        return exTar_Mapping;
    }

    public static HashMap<String, Player> getGilde_invitation_Mapping()
    {
        return gilde_invitation_Mapping;
    }

    public static Integer getTaskByPlayer(Player pl)
    {
        for(Map.Entry<Integer, Player> entry : gildentimer.entrySet())
        {
            if(entry.getValue() == pl)
            {
                return entry.getKey();
            }
        }
        return -1;
    }

    public static Object getExecByTarget(Player pl)
    {
        for(Map.Entry<Player, Player> entry : exTar_Mapping.entrySet())
        {
            if(entry.getValue() == pl)
            {
                return entry.getKey();
            }
        }
        return "";
    }
}