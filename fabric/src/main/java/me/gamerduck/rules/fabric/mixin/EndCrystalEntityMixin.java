package me.gamerduck.rules.fabric.mixin;

import me.gamerduck.rules.common.GameRule;
import me.gamerduck.rules.fabric.behaviors.NoExplosionBehavior;
import net.minecraft.entity.Entity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.decoration.EndCrystalEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;
import net.minecraft.world.explosion.ExplosionBehavior;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(EndCrystalEntity.class)
public abstract class EndCrystalEntityMixin {
    @Redirect(method = "damage", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/world/ServerWorld;createExplosion(Lnet/minecraft/entity/Entity;Lnet/minecraft/entity/damage/DamageSource;Lnet/minecraft/world/explosion/ExplosionBehavior;DDDFZLnet/minecraft/world/World$ExplosionSourceType;)V"))
    private void inject(ServerWorld instance, Entity entity, DamageSource damageSource, ExplosionBehavior explosionBehavior, double x, double y, double z, float v, boolean b, World.ExplosionSourceType explosionSourceType) {
        instance.createExplosion(entity, null,
                new NoExplosionBehavior(instance, GameRule.CRYSTAL_EXPLOSION), x, y, z, v, b, explosionSourceType);
    }
}
