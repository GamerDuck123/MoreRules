package me.gamerduck.rules.neoforged.mixin;

import me.gamerduck.rules.common.GameRule;
import net.minecraft.core.Holder;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.block.entity.SculkCatalystBlockEntity;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static me.gamerduck.rules.neoforged.MoreRulesMod.gameRules;

@Mixin(SculkCatalystBlockEntity.CatalystListener.class)
public abstract class SculkCatalystMixin {

    @Inject(method = "handleGameEvent", at = @At(value = "HEAD"), cancellable = true)
    private void inject(ServerLevel p_283470_, Holder<GameEvent> p_316661_, GameEvent.Context p_283014_, Vec3 p_282350_, CallbackInfoReturnable<Boolean> cir) {
        if (!gameRules.gameRuleValueBool(p_283470_, GameRule.SCULK_SPREADING))
            cir.setReturnValue(false);

    }


}
