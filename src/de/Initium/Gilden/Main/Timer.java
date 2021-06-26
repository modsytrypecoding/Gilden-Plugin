package de.Initium.Gilden.Main;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.TimerTask;

public class Timer extends JavaPlugin
{
    static HashMap<Integer, Player> gildentimer = new HashMap<>();
    static HashMap<Player, Player> exTar_Mapping = new HashMap<>();

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
                    exe.sendMessage("Deine Gildenanfrage an " + tar.getName() + " ist ausgelaufen.");
                    tar.sendMessage("Die Gildenanfrage von " + ToolBox.getGildeNameOfPlayer(exe) + " ist ausgelaufen.");
                    Timer.remove(this.getTaskId());
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
}
