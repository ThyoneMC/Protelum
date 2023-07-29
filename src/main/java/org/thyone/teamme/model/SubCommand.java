package org.thyone.teamme.model;

import org.bukkit.entity.Player;

public abstract class SubCommand extends SubCommandBase {
    public abstract SubCommandSyntax[] getSyntax();
    public abstract void execute(Player player, String[] args);
}

