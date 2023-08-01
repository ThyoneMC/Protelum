package org.thyone.teamme.command.team;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.thyone.teamme.model.*;
import org.thyone.teamme.util.TeamStorage;

import java.io.IOException;
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
    public void execute(Player player, String[] args) {
        if (null != TeamStorage.getTeamMember(player.getUniqueId())) {
            TextComponent textTeamOwn =
                    Component
                            .text("you can't create another team")
                            .color(NamedTextColor.RED);
            player.sendMessage(textTeamOwn);

            return;
        }

        String teamName = player.getName();
        if (args.length > 0) {
            teamName = String.join(" ", args);
        }

        Team newTeam = new Team(teamName);
        newTeam.members.add(
                new TeamMember(player.getUniqueId(), TeamRole.Owner)
        );

        try {
            TeamStorage.create(newTeam);

            TextComponent text =
                    Component
                            .text("Team Created")
                            .color(NamedTextColor.GREEN);
            player.sendMessage(text);
        } catch (IOException exception) {
            Bukkit.getLogger().log(Level.WARNING, exception.getMessage(),exception.getCause());
        }
    }
}
