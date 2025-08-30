package me.gamerduck.rules.fabric.mixin;

import me.gamerduck.rules.common.GameRule;
import net.minecraft.entity.player.PlayerAbilities;
import net.minecraft.screen.AnvilScreenHandler;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import static me.gamerduck.rules.fabric.MoreRulesMod.gameRules;

@Mixin(AnvilScreenHandler.class)
public abstract class AnvilInventoryMixin {
    private final AnvilScreenHandler me = ((AnvilScreenHandler) (Object) this);

    @Redirect(method = "updateResult", at = @At(value = "FIELD", target = "Lnet/minecraft/entity/player/PlayerAbilities;creativeMode:Z", opcode = Opcodes.GETFIELD))
    private boolean inject(PlayerAbilities instance) {
        return !instance.creativeMode && gameRules.gameRuleValueBool(me.player.getWorld(), GameRule.ANVIL_COST_TOO_MUCH);
    }

}
