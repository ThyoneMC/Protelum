package org.thyone.teamme.model;

import java.util.ArrayList;
import java.util.UUID;

public class Team extends ContentBase {
    public String name;
    public ArrayList<TeamMember> members;
    public long score;

    public Team(String name) {
        super();

        this.name = name;
        this.members = new ArrayList<>();
    }

    public Team(Team teamData) {
        super(teamData);

        this.name = teamData.name;
        this.members = teamData.members;
        this.score = teamData.score;
    }

    public TeamMember getMember(UUID uuid) {
        for (TeamMember teamMember: members) {
            if (teamMember.uuid.equals(uuid)) {
                return teamMember;
            }
        }

        return null;
    }

    public TeamMember getOwner() {
        for (TeamMember teamMember: members) {
            if (teamMember.role == TeamRole.Owner) {
                return teamMember;
            }
        }

        return null;
    }
}
