package me.gamerduck.rules.mixins;

import me.gamerduck.rules.common.GameRule;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.monster.EnderMan;
import net.minecraft.world.entity.monster.Silverfish;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static me.gamerduck.rules.MixinsVariable.gameRules;

@Mixin(Silverfish.SilverfishMergeWithStoneGoal.class)
public abstract class SilverFishEntityMixin extends RandomStrollGoal {
    public SilverFishEntityMixin(PathfinderMob p_25734_, double p_25735_) {
        super(p_25734_, p_25735_);
    }

    @Inject(method = "canUse", at = @At(value = "RETURN"), cancellable = true)
    private void injectCanDestroy(CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(gameRules.gameRuleValueBool(mob.level(), GameRule.SILVERFISH_INFEST));
    }
}
