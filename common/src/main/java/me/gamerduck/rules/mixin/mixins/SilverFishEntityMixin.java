package me.gamerduck.rules.mixin.mixins;

import me.gamerduck.rules.common.GameRule;
import me.gamerduck.rules.mixin.MixinsVariable;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.monster.Silverfish;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Silverfish.SilverfishMergeWithStoneGoal.class)
public abstract class SilverFishEntityMixin extends RandomStrollGoal {
    public SilverFishEntityMixin(PathfinderMob p_25734_, double p_25735_) {
        super(p_25734_, p_25735_);
    }

    @Inject(method = "canUse", at = @At(value = "RETURN"), cancellable = true)
    private void injectCanDestroy(CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(MixinsVariable.gameRules.gameRuleValueBool(mob.level(), GameRule.SILVERFISH_INFEST));
    }
}
