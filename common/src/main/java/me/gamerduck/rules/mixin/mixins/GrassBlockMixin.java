package me.gamerduck.rules.mixin.mixins;


import me.gamerduck.rules.common.GameRule;
import me.gamerduck.rules.mixin.MixinsVariable;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.GrassBlock;
import net.minecraft.world.level.block.HangingMossBlock;
import net.minecraft.world.level.block.SpreadingSnowyDirtBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(SpreadingSnowyDirtBlock.class)
public abstract class GrassBlockMixin {

    @Inject(method = "canPropagate", at = @At(value = "RETURN"), cancellable = true)
    private static void inject(BlockState state, LevelReader p_56825_, BlockPos p_56826_, CallbackInfoReturnable<Boolean> cir) {
        if (!MixinsVariable.gameRules.gameRuleValueBool((Level) p_56825_, GameRule.GRASS_SPREAD)) {
            cir.setReturnValue(false);
            cir.cancel();
        }

    }


}
