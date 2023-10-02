package org.thyone.teamme.util;

import com.google.gson.Gson;
import org.bukkit.Bukkit;
import org.thyone.teamme.Protelum;
import org.thyone.teamme.model.ProtelumConfig;

import java.io.*;
import java.nio.file.Paths;
import java.text.MessageFormat;
import java.util.logging.Level;

public class ConfigFile {
    public static ProtelumConfig data;
    public static File file = new File(Paths.get(Protelum.getPlugin().getDataFolder().getAbsolutePath(), "config.json").toString());

    public static void create() throws IOException {
        if (file.getParentFile().mkdir()) {
            Bukkit.getLogger().log(Level.INFO, MessageFormat.format("{0} Folders Created", file.getName()));
        }
        if (file.createNewFile()) {
            Bukkit.getLogger().log(Level.INFO, MessageFormat.format("{0} Created", file.getName()));
        }

        Writer writer = new FileWriter(file);
        new Gson().toJson(new ProtelumConfig(), writer);
        writer.flush();
        writer.close();

        Bukkit.getLogger().log(Level.INFO, MessageFormat.format("{0} Saved", file.getName()));
    }

    public static void load() throws IOException {
        if (!file.exists()) {
            create();
        }

        Reader reader = new FileReader(file);

        data = new Gson().fromJson(reader, ProtelumConfig.class);

        Bukkit.getLogger().log(Level.INFO, MessageFormat.format("{0} Loaded", file.getName()));
    }

    public static ProtelumConfig getConfig() {
        return data;
    }
}
