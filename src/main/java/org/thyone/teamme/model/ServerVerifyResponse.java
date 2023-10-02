package org.thyone.teamme.model;

import java.io.Serializable;

public class ServerVerifyResponse implements Serializable {
    public final short status;
    public final ServerVerifyOnlyResponse data;

    public ServerVerifyResponse(ServerVerifyResponse response) {
        this.status = response.status;
        this.data = response.data;
    }
}
