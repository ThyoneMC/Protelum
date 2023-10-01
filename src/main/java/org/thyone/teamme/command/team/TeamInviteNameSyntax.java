package org.thyone.teamme.command.team;

import org.jetbrains.annotations.NotNull;
import org.thyone.teamme.model.SubCommandSyntax;

public class TeamInviteNameSyntax extends SubCommandSyntax {

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
        return "name of player";
    }

    @Override
    public boolean getRequired() {
        return true;
    }
}
