package org.thyone.teamme.model;

import java.util.UUID;

public class DiscordMember extends ContentBase {
    public String discordId;

    public DiscordMember(UUID uuid, String discordId) {
        super(uuid);

        this.discordId = discordId;
    }

    public DiscordMember(DiscordMember member) {
        super(member);

        this.discordId = member.discordId;
    }
}
