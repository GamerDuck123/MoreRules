package me.gamerduck.rules.paper.events;

import me.gamerduck.rules.common.GameRule;
import net.minecraft.world.entity.monster.EnderMan;
import net.minecraft.world.entity.monster.Silverfish;
import org.bukkit.damage.DamageType;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Tameable;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.*;

import static me.gamerduck.rules.paper.MoreRules.gameRules;

public class EntityEvents implements Listener {

    @EventHandler
    public void onEntitySpawn(EntitySpawnEvent e) {
        if (e.getEntityType() == EntityType.PHANTOM)
            e.setCancelled(!gameRules.gameRuleValueBool(e.getEntity().getWorld(), GameRule.DRAGON_DAMAGE));
    }

    @EventHandler
    public void onEntityDamage(EntityDamageEvent e) {
        if (e.getEntityType() == EntityType.PLAYER) {
            // PLAYER ONLY
            if (e.getDamageSource().getDamageType() == DamageType.FALL)
                e.setCancelled(!gameRules.gameRuleValueBool(e.getEntity().getWorld(), GameRule.PLAYER_FALL_DAMAGE));
            if (e.getDamageSource().getDamageType() == DamageType.DROWN)
                e.setCancelled(!gameRules.gameRuleValueBool(e.getEntity().getWorld(), GameRule.PLAYER_DROWN));
            if (e.getDamageSource().getDamageType() == DamageType.IN_FIRE
                    || e.getDamageSource().getDamageType() == DamageType.ON_FIRE)
                e.setCancelled(!gameRules.gameRuleValueBool(e.getEntity().getWorld(), GameRule.FIRE_DAMAGE));
            if (e.getDamageSource().getDamageType() == DamageType.ENDER_PEARL)
                e.setCancelled(!gameRules.gameRuleValueBool(e.getEntity().getWorld(), GameRule.ENDER_PEARL_DAMAGE));
            if (e.getEntityType() == EntityType.ENDER_DRAGON)
                e.setCancelled(!gameRules.gameRuleValueBool(e.getEntity().getWorld(), GameRule.DRAGON_DAMAGE));
        } else if (e.getEntity() instanceof Tameable tamed
                && tamed.isTamed()
                && tamed.getOwner().getUniqueId() == e.getEntity().getUniqueId()) {
            e.setCancelled(!gameRules.gameRuleValueBool(e.getEntity().getWorld(), GameRule.PET_FRIENDLY_FIRE));
        }
    }
    @EventHandler
    public void onSlimeSplitEvent(SlimeSplitEvent e) {
        if (!gameRules.gameRuleValueBool(e.getEntity().getWorld(), GameRule.SLIMES_SPLIT)) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onEntityEnterBlockEvent(EntityEnterBlockEvent e) {
        if (e.getEntity() instanceof Silverfish)
            e.setCancelled(!gameRules.gameRuleValueBool(e.getEntity().getWorld(), GameRule.SILVERFISH_INFEST));
    }

    @EventHandler
    public void onEntityChangeBlockEvent(EntityChangeBlockEvent e) {
        if (e.getEntity() instanceof EnderMan)
            e.setCancelled(!gameRules.gameRuleValueBool(e.getEntity().getWorld(), GameRule.ENDERMEN_GRIEFING));
    }

    @EventHandler
    public void onEntityConvert(EntityTransformEvent e) {
        if (e.getEntityType() == EntityType.PIG && e.getTransformedEntity().getType() == EntityType.ZOMBIFIED_PIGLIN)
            e.setCancelled(!gameRules.gameRuleValueBool(e.getEntity().getWorld(), GameRule.PIG_PIGLIN_CONVERSIONS));
        if (e.getEntityType() == EntityType.VILLAGER && e.getTransformedEntity().getType() == EntityType.ZOMBIE_VILLAGER)
            e.setCancelled(!gameRules.gameRuleValueBool(e.getEntity().getWorld(), GameRule.VILLAGER_ZOMBIE_CONVERSIONS));
        if (e.getEntityType() == EntityType.ZOMBIE_VILLAGER && e.getTransformedEntity().getType() == EntityType.VILLAGER)
            e.setCancelled(!gameRules.gameRuleValueBool(e.getEntity().getWorld(), GameRule.ZOMBIE_VILLAGER_CONVERSIONS));
        if (e.getEntityType() == EntityType.VILLAGER && e.getTransformedEntity().getType() == EntityType.WITCH)
            e.setCancelled(!gameRules.gameRuleValueBool(e.getEntity().getWorld(), GameRule.VILLAGER_WITCH_CONVERSIONS));
    }

    @EventHandler
    public void onPickup(EntityPickupItemEvent e) {
        if (e.getEntity() instanceof Player || e.getEntity() instanceof Villager) return;
        e.setCancelled(!gameRules.gameRuleValueBool(e.getEntity().getWorld(), GameRule.MOB_PICKUP));
    }
}
