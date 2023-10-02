package org.thyone.teamme.model;

import java.io.Serializable;

public class ProtelumConfig implements Serializable {
    public short SERVER_PORT;

    public ProtelumConfig() {
        this.SERVER_PORT = 2255;
    }
}
