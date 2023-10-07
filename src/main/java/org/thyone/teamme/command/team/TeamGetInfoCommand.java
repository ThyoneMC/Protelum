package org.thyone.teamme.command.team;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.thyone.teamme.model.SubCommand;
import org.thyone.teamme.model.SubCommandSyntax;
import org.thyone.teamme.model.Team;
import org.thyone.teamme.database.TeamStorage;

import java.text.MessageFormat;
import java.util.Date;
import java.util.UUID;

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
    public TextComponent[] execute(Player player, String[] args) {
        Team teamIn = TeamStorage.getTeamIn(player.getUniqueId());
        if (null == teamIn)
            return new TextComponent[]{
                    Component
                            .text("Team Not Found")
                            .color(NamedTextColor.RED)
            };

        UUID teamOwnerUUID = teamIn.getOwner().uuid;

        Date createdTime = new Date();
        createdTime.setTime(teamIn.createdAt);

        return new TextComponent[]{
                Component
                        .text("--------------------")
                        .color(NamedTextColor.AQUA),
                Component
                        .text(MessageFormat.format("Name: {0}", teamIn.name))
                        .color(NamedTextColor.AQUA)
                        .clickEvent(ClickEvent.suggestCommand(MessageFormat.format("TeamUUID: {0}", teamIn.uuid))),
                Component
                        .text(MessageFormat.format("Owner: {0}", Bukkit.getOfflinePlayer(teamOwnerUUID).getName()))
                        .color(NamedTextColor.AQUA)
                        .clickEvent(ClickEvent.suggestCommand(MessageFormat.format("OwnerUUID: {0}", teamOwnerUUID))),
                Component
                        .text(MessageFormat.format("Create At: {0}", createdTime))
                        .color(NamedTextColor.AQUA)
        };
    }
}
