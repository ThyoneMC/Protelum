package org.thyone.teamme.model;

public class DiscordMember extends ContentBase {

    public DiscordMember(String discordId) {
        super(discordId);
    }

    public DiscordMember(DiscordMember member) {
        super(member);
    }
}
