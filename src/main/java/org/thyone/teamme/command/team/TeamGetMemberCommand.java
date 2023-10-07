package org.thyone.teamme.command.team;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.thyone.teamme.model.SubCommand;
import org.thyone.teamme.model.SubCommandSyntax;
import org.thyone.teamme.model.Team;
import org.thyone.teamme.model.TeamMember;
import org.thyone.teamme.database.TeamStorage;

import java.text.MessageFormat;
import java.util.ArrayList;

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
    public @Nullable TextComponent[] execute(Player player, String[] args) {
        Team teamIn = TeamStorage.getTeamIn(player.getUniqueId());
        if (null == teamIn)
            return new TextComponent[]{
                    Component
                            .text("Team Not Found")
                            .color(NamedTextColor.RED)
            };

        ArrayList<String> teamMembers = new ArrayList<>();
        for (TeamMember member: teamIn.members) {
            OfflinePlayer thatPlayer = Bukkit.getOfflinePlayer(member.uuid);

            teamMembers.add(thatPlayer.getName());
        }

        return new TextComponent[]{
                Component
                        .text("--------------------")
                        .color(NamedTextColor.AQUA),
                Component
                        .text(MessageFormat.format("Members: {0}", String.join(", ", teamMembers)))
                        .color(NamedTextColor.AQUA)
        };
    }
}
