package me.gamerduck.rules.mixins;

import me.gamerduck.rules.common.GameRule;
import net.minecraft.world.entity.Attackable;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.waypoints.WaypointTransmitter;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static me.gamerduck.rules.MixinsVariable.gameRules;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity implements Attackable, WaypointTransmitter {

    public LivingEntityMixin(EntityType<?> p_19870_, Level p_19871_) {
        super(p_19870_, p_19871_);
    }

    @Inject(method = "onItemPickup", at = @At(value = "HEAD"), cancellable = true)
    private void inject(ItemEntity itemEntity, CallbackInfo ci) {
        if (!(getType().equals(EntityType.PLAYER)) && !gameRules.gameRuleValueBool(level(), GameRule.MOB_PICKUP)) ci.cancel();
    }
}
