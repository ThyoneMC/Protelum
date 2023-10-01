package org.thyone.teamme.command.team;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.thyone.teamme.model.SubCommand;
import org.thyone.teamme.model.SubCommandSyntax;
import org.thyone.teamme.model.Team;
import org.thyone.teamme.util.TeamStorage;

import java.io.IOException;
import java.text.MessageFormat;

public class TeamInviteCommand extends SubCommand {
    @Override
    public @NotNull String getName() {
        return "invite";
    }

    @Override
    public String getDescription() {
        return "invite a new member";
    }

    @Override
    public SubCommandSyntax[] getSyntax() {
        return new SubCommandSyntax[]{
            new TeamInviteNameSyntax()
        };
    }

    @Override
    public void execute(Player player, String[] args) {
        Team teamOwn = TeamStorage.getTeamOwn(player.getUniqueId());
        if (teamOwn == null) {
            TextComponent textNotOwn =
                    Component
                            .text("You are not owner of the team")
                            .color(NamedTextColor.RED);
            player.sendMessage(textNotOwn);

            return;
        }

        Player targetPlayer = Bukkit.getServer().getPlayer(args[0]);
        if (targetPlayer == null) {
            TextComponent textNotFound =
                    Component
                            .text("Player Not Found")
                            .color(NamedTextColor.RED);
            player.sendMessage(textNotFound);

            return;
        }

        if (TeamStorage.getTeamMember(targetPlayer.getUniqueId()) != null) {
            TextComponent textInTeam =
                    Component
                            .text("This member are already in some team")
                            .color(NamedTextColor.RED);
            player.sendMessage(textInTeam);

            return;
        }

        teamOwn.invites.add(targetPlayer.getUniqueId());
        try {
            TeamStorage.update(teamOwn.uuid, teamOwn);
        } catch (IOException exception) {
            TextComponent textInviteError =
                    Component
                            .text("Team Invite Error")
                            .color(NamedTextColor.RED);
            player.sendMessage(textInviteError);

            return;
        }

        TextComponent textSayJoin =
                Component
                        .text(MessageFormat.format("<{0}> are invite you to [ JOIN THE TEAM ]", targetPlayer.getName()))
                        .color(NamedTextColor.GOLD)
                        .clickEvent(ClickEvent.suggestCommand(MessageFormat.format("/protelum team join {0}", teamOwn.uuid.toString())));
        targetPlayer.sendMessage(textSayJoin);

        TextComponent textInvited =
                Component
                        .text("Invite sent")
                        .color(NamedTextColor.GREEN);
        player.sendMessage(textInvited);
    }
}
