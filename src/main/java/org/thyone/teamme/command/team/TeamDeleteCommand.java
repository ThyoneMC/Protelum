package org.thyone.teamme.command.team;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.thyone.teamme.model.SubCommand;
import org.thyone.teamme.model.SubCommandSyntax;
import org.thyone.teamme.model.Team;
import org.thyone.teamme.util.TeamStorage;

import java.io.IOException;
import java.util.logging.Level;

public class TeamDeleteCommand extends SubCommand {
    @Override
    public String getName() {
        return "delete";
    }

    @Override
    public String getDescription() {
        return "delete a team";
    }

    @Override
    public SubCommandSyntax[] getSyntax() {
        return new SubCommandSyntax[0];
    }

    @Override
    public void execute(Player player, String[] args) {
        Team team = TeamStorage.getTeamOwn(player.getUniqueId());
        if (null != team) {
            try {
                TeamStorage.delete(team.uuid);

                TextComponent textDelete =
                        Component
                                .text("Team Deleted")
                                .color(NamedTextColor.GREEN);
                player.sendMessage(textDelete);
            } catch (IOException exception) {
                Bukkit.getLogger().log(Level.WARNING, exception.getMessage(), exception.getCause());
            }

            return;
        }

        TextComponent textNotFound =
                Component
                        .text("Team Not Found")
                        .color(NamedTextColor.RED);
        player.sendMessage(textNotFound);
    }
}
