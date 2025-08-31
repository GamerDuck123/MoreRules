package me.gamerduck.rules.neoforged.behaviors;

import me.gamerduck.rules.common.GameRule;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.ExplosionDamageCalculator;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;

import java.util.Optional;

import static me.gamerduck.rules.neoforged.MoreRulesMod.gameRules;


public class NoExplosionBehavior extends ExplosionDamageCalculator {

    private final ExplosionDamageCalculator secondBehavior;
    private final GameRule rule;
    private final Level world;

    public NoExplosionBehavior(ExplosionDamageCalculator secondBehavior, Level world, GameRule rule) {
        this.secondBehavior = secondBehavior;
        this.world = world;
        this.rule = rule;
    }

    public NoExplosionBehavior(Level world, GameRule rule) {
        this.secondBehavior = null;
        this.world = world;
        this.rule = rule;
    }

    @Override
    public Optional<Float> getBlockExplosionResistance(Explosion explosion, BlockGetter reader, BlockPos pos, BlockState state, FluidState fluid) {
        return gameRules.gameRuleValueBool(world, rule) ?
                (secondBehavior == null ? super.getBlockExplosionResistance(explosion, reader, pos, state, fluid) :
                        secondBehavior.getBlockExplosionResistance(explosion, reader, pos, state, fluid)) : Optional.of(-1f);
    }

    @Override
    public boolean shouldBlockExplode(Explosion explosion, BlockGetter reader, BlockPos pos, BlockState state, float power) {
        return secondBehavior == null ? gameRules.gameRuleValueBool(world, rule) : secondBehavior.shouldBlockExplode(explosion, reader, pos, state, power);
    }

    /* @Override
    public Optional<Float> getBlastResistance(Explosion explosion, BlockView w, BlockPos pos, BlockState blockState, FluidState fluidState) {
        return gameRules.gameRuleValueBool(world, rule) ?
                (secondBehavior == null ? super.getBlastResistance(explosion, w, pos, blockState, fluidState) :
                        secondBehavior.getBlastResistance(explosion, w, pos, blockState, fluidState)) : Optional.of(-1f);
    }

    @Override
    public boolean canDestroyBlock(Explosion explosion, BlockView w, BlockPos pos, BlockState state, float power) {
        return secondBehavior == null ? gameRules.gameRuleValueBool(world, rule) : secondBehavior.canDestroyBlock(explosion, w, pos, state, power);
    }*/
}
