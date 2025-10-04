package me.gamerduck.rules.mixin.mixins;

import me.gamerduck.rules.common.GameRule;
import me.gamerduck.rules.mixin.MixinsVariable;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.CoralBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(CoralBlock.class)
public abstract class CoralBlockMixin {
    @Inject(method = "tick", at = @At(value = "HEAD"), cancellable = true)
    private void inject(BlockState p_221020_, ServerLevel p_221021_, BlockPos p_221022_, RandomSource p_221023_, CallbackInfo ci) {
        if (!MixinsVariable.gameRules.gameRuleValueBool(p_221021_, GameRule.CORAL_DECAY)) ci.cancel();
    }


}
