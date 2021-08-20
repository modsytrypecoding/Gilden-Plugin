package de.Initium.Gilden.Commands.Tag;

import de.Initium.Gilden.Commands.gilde_Main;
import de.Initium.Gilden.Main.Main;
import de.Initium.Gilden.Main.ToolBox;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.hover.content.Text;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.time.LocalDate;

public class gilde_delTag extends JavaPlugin {

    public static void execute(Integer nr) {
        Player p = gilde_Main.getPlayer(nr);
        String gilde = ToolBox.getGildeNameOfPlayer(p);

        if(ToolBox.getallPlayers().contains(p.getUniqueId().toString())) {
            if(ToolBox.getGildeRankByPlayer(gilde, p.getUniqueId().toString()).equalsIgnoreCase("Leiter")) {

                    if(ToolBox.checkTagExists(ToolBox.getTagbyGilde(gilde))) {
                            if(response.responsecancel.contains(p)) {
                                response.responsecancel.remove(p);
                            }

                            p.sendMessage("Wenn du deinen Tag löscht wirst du erst in 30 Tagen\neinen neuen Tag setzten können!");
                            p.sendMessage("Bist du dir sicher das du deinen Tag §clöschen §rwillst?");
                            TextComponent tc = new TextComponent();
                            TextComponent tc2 = new TextComponent();
                            tc.setText("           §aJa");
                            tc.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/gilde responsedelet"));
                            tc.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new Text("Löschen Bestätigen")));
                            tc2.setText("          §cNein");
                            tc2.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/gilde responsecancle"));
                            tc2.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new Text("Löschen abbrechen")));

                            p.spigot().sendMessage(tc, tc2);




                    }else {
                        p.sendMessage("§cDeine Gilde hat noch kein Tag!");
                    }


            }
        }else {
            p.sendMessage("§cDu kannst diesen Befehl nicht benutzen!");
        }



    }

}
