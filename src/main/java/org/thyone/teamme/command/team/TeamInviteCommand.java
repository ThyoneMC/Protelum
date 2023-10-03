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
    public TextComponent[] execute(Player player, String[] args) {
        Team teamOwn = TeamStorage.getTeamOwn(player.getUniqueId().toString());
        if (teamOwn == null)
            return new TextComponent[]{
                    Component
                            .text("You are not owner of the team")
                            .color(NamedTextColor.RED)
            };

        Player targetPlayer = Bukkit.getServer().getPlayer(args[0]);
        if (targetPlayer == null)
            return new TextComponent[] {
                    Component
                            .text("Player Not Found")
                            .color(NamedTextColor.RED)
            };

        String targetPlayerUUID = targetPlayer.getUniqueId().toString();
        if (TeamStorage.getTeamMember(targetPlayerUUID) != null)
            return new TextComponent[]{
                    Component
                            .text("This member are already in some team")
                            .color(NamedTextColor.RED)
            };

        teamOwn.invites.add(targetPlayerUUID);
        try {
            TeamStorage.update(teamOwn.uuid, teamOwn);
        } catch (IOException exception) {
            return new TextComponent[]{
                    Component
                            .text("Team Invite Error")
                            .color(NamedTextColor.RED)
            };
        }

        TextComponent textSayJoin =
                Component
                        .text(MessageFormat.format("<{0}> are invite you to [ JOIN THE TEAM ]", targetPlayer.getName()))
                        .color(NamedTextColor.GOLD)
                        .clickEvent(ClickEvent.suggestCommand(MessageFormat.format("/protelum team join {0}", teamOwn.uuid)));
        targetPlayer.sendMessage(textSayJoin);

        return new TextComponent[]{
                Component
                        .text("Invite sent")
                        .color(NamedTextColor.GREEN)
        };
    }
}
