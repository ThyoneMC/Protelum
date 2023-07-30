package org.thyone.teamme.model;

import org.jetbrains.annotations.NotNull;

public abstract class SubCommandBase {
    public abstract @NotNull String getName();
    public abstract String getDescription();
}
