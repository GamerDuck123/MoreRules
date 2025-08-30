package me.gamerduck.rules.fabric.mixin;

import me.gamerduck.rules.common.GameRule;
import net.minecraft.block.FarmlandBlock;
import net.minecraft.world.World;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import static me.gamerduck.rules.fabric.MoreRulesMod.gameRules;

@Mixin(FarmlandBlock.class)
public abstract class FarmlandBlockMixin {

    @Redirect(method = "onLandedUpon", at = @At(value = "FIELD", target = "Lnet/minecraft/world/World;isClient:Z", opcode = Opcodes.GETFIELD))
    private boolean inject(World instance) {

        return !instance.isClient && !gameRules.gameRuleValueBool(instance, GameRule.CROP_TRAMPLE);
    }
}
