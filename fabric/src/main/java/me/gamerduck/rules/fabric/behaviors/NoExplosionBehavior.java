package me.gamerduck.rules.fabric.behaviors;

import me.gamerduck.rules.common.GameRule;
import net.minecraft.block.BlockState;
import net.minecraft.fluid.FluidState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;
import net.minecraft.world.explosion.ExplosionBehavior;

import java.util.Optional;

import static me.gamerduck.rules.fabric.MoreRulesMod.gameRules;

public class NoExplosionBehavior extends ExplosionBehavior {

    private final ExplosionBehavior secondBehavior;
    private final GameRule rule;
    private final World world;

    public NoExplosionBehavior(ExplosionBehavior secondBehavior, World world, GameRule rule) {
        this.secondBehavior = secondBehavior;
        this.world = world;
        this.rule = rule;
    }

    public NoExplosionBehavior(World world, GameRule rule) {
        this.secondBehavior = null;
        this.world = world;
        this.rule = rule;
    }

    @Override
    public Optional<Float> getBlastResistance(Explosion explosion, BlockView w, BlockPos pos, BlockState blockState, FluidState fluidState) {
        return gameRules.gameRuleValueBool(world, rule) ?
                (secondBehavior == null ? super.getBlastResistance(explosion, w, pos, blockState, fluidState) :
                        secondBehavior.getBlastResistance(explosion, w, pos, blockState, fluidState)) : Optional.of(-1f);
    }

    @Override
    public boolean canDestroyBlock(Explosion explosion, BlockView w, BlockPos pos, BlockState state, float power) {
        return secondBehavior == null ? gameRules.gameRuleValueBool(world, rule) : secondBehavior.canDestroyBlock(explosion, w, pos, state, power);
    }
}
