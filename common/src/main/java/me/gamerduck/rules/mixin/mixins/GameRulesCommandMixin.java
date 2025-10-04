package me.gamerduck.rules.mixin.mixins;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import me.gamerduck.rules.common.GameRule;
import me.gamerduck.rules.mixin.MixinsVariable;
import net.minecraft.commands.CommandBuildContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.server.commands.GameRuleCommand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.stream.Stream;

@Mixin(GameRuleCommand.class)
public class GameRulesCommandMixin {

    @Inject(method = "register", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/GameRules;visitGameRuleTypes(Lnet/minecraft/world/level/GameRules$GameRuleTypeVisitor;)V", shift = At.Shift.AFTER), locals = LocalCapture.CAPTURE_FAILHARD)
    private static void injected(CommandDispatcher<CommandSourceStack> dispatcher, CommandBuildContext commandBuildContext, CallbackInfo ci, LiteralArgumentBuilder<CommandSourceStack> literalArgumentBuilder) {
        Stream.of(GameRule.values()).forEach(rule -> {
            RequiredArgumentBuilder<CommandSourceStack, Boolean> type = Commands.argument("value", BoolArgumentType.bool());
            literalArgumentBuilder.then(Commands.literal(rule.id()).executes((commandContext) ->
                    executeQuery(commandContext, rule)
            ).then(type.executes((commandContext) -> executeSet(commandContext, rule))));
        });

        dispatcher.register(literalArgumentBuilder);
    }

    private static int executeSet(CommandContext<CommandSourceStack> commandContext, GameRule rule) {
        CommandSourceStack source = commandContext.getSource();
        MixinsVariable.gameRules.gameRuleValue(source.getEntity().level(), rule, commandContext.getArgument("value", rule.type()));
        source.sendSystemMessage(Component.literal(String.format("Gamerule %s is now set to: %s", rule.id(), MixinsVariable.gameRules.gameRuleValue(source.getEntity().level(), rule))));
        return 1;
    }

    private static int executeQuery(CommandContext<CommandSourceStack> commandContext, GameRule rule) {
        CommandSourceStack source = commandContext.getSource();
        source.sendSystemMessage(Component.literal(String.format("Gamerule %s is currently set to: %s", rule.id(), MixinsVariable.gameRules.gameRuleValue(source.getEntity().level(), rule))));

        return 1;
    }


}
