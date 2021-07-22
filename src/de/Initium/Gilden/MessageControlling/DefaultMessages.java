package de.Initium.Gilden.MessageControlling;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class DefaultMessages extends JavaPlugin
{
    /*
    * Variables (Have to be replaced, if possible):
    * - %player% -> Executor-Name
    * - %target% -> Target-Name
    * - %own-gilde% -> Own-Gilden-Name
    * - %target-gilde% -> Target-Gilden-Name
    *
    * - %target-page% -> Page-Nr
    * - %msg% -> (Chat)Message
    * - %remaining-time%
    *
    * - \n -> Zeilenumbruch
    * */

    final static String basic_key = "Basic.";
    final static String team_Ukey = "Team.";
    final static String kick_key = "Kick.";
    final static String create_key = "Create.";
    final static String rank_key = "Rank.";
    final static String top_key = "Top.";
    final static String chat_key = "Chat.";
    final static String show_key = "Show.";
    final static String home_key = "Home.";
    final static String set_Ukey = "Set.";
    final static String invite_key = "Invite.";
    final static String response_key = "Response.";

    public static void set(YamlConfiguration messagesfileConfiguration)
    {
        messagesfileConfiguration.set(basic_key + "Prefix", "§7[§bGilde§7]§r ");
        messagesfileConfiguration.set(basic_key + "NoPerms", "§cDu hast keine Berechtigungen um diesen Befehl auszuführen!");
        messagesfileConfiguration.set(basic_key + "MainCMDasConsole", "§cDu kannst diesen Befehl nicht als Console ausführen!");
        messagesfileConfiguration.set(basic_key + "NoGilde", "§cDu kannst diesen Befehl nicht benutzen, da du in keiner Gilde bist!");

        messagesfileConfiguration.set(kick_key + "Successfull-ToExecutor", "§aDu hast den Spieler %target% aus der Gilde entfernt!");
        messagesfileConfiguration.set(kick_key + "Successfull-ToTarget", "§cDu wurdest von dem Spieler %player% aus der Gilde entfernt!");
        messagesfileConfiguration.set(kick_key + "Successfull-ToGilde", "[§a %own-gilde% §r] §rDer Spieler %player% hat den Spieler %target% aus der Gilde geworfen!");
        messagesfileConfiguration.set(kick_key + "Failed-PlayerNotFound", "§cDieser Spieler ist nicht in deiner Gilde!");
        messagesfileConfiguration.set(kick_key + team_Ukey + "Successfull-ToExecutor", "§a Der Spieler %target% wurde erfolgreich aus der Gilde %target-gilde% entfernt!");
        messagesfileConfiguration.set(kick_key + team_Ukey + "Failed-PlayerNotFound", "§cDer Spieler %target% ist nicht in der Gilde %target-gilde%!");
        messagesfileConfiguration.set(kick_key + team_Ukey + "Failed-GildeNotExisting", "§cDie Gilde %target-gilde% existiert nicht!");
        messagesfileConfiguration.set(kick_key + team_Ukey + "Failed-NoPerms", "§cDu hast keine Rechte Spieler aus fremden Gilden zu kicken!");

        messagesfileConfiguration.set(create_key +  "Failed-AlreadyInAGilde", "§cDu bist bereits in einer Gilde und kannst aufgrund dessen keine gründen!");
        messagesfileConfiguration.set(create_key +  "Failed-AlreadyExisting", "Die Gilde mit dem Namen %target-gilde% ist bereits vorhanden");
        messagesfileConfiguration.set(create_key +  "Failed-ConditionsNotMet", "Der Gildenname %target-gilde% ist ungültig!\nEr muss folgende Kriterien erfüllen:\n- Länge: Mindestens 3 Buchstaben\n- Nur folgender Character dürfen enthalten sein: [A-Z], [a-z]");
        messagesfileConfiguration.set(create_key +  "Successfull-ToExecutor", "Die Gilde wurde erfolgreich erstellt. Du bist der Gildenleiter von %own-gilde%");
        messagesfileConfiguration.set(create_key +  "Successfull-Broadcast", "Die Gilde %target-gilde% wurde erfolgreich von %player% gegründet!");

        messagesfileConfiguration.set(rank_key +  "Failed-ChangeOwnRank", "§cDu kannst deinen eigenen Rang nicht ändern!");
        messagesfileConfiguration.set(rank_key +  "Failed-PlayerNotFound", "§cDer Spieler %target% ist nicht in deiner Gilde!");
        messagesfileConfiguration.set(rank_key +  "Failed-AsMitglied", "§cDein Rang erlaubt es dir nicht die Ränge anderer Gildenmitglieder zu ändern!");
        messagesfileConfiguration.set(rank_key +  "Failed-WrongRanknames", "§cDer von dir eingegebene Rang funktioniert nicht! /nVerfügbare Ränge: /n-§6Gildenleiter /n§r-§6Forsitzender /n§r-§6Member");
        messagesfileConfiguration.set(rank_key +  "Successfull-MemberToChairman-ToExecutor", "§aDu hast den Rang des Spielers §6%target% §avon Mitglied auf Forsitzender befördert!");
        messagesfileConfiguration.set(rank_key +  "Successfull-MemberToChairman-ToTarget", "§aDein Rang wurde von §6Member §aauf Rang §6Forsitzender §abefördert!");
        messagesfileConfiguration.set(rank_key +  "Failed-AlreadyChairman", "Dieser Spieler ist bereits Forsitzender");
        messagesfileConfiguration.set(rank_key +  "Failed-IsLeader", "§cDieser Spieler ist ein Leiter! Du kannst seinen Rang nicht verändern");

        messagesfileConfiguration.set(rank_key +  "Successfull-ChairmanToLeader-ToExecutor", "§aDu hast den Rang des Spielers §6%target% §avon Forsitzender auf Leiter befördert!");
        messagesfileConfiguration.set(rank_key +  "Successfull-ChairmanToLeader-ToTarget", "§aDein Rang wurde von §6Forsitzender §aauf Rang §6Leiter §abefördert!");
        messagesfileConfiguration.set(rank_key +  "Successfull-MemberToLeader-ToExecutor", "§aDu hast den Rang des Spielers §6%target% §avon Mitglied auf Leiter befördert!");
        messagesfileConfiguration.set(rank_key +  "Successfull-MemberToLeader-ToTarget", "§aDein Rang wurde von §6Mitglied §aauf Rang §6Leiter §abefördert!");

        messagesfileConfiguration.set(rank_key +  "Successfull-ChairmanToMember-ToExecutor", "§aDu hast den Rang des Spielers §6%target% §avon Forsitzender auf Mitglied degradiert!");
        messagesfileConfiguration.set(rank_key +  "Successfull-ChairmanToMember-ToTarget", "§aDein Rang wurde von §6Forsitzender §aauf Rang §6Member §adegradiert!");
        messagesfileConfiguration.set(rank_key +  "Successfull-AlreadyMember", "§cDieser Spieler ist bereits ein Mitglied!");

        messagesfileConfiguration.set(rank_key +  "Failed-NotInAGilde", "§cDu kannst diesen Befehl nicht ausführen, da du dich in keiner Gilde befindest!");
            //Team
        messagesfileConfiguration.set(rank_key + team_Ukey + "Successfull-MemberToChairman-ToTeammember", "§aDu hast den Rang des Spielers §6%target% §avon Mitglied auf Forsitzender befördert!");
        messagesfileConfiguration.set(rank_key + team_Ukey + "Successfull-MemberToChairman-ToTarget", "§aEin Teammitglied hat deinen Rang von §6Member §aauf Rang §6Forsitzender §abefördert!");
        messagesfileConfiguration.set(rank_key + team_Ukey + "Failed-AlreadyChairman", "Dieser Spieler ist bereits Forsitzender");
        messagesfileConfiguration.set(rank_key + team_Ukey + "Successfull-LeaderToChairman-ToTeammember", "§aDu hast den Rang des Spielers §6%target% §avon Leiter auf Forsitzender degradiert!");
        messagesfileConfiguration.set(rank_key + team_Ukey + "Successfull-LeaderToChairman-ToTarget", "§cEin Teammitglied hat deinen Rang von §6Leiter §cauf Rang §6Forsitzender §cdegradiert!");

        messagesfileConfiguration.set(rank_key + team_Ukey + "Successfull-ChairmanToLeader-ToTeammember", "§aDu hast den Rang des Spielers §6%target% §avon Forsitzender auf Leiter befördert!");
        messagesfileConfiguration.set(rank_key + team_Ukey + "Successfull-ChairmanToLeader-ToTarget", "§aEin Teammitglied hat deinen Rang von §6Forsitzender §aauf Rang §6Leiter §abefördert!");

        messagesfileConfiguration.set(rank_key + team_Ukey + "Successfull-MemberToLeader-ToTeammember", "§aDu hast den Rang des Spielers §6%target% §avon Mitglied auf Leiter befördert!");
        messagesfileConfiguration.set(rank_key + team_Ukey + "Successfull-MemberToLeader-ToTarget", "§aEin Teammitglied hat deinen Rang von §6Mitglied §aauf Rang §6Leiter §abefördert!");
        messagesfileConfiguration.set(rank_key + team_Ukey + "Failed-AlreadyLeader", "§cDieser Spieler ist bereits ein Leiter");

        messagesfileConfiguration.set(rank_key + team_Ukey + "Successfull-ChairmanToMember-ToTeammember", "§aDu hast den Rang des Spielers §6%target% §avon Forsitzender auf Mitglied degradiert!");
        messagesfileConfiguration.set(rank_key + team_Ukey + "Successfull-ChairmanToMember-ToTarget", "§cEin Teammitglied hat deinen Rang von §6Forsitzender §cauf Rang §6Member §cdegradiert!");

        messagesfileConfiguration.set(rank_key + team_Ukey + "Failed-AlreadyMember", "§cDieser Spieler ist bereits ein Mitglied!");
        messagesfileConfiguration.set(rank_key + team_Ukey + "Successfull-LeaderToMember-ToTeammember", "§aDu hast den Rang des Spielers §6%target% §avon Leiter auf Mitglied degradiert!");
        messagesfileConfiguration.set(rank_key + team_Ukey + "Successfull-LeaderToMember-ToTarget", "§cEin Teammitglied hat deinen Rang von §6Leiter §cauf Rang §6Mitglied §cdegradiert!");

        messagesfileConfiguration.set(rank_key + team_Ukey + "Failed-PlayerNotInGilde", "§cDieser Spieler ist nicht in der von dir gewählten Gilde!");
        messagesfileConfiguration.set(rank_key + team_Ukey + "Failed-GildeNotExisting", "§cDie von dir gewählte Gilde existiert nicht");
        messagesfileConfiguration.set(rank_key + team_Ukey + "Failed-WrongRanks", "Der von dir eingegebene Rang funktioniert nicht! \nVerfügbare Ränge: \n-§6Leiter \n§r-§6Forsitzender \n§r-§6Member");
        messagesfileConfiguration.set(rank_key + team_Ukey + "Failed-NoPerms", "§cDazu fehlen dir die Berechtigungen!");
        messagesfileConfiguration.set(rank_key + team_Ukey + "Failed-ToManyArguments", "§cZuviele Args");

        messagesfileConfiguration.set(top_key + "Failed-WrongPageNR", "§cDie eingegebene Seitenzahl muss eine Nummer über 0 sein");
        messagesfileConfiguration.set(top_key + "Successfull-Header", "§c====Gilde-Top===Seite %target-page%====\n");
        //gilden_chat
        messagesfileConfiguration.set(chat_key + "Successfull-ToExecutor", "[§a %target-gilde% §r] §6 %player% §r: %msg%");
        messagesfileConfiguration.set(chat_key + "Successfull-ToGilde", "[§a %target-gilde% §r] §6 %player% §r: %msg%");
        messagesfileConfiguration.set(chat_key + "Failed-NotInAGilde", "§cDu kannst diesen Befehl nicht benutzen, da du in keiner Gilde bist!");
        //gilde_chat_join (Gleich wie bei Gc)
        messagesfileConfiguration.set(chat_key + team_Ukey + "Failed-Watcher", "§cDu bist lediglich Zuschauer und kannst keine Nachrichten schreiben!");
        messagesfileConfiguration.set(chat_key + team_Ukey + "Failed-GildeNotExisting", "§cDie Gilde %target-gilde% existiert nicht!");
        messagesfileConfiguration.set(chat_key + team_Ukey + "Failed-NoPerms", "§cDazu hast du keine Berechtigungen!");
        messagesfileConfiguration.set(chat_key + team_Ukey + "Successfull-WatcherActivated", "§aWatcher aktiviert\nDu liest jetzt den Gilden-Chat der Gilde %target-gilde% mit!");
        messagesfileConfiguration.set(chat_key + team_Ukey + "Successfull-WatcherDeactivated", "§cWatcher deaktiviert!");

        messagesfileConfiguration.set(show_key + "Failed-NotInAGilde", "§cDu kannst dir deine eigene Gilde nicht anzeigen, da du in keiner bist!");
        messagesfileConfiguration.set(show_key + "Failed-GildeNotExisting", "§cDie Gilde %target-gilde% existiert nicht!");
        messagesfileConfiguration.set(show_key + "Successfull-Header", "====Gilde %target-gilde%====");

        messagesfileConfiguration.set(home_key + set_Ukey + "Successfull-ToExecutor", "§aDu hast dein Gilden-Home erfolgreich gesetzt!");
        messagesfileConfiguration.set(home_key + set_Ukey + "Failed-AsMitglied", "§cDu kannst kein Gilden-Home setzten!");
        messagesfileConfiguration.set(home_key + set_Ukey + "Failed-NotInAGilde", "§cDu kannst diesen Befehl nicht nutzen da du in keiner Gilde bist!");

        //Invitation(gilde_invite + Timer)
        messagesfileConfiguration.set(invite_key + "Failed-RunningOut-ToExecutor", "§cDeine Gildenanfrage an %target% ist ausgelaufen!");
        messagesfileConfiguration.set(invite_key + "Failed-RunningOut-ToTarget", "§cDie Gildenanfrage von %target-gilde% ist ausgelaufen!");
        messagesfileConfiguration.set(invite_key + "Failed-RemainingTime", "§cDu kannst erst wieder in %remaining-time% Sekunden eine weitere Anfrage schicken!");
        messagesfileConfiguration.set(invite_key + "Failed-SelfInvite", "§cDu kannst dich nicht selber inviten!");
        messagesfileConfiguration.set(invite_key + "Failed-PlayerNotOnline", "§cDer angegebene Spieler ist nicht online!");
        messagesfileConfiguration.set(invite_key + "Failed-PlayerAlreadyInAGilde", "Der Spieler %target% ist bereits in einer Gilde!");
        messagesfileConfiguration.set(invite_key + "Invitation-ToExecutor", "Du hast %target% in deine Gilde eingeladen!");
        messagesfileConfiguration.set(invite_key + "Invitation-ToTarget", "§2Du wurdest von §6§l%player%§r§2 in die Gilde §6§l%target-gilde%§r§2 eingeladen.\n\n" +
                "§2Achtung! Deine persönliche Insel wird beim übernächsten Serverrestart (um 5 Uhr) aufgegeben.\n" +
                "Sichere bis dahin deine Items, damit es nicht zu Verlusten kommt.\n" +
                "Du hast §4§n90 §r§2Sekunden um diese Einladung anzunehmen. Danach wird sie automatisch §4§nabgelehnt\n" +
                "§r§2Du kannst entweder per Click auf die folgenden Button annehmen, oder per /gilde accept|deny %target-gilde%");
        messagesfileConfiguration.set(invite_key + "AcceptInvitation-ClickText", "Annehmen!");
        messagesfileConfiguration.set(invite_key + "AcceptInvitation-HoverText", "Du nimmst diese Einladung an!");
        messagesfileConfiguration.set(invite_key + "DenyInvitation-ClickText", "Ablehnen!");
        messagesfileConfiguration.set(invite_key + "DenyInvitation-HoverText", "Du lehnst diese Einladung ab!");
        //gilde_response
        messagesfileConfiguration.set(response_key + "Failed-NoInvitation", "Du hast keine Einladung von dieser Gilde erhalten");
        messagesfileConfiguration.set(response_key + "Failed-AlreadyReplied", "Du hast bereits eine Antwort zu dieser Einladung gegeben.");
        messagesfileConfiguration.set(response_key + "Successfull-Acceptance-ToPlayer", "Der eingeladenen Spieler %target% hat deine Einladung angenommen");
        messagesfileConfiguration.set(response_key + "Successfull-Acceptance-ToTarget", "Du hast die Einladung von der Gilde %target-gilde% erfolgreich angenommen.\n" +
                "Du befindest dich nun in der Gilde %target-gilde%.\n" +
                "Deine Insel wird in %remaining-time% Stunden resettet. Transferiere deswegen deine Items auf die Gildeninsel.\n" +
                "Der Countdown stoppt, wenn du die Gilde vor den restlichen " + "" + " Stunden wieder verlässt.");
        messagesfileConfiguration.set(response_key + "Successfull-Acceptance-ToGilde", "Der Spieler %target% ist der Gilde beigetreten.");
        messagesfileConfiguration.set(response_key + "Successfull-Denial-ToTarget", "§4Du hast die Einladung von der Gilde §n%target-gilde%§r§4 erfolgreich abgelehnt");
        messagesfileConfiguration.set(response_key + "Successfull-Denial-ToExecutor", "§4Der eingeladenen Spieler §n%target%§r§4 hat die Einladung abgelehnt");

        //NPC-MSGs
    }
}