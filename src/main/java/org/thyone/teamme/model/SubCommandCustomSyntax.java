package org.thyone.teamme.model;

import org.bukkit.entity.Player;

import java.util.List;

public abstract class SubCommandCustomSyntax extends SubCommandSyntax {
    @Override
    public boolean getRequired() {
        return true;
    }

    public abstract List<String> getTabCompletion(Player player, String arg);
}
