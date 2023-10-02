package org.thyone.teamme.command;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.thyone.teamme.model.SubCommand;
import org.thyone.teamme.model.SubCommandBase;
import org.thyone.teamme.model.SubCommandGroup;
import org.thyone.teamme.model.SubCommandSyntax;

import java.text.MessageFormat;
import java.util.Arrays;

public class CommandManager implements CommandExecutor {
    public boolean handleCommand(Player player, String[] args, SubCommandBase subCommandBase, int index) {
        if (args.length > index && args[index].equalsIgnoreCase(subCommandBase.getName())) {
            if (subCommandBase instanceof SubCommand subCommand) {
                for (SubCommandSyntax subCommandSyntax: subCommand.getSyntax()) {
                    if (!(args.length > (index + subCommandSyntax.getId())) && subCommandSyntax.getRequired()) {
                        player.sendMessage(
                                Component
                                        .text(MessageFormat.format(
                                                "/protelum {0} {1}",
                                                String.join(
                                                        " ",
                                                        Arrays.copyOfRange(args, 0 ,index + 1)
                                                ),
                                                String.join(
                                                        " ",
                                                        Arrays.stream(subCommand.getSyntax())
                                                                .map(commandSyntax -> new Object[]{ commandSyntax.getName(), commandSyntax.getRequired() })
                                                                .map(syntax -> "<" + syntax[0] + ((boolean) syntax[1] ? "" : "?") + ">").toList()
                                                )
                                        ))
                                        .color(NamedTextColor.RED)
                        );
                        return true;
                    }
                }

                TextComponent[] returnMessages = subCommand.execute(player, Arrays.copyOfRange(args, index + 1, args.length));
                for (TextComponent message: returnMessages) {
                    player.sendMessage(message);
                }
            }

            if (subCommandBase instanceof SubCommandGroup subCommandGroup) {
                for (SubCommandBase subCommandBaseInGroup: subCommandGroup.getSubCommand()) {
                    if (handleCommand(player, args, subCommandBaseInGroup, index + 1)) {
                        return true;
                    }
                }

                player.sendMessage(Component
                        .text(MessageFormat.format(
                                "/protelum {0} [ {1} ]",
                                String.join(
                                        " ",
                                        Arrays.copyOfRange(args, 0 ,index + 1)
                                ),
                                String.join(
                                        " | ",
                                        Arrays.stream(subCommandGroup.getSubCommand()).map(SubCommandBase::getName).toList()
                                )
                        ))
                        .color(NamedTextColor.RED));
            }

            return true;
        }

        return false;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player player) {
            ProtelumCommand thisCommand = new ProtelumCommand();

            for (SubCommandBase subCommandBase: thisCommand.getSubCommand()) {
                if (handleCommand(player, args, subCommandBase, 0)) {
                    return true;
                }
            }

            player.sendMessage(
                    Component
                            .text(MessageFormat.format(
                                    "/protelum [ {0} ]",
                                    String.join(
                                            " | ",
                                            Arrays.stream(thisCommand.getSubCommand()).map(SubCommandBase::getName).toList()
                                    )
                            ))
                            .color(NamedTextColor.RED)
            );
        }

        return false;
    }
}
