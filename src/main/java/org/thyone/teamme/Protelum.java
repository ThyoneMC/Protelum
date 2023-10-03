package org.thyone.teamme;

import org.bukkit.plugin.java.JavaPlugin;
import org.thyone.teamme.command.CommandManager;
import org.thyone.teamme.command.CommandTabCompletion;
import org.thyone.teamme.event.PlayerJoin;
import org.thyone.teamme.model.RefreshRunnable;
import org.thyone.teamme.util.ConfigFile;
import org.thyone.teamme.util.TeamStorage;

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

    @Override
    public void onEnable() {
        plugin = this;

        try {
            TeamStorage.load();
            ConfigFile.load();
        } catch (IOException exception) {
            getLogger().log(Level.SEVERE, exception.getMessage(), exception.getCause());
        }

        getCommand("protelum").setExecutor(new CommandManager());
        getCommand("protelum").setTabCompleter(new CommandTabCompletion());

        getServer().getPluginManager().registerEvents(new PlayerJoin(), this);

        long RefreshRate = ConfigFile.getConfig().REFRESH_RATE;
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        executor.scheduleWithFixedDelay(RefreshRunnable.RefreshTeam(), RefreshRate * 2, RefreshRate, TimeUnit.SECONDS);
    }

    @Override
    public void onDisable() {
        try {
            TeamStorage.save();
        } catch (IOException exception) {
            getLogger().log(Level.WARNING, exception.getMessage(),exception.getCause());
        }
    }
}
