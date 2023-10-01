package org.thyone.teamme.command.team;

import org.jetbrains.annotations.NotNull;
import org.thyone.teamme.model.SubCommandBase;
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
    public SubCommandBase[] getSubCommand() {
        return new SubCommandBase[]{new TeamCreateCommand(), new TeamDeleteCommand(), new TeamGetCommand(), new TeamInviteCommand(), new TeamJoinCommand()};
    }
}
