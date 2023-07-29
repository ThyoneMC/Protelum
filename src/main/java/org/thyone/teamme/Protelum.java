package org.thyone.teamme;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import org.thyone.teamme.command.CommandManager;
import org.thyone.teamme.command.subcommand.CreateCommand;
import org.thyone.teamme.util.TeamStorage;

import java.io.IOException;
import java.util.Objects;
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
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        getLogger().log(Level.ALL, command.getName());

        if (command.getName().equalsIgnoreCase("protelum")) {

        }

        return true;
    }
}
