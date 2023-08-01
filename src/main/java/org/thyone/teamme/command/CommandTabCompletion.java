package org.thyone.teamme.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.thyone.teamme.model.SubCommand;
import org.thyone.teamme.model.SubCommandBase;
import org.thyone.teamme.model.SubCommandGroup;

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

    public CommandTabCompletionResult handleCommand(String[] args, SubCommandBase[] subCommandsBase) {
        for (SubCommandBase subCommandBase: subCommandsBase) {
            if (args.length > 0 && args[0].equalsIgnoreCase(subCommandBase.getName())) {
                if (subCommandBase instanceof SubCommand) {
                    return new CommandTabCompletionResult(true);
                }

                if (subCommandBase instanceof SubCommandGroup subCommandGroup) {
                    CommandTabCompletionResult handled = handleCommand(
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
        CommandTabCompletionResult handled = handleCommand(
                args,
                new ProtelumCommand().getSubCommand()
        );

        if (!handled.complete) {
            return Arrays.stream(new ProtelumCommand().getSubCommand()).map(SubCommandBase::getName).toList();
        }

        return handled.result;
    }
}
