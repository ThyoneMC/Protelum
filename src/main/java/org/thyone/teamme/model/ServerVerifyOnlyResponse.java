package org.thyone.teamme.model;

public class ServerVerifyOnlyResponse extends ContentBase {
    public final String code;
    public final String discordId;

    public ServerVerifyOnlyResponse(ServerVerifyOnlyResponse response) {
        super(response.uuid);

        this.code = response.code;
        this.discordId = response.discordId;
    }
}
