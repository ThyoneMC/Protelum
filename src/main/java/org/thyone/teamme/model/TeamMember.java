package org.thyone.teamme.model;

public class TeamMember extends ContentBase {
    public TeamRole role;

    public TeamMember(String  uuid, TeamRole role) {
        super(uuid);

        this.role = role;
    }
}
