package me.gamerduck.rules.mixin.mixins;


import me.gamerduck.rules.common.GameRule;
import me.gamerduck.rules.mixin.MixinsVariable;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.RegistryOps;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ProjectileWeaponItem;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;

@Mixin(BowItem.class)
public abstract class BowItemMixin extends ProjectileWeaponItem {

    public BowItemMixin(Properties p_43009_) {
        super(p_43009_);
    }

    @Redirect(method = "use", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/player/Player;hasInfiniteMaterials()Z"))
    private boolean injectUse(Player instance) {
        return !MixinsVariable.gameRules.gameRuleValueBool(instance.level(), GameRule.INFINITY_NEED_ARROW) && !instance.hasInfiniteMaterials();
    }

    @Redirect(method = "releaseUsing", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/player/Player;getProjectile(Lnet/minecraft/world/item/ItemStack;)Lnet/minecraft/world/item/ItemStack;"))
    private ItemStack injected(Player player, ItemStack bow) {;
        RegistryAccess access = player.registryAccess();
        return !MixinsVariable.gameRules.gameRuleValueBool(player.level(), GameRule.INFINITY_NEED_ARROW)
                && bow.isEnchanted()
                && bow.getEnchantments().getLevel(access.lookupOrThrow(Registries.ENCHANTMENT)
                        .wrapAsHolder(access.lookupOrThrow(Registries.ENCHANTMENT).getValue(Enchantments.INFINITY))) > 0
                ? new ItemStack(Items.ARROW) : player.getProjectile(bow);
    }

}
