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
import org.thyone.teamme.util.TeamStorage;

import java.text.MessageFormat;
import java.util.Date;

public class TeamGetInfoCommand extends SubCommand {
    @Override
    public @NotNull String getName() {
        return "info";
    }

    @Override
    public String getDescription() {
        return "info of your teams";
    }

    @Override
    public SubCommandSyntax[] getSyntax() {
        return new SubCommandSyntax[0];
    }

    @Override
    public void execute(Player player, String[] args) {
        Team teamIn = TeamStorage.getTeamIn(player.getUniqueId());

        if (null == teamIn) {
            TextComponent textNotFound =
                    Component
                            .text("Team Not Found")
                            .color(NamedTextColor.RED);
            player.sendMessage(textNotFound);

            return;
        }

        TextComponent textNewLine =
                Component
                        .text("--------------------")
                        .color(NamedTextColor.AQUA);
        player.sendMessage(textNewLine);

        TextComponent textOwner =
                Component
                        .text(MessageFormat.format("Owner: {0}", Bukkit.getPlayer(teamIn.getOwner().uuid).getName()))
                        .color(NamedTextColor.AQUA);
        player.sendMessage(textOwner);

        Date createdTime = new Date();
        createdTime.setTime(teamIn.createdAt);
        TextComponent textCreateAt =
                Component
                        .text(MessageFormat.format("Create At: {0}", createdTime))
                        .color(NamedTextColor.AQUA);
        player.sendMessage(textCreateAt);
    }
}
