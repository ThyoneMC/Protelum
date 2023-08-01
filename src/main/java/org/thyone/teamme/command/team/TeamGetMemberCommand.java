package org.thyone.teamme.command.team;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.thyone.teamme.model.SubCommand;
import org.thyone.teamme.model.SubCommandSyntax;
import org.thyone.teamme.model.Team;
import org.thyone.teamme.util.TeamStorage;

import java.text.MessageFormat;

public class TeamGetMemberCommand extends SubCommand {
    @Override
    public @NotNull String getName() {
        return "members";
    }

    @Override
    public String getDescription() {
        return "members of your team";
    }

    @Override
    public SubCommandSyntax[] getSyntax() {
        return new SubCommandSyntax[0];
    }

    @Override
    public void execute(Player player, String[] args) {
        Team teamIn = TeamStorage.getTeamIn(player.getUniqueId());

        if (null == teamIn) {
            TextComponent textNotFound =
                    Component
                            .text("Team Not Found")
                            .color(NamedTextColor.RED);
            player.sendMessage(textNotFound);

            return;
        }

        TextComponent textNewLine =
                Component
                        .text("--------------------")
                        .color(NamedTextColor.AQUA);
        player.sendMessage(textNewLine);

        TextComponent text =
                Component
                        .text(MessageFormat.format("Members: {0}", teamIn.members.stream().map(teamMember -> Bukkit.getPlayer(teamMember.uuid).getName()).toArray()))
                        .color(NamedTextColor.AQUA);
        player.sendMessage(text);
    }
}
