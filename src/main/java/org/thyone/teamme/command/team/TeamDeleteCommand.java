package org.thyone.teamme.command.team;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.thyone.teamme.Protelum;
import org.thyone.teamme.model.SubCommand;
import org.thyone.teamme.model.SubCommandSyntax;
import org.thyone.teamme.model.Team;
import org.thyone.teamme.database.TeamStorage;

import java.io.IOException;
import java.util.logging.Level;

public class TeamDeleteCommand extends SubCommand {
    @Override
    public @NotNull String getName() {
        return "delete";
    }

    @Override
    public String getDescription() {
        return "delete a team";
    }

    @Override
    public SubCommandSyntax[] getSyntax() {
        return new SubCommandSyntax[]{
                new TeamDeleteNameSyntax()
        };
    }

    @Override
    public TextComponent[] execute(Player player, String[] args) {
        Team team = TeamStorage.getTeamOwn(player.getUniqueId());
        if (team == null)
            return new TextComponent[]{
                    Component
                            .text("Team Not Found")
                            .color(NamedTextColor.RED)
            };

        if(!team.name.equals(args[0]))
            return new TextComponent[]{
                    Component
                            .text("your givin name does not match with your team name")
                            .color(NamedTextColor.RED)
            };

        try {
            TeamStorage.delete(team.uuid);
        } catch (IOException exception) {
            Protelum.getPlugin().getLogger().log(Level.WARNING, exception.getMessage(), exception.getCause());

            return new TextComponent[]{
                    Component
                            .text("Team Delete Error")
                            .color(NamedTextColor.RED)
            };
        }

        return new TextComponent[]{
                Component
                        .text("Team Deleted")
                        .color(NamedTextColor.GREEN)
        };
    }
}
