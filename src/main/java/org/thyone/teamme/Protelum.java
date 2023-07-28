package org.thyone.teamme;

import org.bukkit.plugin.java.JavaPlugin;

public final class Protelum extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        System.out.println("[Protelum] Enabled");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        System.out.println("[Protelum] Disable");
    }
}
