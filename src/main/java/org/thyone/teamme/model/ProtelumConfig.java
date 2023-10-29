package org.thyone.teamme.model;

import java.io.Serializable;

public class ProtelumConfig implements Serializable {
    public short SERVER_PORT = 2255;

    public long REFRESH_RATE = 5 * 60;

    public boolean TEAM_ATTACK = true;

    public String PUBLIC_ADDRESS = "";
}
