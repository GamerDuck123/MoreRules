package me.gamerduck.rules.mixins;

import me.gamerduck.rules.common.GameRule;
import me.gamerduck.rules.behaviors.NoExplosionBehavior;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(Creeper.class)
public abstract class CreeperEntityMixin {
    @Redirect(method = "explodeCreeper", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/level/ServerLevel;explode(Lnet/minecraft/world/entity/Entity;DDDFLnet/minecraft/world/level/Level$ExplosionInteraction;)V"))
    private void inject(ServerLevel instance, Entity entity, double x, double y, double z, float v, Level.ExplosionInteraction explosionInteraction) {
        instance.explode(entity, null,
                new NoExplosionBehavior(instance, GameRule.CREEPER_GRIEFING, GameRule.CREEPER_DAMAGE), x, y, z, v, false, explosionInteraction);
    }
}
