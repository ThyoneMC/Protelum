package org.thyone.teamme.command.team;

import org.jetbrains.annotations.NotNull;
import org.thyone.teamme.model.SubCommandSyntax;

public class TeamJoinIdSyntax extends SubCommandSyntax {

    @Override
    public @NotNull String getName() {
        return "uuid";
    }

    @Override
    public String getDescription() {
        return "uuid of team";
    }

    @Override
    public boolean getRequired() {
        return true;
    }
}
