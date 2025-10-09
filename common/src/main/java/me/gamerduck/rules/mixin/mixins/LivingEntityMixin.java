package me.gamerduck.rules.mixin.mixins;

import me.gamerduck.rules.common.GameRule;
import me.gamerduck.rules.mixin.MixinsVariable;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.waypoints.WaypointTransmitter;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity implements Attackable, WaypointTransmitter {

    public LivingEntityMixin(EntityType<?> p_19870_, Level p_19871_) {
        super(p_19870_, p_19871_);
    }

    @Inject(method = "onItemPickup", at = @At(value = "HEAD"), cancellable = true)
    private void inject(ItemEntity itemEntity, CallbackInfo ci) {
        if (!(getType().equals(EntityType.PLAYER)) && !MixinsVariable.gameRules.gameRuleValueBool(level(), GameRule.MOB_PICKUP)) ci.cancel();
    }
    @Inject(method = "hurtServer", at = @At(value = "HEAD"), cancellable = true)
    private void injectHurtServer(ServerLevel level, DamageSource source, float p_376610_, CallbackInfoReturnable<Boolean> cir) {
        if (getType().equals(EntityType.PLAYER)) {
            if ((source.is(DamageTypes.ON_FIRE) || source.is(DamageTypes.IN_FIRE))
                && !MixinsVariable.gameRules.gameRuleValueBool(level, GameRule.FIRE_DAMAGE)) {
                cir.setReturnValue(false);
                cir.cancel();
            }
            if (source.is(DamageTypes.ENDER_PEARL)
                && !MixinsVariable.gameRules.gameRuleValueBool(level, GameRule.ENDER_PEARL_DAMAGE)) {
                cir.setReturnValue(false);
                cir.cancel();
            }
            if (source.is(DamageTypes.FALL)
                && !MixinsVariable.gameRules.gameRuleValueBool(level, GameRule.PLAYER_FALL_DAMAGE)) {
                cir.setReturnValue(false);
                cir.cancel();
            }
            if (source.is(DamageTypes.DROWN)
                && !MixinsVariable.gameRules.gameRuleValueBool(level, GameRule.PLAYER_DROWN)) {
                cir.setReturnValue(false);
                cir.cancel();
            }
        } else if ((LivingEntity)(Object)this instanceof TamableAnimal tamed
                    && !MixinsVariable.gameRules.gameRuleValueBool(level, GameRule.PET_FRIENDLY_FIRE)
                    && tamed.isTame()
                    && tamed.getOwner() != null
                    && source.getEntity() != null
                    && tamed.getOwner().getUUID().equals(source.getEntity().getUUID())) {
            cir.setReturnValue(false);
            cir.cancel();
        }
    }


}
