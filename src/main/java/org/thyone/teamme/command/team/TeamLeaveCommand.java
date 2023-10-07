package org.thyone.teamme.command.team;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.thyone.teamme.model.SubCommand;
import org.thyone.teamme.model.SubCommandSyntax;
import org.thyone.teamme.model.Team;
import org.thyone.teamme.database.TeamStorage;

import java.io.IOException;
import java.util.UUID;

public class TeamLeaveCommand extends SubCommand {
    @Override
    public @NotNull String getName() {
        return "leave";
    }

    @Override
    public String getDescription() {
        return "leave from the current team";
    }

    @Override
    public SubCommandSyntax[] getSyntax() {
        return new SubCommandSyntax[0];
    }

    @Override
    public TextComponent[] execute(Player player, String[] args) {
        UUID playerUUID = player.getUniqueId();
        Team teamIn = TeamStorage.getTeamIn(playerUUID);
        if (teamIn == null)
            return new TextComponent[]{
                    Component
                            .text("Team Not Found")
                            .color(NamedTextColor.RED)
            };

        if (teamIn.getOwner().uuid.equals(playerUUID))
            return new TextComponent[]{
                    Component
                            .text("Owner of the team can not leave")
                            .color(NamedTextColor.RED)
            };

        teamIn.members.removeIf(member -> member.uuid.equals(playerUUID));

        try {
            TeamStorage.update(teamIn.uuid, teamIn);
        } catch (IOException exception) {
            return new TextComponent[]{
                    Component
                            .text("Team Leave Error")
                            .color(NamedTextColor.RED)
            };
        }

        return new TextComponent[]{
                Component
                        .text("Team Leaved")
                        .color(NamedTextColor.GREEN)
        };
    }
}
