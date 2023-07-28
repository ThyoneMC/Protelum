package org.thyone.teamme.Data;

import org.bukkit.Bukkit;
import org.bukkit.util.io.BukkitObjectInputStream;
import org.bukkit.util.io.BukkitObjectOutputStream;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.text.MessageFormat;
import java.util.HashSet;
import java.util.UUID;
import java.util.logging.Level;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class Team implements Serializable {
    public String name;
    public UUID uuid;
    public HashSet<TeamMember> members;

    // for saving
    public Team(String name, UUID uuid, HashSet<TeamMember> members) {
        this.name = name;
        this.uuid = uuid;
        this.members = members;
    }

    // for loading
    public Team(Team teamData) {
        this.name = teamData.name;
        this.uuid = teamData.uuid;
        this.members = teamData.members;
    }

    public boolean saveData(String filePath) {
        try {
            BukkitObjectOutputStream out = new BukkitObjectOutputStream(new GZIPOutputStream(new FileOutputStream(filePath)));
            out.writeObject(this);
            out.close();
            return true;
        } catch (IOException exception) {
            Bukkit.getServer().getLogger().log(Level.WARNING, exception.getMessage(), exception.getCause());
            return false;
        }
    }
    public static Team loadData(String filePath) {
        try {
            BukkitObjectInputStream in = new BukkitObjectInputStream(new GZIPInputStream(new FileInputStream(filePath)));
            Team data = (Team) in.readObject();
            in.close();
            return data;
        } catch (ClassNotFoundException | IOException exception) {
            Bukkit.getServer().getLogger().log(Level.WARNING, exception.getMessage(), exception.getCause());
            return null;
        }
    }

    public static Team newTeam(String name) {
        String teamPath = MessageFormat.format("teamme.Teams.{0}", "NAME");
        Team team = new Team(name, UUID.randomUUID(), new HashSet<TeamMember>());

        team.saveData(teamPath);
        Bukkit.getServer().getLogger().log(Level.INFO, "New Data Saved");

        return team;
    }

    public static void addMember(TeamMember member) {
        String teamPath = MessageFormat.format("teamme.Teams.{0}", "NAME");
        Team teamData = Team.loadData(teamPath);

        if (null != teamData) {
            Team team = new Team(teamData);
            team.members.add(member);

            team.saveData(teamPath);
            Bukkit.getServer().getLogger().log(Level.INFO, "Member Changed");
        }
    }

    public static void setName(String name) {
        String teamPath = MessageFormat.format("teamme.Teams.{0}", "NAME");
        Team teamData = Team.loadData(teamPath);

        if (null != teamData) {
            Team team = new Team(teamData);
            team.name = name;

            team.saveData(teamPath);
            Bukkit.getServer().getLogger().log(Level.INFO, "Name Changed");
        }
    }
}
