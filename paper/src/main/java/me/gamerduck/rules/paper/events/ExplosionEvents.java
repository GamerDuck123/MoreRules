package me.gamerduck.rules.paper.events;

import me.gamerduck.rules.common.GameRule;
import me.gamerduck.rules.paper.behaviors.NoExplosionBehavior;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import org.bukkit.Material;
import org.bukkit.craftbukkit.CraftWorld;
import org.bukkit.craftbukkit.block.CraftBlock;
import org.bukkit.craftbukkit.entity.CraftEntity;
import org.bukkit.entity.Ghast;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockExplodeEvent;
import org.bukkit.event.entity.EntityExplodeEvent;

import static me.gamerduck.rules.paper.MoreRules.gameRules;

public class ExplosionEvents implements Listener {

    @EventHandler
    public void onExplosion(EntityExplodeEvent e) {
        CraftWorld craftWorld = ((CraftWorld) e.getLocation().getWorld());
        Level world = craftWorld.getHandle();
        Entity entity = ((CraftEntity) e.getEntity()).getHandle();
        double x = e.getLocation().getX();
        double y = e.getLocation().getY();
        double z = e.getLocation().getZ();
        float yield = e.getYield();

        switch (e.getEntityType()) {
            case CREEPER -> {
                if (!gameRules.gameRuleValueBool(craftWorld, GameRule.CREEPER_GRIEFING)
                        || !gameRules.gameRuleValueBool(craftWorld, GameRule.CREEPER_DAMAGE)) {
                    e.setCancelled(true);
                    world.explode(entity, Explosion.getDefaultDamageSource(world, entity),
                            new NoExplosionBehavior(craftWorld, GameRule.CREEPER_GRIEFING, GameRule.CREEPER_DAMAGE),
                            x, y, z, yield, false, Level.ExplosionInteraction.MOB);
                }
            }
            case TNT -> {
                if (!gameRules.gameRuleValueBool(craftWorld, GameRule.TNT_EXPLOSION)
                        || !gameRules.gameRuleValueBool(craftWorld, GameRule.TNT_DAMAGE)) {
                    e.setCancelled(true);
                    world.explode(entity, Explosion.getDefaultDamageSource(world, entity),
                            new NoExplosionBehavior(craftWorld, GameRule.TNT_EXPLOSION, GameRule.TNT_DAMAGE),
                            x, y, z, yield, false, Level.ExplosionInteraction.TNT);
                }
            }
            case END_CRYSTAL -> {
                if (!gameRules.gameRuleValueBool(craftWorld, GameRule.CRYSTAL_GRIEFING)
                        || !gameRules.gameRuleValueBool(craftWorld, GameRule.CRYSTAL_DAMAGE)) {
                    e.setCancelled(true);
                    world.explode(entity, Explosion.getDefaultDamageSource(world, entity),
                            new NoExplosionBehavior(craftWorld, GameRule.CRYSTAL_GRIEFING, GameRule.CRYSTAL_DAMAGE),
                            x, y, z, yield, true, Level.ExplosionInteraction.STANDARD);
                }
            }
            case FIREBALL, SMALL_FIREBALL -> {
                Projectile proj = (Projectile) e.getEntity();
                if (proj.getShooter() instanceof Ghast
                        && !gameRules.gameRuleValueBool(craftWorld, GameRule.BED_GRIEFING)
                        || !gameRules.gameRuleValueBool(craftWorld, GameRule.BED_DAMAGE)) {
                    e.setCancelled(true);
                    world.explode(entity, Explosion.getDefaultDamageSource(world, entity),
                            new NoExplosionBehavior(craftWorld, GameRule.GHAST_GRIEFING, GameRule.GHAST_DAMAGE),
                            x, y, z, yield, true, Level.ExplosionInteraction.MOB);
                }
            }
        }
    }

    @EventHandler
    public void onBlockExplosion(BlockExplodeEvent e) {
        Level world = ((CraftWorld) e.getBlock().getLocation().getWorld()).getHandle();
        double x = e.getBlock().getLocation().getX();
        double y = e.getBlock().getLocation().getY();
        double z = e.getBlock().getLocation().getZ();
        float yield = e.getYield();
        if (e.getBlock().getType().toString().contains("BED")
                && !gameRules.gameRuleValueBool(e.getBlock().getWorld(), GameRule.BED_GRIEFING)
                || !gameRules.gameRuleValueBool(e.getBlock().getWorld(), GameRule.BED_DAMAGE)) {
            e.setCancelled(true);
            world.explode(null, Explosion.getDefaultDamageSource(world, null),
                    new NoExplosionBehavior(world.getWorld(), GameRule.BED_GRIEFING, GameRule.BED_DAMAGE),
                    x, y, z, yield, false, Level.ExplosionInteraction.STANDARD);
        }
        else if (e.getBlock().getType() == Material.RESPAWN_ANCHOR
                && !gameRules.gameRuleValueBool(e.getBlock().getWorld(), GameRule.RESPAWN_ANCHOR_GRIEFING)
                || !gameRules.gameRuleValueBool(e.getBlock().getWorld(), GameRule.RESPAWN_ANCHOR_DAMAGE)) {
            e.setCancelled(true);
            world.explode(null, Explosion.getDefaultDamageSource(world, null),
                    new NoExplosionBehavior(world.getWorld(), GameRule.RESPAWN_ANCHOR_GRIEFING, GameRule.RESPAWN_ANCHOR_DAMAGE),
                    x, y, z, yield, false, Level.ExplosionInteraction.STANDARD);
        }
    }

}
