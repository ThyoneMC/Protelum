package org.thyone.teamme.model;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

public abstract class ContentBase implements Serializable {
    public final String uuid;
    public final long createdAt;

    protected ContentBase() {
        this.uuid = UUID.randomUUID().toString();
        this.createdAt = new Date().getTime();
    }

    protected ContentBase(String uuid) {
        this.uuid = uuid;
        this.createdAt = new Date().getTime();
    }

    protected ContentBase(ContentBase content) {
        this.uuid = content.uuid;
        this.createdAt = content.createdAt;
    }
}
