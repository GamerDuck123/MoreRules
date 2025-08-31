package me.gamerduck.rules.neoforged.mixin;

import me.gamerduck.rules.common.GameRule;
import net.minecraft.world.entity.monster.ZombieVillager;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import static me.gamerduck.rules.neoforged.MoreRulesMod.gameRules;

@Mixin(ZombieVillager.class)
public abstract class ZombieVillagerMixin {
    private final ZombieVillager me = ((ZombieVillager) (Object) this);
    @Redirect(method = "mobInteract", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;is(Lnet/minecraft/world/item/Item;)Z"))
    private boolean inject(net.minecraft.world.item.ItemStack instance, net.minecraft.world.item.Item item) {
        return gameRules.gameRuleValueBool(me.level(), GameRule.ZOMBIE_VILLAGER_CONVERSIONS) ? instance.is(Items.GOLDEN_APPLE) : false;
    }
}
