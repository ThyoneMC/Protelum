package org.thyone.teamme.util;

import org.thyone.teamme.Protelum;
import org.thyone.teamme.model.Team;
import org.thyone.teamme.model.TeamMember;
import org.thyone.teamme.model.TeamRole;

import java.io.*;
import java.util.UUID;
import java.util.logging.Level;

public class TeamStorage {
    public static DataStorage<Team> storage = new DataStorage<>("team");

    // utils

    public static Team getTeamIn(UUID uuid) {
        for (Team team: storage.readAll()) {
            if (null != team.getMember(uuid)) {
                return team;
            }
        }

        return null;
    }

    public static TeamMember getTeamMember(UUID uuid) {
        for (Team team: storage.readAll()) {
            TeamMember teamMember = team.getMember(uuid);
            if (null != team.getMember(uuid)) {
                return teamMember;
            }
        }

        return null;
    }

    public static Team getTeamOwn(UUID uuid) {
        teamsLoop:
        for (Team team: storage.readAll()) {
            for (TeamMember teamMember: team.members) {
                if (teamMember.uuid.equals(uuid)) {
                    if (teamMember.role == TeamRole.Owner) {
                        return team;
                    }

                    return null;
                }

                if (teamMember.role == TeamRole.Owner) {
                    continue teamsLoop;
                }
            }
        }

        return null;
    }

    public static Team getTeamInvite(UUID userUuid, UUID teamUUID) {
        for (Team team: storage.readAll()) {
            if (team.uuid.equals(teamUUID) && team.isInvite(userUuid)) {
                return team;
            }
        }

        return null;
    }

    // storage

    public static void load() throws IOException {
        storage.load(Team[].class);
    }

    public static void save() throws IOException {
        storage.save();
    }

    public static Team create(Team teamData) throws IOException {
        Team team = new Team(teamData);

        storage.create(team);

        save();
        return team;
    }

    public static Team read(UUID uuid) {
        return storage.read(uuid);
    }

    public static void update(UUID uuid, Team teamData) throws IOException {
        storage.update(uuid, teamData);

        save();
    }

    public static void delete(UUID uuid) throws IOException {
        storage.delete(uuid);

        save();

        try {
            ServerRequest client = new ServerRequest();
            client.teamDelete(uuid);
        } catch (Exception exception) {
            Protelum.getPlugin().getLogger().log(Level.WARNING, exception.getMessage(), exception.getCause());
        }
    }
}
