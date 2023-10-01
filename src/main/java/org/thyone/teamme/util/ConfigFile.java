package org.thyone.teamme.util;

import com.google.gson.Gson;
import org.bukkit.Bukkit;
import org.thyone.teamme.Protelum;

import java.io.*;
import java.nio.file.Paths;
import java.text.MessageFormat;
import java.util.logging.Level;

public class ConfigFile<T> {
    public T data;
    public File file;

    public ConfigFile(String name) {
        this.file = new File(Paths.get(Protelum.getPlugin().getDataFolder().getAbsolutePath(), "config", MessageFormat.format("{0}.json", name)).toString());
    }

    public void create(T defaultData) throws IOException {
        if (file.getParentFile().mkdir()) {
            Bukkit.getLogger().log(Level.INFO, MessageFormat.format("{0} Folders Created", file.getName()));
        }
        if (file.createNewFile()) {
            Bukkit.getLogger().log(Level.INFO, MessageFormat.format("{0} Created", file.getName()));
        }

        Writer writer = new FileWriter(file);
        new Gson().toJson(defaultData, writer);
        writer.flush();
        writer.close();

        Bukkit.getLogger().log(Level.INFO, MessageFormat.format("{0} Saved", file.getName()));
    }

    public void load(Class<T> DataClass, T defaultData) throws IOException {
        if (!file.exists()) {
            create(defaultData);
        }

        Reader reader = new FileReader(file);

        this.data = new Gson().fromJson(reader, DataClass);

        Bukkit.getLogger().log(Level.INFO, MessageFormat.format("{0} Loaded", file.getName()));
    }
}
