package me.gamerduck.rules.mixin.mixins;


import me.gamerduck.rules.common.GameRule;
import me.gamerduck.rules.mixin.MixinsVariable;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.features.CaveFeatures;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.SculkCatalystBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BonemealableFeaturePlacerBlock.class)
public abstract class MossBlockMixin extends Block implements BonemealableBlock {

    public MossBlockMixin(Properties p_49795_) {
        super(p_49795_);
    }

    @Inject(method = "isBonemealSuccess", at = @At(value = "HEAD"), cancellable = true)
    private void inject(Level p_380206_, RandomSource p_380151_, BlockPos p_379719_, BlockState p_379567_, CallbackInfoReturnable<Boolean> cir) {
        if (!MixinsVariable.gameRules.gameRuleValueBool(p_380206_, GameRule.MOSS_SPREAD)) {
            cir.setReturnValue(false);
            cir.cancel();
        }

    }


}
