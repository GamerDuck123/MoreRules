package me.gamerduck.rules.neoforged.mixin;

import me.gamerduck.rules.common.GameRule;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.level.block.SnowLayerBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import static me.gamerduck.rules.neoforged.MoreRulesMod.gameRules;

@Mixin(SnowLayerBlock.class)
public abstract class SnowBlockMixin {

    @Redirect(method = "randomTick", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/level/ServerLevel;getBrightness(Lnet/minecraft/world/level/LightLayer;Lnet/minecraft/core/BlockPos;)I"))
    private int inject(ServerLevel instance, LightLayer lightLayer, BlockPos blockPos) {
        return gameRules.gameRuleValueBool(instance, GameRule.LIGHT_MELT_SNOW) ? instance.getBrightness(LightLayer.BLOCK, blockPos) : 0;
    }


}
