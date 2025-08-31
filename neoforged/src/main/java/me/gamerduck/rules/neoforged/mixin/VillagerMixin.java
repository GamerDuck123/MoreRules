package me.gamerduck.rules.neoforged.mixin;

import me.gamerduck.rules.common.GameRule;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.Difficulty;
import net.minecraft.world.entity.npc.Villager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import static me.gamerduck.rules.neoforged.MoreRulesMod.gameRules;

@Mixin(Villager.class)
public abstract class VillagerMixin {
    @Redirect(method = "thunderHit", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/level/ServerLevel;getDifficulty()Lnet/minecraft/world/Difficulty;"))
    private Difficulty inject(ServerLevel instance) {
        return gameRules.gameRuleValueBool(instance, GameRule.VILLAGER_WITCH_CONVERSIONS) ? instance.getDifficulty() : Difficulty.PEACEFUL;
    }
}
