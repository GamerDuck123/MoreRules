package me.gamerduck.rules.mixin.mixins;

import me.gamerduck.rules.common.GameRule;
import me.gamerduck.rules.mixin.behaviors.NoExplosionBehavior;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.monster.Phantom;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Phantom.class)
public abstract class PhantomEntityMixin extends Mob implements Enemy {
    protected PhantomEntityMixin(EntityType<? extends Mob> p_21368_, Level p_21369_) {
        super(p_21368_, p_21369_);
    }

    @Inject(method = "finalizeSpawn", at = @At(value = "HEAD"), cancellable = true)
    private void inject(ServerLevelAccessor p_33126_, DifficultyInstance p_33127_, EntitySpawnReason p_364084_, SpawnGroupData p_33129_, CallbackInfoReturnable<SpawnGroupData> cir) {
        remove(RemovalReason.DISCARDED);
        cir.cancel();
    }
}
