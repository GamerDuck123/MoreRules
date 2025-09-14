package me.gamerduck.rules.mixins;

import me.gamerduck.rules.common.GameRule;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.boss.enderdragon.EnderDragon;
import net.minecraft.world.entity.boss.wither.WitherBoss;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static me.gamerduck.rules.MixinsVariable.gameRules;

@Mixin(EnderDragon.class)
public abstract class DragonBossMixin {
    @Redirect(method = "checkWalls", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/level/ServerLevel;removeBlock(Lnet/minecraft/core/BlockPos;Z)Z"))
    private boolean injectCanDestroy(ServerLevel instance, BlockPos blockPos, boolean b) {
        return gameRules.gameRuleValueBool(instance, GameRule.DRAGON_GRIEFING) && instance.removeBlock(blockPos, false);
    }
    @Inject(method = "hurtServer", at = @At(value = "HEAD"), cancellable = true)
    private void injectHurtServer(ServerLevel p_376615_, DamageSource p_376766_, float p_376552_, CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(gameRules.gameRuleValueBool(p_376615_, GameRule.DRAGON_DAMAGE));
        cir.cancel();
    }
}
