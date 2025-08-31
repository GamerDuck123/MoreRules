package me.gamerduck.rules.neoforged.mixin;

import me.gamerduck.rules.common.GameRule;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static me.gamerduck.rules.neoforged.MoreRulesMod.gameRules;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {
    private final LivingEntity me = ((LivingEntity) (Object) this);
    @Inject(method = "onItemPickup", at = @At(value = "HEAD"), cancellable = true)
    private void inject(ItemEntity itemEntity, CallbackInfo ci) {
        if (!(me instanceof Player) && !gameRules.gameRuleValueBool(me.level(), GameRule.MOB_PICKUP)) ci.cancel();
    }
}
