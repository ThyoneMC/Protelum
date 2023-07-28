package org.thyone.teamme;

import io.papermc.paper.datapack.Datapack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Collection;
import java.util.logging.Level;


public final class Protelum extends JavaPlugin {

    @Override
    public void onEnable() {
//        Collection<Datapack> Datapacks = getServer().getDatapackManager().getPacks();
//
//        for (Datapack datapack: Datapacks) {
//            if (datapack.isEnabled()) {
//                String name = datapack.getName();
//
//                getLogger().log(Level.INFO, name);
//            }
//        }
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
