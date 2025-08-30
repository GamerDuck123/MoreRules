package me.gamerduck.rules.paper.events;

import me.gamerduck.rules.common.GameRule;
import org.bukkit.Material;
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
        switch (e.getEntityType()) {
            case CREEPER -> {
                if (!gameRules.gameRuleValueBool(e.getLocation().getWorld(), GameRule.CREEPER_GRIEFING)) {
                    e.setCancelled(true);
                    e.getLocation().getWorld().createExplosion(e.getLocation(), 2, false, false);
                }
            }
            case TNT, TNT_MINECART -> {
                if (!gameRules.gameRuleValueBool(e.getLocation().getWorld(), GameRule.TNT_EXPLOSION)) {
                    e.setCancelled(true);
                    e.getLocation().getWorld().createExplosion(e.getLocation(), 2, false, false);
                }
            }
            case END_CRYSTAL -> {
                if (!gameRules.gameRuleValueBool(e.getLocation().getWorld(), GameRule.CRYSTAL_EXPLOSION)) {
                    e.setCancelled(true);
                    e.getLocation().getWorld().createExplosion(e.getLocation(), 2, false, false);
                }
            }
            case FIREBALL, SMALL_FIREBALL -> {
                Projectile proj = (Projectile) e.getEntity();
                if (proj.getShooter() instanceof Ghast) {
                    if (!gameRules.gameRuleValueBool(e.getLocation().getWorld(), GameRule.GHAST_GRIEFING)) {
                        e.setCancelled(true);
                        e.getLocation().getWorld().createExplosion(e.getLocation(), 2, false, false);
                    }
                }
            }
        }
    }

    @EventHandler
    public void onBlockExplosion(BlockExplodeEvent e) {
        if (e.getBlock().getType().toString().contains("BED"))
            e.setCancelled(!gameRules.gameRuleValueBool(e.getBlock().getWorld(), GameRule.BED_EXPLOSION));
        else if (e.getBlock().getType() == Material.RESPAWN_ANCHOR)
            e.setCancelled(!gameRules.gameRuleValueBool(e.getBlock().getWorld(), GameRule.RESPAWN_ANCHOR_EXPLOSION));
    }

}
