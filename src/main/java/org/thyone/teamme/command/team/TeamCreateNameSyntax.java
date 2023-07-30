package org.thyone.teamme.command.team;

import org.jetbrains.annotations.NotNull;
import org.thyone.teamme.model.SubCommandSyntax;

public class TeamCreateNameSyntax extends SubCommandSyntax {

    @Override
    public int getId() {
        return 1;
    }

    @Override
    public @NotNull String getName() {
        return "name";
    }

    @Override
    public String getDescription() {
        return "name of team";
    }

    @Override
    public boolean getRequired() {
        return false;
    }
}
