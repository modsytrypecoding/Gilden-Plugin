package de.Initium.Gilden.Main.MessageControlling;

import de.Initium.Gilden.Main.Main;
import org.bukkit.plugin.java.JavaPlugin;

public class MessageMapping extends JavaPlugin
{
    public static String getMSG(String key)
    {
        String returnMSG = "";
        switch (key)
        {
            //========================================================================================================================
            //region Basic
            case "Basic.Prefix":
                returnMSG = Main.getMessages().getString("Basic.Prefix");
                break;
            case "Basic.NoPerms":
                returnMSG = Main.getMessages().getString("Basic.NoPerms");
                break;
            case "Basic.MainCMDasConsole":
                returnMSG = Main.getMessages().getString("Basic.MainCMDasConsole");
                break;
            case "Basic.NoGilde":
                returnMSG = Main.getMessages().getString("Basic.NoGilde");
                break;
            //endregion
            //========================================================================================================================
            //region Kick
            case "Kick.Successfull-ToExecutor":
                returnMSG = Main.getMessages().getString("Kick.Successfull-ToExecutor");
                break;
            case "Kick.Successfull-ToTarget":
                returnMSG = Main.getMessages().getString("Kick.Successfull-ToTarget");
                break;
            case "Kick.Successfull-ToGilde":
                returnMSG = Main.getMessages().getString("Kick.Successfull-ToGilde");
                break;
            case "Kick.Failed-PlayerNotFound":
                returnMSG = Main.getMessages().getString("Kick.Failed-PlayerNotFound");
                break;
            case "Kick.Team.Successfull-ToExecutor":
                returnMSG = Main.getMessages().getString("Kick.Team.Successfull-ToExecutor");
                break;
            case "Kick.Team.Failed-PlayerNotFound":
                returnMSG = Main.getMessages().getString("Kick.Team.Failed-PlayerNotFound");
                break;
            case "Kick.Team.Failed-GildeNotExisting":
                returnMSG = Main.getMessages().getString("Kick.Team.Failed-GildeNotExisting");
                break;
            case "Kick.Team.Failed-NoPerms":
                returnMSG = Main.getMessages().getString("Kick.Team.Failed-NoPerms");
                break;
            //endregion
            //========================================================================================================================
            //region Create
            case "Create.Failed-AlreadyInAGilde":
                returnMSG = Main.getMessages().getString("Create.Failed-AlreadyInAGilde");
                break;
            case "Create.Failed-AlreadyExisting":
                returnMSG = Main.getMessages().getString("Create.Failed-AlreadyExisting");
                break;
            case "Create.Failed-ConditionsNotMet":
                returnMSG = Main.getMessages().getString("Create.Failed-ConditionsNotMet");
                break;
            case "Create.Successfull-ToExecutor":
                returnMSG = Main.getMessages().getString("Create.Successfull-ToExecutor");
                break;
            case "Create.Successfull-Broadcast":
                returnMSG = Main.getMessages().getString("Create.Successfull-Broadcast");
                break;
            //endregion
            //========================================================================================================================
            //region Rank
            case "Rank.Failed-ChangeOwnRank":
                returnMSG = Main.getMessages().getString("Rank.Failed-ChangeOwnRank");
                break;
            case "Rank.Failed-PlayerNotFound":
                returnMSG = Main.getMessages().getString("Rank.Failed-PlayerNotFound");
                break;
            case "Rank.Failed-AsMitglied":
                returnMSG = Main.getMessages().getString("Rank.Failed-AsMitglied");
                break;
            case "Rank.Failed-WrongRanknames":
                returnMSG = Main.getMessages().getString("Rank.Failed-WrongRanknames");
                break;
            case "Rank.Successfull-MemberToChairman-ToExecutor":
                returnMSG = Main.getMessages().getString("Rank.Successfull-MemberToChairman-ToExecutor");
                break;
            case "Rank.Successfull-MemberToChairman-ToTarget":
                returnMSG = Main.getMessages().getString("Rank.Successfull-MemberToChairman-ToTarget");
                break;
            case "Rank.Failed-AlreadyChairman":
                returnMSG = Main.getMessages().getString("Rank.Failed-AlreadyChairman");
                break;
            case "Rank.Failed-IsLeader":
                returnMSG = Main.getMessages().getString("Rank.Failed-IsLeader");
                break;
            case "Rank.Successfull-ChairmanToLeader-ToExecutor":
                returnMSG = Main.getMessages().getString("Rank.Successfull-ChairmanToLeader-ToExecutor");
                break;
            case "Rank.Successfull-ChairmanToLeader-ToTarget":
                returnMSG = Main.getMessages().getString("Rank.Successfull-ChairmanToLeader-ToTarget");
                break;
            case "Rank.Successfull-MemberToLeader-ToExecutor":
                returnMSG = Main.getMessages().getString("Rank.Successfull-MemberToLeader-ToExecutor");
                break;
            case "Rank.Successfull-MemberToLeader-ToTarget":
                returnMSG = Main.getMessages().getString("Rank.Successfull-MemberToLeader-ToTarget");
                break;
            case "Rank.Successfull-ChairmanToMember-ToExecutor":
                returnMSG = Main.getMessages().getString("Rank.Successfull-ChairmanToMember-ToExecutor");
                break;
            case "Rank.Successfull-ChairmanToMember-ToTarget":
                returnMSG = Main.getMessages().getString("Rank.Successfull-ChairmanToMember-ToTarget");
                break;
            case "Rank.Successfull-AlreadyMember":
                returnMSG = Main.getMessages().getString("Rank.Successfull-AlreadyMember");
                break;
            case "Rank.Failed-NotInAGilde":
                returnMSG = Main.getMessages().getString("Rank.Failed-NotInAGilde");
                break;
            case "Rank.Team.Successfull-MemberToChairman-ToTeammember":
                returnMSG = Main.getMessages().getString("Rank.Team.Successfull-MemberToChairman-ToTeammember");
                break;
            case "Rank.Team.Successfull-MemberToChairman-ToTarget":
                returnMSG = Main.getMessages().getString("Rank.Team.Successfull-MemberToChairman-ToTarget");
                break;
            case "Rank.Team.Failed-AlreadyChairman":
                returnMSG = Main.getMessages().getString("Rank.Team.Failed-AlreadyChairman");
                break;
            case "Rank.Team.Successfull-LeaderToChairman-ToTeammember":
                returnMSG = Main.getMessages().getString("Rank.Team.Successfull-LeaderToChairman-ToTeammember");
                break;
            case "Rank.Team.Successfull-LeaderToChairman-ToTarget":
                returnMSG = Main.getMessages().getString("Rank.Team.Successfull-LeaderToChairman-ToTarget");
                break;
            case "Rank.Team.Successfull-ChairmanToLeader-ToTeammember":
                returnMSG = Main.getMessages().getString("Rank.Team.Successfull-ChairmanToLeader-ToTeammember");
                break;
            case "Rank.Team.Successfull-ChairmanToLeader-ToTarget":
                returnMSG = Main.getMessages().getString("Rank.Team.Successfull-ChairmanToLeader-ToTarget");
                break;
            case "Rank.Team.Successfull-MemberToLeader-ToTeammember":
                returnMSG = Main.getMessages().getString("Rank.Team.Successfull-MemberToLeader-ToTeammember");
                break;
            case "Rank.Team.Successfull-MemberToLeader-ToTarget":
                returnMSG = Main.getMessages().getString("Rank.Team.Successfull-MemberToLeader-ToTarget");
                break;
            case "Rank.Team.Failed-AlreadyLeader":
                returnMSG = Main.getMessages().getString("Rank.Team.Failed-AlreadyLeader");
                break;
            case "Rank.Team.Successfull-ChairmanToMember-ToTeammember":
                returnMSG = Main.getMessages().getString("Rank.Team.Successfull-ChairmanToMember-ToTeammember");
                break;
            case "Rank.Team.Successfull-ChairmanToMember-ToTarget":
                returnMSG = Main.getMessages().getString("Rank.Team.Successfull-ChairmanToMember-ToTarget");
                break;
            case "Rank.Team.Failed-AlreadyMember":
                returnMSG = Main.getMessages().getString("Rank.Team.Failed-AlreadyMember");
                break;
            case "Rank.Team.Successfull-LeaderToMember-ToTeammember":
                returnMSG = Main.getMessages().getString("Rank.Team.Successfull-LeaderToMember-ToTeammember");
                break;
            case "Rank.Team.Successfull-LeaderToMember-ToTarget":
                returnMSG = Main.getMessages().getString("Rank.Team.Successfull-LeaderToMember-ToTarget");
                break;
            case "Rank.Team.Failed-PlayerNotInGilde":
                returnMSG = Main.getMessages().getString("Rank.Team.Failed-PlayerNotInGilde");
                break;
            case "Rank.Team.Failed-GildeNotExisting":
                returnMSG = Main.getMessages().getString("Rank.Team.Failed-GildeNotExisting");
                break;
            case "Rank.Team.Failed-WrongRanks":
                returnMSG = Main.getMessages().getString("Rank.Team.Failed-WrongRanks");
                break;
            case "Rank.Team.Failed-NoPerms":
                returnMSG = Main.getMessages().getString("Rank.Team.Failed-NoPerms");
                break;
            case "Rank.Team.Failed-ToManyArguments":
                returnMSG = Main.getMessages().getString("Rank.Team.Failed-ToManyArguments");
                break;
            //endregion
            //========================================================================================================================
            //region Top
            case "Top.Failed-WrongPageNR":
                returnMSG = Main.getMessages().getString("Top.Failed-WrongPageNR");
                break;
            case "Top.Successfull-Header":
                returnMSG = Main.getMessages().getString("Top.Successfull-Header");
                break;
            //endregion
            //========================================================================================================================
            //region Chat
            case "Chat.Successfull-ToExecutor":
                returnMSG = Main.getMessages().getString("Chat.Successfull-ToExecutor");
                break;
            case "Chat.Successfull-ToGilde":
                returnMSG = Main.getMessages().getString("Chat.Successfull-ToGilde");
                break;
            case "Chat.Failed-NotInAGilde":
                returnMSG = Main.getMessages().getString("Chat.Failed-NotInAGilde");
                break;
            case "Chat.Team.Failed-Watcher":
                returnMSG = Main.getMessages().getString("Chat.Team.Failed-Watcher");
                break;
            case "Chat.Team.Failed-GildeNotExisting":
                returnMSG = Main.getMessages().getString("Chat.Team.Failed-GildeNotExisting");
                break;
            case "Chat.Team.Failed-NoPerms":
                returnMSG = Main.getMessages().getString("Chat.Team.Failed-NoPerms");
                break;
            case "Chat.Team.Successfull-WatcherActivated":
                returnMSG = Main.getMessages().getString("Chat.Team.Successfull-WatcherActivated");
                break;
            case "Chat.Team.Successfull-WatcherDeactivated":
                returnMSG = Main.getMessages().getString("Chat.Team.Successfull-WatcherDeactivated");
                break;
            //endregion
            //========================================================================================================================
            //region Show
            case "Show.Failed-NotInAGilde":
                returnMSG = Main.getMessages().getString("Show.Failed-NotInAGilde");
                break;
            case "Show.Failed-GildeNotExisting":
                returnMSG = Main.getMessages().getString("Show.Failed-GildeNotExisting");
                break;
            case "Show.Successfull-Header":
                returnMSG = Main.getMessages().getString("Show.Successfull-Header");
                break;
            //endregion
            //========================================================================================================================
            //region Set-Home
            case "Home.Set.Successfull-ToExecutor":
                returnMSG = Main.getMessages().getString("Home.Set.Successfull-ToExecutor");
                break;
            case "Home.Set.Failed-AsMitglied":
                returnMSG = Main.getMessages().getString("Home.Set.Failed-AsMitglied");
                break;
            case "Home.Set.Failed-NotInAGilde":
                returnMSG = Main.getMessages().getString("Home.Set.Failed-NotInAGilde");
                break;
            //endregion
            //========================================================================================================================
            //region Invite
            case "Invite.Failed-RunningOut-ToExecutor":
                returnMSG = Main.getMessages().getString("Invite.Failed-RunningOut-ToExecutor");
                break;
            case "Invite.Failed-RunningOut-ToTarget":
                returnMSG = Main.getMessages().getString("Invite.Failed-RunningOut-ToTarget");
                break;
            case "Invite.Failed-RemainingTime":
                returnMSG = Main.getMessages().getString("Invite.Failed-RemainingTime");
                break;
            case "Invite.Failed-SelfInvite":
                returnMSG = Main.getMessages().getString("Invite.Failed-SelfInvite");
                break;
            case "Invite.Failed-PlayerNotOnline":
                returnMSG = Main.getMessages().getString("Invite.Failed-PlayerNotOnline");
                break;
            case "Invite.Failed-PlayerAlreadyInAGilde":
                returnMSG = Main.getMessages().getString("Invite.Failed-PlayerAlreadyInAGilde");
                break;
            case "Invite.Invitation-ToExecutor":
                returnMSG = Main.getMessages().getString("Invite.Invitation-ToExecutor");
                break;
            case "Invite.Invitation-ToTarget":
                returnMSG = Main.getMessages().getString("Invite.Invitation-ToTarget");
                break;
            case "Invite.AcceptInvitation-ClickText":
                returnMSG = Main.getMessages().getString("Invite.AcceptInvitation-ClickText");
                break;
            case "Invite.AcceptInvitation-HoverText":
                returnMSG = Main.getMessages().getString("Invite.AcceptInvitation-HoverText");
                break;
            case "Invite.DenyInvitation-ClickText":
                returnMSG = Main.getMessages().getString("Invite.DenyInvitation-ClickText");
                break;
            case "Invite.DenyInvitation-HoverText":
                returnMSG = Main.getMessages().getString("Invite.DenyInvitation-HoverText");
                break;
            //endregion
            //========================================================================================================================
            //region Response
            case "Response.Failed-NoInvitation":
                returnMSG = Main.getMessages().getString("Response.Failed-NoInvitation");
                break;
            case "Response.Failed-AlreadyReplied":
                returnMSG = Main.getMessages().getString("Response.Failed-AlreadyReplied");
                break;
            case "Response.Successfull-Acceptance-ToPlayer":
                returnMSG = Main.getMessages().getString("Response.Successfull-Acceptance-ToPlayer");
                break;
            case "Response.Successfull-Acceptance-ToTarget":
                returnMSG = Main.getMessages().getString("Response.Successfull-Acceptance-ToTarget");
                break;
            case "Response.Successfull-Acceptance-ToGilde":
                returnMSG = Main.getMessages().getString("Response.Successfull-Acceptance-ToGilde");
                break;
            case "Response.Successfull-Denial-ToTarget":
                returnMSG = Main.getMessages().getString("Response.Successfull-Denial-ToTarget");
                break;
            case "Response.Successfull-Denial-ToExecutor":
                returnMSG = Main.getMessages().getString("Response.Successfull-Denial-ToExecutor");
                break;
            //endregion
            //========================================================================================================================
            default:
                return "Es wurde kein Message-Key gefunden. Prüfen sie die jeweiligen Dateien nach Fehlern!";
        }
        return getPrefix() + returnMSG;
    }

    public static String getPrefix()
    {
        return Main.getMessages().getString("Basic.Prefix");
    }
}
