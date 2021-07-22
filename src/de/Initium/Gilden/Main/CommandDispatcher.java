package de.Initium.Gilden.Main;

import de.Initium.Gilden.Commands.*;
import de.Initium.Gilden.Commands.Chat.gilde_chat_join;
import de.Initium.Gilden.Commands.Chat.gilden_chat;
import de.Initium.Gilden.Commands.Invitation.gilde_invite;
import de.Initium.Gilden.Commands.Invitation.gilde_response;
import de.Initium.Gilden.Commands.SignMethod.gilde_rename;
import de.Initium.Gilden.NPCs.Commands.gilde_setnpc;
import de.Initium.Gilden.NPCs.Main.Creation.CreationResponse;
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
            case "setHome":
            	gilde_SetHome.execute(nr);
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
            case "create":
                gilde_create.execute(nr, args[1]);
                break;
            case "chat":
            	gilden_chat.execute(nr, args);
            	break;
            case "chat-join":
            	gilde_chat_join.execute(nr, args);
            	break;
            case "kick":
            	gilde_kick.execute(nr, args);
            	break;
            case "rank":
            	gilde_rank.execute(nr, args);
            	break;
            case "invite":
                gilde_invite.execute(nr, args[1]);
                break;
            case "rename":
            	gilde_rename.execute(nr, args);
            	break;
            case "accept":
            case "deny":
                gilde_response.response(nr, args);
                break;
            case "top":
                gilde_top.execute(nr, args[1]);
                break;
            case "setnpc":
                gilde_setnpc.execute(nr, args[1]);
                break;
            case "create-confirm":
            case "create-quit":
                CreationResponse.execute(nr, args);
                break;
            case "set":
                _show.set(nr, args[1]);
                break;
        }
    }
}