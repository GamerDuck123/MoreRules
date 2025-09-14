package me.gamerduck.rules.mixins;

import me.gamerduck.rules.common.GameRule;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.FarmBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static me.gamerduck.rules.MixinsVariable.gameRules;

@Mixin(FarmBlock.class)
public abstract class FarmlandBlockMixin {

    @Inject(method = "fallOn", at = @At(value = "HEAD"), cancellable = true)
    private void inject(Level instance, BlockState p_153228_, BlockPos p_153229_, Entity p_153230_, double p_397639_, CallbackInfo ci) {
        if (!instance.isClientSide && !gameRules.gameRuleValueBool(instance, GameRule.CROP_TRAMPLE)) ci.cancel();
    }
}
