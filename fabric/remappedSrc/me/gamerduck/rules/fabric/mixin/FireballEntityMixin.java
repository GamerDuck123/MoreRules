package me.gamerduck.rules.fabric.mixin;

import me.gamerduck.rules.common.GameRule;
import me.gamerduck.rules.fabric.behaviors.NoExplosionBehavior;
import net.minecraft.entity.Entity;
import net.minecraft.entity.projectile.FireballEntity;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(FireballEntity.class)
public abstract class FireballEntityMixin {

    @Redirect(method = "onCollision", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World;createExplosion(Lnet/minecraft/entity/Entity;DDDFZLnet/minecraft/world/World$ExplosionSourceType;)Lnet/minecraft/world/explosion/Explosion;"))
    private Explosion inject(World world, Entity entity, double x, double y, double z, float power, boolean createFire, World.ExplosionSourceType explosionSourceType) {
        return world.createExplosion(entity, null,
                new NoExplosionBehavior(world, GameRule.GHAST_GRIEFING), x, y, z, power, createFire, explosionSourceType);
    }

}
