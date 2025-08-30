package me.gamerduck.rules.fabric.mixin;

import me.gamerduck.rules.common.GameRule;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.item.Items;
import net.minecraft.server.world.ServerWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static me.gamerduck.rules.fabric.MoreRulesMod.gameRules;

@Mixin(ZombieEntity.class)
public abstract class ZombieMixin {
    @Inject(method = "onKilledOther", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/mob/HostileEntity;onKilledOther(Lnet/minecraft/server/world/ServerWorld;Lnet/minecraft/entity/LivingEntity;)Z"), cancellable = true)
    private void inject(ServerWorld serverWorld, LivingEntity livingEntity, CallbackInfoReturnable<Boolean> cir) {
        if (!gameRules.gameRuleValueBool(serverWorld, GameRule.VILLAGER_ZOMBIE_CONVERSIONS)) cir.cancel();
    }
}
