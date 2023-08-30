package me.gamerduck.rules.fabric.mixin;

import me.gamerduck.rules.common.GameRule;
import net.minecraft.block.BlockState;
import net.minecraft.block.IceBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import static me.gamerduck.rules.fabric.MoreRulesMod.gameRules;

@Mixin(IceBlock.class)
public abstract class IceBlockMixin {
    private final IceBlock me = ((IceBlock) (Object) this);

    @Redirect(method = "randomTick", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/IceBlock;melt(Lnet/minecraft/block/BlockState;Lnet/minecraft/world/World;Lnet/minecraft/util/math/BlockPos;)V"))
    private void inject(IceBlock instance, BlockState state, World world, BlockPos pos) {
        if (gameRules.gameRuleValueBool(world, GameRule.LIGHT_MELT_ICE)) me.melt(state, world, pos);
    }


}
