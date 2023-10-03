package org.thyone.teamme.command.team;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.thyone.teamme.Protelum;
import org.thyone.teamme.model.*;
import org.thyone.teamme.util.TeamStorage;

import java.io.IOException;
import java.util.UUID;
import java.util.logging.Level;

public class TeamCreateCommand extends SubCommand {
    @Override
    public @NotNull String getName() {
        return "add";
    }

    @Override
    public String getDescription() {
        return "create new team";
    }

    @Override
    public SubCommandSyntax[] getSyntax() {
        return new SubCommandSyntax[]{
                new TeamCreateNameSyntax()
        };
    }

    @Override
    public TextComponent[] execute(Player player, String[] args) {
        UUID playerUUID = player.getUniqueId();
        if (TeamStorage.getTeamMember(playerUUID) != null)
            return new TextComponent[]{
                    Component
                            .text("you can not create another team")
                            .color(NamedTextColor.RED)
            };

        String teamName = player.getName();
        if (args.length > 0) {
            teamName = String.join(" ", args);
        }

        Team newTeam = new Team(teamName);
        newTeam.members.add(
                new TeamMember(playerUUID, TeamRole.Owner)
        );

        try {
            TeamStorage.create(newTeam);
        } catch (IOException exception) {
            Protelum.getPlugin().getLogger().log(Level.WARNING, exception.getMessage(),exception.getCause());

            return new TextComponent[]{
                    Component
                            .text("Team Create Error")
                            .color(NamedTextColor.RED)
            };
        }

        return new TextComponent[]{
                Component
                        .text("Team Created")
                        .color(NamedTextColor.GREEN)
        };
    }
}
