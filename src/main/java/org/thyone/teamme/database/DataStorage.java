package org.thyone.teamme.database;

import com.google.gson.Gson;
import org.thyone.teamme.Protelum;
import org.thyone.teamme.model.ContentBase;

import java.io.*;
import java.nio.file.Paths;
import java.text.MessageFormat;
import java.util.*;
import java.util.function.Function;
import java.util.logging.Level;

public class DataStorage<T extends ContentBase> {
    public Map<UUID, T> data;
    public File file;

    public DataStorage(String name) {
        this.data = new HashMap<>();
        this.file = new File(Paths.get(Protelum.getPlugin().getDataFolder().getAbsolutePath(), MessageFormat.format("{0}.json", name)).toString());
    }
    public void save() throws IOException {
        if (file.getParentFile().mkdir()) {
            Protelum.getPlugin().getLogger().log(Level.INFO, MessageFormat.format("{0} Folders Created", file.getName()));
        }
        if (file.createNewFile()) {
            Protelum.getPlugin().getLogger().log(Level.INFO, MessageFormat.format("{0} Created", file.getName()));
        }

        Writer writer = new FileWriter(file);
        new Gson().toJson(data.values(), writer);
        writer.flush();
        writer.close();

        Protelum.getPlugin().getLogger().log(Level.INFO, MessageFormat.format("{0} Saved", file.getName()));
    }

    public void load(Class<T[]> DataClass) throws IOException {
        if (file.exists()) {
            Reader reader = new FileReader(file);

            T[] dataInJson = new Gson().fromJson(reader, DataClass);
            for (T content: dataInJson) {
                data.putIfAbsent(content.uuid, content);
            }

            Protelum.getPlugin().getLogger().log(Level.INFO, MessageFormat.format("{0} Loaded", file.getName()));
        }
    }

    public void create(T content) {
        data.put(content.uuid, content);
    }

    public T read(UUID uuid) {
        return data.get(uuid);
    }

    public Collection<T> readAll() {
        return data.values();
    }

    public void update(UUID uuid, T content) {
        data.replace(uuid, content);
    }

    public void update(UUID uuid, Function<T, T> contentUpdate) {
        data.replace(uuid, contentUpdate.apply(read(uuid)));
    }

    public void delete(UUID uuid) {
        data.remove(uuid);
    }
}
