package org.thyone.teamme.model;

import org.thyone.teamme.Protelum;
import org.thyone.teamme.util.ServerRequest;
import org.thyone.teamme.util.TeamStorage;

import java.util.logging.Level;

public class RefreshRunnable {
    public static Runnable RefreshTeam() {
        return () -> {
            try {
                ServerRequest client = new ServerRequest();
                client.teamsUpdate(TeamStorage.storage.readAll().toArray(Team[]::new));

                Protelum.getPlugin().getLogger().log(Level.INFO, "Team package sent");
            } catch (Exception exception) {
                Protelum.getPlugin().getLogger().log(Level.WARNING, exception.getMessage(), exception.getCause());
            }
        };
    }
}
