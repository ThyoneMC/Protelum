package org.thyone.teamme.event;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.thyone.teamme.model.DiscordMember;
import org.thyone.teamme.model.ServerResponse;
import org.thyone.teamme.model.ServerVerifyResponse;
import org.thyone.teamme.util.DiscordMemberStorage;
import org.thyone.teamme.util.ServerRequest;

import java.io.IOException;
import java.net.UnknownHostException;

public class PlayerJoin implements Listener {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        ServerVerifyResponse response = null;
        try {
            ServerRequest client = new ServerRequest();

            response = client.findVerification(player.getUniqueId());
            if (response == null) return;
        } catch (UnknownHostException exception) {
            player.sendMessage(
                    Component
                            .text("Verification Fails")
                            .color(NamedTextColor.RED)
            );
            return;
        }

        try {
            DiscordMemberStorage.create(new DiscordMember(response.data.discordId));
        } catch (IOException exception) {
            player.sendMessage(
                    Component
                            .text("Verification Fails")
                            .color(NamedTextColor.RED)
            );
            return;
        }

        player.sendMessage(
                Component
                        .text("Verification Success")
                        .color(NamedTextColor.GREEN)
        );
    }
}
