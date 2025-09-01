package me.gamerduck.rules.neoforged.mixin;

import me.gamerduck.rules.common.GameRule;
import me.gamerduck.rules.neoforged.behaviors.NoExplosionBehavior;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.projectile.Fireball;
import net.minecraft.world.entity.projectile.LargeFireball;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(LargeFireball.class)
public abstract class FireballEntityMixin {

    @Redirect(method = "onHit", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/Level;explode(Lnet/minecraft/world/entity/Entity;DDDFZLnet/minecraft/world/level/Level$ExplosionInteraction;)V"))
    private void inject(Level instance, Entity source, double x, double y, double z, float radius, boolean fire, Level.ExplosionInteraction explosionInteraction) {
        instance.explode(source, null,
                new NoExplosionBehavior(instance, GameRule.GHAST_GRIEFING, GameRule.GHAST_DAMAGE), x, y, z, radius, false, explosionInteraction);
    }

}
