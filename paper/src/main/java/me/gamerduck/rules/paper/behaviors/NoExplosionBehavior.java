package me.gamerduck.rules.paper.behaviors;

import me.gamerduck.rules.common.GameRule;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.ExplosionDamageCalculator;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import org.bukkit.World;

import java.util.Optional;

import static me.gamerduck.rules.paper.MoreRules.gameRules;


public class NoExplosionBehavior extends ExplosionDamageCalculator {

    private final ExplosionDamageCalculator secondBehavior;
    private final GameRule breakBlocksRule;
    private final GameRule doDamageRule;
    private final World world;

    public NoExplosionBehavior(ExplosionDamageCalculator secondBehavior, World world, GameRule breakBlocksRule, GameRule doDamageRule) {
        this.secondBehavior = secondBehavior;
        this.world = world;
        this.breakBlocksRule = breakBlocksRule;
        this.doDamageRule = doDamageRule;
    }

    public NoExplosionBehavior(World world, GameRule breakBlocksRule, GameRule doDamageRule) {
        this.secondBehavior = null;
        this.world = world;
        this.breakBlocksRule = breakBlocksRule;
        this.doDamageRule = doDamageRule;
    }

    @Override
    public Optional<Float> getBlockExplosionResistance(Explosion explosion, BlockGetter reader, BlockPos pos, BlockState state, FluidState fluid) {
        return gameRules.gameRuleValueBool(world, breakBlocksRule) ?
                (secondBehavior == null ? super.getBlockExplosionResistance(explosion, reader, pos, state, fluid) :
                        secondBehavior.getBlockExplosionResistance(explosion, reader, pos, state, fluid)) : Optional.of(-1f);
    }

    @Override
    public boolean shouldBlockExplode(Explosion explosion, BlockGetter reader, BlockPos pos, BlockState state, float power) {
        return secondBehavior == null ? gameRules.gameRuleValueBool(world, breakBlocksRule) : secondBehavior.shouldBlockExplode(explosion, reader, pos, state, power);
    }

    @Override
    public boolean shouldDamageEntity(Explosion explosion, Entity entity) {
        return doDamageRule == null || (secondBehavior == null ? gameRules.gameRuleValueBool(world, doDamageRule) : secondBehavior.shouldDamageEntity(explosion, entity));
    }
}
