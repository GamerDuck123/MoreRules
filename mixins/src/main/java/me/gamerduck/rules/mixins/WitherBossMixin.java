package me.gamerduck.rules.mixins;

import me.gamerduck.rules.common.GameRule;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.boss.wither.WitherBoss;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.RangedAttackMob;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static me.gamerduck.rules.MixinsVariable.gameRules;

@Mixin(WitherBoss.class)
public abstract class WitherBossMixin extends Monster implements RangedAttackMob {

    protected WitherBossMixin(EntityType<? extends Monster> p_33002_, Level p_33003_) {
        super(p_33002_, p_33003_);
    }

    @Redirect(method = "customServerAiStep", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/boss/wither/WitherBoss;canDestroy(Lnet/minecraft/world/level/block/state/BlockState;)Z"))
    private boolean injectCanDestroy(BlockState state) {
        return WitherBoss.canDestroy(state) && gameRules.gameRuleValueBool(level(), GameRule.WITHER_GRIEFING);
    }
    @Inject(method = "hurtServer", at = @At(value = "HEAD"), cancellable = true)
    private void injectHurtServer(ServerLevel p_376615_, DamageSource p_376766_, float p_376552_, CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(gameRules.gameRuleValueBool(p_376615_, GameRule.WITHER_DAMAGE));
        cir.cancel();
    }
}
