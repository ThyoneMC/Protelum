package org.thyone.teamme.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

public class Team implements Serializable {
    public String name;
    public final UUID uuid;
    public final Date createdAt;
    public ArrayList<TeamMember> members;

    public Team(String name) {
        this.name = name;
        this.uuid = UUID.randomUUID();
        this.createdAt = new Date();
        this.members = new ArrayList<>();
    }
    // for saving
    public Team(String name, ArrayList<TeamMember> members) {
        this.name = name;
        this.uuid = UUID.randomUUID();
        this.createdAt = new Date();
        this.members = members;
    }

    // for loading
    public Team(Team teamData) {
        this.name = teamData.name;
        this.uuid = teamData.uuid;
        this.createdAt = teamData.createdAt;
        this.members = teamData.members;
    }

    public TeamMember getMember(UUID uuid) {
        for (TeamMember teamMember: members) {
            if (teamMember.uuid == uuid) {
                return teamMember;
            }
        }

        return null;
    }
}
