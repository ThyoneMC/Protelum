package org.thyone.teamme.model;

import net.kyori.adventure.text.TextComponent;
import org.bukkit.entity.Player;

public abstract class SubCommand extends SubCommandBase {
    public abstract SubCommandSyntax[] getSyntax();
    public abstract TextComponent[] execute(Player player, String[] args);
}

