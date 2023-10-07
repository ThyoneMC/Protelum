package org.thyone.teamme.command.team;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.thyone.teamme.database.TeamStorage;
import org.thyone.teamme.model.SubCommandCustomSyntax;
import org.thyone.teamme.model.Team;

import java.util.ArrayList;
import java.util.List;

public class TeamKickNameSyntax extends SubCommandCustomSyntax {

    @Override
    public @NotNull String getName() {
        return "name";
    }

    @Override
    public String getDescription() {
        return "name of player";
    }

    @Override
    public List<String> getTabCompletion(Player player, String arg) {
        Team team = TeamStorage.getTeamOwn(player.getUniqueId());
        if (team == null) return new ArrayList<>();

        return team.members.stream().map(member -> Bukkit.getOfflinePlayer(member.uuid).getName()).toList();
    }
}
