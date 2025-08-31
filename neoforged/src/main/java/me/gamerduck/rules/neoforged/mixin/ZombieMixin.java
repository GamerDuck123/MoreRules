package me.gamerduck.rules.neoforged.mixin;

import me.gamerduck.rules.common.GameRule;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Zombie;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static me.gamerduck.rules.neoforged.MoreRulesMod.gameRules;

@Mixin(Zombie.class)
public abstract class ZombieMixin {
    @Inject(method = "killedEntity", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/monster/Monster;killedEntity(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/world/entity/LivingEntity;)Z"), cancellable = true)
    private void inject(ServerLevel p_219160_, LivingEntity p_219161_, CallbackInfoReturnable<Boolean> cir) {
        if (!gameRules.gameRuleValueBool(p_219160_, GameRule.VILLAGER_ZOMBIE_CONVERSIONS)) cir.cancel();
    }
}
