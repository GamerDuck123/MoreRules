package me.gamerduck.rules.fabric.mixin;

import me.gamerduck.rules.common.GameRule;
import net.minecraft.entity.passive.PigEntity;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.Difficulty;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import static me.gamerduck.rules.fabric.MoreRulesMod.gameRules;

@Mixin(PigEntity.class)
public abstract class PigMixin {
    @Redirect(method = "onStruckByLightning", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/world/ServerWorld;getDifficulty()Lnet/minecraft/world/Difficulty;"))
    private Difficulty inject(ServerWorld instance) {
        return gameRules.gameRuleValueBool(instance, GameRule.PIG_PIGLIN_CONVERSIONS) ? instance.getDifficulty() : Difficulty.PEACEFUL;
    }
}
