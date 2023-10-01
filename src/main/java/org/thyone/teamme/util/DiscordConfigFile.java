package org.thyone.teamme.util;

import org.thyone.teamme.model.DiscordConfig;

import java.io.IOException;

public class DiscordConfigFile {
    public static ConfigFile<DiscordConfig> storage = new ConfigFile<>("discord");

    public static DiscordConfig getConfig() {
        return storage.data;
    }

    public static void load() throws IOException {
        storage.load(DiscordConfig.class, new DiscordConfig());
    }
}
