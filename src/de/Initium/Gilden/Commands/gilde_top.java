package de.Initium.Gilden.Commands;

import de.Initium.Gilden.Main.ToolBox;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.*;

public class gilde_top extends JavaPlugin
{
    public static void execute(Integer nr)
    {
        Player pl = gilde_Main.getPlayer(nr);

        execute(nr, "1");

        //String MSG = manipulateMSG(pl);
        //pl.sendMessage(MSG);
    }

    public static void execute(Integer nr, String arg)
    {
        Player pl = gilde_Main.getPlayer(nr);

        //Check arg is a valid Number above 0
        Object temp = ToolBox.parseUIntOrNull(arg);
        if(temp == null)
        {
            pl.sendMessage("Die eingegebene Seitenzahl muss eine Nummer über 0 sein");
            return;
        }
        Integer arg_numb = (Integer) temp;

        //Get Gildennamen
        ArrayList<String> allGildenNamen = ToolBox.getAllGildennamen();
        //Get Sizes to the Gilden
        Map<String, Integer> temp_map_unsorted = getGildeSizes(allGildenNamen);
        //Sort Gilden after Size
        Map<String, Integer> temp_map_sorted = ToolBox.sort(temp_map_unsorted);

        //Get the highest possible Site-Number
        int temp_seitenanzahl = (int) Math.ceil(temp_map_sorted.size() * 0.1);

        //Check Site-Nr from Player is above highest
        //if so -> get the last site
        int siteToShow;
        if(temp_seitenanzahl < arg_numb) siteToShow = temp_seitenanzahl;
        else siteToShow = arg_numb;

        //Calculation of first and last Position of Gilden to get
        int showFirst = siteToShow * 10 - 9;
        int showLast = siteToShow * 10 - 9 + 10 -1;

        //If calculated last Position is higher than all positions, limit last position
        if (showLast > temp_map_sorted.size())
            showLast = temp_map_unsorted.size();

        //Creation of ArrayList of all Gilden to show
        ArrayList<String> showGilden = new ArrayList<>();
        for(int i = showFirst; i <= showLast; i++)
        {
            showGilden.add(new ArrayList<>(temp_map_sorted.keySet()).get(i-1));
        }
        pl.sendMessage(getMSG(siteToShow, showGilden));
    }

    public static String getMSG(Integer site, List<String> showGilden)
    {
        //Message-Creation (withhin: sort)
        String MSG = "====Gilde-Top===Seite " + site + "====\n";
        int i = site * 10 -9;
        for(Map.Entry<String, Integer> entry : ToolBox.sort(getGildeSizes(showGilden)).entrySet())
        {
            MSG += i + ": " + entry.getKey() + "\n";
            i++;
        }
        return MSG;
    }

    public static Map<String, Integer> getGildeSizes(List<String> gilden)
    {
        HashMap<String, Integer> temp = new HashMap<>();

        for(String gilde : gilden)
        {
            Integer size = ToolBox.getallPlayersinGilde(gilde).size();
            temp.put(gilde, size);
        }
        return temp;
    }
}