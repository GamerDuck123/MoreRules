package me.gamerduck.rules.mixins;

import me.gamerduck.rules.common.GameRule;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.monster.Slime;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import static me.gamerduck.rules.MixinsVariable.gameRules;

@Mixin(Slime.class)
public abstract class SlimeEntityMixin {
    @Redirect(method = "remove", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/monster/Slime;isDeadOrDying()Z"))
    private boolean injectCanDestroy(Slime instance) {
        return instance.isDeadOrDying() && gameRules.gameRuleValueBool(instance.level(), GameRule.SLIMES_SPLIT);
    }
}
