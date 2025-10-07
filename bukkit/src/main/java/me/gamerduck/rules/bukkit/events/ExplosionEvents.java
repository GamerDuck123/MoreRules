package me.gamerduck.rules.bukkit.events;

import me.gamerduck.rules.common.GameRule;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.damage.DamageType;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Ghast;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockExplodeEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityExplodeEvent;

import static me.gamerduck.rules.bukkit.MoreRules.gameRules;

public class ExplosionEvents implements Listener {

    @EventHandler
    public void onExplosionDamage(EntityDamageEvent e) {
        World world = e.getEntity().getWorld();
        if (e.getDamageSource().getDamageType() == DamageType.PLAYER_EXPLOSION
                || e.getDamageSource().getDamageType() == DamageType.EXPLOSION) {
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
                default -> e.isCancelled();
            });
        }
    }
//    @EventHandler
//    public void onExplosionDamage(EntityDamageByBlockEvent e) {
//        World world = e.getEntity().getWorld();
//        if (e.getDamageSource().getDamageType() == DamageType.EXPLOSION) {
//            Bukkit.getLogger().info("THIS IS A TEST2");
//            if (e.getDamager().getType().toString().contains("BED")) e.setCancelled(!gameRules.gameRuleValueBool(world, GameRule.BED_DAMAGE));
//            else if (e.getDamager().getType() == Material.RESPAWN_ANCHOR) e.setCancelled(!gameRules.gameRuleValueBool(world, GameRule.RESPAWN_ANCHOR_DAMAGE));
//        }
//    }

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
        }
    }


    @EventHandler
    public void onBlockExplosion(BlockExplodeEvent e) {
        World world = e.getBlock().getLocation().getWorld();
        double x = e.getBlock().getLocation().getX();
        double y = e.getBlock().getLocation().getY();
        double z = e.getBlock().getLocation().getZ();

        if (e.getBlock().getType().toString().contains("BED")
                && !gameRules.gameRuleValueBool(world, GameRule.BED_GRIEFING)) {
            e.setCancelled(true);
            world.createExplosion(x, y, z, 5.0F, true, false);
        }
        else if (e.getBlock().getType().equals(Material.RESPAWN_ANCHOR)
                && !gameRules.gameRuleValueBool(world, GameRule.RESPAWN_ANCHOR_GRIEFING)) {
            e.setCancelled(true);
            world.createExplosion(x, y, z, 5.0F, false, false);
        }
    }

}
