package me.gamerduck.rules.neoforged.mixin;

import me.gamerduck.rules.common.GameRule;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.boss.enderdragon.EndCrystal;
import net.minecraft.world.level.ExplosionDamageCalculator;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import static com.ibm.icu.impl.ValidIdentifiers.Datatype.x;

@Mixin(EndCrystal.class)
public abstract class EndCrystalEntityMixin {
    @Redirect(method = "hurtServer", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/level/ServerLevel;explode(Lnet/minecraft/world/entity/Entity;Lnet/minecraft/world/damagesource/DamageSource;Lnet/minecraft/world/level/ExplosionDamageCalculator;DDDFZLnet/minecraft/world/level/Level$ExplosionInteraction;)V"))
    private void inject(ServerLevel instance, net.minecraft.world.entity.Entity entity, net.minecraft.world.damagesource.DamageSource damageSource, ExplosionDamageCalculator explosionDamageCalculator, double x, double y, double z, float v, boolean b, Level.ExplosionInteraction explosionInteraction) {
        instance.explode(entity, null,
                new me.gamerduck.rules.neoforged.behaviors.NoExplosionBehavior(instance, GameRule.TNT_EXPLOSION), x, y, z, v, false, explosionInteraction);
    }
}
