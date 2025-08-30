package me.gamerduck.rules.fabric.mixin;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import me.gamerduck.rules.common.GameRule;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.GameRuleCommand;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.stream.Stream;

import static me.gamerduck.rules.fabric.MoreRulesMod.gameRules;

@Mixin(GameRuleCommand.class)
public class GameRulesCommandMixin {

    @Inject(method = "register", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/GameRules;accept(Lnet/minecraft/world/GameRules$Visitor;)V", shift = At.Shift.AFTER), locals = LocalCapture.CAPTURE_FAILHARD)
    private static void injected(CommandDispatcher<ServerCommandSource> commandDispatcher, CallbackInfo ci, LiteralArgumentBuilder literalArgumentBuilder) {
        Stream.of(GameRule.values()).forEach(rule -> {
            RequiredArgumentBuilder<ServerCommandSource, Boolean> type = CommandManager.argument("value", BoolArgumentType.bool());
            literalArgumentBuilder.then(CommandManager.literal(rule.id()).executes((commandContext) ->
                    executeQuery(commandContext, rule)
            ).then(type.executes((commandContext) -> executeSet(commandContext, rule))));
        });
        commandDispatcher.register(literalArgumentBuilder);
    }

    private static int executeSet(CommandContext<ServerCommandSource> commandContext, GameRule rule) {
        ServerCommandSource source = commandContext.getSource();
        gameRules.gameRuleValue(source.getEntity().getWorld(), rule, commandContext.getArgument("value", rule.type()));
        source.sendMessage(Text.of(String.format("Gamerule %s is now set to: %s", rule.id(), gameRules.gameRuleValue(source.getEntity().getWorld(), rule))));
        return 1;
    }

    private static int executeQuery(CommandContext<ServerCommandSource> commandContext, GameRule rule) {
        ServerCommandSource source = commandContext.getSource();
        source.sendMessage(Text.of(String.format("Gamerule %s is currently set to: %s", rule.id(), gameRules.gameRuleValue(source.getEntity().getWorld(), rule))));

        return 1;
    }

}
