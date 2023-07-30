package org.thyone.teamme.command.discord;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.thyone.teamme.model.SubCommand;
import org.thyone.teamme.model.SubCommandSyntax;

public class DiscordCommand extends SubCommand {
    @Override
    public @NotNull String getName() {
        return "discord";
    }

    @Override
    public String getDescription() {
        return "discord link";
    }

    @Override
    public SubCommandSyntax[] getSyntax() {
        return new SubCommandSyntax[]{};
    }

    @Override
    public void execute(Player player, String[] args) {
        TextComponent textTeamOwn =
                Component
                        .text("This command is Work-In-Progress")
                        .color(NamedTextColor.RED);
        player.sendMessage(textTeamOwn);
    }
}
