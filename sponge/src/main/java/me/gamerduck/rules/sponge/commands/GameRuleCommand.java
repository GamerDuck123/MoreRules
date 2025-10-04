package me.gamerduck.rules.sponge.commands;

import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import me.gamerduck.rules.common.GameRule;
import net.kyori.adventure.text.Component;

import java.util.stream.Stream;

public class GameRuleCommand {
//    public static void inject() {
//        final LiteralArgumentBuilder<CommandSourceStack> argumentBuilder =
//                Commands.literal("gamerule").requires((wrapper) -> wrapper.hasPermission(2));
//
//        Stream.of(GameRule.values()).forEach(rule -> {
//            RequiredArgumentBuilder<CommandSourceStack, Boolean> type = Commands.argument("value", BoolArgumentType.bool());
//            argumentBuilder.then(Commands.literal(rule.id()).executes((commandContext) ->
//                    executeQuery(commandContext, rule)
//            ).then(type.executes((commandContext) -> executeSet(commandContext, rule))));
//        });
//
//        MinecraftServer.getServer().getCommands().getDispatcher().register(argumentBuilder);
//    }
//
//    private static int executeSet(CommandContext<CommandSourceStack> commandContext, GameRule rule) {
//        CommandSourceStack source = commandContext.getSource();
//        gameRules.gameRuleValue(source.getBukkitEntity().getWorld(), rule, commandContext.getArgument("value", rule.type()));
//        source.getBukkitEntity().sendMessage(Component.text(String.format("Gamerule %s is now set to: %s", rule.id(), gameRules.gameRuleValue(source.getBukkitEntity().getWorld(), rule))));
//        return 1;
//    }
//
//    private static int executeQuery(CommandContext<CommandSourceStack> commandContext, GameRule rule) {
//        CommandSourceStack source = commandContext.getSource();
//        source.getBukkitEntity().sendMessage(Component.text(String.format("Gamerule %s is currently set to: %s", rule.id(), gameRules.gameRuleValue(source.getBukkitEntity().getWorld(), rule))));
//        return 1;
//    }
//
}
