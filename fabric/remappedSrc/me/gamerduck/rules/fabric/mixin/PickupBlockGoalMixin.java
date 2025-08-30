package me.gamerduck.rules.fabric.mixin;

import me.gamerduck.rules.common.GameRule;
import net.minecraft.entity.mob.EndermanEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static me.gamerduck.rules.fabric.MoreRulesMod.gameRules;

@Mixin(EndermanEntity.PickUpBlockGoal.class)
public abstract class PickupBlockGoalMixin {
    private final EndermanEntity.PickUpBlockGoal me = ((EndermanEntity.PickUpBlockGoal) (Object) this);

    @Inject(method = "canStart", at = @At(value = "HEAD"), cancellable = true)
    private void inject(CallbackInfoReturnable<Boolean> cir) {
        if (!gameRules.gameRuleValueBool(me.enderman.getWorld(), GameRule.ENDERMEN_GRIEFING)) cir.setReturnValue(false);
    }

}
