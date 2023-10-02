package org.thyone.teamme.model;

import java.io.Serializable;

public class ServerResponse implements Serializable {
    public final short status;
    public final Object data;

    public ServerResponse(ServerResponse response) {
        this.status = response.status;
        this.data = response.data;
    }
}
