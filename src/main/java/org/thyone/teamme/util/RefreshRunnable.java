package org.thyone.teamme.util;

import org.thyone.teamme.Protelum;
import org.thyone.teamme.database.TeamStorage;
import org.thyone.teamme.model.Team;

import java.util.logging.Level;

public class RefreshRunnable {
    public static Runnable RefreshTeam() {
        return () -> {
            try {
                ServerRequest client = new ServerRequest();
                client.teamUpdate(TeamStorage.storage.readAll().toArray(Team[]::new));
            } catch (Exception exception) {
                Protelum.getPlugin().getLogger().log(Level.WARNING, exception.getMessage(), exception.getCause());
            }
        };
    }
}
