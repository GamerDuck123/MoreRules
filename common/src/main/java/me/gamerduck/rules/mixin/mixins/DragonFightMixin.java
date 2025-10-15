package me.gamerduck.rules.mixin.mixins;

import me.gamerduck.rules.common.GameRule;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.dimension.end.EndDragonFight;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static me.gamerduck.rules.mixin.MixinsVariable.gameRules;

@Mixin(EndDragonFight.class)
public abstract class DragonFightMixin {

    @Shadow
    @Final
    private ServerLevel level;

    @Shadow
    private boolean previouslyKilled;

    @Redirect(method = "setDragonKilled", at = @At(value = "FIELD", target = "Lnet/minecraft/world/level/dimension/end/EndDragonFight;previouslyKilled:Z", opcode = Opcodes.GETFIELD))
    private boolean inject(EndDragonFight instance) {
        return !previouslyKilled && !gameRules.gameRuleValueBool(level, GameRule.NEW_DRAGON_EGGS);
    }

}
