package org.thyone.teamme.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.thyone.teamme.command.discord.DiscordCommand;
import org.thyone.teamme.command.team.TeamCommand;
import org.thyone.teamme.model.SubCommandBase;

import java.util.ArrayList;
import java.util.List;

public class CommandTabCompletion implements TabCompleter {

    public ArrayList<SubCommandBase> subCommands = new ArrayList<>();

    public CommandTabCompletion() {
        subCommands.add(new TeamCommand());
        subCommands.add(new DiscordCommand());
    }
    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        return null;
    }
}
