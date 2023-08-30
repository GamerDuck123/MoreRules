package me.gamerduck.rules.fabric.mixin;

import me.gamerduck.rules.common.GameRule;
import net.minecraft.block.*;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static me.gamerduck.rules.fabric.MoreRulesMod.gameRules;

@Mixin(CoralBlockBlock.class)
public abstract class CoralBlockMixin {
    @Inject(method = "scheduledTick", at = @At(value = "HEAD"), cancellable = true)
    private static void inject(BlockState blockState, ServerWorld serverWorld, BlockPos blockPos, Random random, CallbackInfo ci) {
        if (gameRules.gameRuleValueBool(serverWorld, GameRule.CORAL_DECAY)) ci.cancel();
    }


}
