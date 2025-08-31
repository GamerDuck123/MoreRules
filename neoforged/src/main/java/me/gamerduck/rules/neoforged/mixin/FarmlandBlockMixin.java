package me.gamerduck.rules.neoforged.mixin;

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

import static me.gamerduck.rules.neoforged.MoreRulesMod.gameRules;

@Mixin(FarmBlock.class)
public abstract class FarmlandBlockMixin {

//    @Redirect(method = "onLandedUpon", at = @At(value = "FIELD", target = "Lnet/minecraft/world/World;isClient:Z", opcode = Opcodes.GETFIELD))
//    private boolean inject(World instance) {
//
//        return !instance.isClient && !gameRules.gameRuleValueBool(instance, GameRule.CROP_TRAMPLE);
//    }


    @Inject(method = "fallOn", at = @At(value = "HEAD"), cancellable = true)
    private void inject(Level instance, BlockState p_153228_, BlockPos p_153229_, Entity p_153230_, double p_397639_, CallbackInfo ci) {
        if (!instance.isClientSide && !gameRules.gameRuleValueBool(instance, GameRule.CROP_TRAMPLE)) ci.cancel();
    }
}
