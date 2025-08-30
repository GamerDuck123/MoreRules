package me.gamerduck.rules.fabric.mixin;

import me.gamerduck.rules.common.GameRule;
import me.gamerduck.rules.fabric.behaviors.NoExplosionBehavior;
import net.minecraft.entity.Entity;
import net.minecraft.entity.TntEntity;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(TntEntity.class)
public abstract class TntEntityMixin {

    @Redirect(method = "explode", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World;createExplosion(Lnet/minecraft/entity/Entity;DDDFLnet/minecraft/world/World$ExplosionSourceType;)Lnet/minecraft/world/explosion/Explosion;"))
    private Explosion injected(World world, Entity entity, double x, double y, double z, float power, World.ExplosionSourceType explosionSourceType) {
        return world.createExplosion(entity, null,
                new NoExplosionBehavior(world, GameRule.TNT_EXPLOSION), x, y, z, power, false, explosionSourceType);
    }

}
