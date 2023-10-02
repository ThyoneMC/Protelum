package org.thyone.teamme.command.discord;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.thyone.teamme.model.SubCommand;
import org.thyone.teamme.model.SubCommandSyntax;
import org.thyone.teamme.util.ServerRequest;

import java.net.UnknownHostException;
import java.text.MessageFormat;
import java.util.UUID;

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
    public TextComponent[] execute(Player player, String[] args) {
        String verifyCode = UUID.randomUUID().toString();

        try {
            ServerRequest client = new ServerRequest();
            client.addVerification(player.getUniqueId(), verifyCode);
        } catch (UnknownHostException exception) {
            return new TextComponent[]{
                    Component
                            .text("New Verification Request Error")
                            .color(NamedTextColor.RED)
            };
        }

        return new TextComponent[]{
                Component
                        .text("Click & Paste this message to your discord server")
                        .color(NamedTextColor.GREEN)
                        .clickEvent(ClickEvent.copyToClipboard(MessageFormat.format("/protelum discord code:{0}", verifyCode)))
        };
    }
}
