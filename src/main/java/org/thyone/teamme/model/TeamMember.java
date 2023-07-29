package org.thyone.teamme.model;

import java.util.UUID;

public class TeamMember {
    public UUID uuid;
    public TeamRole role;

    public TeamMember(UUID uuid, TeamRole role) {
        this.uuid = uuid;
        this.role = role;
    }
}
