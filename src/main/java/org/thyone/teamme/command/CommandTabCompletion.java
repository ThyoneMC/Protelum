package org.thyone.teamme.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.thyone.teamme.model.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CommandTabCompletion implements TabCompleter {
    public static class CommandTabCompletionResult {
        public boolean complete;
        public List<String> result;

        public CommandTabCompletionResult(boolean isSearchComplete) {
            this.complete = isSearchComplete;
            this.result = new ArrayList<>();
        }

        public CommandTabCompletionResult(List<String> searchResult) {
            this.complete = true;
            this.result = searchResult;
        }
    }

    public CommandTabCompletionResult handleSyntax(Player sender, String[] args, SubCommandSyntax[] subCommandsSyntax) {
        int index = 1;

        for (SubCommandSyntax subCommandSyntax: subCommandsSyntax) {
            if (args.length > 0 && index == args.length) {
                if (subCommandSyntax instanceof SubCommandCustomSyntax subCommandCustomSyntax) {
                    return new CommandTabCompletionResult(subCommandCustomSyntax.getTabCompletion(sender, ""));
                }
            }

            index = index + 1;
        }

        return new CommandTabCompletionResult(true);
    }

    public CommandTabCompletionResult handleCommand(Player sender, String[] args, SubCommandBase[] subCommandsBase) {
        for (SubCommandBase subCommandBase: subCommandsBase) {
            if (args.length > 0 && args[0].equalsIgnoreCase(subCommandBase.getName())) {
                if (subCommandBase instanceof SubCommand subCommand) {
                    SubCommandSyntax[] subCommandsSyntax = subCommand.getSyntax();
                    if (subCommandsSyntax.length > 0) {
                        return handleSyntax(
                                sender,
                                Arrays.copyOfRange(args, 1, args.length),
                                subCommandsSyntax
                        );
                    }

                    return new CommandTabCompletionResult(true);
                }

                if (subCommandBase instanceof SubCommandGroup subCommandGroup) {
                    CommandTabCompletionResult handled = handleCommand(
                            sender,
                            Arrays.copyOfRange(args, 1, args.length),
                            subCommandGroup.getSubCommand()
                    );

                    if (!handled.complete) {
                        return new CommandTabCompletionResult(
                                Arrays.stream(subCommandGroup.getSubCommand()).map(SubCommandBase::getName).toList()
                        );
                    }

                    return handled;
                }
            }
        }

        return new CommandTabCompletionResult(false);
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player player)) return new ArrayList<>();

        CommandTabCompletionResult handled = handleCommand(
                player,
                args,
                new ProtelumCommand().getSubCommand()
        );

        if (!handled.complete) {
            return Arrays.stream(new ProtelumCommand().getSubCommand()).map(SubCommandBase::getName).toList();
        }

        return handled.result;
    }
}
