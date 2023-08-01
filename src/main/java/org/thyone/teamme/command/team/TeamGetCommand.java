package org.thyone.teamme.command.team;

import org.jetbrains.annotations.NotNull;
import org.thyone.teamme.model.SubCommandBase;
import org.thyone.teamme.model.SubCommandGroup;

public class TeamGetCommand extends SubCommandGroup {
    @Override
    public @NotNull String getName() {
        return "get";
    }

    @Override
    public String getDescription() {
        return "infomation of team";
    }

    @Override
    public SubCommandBase[] getSubCommand() {
        return new SubCommandBase[]{ new TeamGetMemberCommand(), new TeamGetInfoCommand() };
    }
}
