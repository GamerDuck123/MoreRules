package me.gamerduck.rules.neoforged.mixin;

import me.gamerduck.rules.common.GameRule;
import net.minecraft.world.entity.ai.goal.BreakDoorGoal;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static me.gamerduck.rules.neoforged.MoreRulesMod.gameRules;


@Mixin(BreakDoorGoal.class)
public abstract class BreakDoorGoalMixin {
    private final BreakDoorGoal me = ((BreakDoorGoal) (Object) this);
    @Inject(method = "canUse", at = @At(value = "HEAD"), cancellable = true)
    private void inject(CallbackInfoReturnable<Boolean> cir) {
        if (!gameRules.gameRuleValueBool(me.mob.level(), GameRule.ZOMBIE_BREAK_DOORS)) cir.cancel();
    }
}
