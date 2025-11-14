package me.gamerduck.rules.bukkit.events;

import me.gamerduck.rules.common.GameRule;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.BlockState;
import org.bukkit.block.data.type.Bed;
import org.bukkit.block.data.type.RespawnAnchor;
import org.bukkit.damage.DamageType;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockExplodeEvent;
import org.bukkit.event.entity.EntityDamageByBlockEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityExplodeEvent;

import static me.gamerduck.rules.bukkit.MoreRules.gameRules;

public class ExplosionEvents implements Listener {

    @EventHandler
    public void onExplosionDamage(EntityDamageEvent e) {
        World world = e.getEntity().getWorld();
        if (e.getDamageSource().getCausingEntity() != null &&
                (e.getDamageSource().getDamageType() == DamageType.PLAYER_EXPLOSION
                || e.getDamageSource().getDamageType() == DamageType.EXPLOSION)) {
            e.setCancelled(switch (e.getDamageSource().getCausingEntity().getType()) {
                case CREEPER -> !gameRules.gameRuleValueBool(world, GameRule.CREEPER_DAMAGE);
                case TNT -> !gameRules.gameRuleValueBool(world, GameRule.TNT_DAMAGE);
                case END_CRYSTAL -> !gameRules.gameRuleValueBool(world, GameRule.CRYSTAL_DAMAGE);
                case FIREBALL, SMALL_FIREBALL -> {
                    Projectile proj = (Projectile) e.getEntity();
                    if (proj.getShooter() instanceof Ghast) yield !gameRules.gameRuleValueBool(world, GameRule.GHAST_DAMAGE);
                    yield e.isCancelled();
                }
                case WITHER_SKULL -> !gameRules.gameRuleValueBool(world, GameRule.WITHER_SKULL_DAMAGE);
                case WITHER -> !gameRules.gameRuleValueBool(world, GameRule.WITHER_DAMAGE);
                case SQUID -> !gameRules.gameRuleValueBool(world, GameRule.BED_DAMAGE);
                case GLOW_SQUID -> !gameRules.gameRuleValueBool(world, GameRule.RESPAWN_ANCHOR_DAMAGE);
                default -> e.isCancelled();
            });
        }
    }

    @EventHandler
    public void onExplosion(EntityExplodeEvent e) {
        World world = e.getLocation().getWorld();
        double x = e.getLocation().getX();
        double y = e.getLocation().getY();
        double z = e.getLocation().getZ();

        switch (e.getEntityType()) {
            case CREEPER -> {
                if (!gameRules.gameRuleValueBool(world, GameRule.CREEPER_GRIEFING)) {
                    e.setCancelled(true);
                    Creeper entity = (Creeper) e.getEntity();
                    world.createExplosion(x, y, z, entity.isPowered() ? 6.0F : 3.0F, false, false, e.getEntity());
                }
            }
            case TNT -> {
                if (!gameRules.gameRuleValueBool(world, GameRule.TNT_GRIEFING)) {
                    e.setCancelled(true);
                    world.createExplosion(x, y, z, 4F, false, false, e.getEntity());
                }
            }
            case END_CRYSTAL -> {
                if (!gameRules.gameRuleValueBool(world, GameRule.CRYSTAL_GRIEFING)) {
                    e.setCancelled(true);
                    world.createExplosion(x, y, z, 6.0F, false, false, e.getEntity());
                }
            }
            case FIREBALL, SMALL_FIREBALL -> {
                Projectile proj = (Projectile) e.getEntity();
                if (proj.getShooter() instanceof Ghast
                        && !gameRules.gameRuleValueBool(world, GameRule.GHAST_GRIEFING)) {
                    e.setCancelled(true);
                    world.createExplosion(x, y, z, 1.0F, false, false, e.getEntity());
                }
            }
            case WITHER_SKULL -> {
                if (!gameRules.gameRuleValueBool(world, GameRule.WITHER_SKULL_GRIEFING)) {
                    e.setCancelled(true);
                    world.createExplosion(x, y, z, 1.0F, false, false, e.getEntity());
                }
            }
            case WITHER -> {
                if (!gameRules.gameRuleValueBool(world, GameRule.WITHER_GRIEFING)) {
                    e.setCancelled(true);
                    world.createExplosion(x, y, z, 7.0F, false, false, e.getEntity());
                }
            }
            case ENDER_DRAGON -> {
                if (!gameRules.gameRuleValueBool(world, GameRule.DRAGON_GRIEFING)) {
                    e.setCancelled(true);
                    world.createExplosion(x, y, z, 1.0F, false, false, e.getEntity());
                }
            }
        }
    }

    @EventHandler
    public void onBlockExplosionDamage(EntityDamageByBlockEvent e) {
        if (e.getDamageSource().getDamageType().equals(DamageType.BAD_RESPAWN_POINT)) {
            if (e.getEntity().getWorld().isPiglinSafe() &&
                    !gameRules.gameRuleValueBool(e.getEntity().getWorld(), GameRule.BED_DAMAGE)) {
                e.setCancelled(true);
            } else if (!e.getEntity().getWorld().isPiglinSafe() &&
                    !gameRules.gameRuleValueBool(e.getEntity().getWorld(), GameRule.RESPAWN_ANCHOR_DAMAGE)) {
                e.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onBlockExplosion(BlockExplodeEvent e) {
        World world = e.getBlock().getLocation().getWorld();
        double x = e.getBlock().getLocation().getX();
        double y = e.getBlock().getLocation().getY();
        double z = e.getBlock().getLocation().getZ();

        if (world.isPiglinSafe() &&
                !gameRules.gameRuleValueBool(world, GameRule.BED_GRIEFING)) {
            e.setCancelled(true);
            Location entLoc = e.getBlock().getLocation().clone();
            entLoc.setY(300);
            Entity ent = world.spawnEntity(entLoc, EntityType.SQUID);
            world.createExplosion(x, y, z, 5.0F, true, false, ent);
            ent.remove();
        } else if (!world.isPiglinSafe() &&
                    !gameRules.gameRuleValueBool(world, GameRule.RESPAWN_ANCHOR_GRIEFING)) {
            e.setCancelled(true);
            Location entLoc = e.getBlock().getLocation().clone();
            entLoc.setY(300);
            Entity ent = world.spawnEntity(entLoc, EntityType.GLOW_SQUID);
            world.createExplosion(x, y, z, 5.0F, false, false, ent);
            ent.remove();
        }
    }

}
