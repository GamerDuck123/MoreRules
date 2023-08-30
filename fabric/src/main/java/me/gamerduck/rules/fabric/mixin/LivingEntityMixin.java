package me.gamerduck.rules.fabric.mixin;

import me.gamerduck.rules.common.GameRule;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.ZombieVillagerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Hand;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static me.gamerduck.rules.fabric.MoreRulesMod.gameRules;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {
    private final LivingEntity me = ((LivingEntity) (Object) this);
    @Inject(method = "setStackInHand", at = @At(value = "HEAD"), cancellable = true)
    private void inject(Hand hand, ItemStack itemStack, CallbackInfo ci) {
        if (!gameRules.gameRuleValueBool(me.getWorld(), GameRule.ZOMBIE_VILLAGER_CONVERSIONS)) ci.cancel();
    }
}
