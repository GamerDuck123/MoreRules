package me.gamerduck.rules.mixin.mixins;

import me.gamerduck.rules.common.GameRule;
import me.gamerduck.rules.mixin.MixinsVariable;
import net.minecraft.world.entity.monster.EnderMan;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EnderMan.EndermanTakeBlockGoal.class)
public abstract class PickupBlockGoalMixin {

    @Shadow
    public EnderMan enderman;

    @Inject(method = "canUse", at = @At(value = "HEAD"), cancellable = true)
    private void inject(CallbackInfoReturnable<Boolean> cir) {
        if (!MixinsVariable.gameRules.gameRuleValueBool(enderman.level(), GameRule.ENDERMEN_GRIEFING)) cir.setReturnValue(false);
    }

}
