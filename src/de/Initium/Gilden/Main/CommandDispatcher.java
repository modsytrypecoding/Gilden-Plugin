package de.Initium.Gilden.Main;

import de.Initium.Gilden.Commands._show;
import de.Initium.Gilden.Commands.gilde_top;
import de.Initium.Gilden.Commands.invite;
import de.Initium.Gilden.Commands.temp_create;
import org.bukkit.plugin.java.JavaPlugin;

public class CommandDispatcher extends JavaPlugin
{
    public static void Main_dispatch(Integer nr, String[] args)
    {
        // Eigene Gilde anzeigen -> /gilde
        if(args.length == 0)
        {
            _show.execute(nr);
            return;
        }

        //All Commands with 1 argument (e.g. /gilde top)
        if(args.length == 1)
        {
            one_arg_dispatch(nr, args[0]);
            return;
        }

        //All with more than 1 argument (e.g. /gilde invite {Playername})
        dispatch(nr, args);
    }

    public static void one_arg_dispatch(Integer nr, String arg)
    {
        switch(arg)
        {
            //Zeigen der Gilden-Tops
            case "top":
                gilde_top.execute(nr);
                break;
            //case ...:


            //Fremde Gilde anzeigen
            default:
                _show.execute(nr, arg);
                break;
        }
    }

    public static void dispatch(Integer nr, String[] args)
    {
        switch(args[0]) {
            //TEMP: Gilde Erstellen
            case "create":
                temp_create.execute(nr, args[1]);
                break;
            case "invite":
                invite.execute(nr, args[1]);
                break;
            case "accept":
                break;
            case "deny":
                break;
            case "set":
                _show.set(nr, args[1]);
        }
    }
}
