package me.gamerduck.rules.bukkit.commands;

import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.brigadier.arguments.DoubleArgumentType;
import com.mojang.brigadier.arguments.FloatArgumentType;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import me.gamerduck.rules.common.GameRule;
import me.lucko.commodore.Commodore;
import org.bukkit.command.PluginCommand;

import java.util.stream.Stream;

public class GameRuleCompletions {
    public static void register(Commodore commodore, PluginCommand command) {
        LiteralArgumentBuilder<Object> gameruleCommand = LiteralArgumentBuilder.literal("gamerule");

        Stream.of(GameRule.values()).forEach(rule -> {
            if (rule.defaultValue() instanceof Double) gameruleCommand.then(LiteralArgumentBuilder.literal(rule.id())
                    .then(LiteralArgumentBuilder.literal("1.0"))
                    .then(LiteralArgumentBuilder.literal("10.0"))
                    .then(LiteralArgumentBuilder.literal("25.0"))
                    .then(LiteralArgumentBuilder.literal("50.0"))
                    .then(LiteralArgumentBuilder.literal("75.0"))
                    .then(LiteralArgumentBuilder.literal("100.0")));
            else if (rule.defaultValue() instanceof Integer) gameruleCommand.then(LiteralArgumentBuilder.literal(rule.id())
                    .then(LiteralArgumentBuilder.literal("1"))
                    .then(LiteralArgumentBuilder.literal("10"))
                    .then(LiteralArgumentBuilder.literal("25"))
                    .then(LiteralArgumentBuilder.literal("50"))
                    .then(LiteralArgumentBuilder.literal("75"))
                    .then(LiteralArgumentBuilder.literal("100")));
            else if (rule.defaultValue() instanceof Float) gameruleCommand.then(LiteralArgumentBuilder.literal(rule.id())
                    .then(LiteralArgumentBuilder.literal("1.0"))
                    .then(LiteralArgumentBuilder.literal("10.0"))
                    .then(LiteralArgumentBuilder.literal("25.0"))
                    .then(LiteralArgumentBuilder.literal("50.0"))
                    .then(LiteralArgumentBuilder.literal("75.0"))
                    .then(LiteralArgumentBuilder.literal("100.0")));
            else gameruleCommand.then(LiteralArgumentBuilder.literal(rule.id())
                    .then(LiteralArgumentBuilder.literal("true"))
                    .then(LiteralArgumentBuilder.literal("false")));
        });

        Stream.of(org.bukkit.GameRule.values()).forEach(rule -> {
            gameruleCommand.then(LiteralArgumentBuilder.literal(rule.getName())
                    .then(LiteralArgumentBuilder.literal("true"))
                    .then(LiteralArgumentBuilder.literal("false")));
        });


        commodore.register(command, gameruleCommand);
    }

}
