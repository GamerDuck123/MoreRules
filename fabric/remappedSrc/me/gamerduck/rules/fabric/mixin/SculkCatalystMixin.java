package me.gamerduck.rules.fabric.mixin;

import me.gamerduck.rules.common.GameRule;
import net.minecraft.block.BlockState;
import net.minecraft.block.CoralBlockBlock;
import net.minecraft.block.entity.SculkCatalystBlockEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static me.gamerduck.rules.fabric.MoreRulesMod.gameRules;

@Mixin(SculkCatalystBlockEntity.Listener.class)
public abstract class SculkCatalystMixin {

    @Inject(method = "listen", at = @At(value = "HEAD"), cancellable = true)
    private void inject(ServerWorld serverWorld, GameEvent gameEvent, GameEvent.Emitter emitter, Vec3d vec3d, CallbackInfoReturnable<Boolean> cir) {
        if (!gameRules.gameRuleValueBool(serverWorld, GameRule.SCULK_SPREADING))
            cir.setReturnValue(false);

    }


}
