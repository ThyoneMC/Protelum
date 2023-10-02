package org.thyone.teamme.model;

import java.io.Serializable;

public class ServerVerifyOnlyResponse implements Serializable {
    public final String uuid;
    public final String code;
    public final String discordId;

    public ServerVerifyOnlyResponse(ServerVerifyOnlyResponse response) {
        this.uuid = response.uuid;
        this.code = response.code;
        this.discordId = response.discordId;
    }
}
