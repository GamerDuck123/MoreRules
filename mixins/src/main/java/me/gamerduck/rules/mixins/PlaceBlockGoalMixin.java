package me.gamerduck.rules.mixins;

import me.gamerduck.rules.common.GameRule;
import net.minecraft.world.entity.monster.EnderMan;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static me.gamerduck.rules.MixinsVariable.gameRules;

@Mixin(EnderMan.EndermanLeaveBlockGoal.class)
public abstract class PlaceBlockGoalMixin {
    private final EnderMan.EndermanLeaveBlockGoal me = ((EnderMan.EndermanLeaveBlockGoal) (Object) this);

    @Inject(method = "canUse", at = @At(value = "HEAD"), cancellable = true)
    private void inject(CallbackInfoReturnable<Boolean> cir) {
        if (!gameRules.gameRuleValueBool(me.enderman.level(), GameRule.ENDERMEN_GRIEFING)) cir.setReturnValue(false);
    }

}
