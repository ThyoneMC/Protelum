package org.thyone.teamme.command.team;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.thyone.teamme.model.SubCommandCustomSyntax;

import java.util.List;

public class TeamInviteNameSyntax extends SubCommandCustomSyntax {

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
        return Bukkit.getOnlinePlayers().stream().map(Player::getName).toList();
    }
}
