package org.thyone.teamme.model;

import java.io.Serializable;

public class ProtelumConfig implements Serializable {
    public short SERVER_PORT;

    // seconds
    public long REFRESH_RATE;

    public ProtelumConfig() {
        this.SERVER_PORT = 2255;
        this.REFRESH_RATE = 5 * 60;
    }
}
