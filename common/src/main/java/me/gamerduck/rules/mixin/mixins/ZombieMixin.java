package me.gamerduck.rules.mixin.mixins;

import me.gamerduck.rules.common.GameRule;
import me.gamerduck.rules.mixin.MixinsVariable;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.npc.Villager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(Zombie.class)
public abstract class ZombieMixin {
    @Inject(method = "killedEntity", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/monster/Zombie;convertVillagerToZombieVillager(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/world/entity/npc/Villager;)Z"), cancellable = true, locals = LocalCapture.CAPTURE_FAILSOFT)
    private void inject(ServerLevel p_219160_, LivingEntity p_219161_, DamageSource p_432761_, CallbackInfoReturnable<Boolean> cir, boolean flag, Villager villager) {
        if (!MixinsVariable.gameRules.gameRuleValueBool(p_219160_, GameRule.VILLAGER_ZOMBIE_CONVERSIONS)) cir.setReturnValue(flag);
    }
}
