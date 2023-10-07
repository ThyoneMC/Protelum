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
import org.thyone.teamme.database.TeamStorage;
import java.io.IOException;
import java.util.UUID;

public class TeamKickCommand extends SubCommand {
    @Override
    public @NotNull String getName() {
        return "kick";
    }

    @Override
    public String getDescription() {
        return "kick player from the team";
    }

    @Override
    public SubCommandSyntax[] getSyntax() {
        return new SubCommandSyntax[]{
                new TeamKickNameSyntax(),
        };
    }

    @Override
    public TextComponent[] execute(Player player, String[] args) {
        UUID playerUUID = player.getUniqueId();
        Team team = TeamStorage.getTeamOwn(playerUUID);
        if (team == null)
            return new TextComponent[]{
                    Component
                            .text("Team Not Found")
                            .color(NamedTextColor.RED)
            };

        UUID targetPlayerUUID = Bukkit.getOfflinePlayer(args[0]).getUniqueId();
        if (playerUUID.equals(targetPlayerUUID))
            return new TextComponent[]{
                    Component
                            .text("You can not kick owner of the team")
                            .color(NamedTextColor.RED)
            };

        team.members.removeIf(member -> member.uuid.equals(targetPlayerUUID));

        try {
            TeamStorage.update(team.uuid, team);
        } catch (IOException exception) {
            return new TextComponent[]{
                    Component
                            .text("Team Kick Error")
                            .color(NamedTextColor.RED)
            };
        }

        return new TextComponent[]{
                Component
                        .text("Player Kicked")
                        .color(NamedTextColor.GREEN)
        };
    }
}
