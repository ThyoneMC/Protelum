package org.thyone.teamme;

import org.bukkit.plugin.java.JavaPlugin;
import org.thyone.teamme.command.CommandManager;
import org.thyone.teamme.command.CommandTabCompletion;
import org.thyone.teamme.util.TeamStorage;

import java.io.IOException;
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
        } catch (IOException exception) {
            getLogger().log(Level.WARNING, exception.getMessage(),exception.getCause());
        }

        getCommand("protelum").setExecutor(new CommandManager());
        getCommand("protelum").setTabCompleter(new CommandTabCompletion());
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
