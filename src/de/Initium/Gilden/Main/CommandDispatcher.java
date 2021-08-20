package de.Initium.Gilden.Main;

import de.Initium.Gilden.Commands.*;
import de.Initium.Gilden.Commands.Chat.gilde_chat_join;
import de.Initium.Gilden.Commands.Chat.gilden_chat;
import de.Initium.Gilden.Commands.Home.gilde_SetHome;
import de.Initium.Gilden.Commands.Home.gilde_delhome;
import de.Initium.Gilden.Commands.Home.gilde_home;
import de.Initium.Gilden.Commands.Home.gilde_homeTeam;
import de.Initium.Gilden.Commands.Invitation.gilde_invite;
import de.Initium.Gilden.Commands.Invitation.gilde_response;
import de.Initium.Gilden.Commands.Request.gilde_request;
import de.Initium.Gilden.Commands.Spawn.gilde_setspawn;
import de.Initium.Gilden.Commands.Spawn.gilde_spawn;
import de.Initium.Gilden.Commands.Tag.*;
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
            case "Requests":
                gilde_request.execute(nr);
                break;
            case "setHome":
            	gilde_SetHome.execute(nr);
            	break;
            case "home":
                gilde_home.execute(nr);
                break;
            case "spawn":
                gilde_spawn.execute(nr);
                break;
            case "setspawn":
                gilde_setspawn.execute(nr);
                break;
            case "leave":
                gilde_leave.execute(nr);
                break;
            case "responsecancle":
            case "responsedelet":
                response.execute(nr, arg);
                break;
            case "delHome":
                gilde_delhome.execute(nr);
                break;
            case "delTag":
                gilde_delTag.execute(nr);
                break;
            case "toggleRequest":
                toggleRequest.execute(nr);
                break;

            //Fremde Gilde anzeigen
            default:
                if(arg.length() >= 4) {
                    _show.execute(nr, arg);
                }else if(arg.length() == 3) {
                    gilde_tag.execute(nr, arg);
                }
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
            case "setspawn":
                gilde_setspawn.execute(nr, args);
                break;
            case "chat-join":
            	gilde_chat_join.execute(nr, args);
            	break;
            case "pay":
            case "take":
                gilden_Bank.execute(nr, args);
                break;
            case "kick":
            	gilde_kick.execute(nr, args);
            	break;
            case "delTag":
                gilde_delTagTeam.execute(nr, args);
                break;
            case "reTag":
            case "renameTag":
                gilde_reTag.execute(nr, args);
                break;
            case "test":
                gilde_testforToolBox.execute(nr, args);
                break;
            case "rank":
            	gilde_rank.execute(nr, args);
            	break;
            case "setTag":
                gilde_setTag.execute(nr, args);
                break;
            case "invite":
                gilde_invite.execute(nr, args[1]);
                break;
            case "rename":
                gilde_rename.execute(nr, args);
                break;
            case "remove":
                gilde_remove.execute(nr, args);
                break;
            case "home":
                gilde_homeTeam.execute(nr, args);
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
            case "check":
                gilde_check.execute(nr, args[1]);
                break;
        }
    }
}