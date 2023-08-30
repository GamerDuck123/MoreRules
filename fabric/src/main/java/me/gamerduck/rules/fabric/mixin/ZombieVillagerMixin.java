package me.gamerduck.rules.fabric.mixin;

import me.gamerduck.rules.common.GameRule;
import net.minecraft.block.IceBlock;
import net.minecraft.entity.mob.ZombieVillagerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.world.Difficulty;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import static me.gamerduck.rules.fabric.MoreRulesMod.gameRules;

@Mixin(ZombieVillagerEntity.class)
public abstract class ZombieVillagerMixin {
    private final ZombieVillagerEntity me = ((ZombieVillagerEntity) (Object) this);
    @Redirect(method = "interactMob", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;isOf(Lnet/minecraft/item/Item;)Z"))
    private boolean inject(ItemStack instance, Item item) {
        return gameRules.gameRuleValueBool(me.getWorld(), GameRule.ZOMBIE_VILLAGER_CONVERSIONS) ? instance.isOf(Items.GOLDEN_APPLE) : false;
    }
}
