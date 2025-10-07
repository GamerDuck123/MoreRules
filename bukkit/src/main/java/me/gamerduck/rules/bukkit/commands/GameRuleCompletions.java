package me.gamerduck.rules.bukkit.commands;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import me.gamerduck.rules.common.GameRule;
import me.lucko.commodore.Commodore;
import org.bukkit.command.PluginCommand;

import java.util.stream.Stream;

public class GameRuleCompletions {
    public static void register(Commodore commodore, PluginCommand command) {
        LiteralArgumentBuilder<Object> gameruleCommand = LiteralArgumentBuilder.literal("gamerule");

        Stream.of(GameRule.values()).forEach(rule -> {
            gameruleCommand.then(LiteralArgumentBuilder.literal(rule.id())
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
