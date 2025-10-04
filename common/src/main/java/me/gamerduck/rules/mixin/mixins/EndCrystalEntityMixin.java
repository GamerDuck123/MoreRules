package me.gamerduck.rules.mixin.mixins;

import me.gamerduck.rules.common.GameRule;
import me.gamerduck.rules.mixin.behaviors.NoExplosionBehavior;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.boss.enderdragon.EndCrystal;
import net.minecraft.world.level.ExplosionDamageCalculator;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(EndCrystal.class)
public abstract class EndCrystalEntityMixin {
    @Redirect(method = "hurtServer", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/level/ServerLevel;explode(Lnet/minecraft/world/entity/Entity;Lnet/minecraft/world/damagesource/DamageSource;Lnet/minecraft/world/level/ExplosionDamageCalculator;DDDFZLnet/minecraft/world/level/Level$ExplosionInteraction;)V"))
    private void inject(ServerLevel instance, net.minecraft.world.entity.Entity entity, net.minecraft.world.damagesource.DamageSource damageSource, ExplosionDamageCalculator explosionDamageCalculator, double x, double y, double z, float v, boolean b, Level.ExplosionInteraction explosionInteraction) {
        instance.explode(entity, null,
                new NoExplosionBehavior(instance, GameRule.CRYSTAL_GRIEFING, GameRule.CRYSTAL_DAMAGE), x, y, z, v, false, explosionInteraction);
    }
}
