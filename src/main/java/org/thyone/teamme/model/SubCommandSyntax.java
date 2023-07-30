package org.thyone.teamme.model;

import org.jetbrains.annotations.NotNull;

public abstract class SubCommandSyntax {
    public abstract int getId();
    public abstract @NotNull String getName();
    public abstract String getDescription();
    public abstract boolean getRequired();
}
