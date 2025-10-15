package me.gamerduck.rules.mixin.mixins;

import me.gamerduck.rules.common.GameRule;
import me.gamerduck.rules.mixin.MixinsVariable;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(LiquidBlock.class)
public abstract class FluidMixin {

    @Inject(method = "shouldSpreadLiquid", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/material/FluidState;isSource()Z"), cancellable = true)
    private void injectBlockCobblestone(Level level, BlockPos blockPos, BlockState blockState, CallbackInfoReturnable<Boolean> cir) {
        if (level.getFluidState(blockPos).isSource() && !MixinsVariable.gameRules.gameRuleValueBool(level, GameRule.OBSIDIAN_GENERATE)) {
            cir.setReturnValue(false);
        } else if (!MixinsVariable.gameRules.gameRuleValueBool(level, GameRule.COBBLESTONE_GENERATE)){
            cir.setReturnValue(false);
        }
    }
    @Inject(method = "shouldSpreadLiquid", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/Level;setBlockAndUpdate(Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/BlockState;)Z"), cancellable = true)
    private void injectBlockBasalt(Level level, BlockPos blockPos, BlockState blockState, CallbackInfoReturnable<Boolean> cir) {
        if (!MixinsVariable.gameRules.gameRuleValueBool(level, GameRule.BASALT_GENERATE)) {
            cir.setReturnValue(false);
        }
    }

}
