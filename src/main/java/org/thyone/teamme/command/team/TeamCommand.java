package org.thyone.teamme.command.team;

import org.jetbrains.annotations.NotNull;
import org.thyone.teamme.model.SubCommand;
import org.thyone.teamme.model.SubCommandGroup;

public class TeamCommand extends SubCommandGroup {
    @Override
    public @NotNull String getName() {
        return "team";
    }

    @Override
    public String getDescription() {
        return "team manager";
    }

    @Override
    public SubCommand[] getSubCommand() {
        return new SubCommand[]{ new TeamCreateCommand(), new TeamDeleteCommand() };
    }
}
