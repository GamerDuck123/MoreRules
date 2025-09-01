package me.gamerduck.rules.neoforged.mixin;

import me.gamerduck.rules.common.GameRule;
import me.gamerduck.rules.neoforged.behaviors.NoExplosionBehavior;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.ExplosionDamageCalculator;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BedBlock;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(BedBlock.class)
public abstract class BedBlockMixin {
    @Redirect(method = "useWithoutItem", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/Level;explode(Lnet/minecraft/world/entity/Entity;Lnet/minecraft/world/damagesource/DamageSource;Lnet/minecraft/world/level/ExplosionDamageCalculator;Lnet/minecraft/world/phys/Vec3;FZLnet/minecraft/world/level/Level$ExplosionInteraction;)V"))
    private void inject(Level instance, Entity source, DamageSource damageSource, ExplosionDamageCalculator damageCalculator, Vec3 pos, float radius, boolean fire, Level.ExplosionInteraction explosionInteraction) {
        instance.explode(source, null,
                new NoExplosionBehavior(instance, GameRule.BED_GRIEFING, GameRule.BED_DAMAGE), pos, radius, false, explosionInteraction);
    }
}
