package me.gamerduck.rules.fabric.mixin;

import me.gamerduck.rules.common.GameRule;
import me.gamerduck.rules.fabric.behaviors.NoExplosionBehavior;
import net.minecraft.entity.Entity;
import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(CreeperEntity.class)
public abstract class CreeperEntityMixin {
    @Redirect(method = "explode", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/world/ServerWorld;createExplosion(Lnet/minecraft/entity/Entity;DDDFLnet/minecraft/world/World$ExplosionSourceType;)V"))
    private void inject(ServerWorld instance, Entity entity, double x, double y, double z, float v, World.ExplosionSourceType explosionSourceType) {
        instance.createExplosion(entity, null,
                new NoExplosionBehavior(instance, GameRule.CREEPER_GRIEFING), x, y, z, v, false, explosionSourceType);
    }
}
