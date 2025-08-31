package me.gamerduck.rules.neoforged.mixin;

import me.gamerduck.rules.common.GameRule;
import net.minecraft.world.entity.monster.EnderMan;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static me.gamerduck.rules.neoforged.MoreRulesMod.gameRules;

@Mixin(EnderMan.EndermanTakeBlockGoal.class)
public abstract class PickupBlockGoalMixin {
    private final EnderMan.EndermanTakeBlockGoal me = ((EnderMan.EndermanTakeBlockGoal) (Object) this);

    @Inject(method = "canUse", at = @At(value = "HEAD"), cancellable = true)
    private void inject(CallbackInfoReturnable<Boolean> cir) {
        if (!gameRules.gameRuleValueBool(me.enderman.level(), GameRule.ENDERMEN_GRIEFING)) cir.setReturnValue(false);
    }

}
