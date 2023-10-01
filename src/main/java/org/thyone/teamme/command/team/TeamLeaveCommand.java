package org.thyone.teamme.command.team;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.thyone.teamme.model.SubCommand;
import org.thyone.teamme.model.SubCommandSyntax;
import org.thyone.teamme.model.Team;
import org.thyone.teamme.util.TeamStorage;

import java.io.IOException;

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
    public void execute(Player player, String[] args) {
        Team teamIn = TeamStorage.getTeamIn(player.getUniqueId());
        if (teamIn == null) {
            TextComponent textNotFound =
                    Component
                            .text("Team Not Found")
                            .color(NamedTextColor.RED);
            player.sendMessage(textNotFound);

            return;
        }

        if (teamIn.getOwner().uuid.equals(player.getUniqueId())) {
            TextComponent textOwnerLeave =
                    Component
                            .text("Owner of the team can not leave")
                            .color(NamedTextColor.RED);
            player.sendMessage(textOwnerLeave);

            return;
        }

        teamIn.members.removeIf(member -> member.uuid.equals(player.getUniqueId()));

        try {
            TeamStorage.update(teamIn.uuid, teamIn);
        } catch (IOException exception) {
            TextComponent textLeaveError =
                    Component
                            .text("Team Leave Error")
                            .color(NamedTextColor.RED);
            player.sendMessage(textLeaveError);

            return;
        }

        TextComponent textLeaved =
                Component
                        .text("Team Leaved")
                        .color(NamedTextColor.GREEN);
        player.sendMessage(textLeaved);
    }
}
