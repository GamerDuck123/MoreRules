package me.gamerduck.rules.neoforged.mixin;

import net.minecraft.client.gui.screens.inventory.AnvilScreen;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(AnvilScreen.class)
public abstract class AnvilInventoryMixin {
//    private final AnvilScreenHandler me = ((AnvilScreenHandler) (Object) this);
//
//    @Redirect(method = "updateResult", at = @At(value = "FIELD", target = "Lnet/minecraft/component/DataComponentTypes;REPAIR_COST:Lnet/minecraft/component/ComponentType;", opcode = Opcodes.GETFIELD))
//    private boolean inject(PlayerAbilities instance) {
//        return !instance.creativeMode && gameRules.gameRuleValueBool(me.player.getWorld(), GameRule.ANVIL_COST_TOO_MUCH);
//    }

}
