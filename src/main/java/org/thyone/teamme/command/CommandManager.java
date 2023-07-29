package org.thyone.teamme.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.thyone.teamme.command.discord.DiscordCommand;
import org.thyone.teamme.command.team.TeamCommand;
import org.thyone.teamme.model.SubCommand;
import org.thyone.teamme.model.SubCommandBase;
import org.thyone.teamme.model.SubCommandGroup;

import java.util.ArrayList;
import java.util.Arrays;

public class CommandManager implements CommandExecutor {

    public ArrayList<SubCommandBase> subCommands = new ArrayList<>();

    public CommandManager() {
        subCommands.add(new TeamCommand());
        subCommands.add(new DiscordCommand());
    }

    public boolean handleCommand(Player player, String[] args, SubCommandBase subCommandBase, int index) {
        System.out.println("handle:" + subCommandBase.getName());
        if (args.length > index && args[index].equalsIgnoreCase(subCommandBase.getName())) {
            System.out.println("in handle:" + subCommandBase.getName());
            if (subCommandBase instanceof SubCommand subCommand) {
                System.out.println("execute:" + subCommand.getName());
                subCommand.execute(player, Arrays.copyOfRange(args, index + 1, args.length));
                return true;
            } else if (subCommandBase instanceof SubCommandGroup subCommandGroup) {
                for (SubCommandBase subCommandBaseInGroup : subCommandGroup.getSubCommand()) {
                    if (handleCommand(player, args, subCommandBaseInGroup, index + 1)) {
                        return true;
                    }
                }
            }

            System.out.println("show in handle:" + subCommandBase.getName());
            return true;
        }

        return false;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player player) {
            for (SubCommandBase subCommandBase: subCommands) {
                if (handleCommand(player, args, subCommandBase, 0)) {
                    return true;
                }
            }
        }

        System.out.println("show all");
        return false;
    }
}
