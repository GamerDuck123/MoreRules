package me.gamerduck.rules.fabric.mixin;

import me.gamerduck.rules.common.GameRule;
import net.minecraft.entity.ai.goal.BreakDoorGoal;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static me.gamerduck.rules.fabric.MoreRulesMod.gameRules;

@Mixin(BreakDoorGoal.class)
public abstract class BreakDoorGoalMixin {
    private final BreakDoorGoal me = ((BreakDoorGoal) (Object) this);
    @Inject(method = "canStart", at = @At(value = "HEAD"), cancellable = true)
    private void inject(CallbackInfoReturnable<Boolean> cir) {
        if (!gameRules.gameRuleValueBool(me.mob.getWorld(), GameRule.ZOMBIE_BREAK_DOORS)) cir.cancel();
    }
}
