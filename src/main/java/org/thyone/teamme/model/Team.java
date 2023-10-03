package org.thyone.teamme.model;

import java.util.ArrayList;

public class Team extends ContentBase {
    public String name;
    public ArrayList<TeamMember> members;
    public ArrayList<String> invites;
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

    public TeamMember getMember(String uuid) {
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

    public boolean isInvite(String  uuid) {
        for (String invite: invites) {
            if (invite.equals(uuid)) {
                return true;
            }
        }

        return false;
    }
}
