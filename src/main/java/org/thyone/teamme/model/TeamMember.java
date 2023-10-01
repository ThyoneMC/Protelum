package org.thyone.teamme.model;

import java.util.Date;
import java.util.UUID;

public class TeamMember extends ContentBase {
    public TeamRole role;

    public TeamMember(UUID uuid, TeamRole role) {
        super(uuid);

        this.role = role;
    }
}
