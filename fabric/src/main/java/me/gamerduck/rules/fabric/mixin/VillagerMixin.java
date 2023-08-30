package me.gamerduck.rules.fabric.mixin;

import me.gamerduck.rules.common.GameRule;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.Difficulty;
import net.minecraft.world.LightType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import static me.gamerduck.rules.fabric.MoreRulesMod.gameRules;

@Mixin(VillagerEntity.class)
public abstract class VillagerMixin {
    @Redirect(method = "onStruckByLightning", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/world/ServerWorld;getDifficulty()Lnet/minecraft/world/Difficulty;"))
    private Difficulty inject(ServerWorld instance) {
        return gameRules.gameRuleValueBool(instance, GameRule.VILLAGER_WITCH_CONVERSIONS) ? instance.getDifficulty() : Difficulty.PEACEFUL;
    }
}
