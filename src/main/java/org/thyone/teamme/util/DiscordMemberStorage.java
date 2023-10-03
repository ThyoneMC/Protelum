package org.thyone.teamme.util;

import org.thyone.teamme.model.DiscordMember;

import java.io.IOException;

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

    public static DiscordMember read(String uuid) {
        return storage.read(uuid);
    }

    public static void update(String uuid, DiscordMember memberData) throws IOException {
        storage.update(uuid, memberData);

        save();
    }

    public static void delete(String uuid) throws IOException {
        storage.delete(uuid);

        save();
    }
}
