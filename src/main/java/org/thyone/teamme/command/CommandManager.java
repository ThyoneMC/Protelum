package org.thyone.teamme.command;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.thyone.teamme.command.subcommand.CreateCommand;
import org.thyone.teamme.command.subcommand.DeleteCommand;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CommandManager implements CommandExecutor {

    public ArrayList<SubCommand> subCommands = new ArrayList<>();

    public CommandManager() {
        subCommands.add(new CreateCommand());
        subCommands.add(new DeleteCommand());
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player player) {
            if (args.length > 0) {
                for (SubCommand subCommand: subCommands) {
                    if (args[0].equalsIgnoreCase(subCommand.getName())) {
                        subCommand.execute(player, Arrays.copyOfRange(args, 1, args.length));
                    }
                }
            } else {
                List<String> allSubCommand = subCommands.stream().map(SubCommand::getName).toList();
                TextComponent text =
                        Component
                                .text(MessageFormat.format(
                                        "/protelum [{0}]",
                                        String.join("|", allSubCommand)
                                ))
                                .color(NamedTextColor.RED);
                player.sendMessage(text);
            }

            return true;
        }

        return false;
    }
}
