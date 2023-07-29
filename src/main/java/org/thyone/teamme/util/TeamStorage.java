package org.thyone.teamme.util;

import com.google.gson.Gson;
import org.bukkit.Bukkit;
import org.thyone.teamme.Protelum;
import org.thyone.teamme.model.Team;
import org.thyone.teamme.model.TeamMember;
import org.thyone.teamme.model.TeamRole;

import java.io.*;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;
import java.util.logging.Level;

public class TeamStorage {
    public static ArrayList<Team> teams = new ArrayList<>();

    // utils

    public static Team getTeamIn(UUID uuid) {
        for (Team team: teams) {
            for (TeamMember teamMember: team.members) {
                if (teamMember.uuid == uuid) {
                    return team;
                }
            }
        }

        return null;
    }

    public static TeamMember getTeamMember(UUID uuid) {
        for (Team team: teams) {
            for (TeamMember teamMember: team.members) {
                if (teamMember.uuid == uuid) {
                    return teamMember;
                }
            }
        }

        return null;
    }

    public static Team getTeamOwn(UUID uuid) {
        teamsLoop:
        for (Team team: teams) {
            for (TeamMember teamMember: team.members) {
                if (teamMember.uuid == uuid) {
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

    // storage

    public static void save() throws IOException {
        Gson gson = new Gson();
        File file = new File(Paths.get(Protelum.getPlugin().getDataFolder().getAbsolutePath(), "team.json").toString());
        file.getParentFile().mkdir();
        file.createNewFile();

        Writer writer = new FileWriter(file);
        gson.toJson(teams, writer);
        writer.flush();
        writer.close();

        Bukkit.getLogger().log(Level.INFO, "Team Saved");
    }

    public static void load() throws IOException {
        Gson gson = new Gson();
        File file = new File(Paths.get(Protelum.getPlugin().getDataFolder().getAbsolutePath(), "team.json").toString());
        if (file.exists()) {
            Reader reader = new FileReader(file);
            Team[] teamsJson = gson.fromJson(reader, Team[].class);
            teams = new ArrayList<>(Arrays.asList(teamsJson));

            Bukkit.getLogger().log(Level.INFO, "Team Loaded");
        }
    }

    public static Team create(Team teamData) throws IOException {
        Team team = new Team(teamData);
        teams.add(team);

        save();

        return team;
    }

    public static Team read(UUID uuid) {
        for (Team team: teams) {
            if (team.uuid == uuid) {
                return team;
            }
        }
        return null;
    }

    public interface TeamUpdate {
        Team edit (Team teamData);
    }

    public static void update(UUID uuid, TeamUpdate teamUpdate) throws IOException {
        for (Team team: teams) {
            if (team.uuid == uuid) {
                team = teamUpdate.edit(team);
                break;
            }
        }

        save();
    }

    public static void delete(UUID uuid) throws IOException {
        for (Team team: teams) {
            if (team.uuid == uuid) {
                teams.remove(team);
                break;
            }
        }

        save();
    }
}
