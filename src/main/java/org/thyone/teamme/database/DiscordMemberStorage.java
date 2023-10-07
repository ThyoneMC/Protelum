package org.thyone.teamme.database;

import org.thyone.teamme.model.DiscordMember;

import java.io.IOException;
import java.util.UUID;

public class DiscordMemberStorage {
    public static DataStorage<DiscordMember> storage = new DataStorage<>("user");

    // storage

    public static void load() throws IOException {
        storage.load(DiscordMember[].class);
    }

    public static void save() throws IOException {
        storage.save();
    }

    public static DiscordMember create(DiscordMember memberData) throws IOException {
        DiscordMember member = new DiscordMember(memberData);

        storage.create(member);

        save();
        return member;
    }

    public static DiscordMember read(UUID uuid) {
        return storage.read(uuid);
    }

    public static void update(UUID uuid, DiscordMember memberData) throws IOException {
        storage.update(uuid, memberData);

        save();
    }

    public static void delete(UUID uuid) throws IOException {
        storage.delete(uuid);

        save();
    }
}
