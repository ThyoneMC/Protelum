package org.thyone.teamme;

import org.bukkit.plugin.java.JavaPlugin;
import org.thyone.teamme.command.CommandManager;
import org.thyone.teamme.command.CommandTabCompletion;
import org.thyone.teamme.event.PlayerJoin;
import org.thyone.teamme.database.DiscordMemberStorage;
import org.thyone.teamme.event.PlayerTakeDamage;
import org.thyone.teamme.model.ProtelumConfig;
import org.thyone.teamme.util.RefreshRunnable;
import org.thyone.teamme.database.ConfigFile;
import org.thyone.teamme.database.TeamStorage;
import org.thyone.teamme.util.ServerRequest;

import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

public final class Protelum extends JavaPlugin {
    public static Protelum plugin;

    public static Protelum getPlugin() {
        return plugin;
    }

    private ScheduledExecutorService executor;

    @Override
    public void onEnable() {
        plugin = this;

        try {
            TeamStorage.load();
            DiscordMemberStorage.load();
            ConfigFile.load();

            new ServerRequest().notifyStart();
        } catch (IOException exception) {
            getLogger().log(Level.SEVERE, exception.getMessage(), exception.getCause());
        }

        ProtelumConfig config = ConfigFile.getConfig();

        getCommand("protelum").setExecutor(new CommandManager());
        getCommand("protelum").setTabCompleter(new CommandTabCompletion());

        getServer().getPluginManager().registerEvents(new PlayerJoin(), this);
        if (!config.TEAM_ATTACK) getServer().getPluginManager().registerEvents(new PlayerTakeDamage(), this);

        this.executor = Executors.newScheduledThreadPool(1);

        long RefreshRate = config.REFRESH_RATE;
        executor.scheduleWithFixedDelay(RefreshRunnable.RefreshTeam(), RefreshRate * 2, RefreshRate, TimeUnit.SECONDS);
    }

    @Override
    public void onDisable() {
        executor.shutdown();

        try {
            TeamStorage.save();
            DiscordMemberStorage.save();

            new ServerRequest().notifyStop();
        } catch (IOException exception) {
            getLogger().log(Level.WARNING, exception.getMessage(),exception.getCause());
        }
    }
}
