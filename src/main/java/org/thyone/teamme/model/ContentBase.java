package org.thyone.teamme.model;

import org.jetbrains.annotations.Nullable;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

public abstract class ContentBase implements Serializable {
    public final UUID uuid;
    public final long createdAt;

    protected ContentBase() {
        this.uuid = UUID.randomUUID();
        this.createdAt = new Date().getTime();
    }

    protected ContentBase(UUID uuid) {
        this.uuid = uuid;
        this.createdAt = new Date().getTime();
    }

    protected ContentBase(ContentBase content) {
        this.uuid = content.uuid;
        this.createdAt = content.createdAt;
    }
}
