package me.gamerduck.rules.mixin.mixins;

import me.gamerduck.rules.common.GameRule;
import me.gamerduck.rules.mixin.MixinsVariable;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.boss.enderdragon.EnderDragon;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EnderDragon.class)
public abstract class DragonBossMixin {
    @Redirect(method = "checkWalls", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/level/ServerLevel;removeBlock(Lnet/minecraft/core/BlockPos;Z)Z"))
    private boolean injectCanDestroy(ServerLevel instance, BlockPos blockPos, boolean b) {
        return MixinsVariable.gameRules.gameRuleValueBool(instance, GameRule.DRAGON_GRIEFING) && instance.removeBlock(blockPos, false);
    }
    @Inject(method = "canAttack", at = @At(value = "HEAD"), cancellable = true)
    private void injectHurtServer(LivingEntity ent, CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(MixinsVariable.gameRules.gameRuleValueBool(ent.level(), GameRule.DRAGON_DAMAGE));
        cir.cancel();
    }
}
