package me.gamerduck.rules.mixin.mixins;

import me.gamerduck.rules.common.GameRule;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.dimension.end.EndDragonFight;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.LavaFluid;
import net.minecraft.world.level.material.WaterFluid;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import static me.gamerduck.rules.mixin.MixinsVariable.gameRules;

@Mixin(LavaFluid.class)
public abstract class LavaFlowMixin {

    @Redirect(method = "spreadTo", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/LevelAccessor;setBlock(Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/BlockState;I)Z", opcode = Opcodes.GETFIELD))
    private boolean inject(LevelAccessor instance, BlockPos blockPos, BlockState blockState, int i) {
        if (gameRules.gameRuleValueBool((Level) instance, GameRule.STONE_GENERATE)) {
            return instance.setBlock(blockPos, Blocks.STONE.defaultBlockState(), 3);
        } else {
            return false;
        }
    }

}
