package org.thyone.teamme.command;

import org.jetbrains.annotations.NotNull;
import org.thyone.teamme.command.discord.DiscordCommand;
import org.thyone.teamme.command.team.TeamCommand;
import org.thyone.teamme.model.SubCommandBase;
import org.thyone.teamme.model.SubCommandGroup;

public class ProtelumCommand extends SubCommandGroup {
    @Override
    public @NotNull String getName() {
        return "protelum";
    }

    @Override
    public String getDescription() {
        return "Protelum Plugin";
    }

    @Override
    public SubCommandBase[] getSubCommand() {
        return new SubCommandBase[]{ new DiscordCommand(), new TeamCommand() };
    }
}
