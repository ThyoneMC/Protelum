package org.thyone.teamme.model;

import java.io.Serializable;

public class ProtelumConfig implements Serializable {
    public short SERVER_PORT;

    public long REFRESH_RATE;

    public boolean TEAM_ATTACK;

    public ProtelumConfig() {
        this.SERVER_PORT = 2255;
        this.REFRESH_RATE = 5 * 60;
        this.TEAM_ATTACK = true;
    }
}
