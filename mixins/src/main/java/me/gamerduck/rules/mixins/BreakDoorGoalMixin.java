package me.gamerduck.rules.mixins;

import me.gamerduck.rules.common.GameRule;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.BreakDoorGoal;
import net.minecraft.world.entity.ai.goal.DoorInteractGoal;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static me.gamerduck.rules.MixinsVariable.gameRules;


@Mixin(BreakDoorGoal.class)
public abstract class BreakDoorGoalMixin extends DoorInteractGoal {
    public BreakDoorGoalMixin(Mob p_25193_) {
        super(p_25193_);
    }

    @Inject(method = "canUse", at = @At(value = "HEAD"), cancellable = true)
    private void inject(CallbackInfoReturnable<Boolean> cir) {
        if (!gameRules.gameRuleValueBool(mob.level(), GameRule.ZOMBIE_BREAK_DOORS)) cir.cancel();
    }
}
