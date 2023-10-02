package org.thyone.teamme.command.team;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.thyone.teamme.model.*;
import org.thyone.teamme.util.TeamStorage;

import java.io.IOException;
import java.util.UUID;

public class TeamJoinCommand extends SubCommand {
    @Override
    public @NotNull String getName() {
        return "join";
    }

    @Override
    public String getDescription() {
        return "join invited team";
    }

    @Override
    public SubCommandSyntax[] getSyntax() {
        return new SubCommandSyntax[]{
                new TeamInviteNameSyntax()
        };
    }

    @Override
    public TextComponent[] execute(Player player, String[] args) {
        if (TeamStorage.getTeamMember(player.getUniqueId()) != null)
            return new TextComponent[]{
                    Component
                            .text("you can not join another team")
                            .color(NamedTextColor.RED)
            };

        Team targetTeam = TeamStorage.getTeamInvite(player.getUniqueId(), UUID.fromString(args[0]));
        if (targetTeam == null)
            return new TextComponent[]{
                    Component
                            .text("Team Not Found")
                            .color(NamedTextColor.RED)
            };

        targetTeam.members.add(
                new TeamMember(player.getUniqueId(), TeamRole.Member)
        );
        targetTeam.invites.removeIf(uuid -> uuid.equals(player.getUniqueId()));

        try {
            TeamStorage.update(targetTeam.uuid, targetTeam);
        } catch (IOException exception) {
            return new TextComponent[]{
                    Component
                            .text("Team Join Error")
                            .color(NamedTextColor.RED)
            };
        }

        return new TextComponent[]{
                Component
                        .text("Team Joined")
                        .color(NamedTextColor.GREEN)
        };
    }
}
