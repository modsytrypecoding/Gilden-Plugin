package de.Initium.Gilden.Commands.Invitation;

import de.Initium.Gilden.Commands.Invitation.gilde_invite;
import de.Initium.Gilden.Commands.gilde_Main;
import de.Initium.Gilden.Main.Timer;
import de.Initium.Gilden.Main.ToolBox;
import de.Initium.Gilden.Main.UUIDManipulation;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Collection;

public class gilde_response extends JavaPlugin
{
    public static void response(Integer nr, String arg1, String arg2)
    {
        Player executor = gilde_Main.getPlayer(nr);
        Object check1 = Timer.getExecByTarget(executor);
        Object check2 = Timer.getGilde_invitation_Mapping().get(arg2);

        if(!checkInvitationExists(check1, check2))
        {
            executor.sendMessage("Du hast keine Einladung von dieser Gilde erhalten");
            return;
        }
        if(!(gilde_invite.getOpenResponses().contains(executor)))
        {
            executor.sendMessage("Du hast bereits eine Antwort zu dieser Einladung gegeben.");
            return;
        }
        Player gilde_exponent = (Player) check2;
        Timer.remove(Timer.getTaskByPlayer(gilde_exponent));
        gilde_invite.getOpenResponses().remove(executor);
        String involved_gilde = ToolBox.getGildeNameOfPlayer(gilde_exponent);
        String exe_MSG = "";
        String tar_MSG = "";

        if(arg1.equals("accept"))
        {
            ToolBox.addPlayertoGilde(executor.getUniqueId().toString(), involved_gilde);
            exe_MSG = "Du hast die Einladung von der Gilde " + involved_gilde + " erfolgreich angenommen.\n" +
                    "Du befindest dich nun in der Gilde " + involved_gilde + ".\n" +
                    "Deine Insel wird in " + "" + " Stunden resettet. Transferiere deswegen deine Items auf die Gildeninsel.\n" +
                    "Der Countdown stoppt, wenn du die Gilde vor den restlichen " + "" + " Stunden wieder verlässt.";
            tar_MSG = "Der eingeladenen Spieler " +
                    executor.getName() +
                    " hat deine Einladung angenommen";

            String in_gilde_MSG = "Der Spieler " + executor.getName() + " ist der Gilde beigetreten.";
            for(String temp : UUIDManipulation.getPlayernameByUUID(ToolBox.getallPlayersinGilde(involved_gilde)))
            {
                Player in_gilde = Bukkit.getPlayer(temp);
                if(in_gilde == gilde_exponent) continue;
                if(Bukkit.getOnlinePlayers().contains(in_gilde))
                {
                    in_gilde.sendMessage(in_gilde_MSG);
                }
            }
        }
        else
        {
            exe_MSG = "§4Du hast die Einladung von der Gilde §n" + involved_gilde + "§r§4 erfolgreich abgelehnt";
            tar_MSG = "§4Der eingeladenen Spieler §n" + executor.getName() + "§r§4 hat die Einladung abgelehnt";
        }

        executor.sendMessage(exe_MSG);
        if(gilde_exponent.isOnline()) gilde_exponent.sendMessage(tar_MSG);
    }

    public static boolean checkInvitationExists(Object check1, Object check2)
    {
        return check1 instanceof Player && check2 != null && check1 == check2;
    }
}
