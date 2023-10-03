package org.thyone.teamme.model;

import java.util.ArrayList;
import java.util.UUID;

public class Team extends ContentBase {
    public String name;
    public ArrayList<TeamMember> members;
    public ArrayList<UUID> invites;
    public long score;

    public Team(String name) {
        super();

        this.name = name;
        this.members = new ArrayList<>();
        this.invites = new ArrayList<>();
    }

    public Team(Team teamData) {
        super(teamData);

        this.name = teamData.name;
        this.members = teamData.members;
        this.invites = teamData.invites;
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

    public boolean isInvite(UUID uuid) {
        for (UUID invite: invites) {
            if (invite.equals(uuid)) {
                return true;
            }
        }

        return false;
    }
}
