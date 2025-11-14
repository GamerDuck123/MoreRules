package me.gamerduck.rules.bukkit.events;

import me.gamerduck.rules.common.GameRule;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.damage.DamageType;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.*;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerPickupArrowEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.concurrent.ThreadLocalRandom;

import static me.gamerduck.rules.bukkit.MoreRules.gameRules;

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
        if (e.getEntityType() == EntityType.SILVERFISH)
            e.setCancelled(!gameRules.gameRuleValueBool(e.getEntity().getWorld(), GameRule.SILVERFISH_INFEST));
    }

    @EventHandler
    public void onEntityChangeBlockEvent(EntityChangeBlockEvent e) {
        if (e.getEntityType() == EntityType.ENDERMAN)
            e.setCancelled(!gameRules.gameRuleValueBool(e.getEntity().getWorld(), GameRule.ENDERMEN_GRIEFING));
    }

    @EventHandler
    public void onEntityConvert(EntityTransformEvent e) {
        if (e.getEntityType() == EntityType.PIG && e.getTransformedEntity().getType() == EntityType.ZOMBIFIED_PIGLIN)
            e.setCancelled(!gameRules.gameRuleValueBool(e.getEntity().getWorld(), GameRule.PIG_PIGLIN_CONVERSIONS));
        else if (e.getEntityType() == EntityType.VILLAGER && e.getTransformedEntity().getType() == EntityType.ZOMBIE_VILLAGER)
            e.setCancelled(!gameRules.gameRuleValueBool(e.getEntity().getWorld(), GameRule.VILLAGER_ZOMBIE_CONVERSIONS));
        else if (e.getEntityType() == EntityType.ZOMBIE_VILLAGER && e.getTransformedEntity().getType() == EntityType.VILLAGER)
            e.setCancelled(!gameRules.gameRuleValueBool(e.getEntity().getWorld(), GameRule.ZOMBIE_VILLAGER_CONVERSIONS));
        else if (e.getEntityType() == EntityType.VILLAGER && e.getTransformedEntity().getType() == EntityType.WITCH)
            e.setCancelled(!gameRules.gameRuleValueBool(e.getEntity().getWorld(), GameRule.VILLAGER_WITCH_CONVERSIONS));
    }

    @EventHandler
    public void onPickup(EntityPickupItemEvent e) {
        if (e.getEntity() instanceof Player || e.getEntity() instanceof Villager) return;
        e.setCancelled(!gameRules.gameRuleValueBool(e.getEntity().getWorld(), GameRule.MOB_PICKUP));
    }

    @EventHandler
    public void onPickupArrow(PlayerPickupArrowEvent e) {
        e.setCancelled(!gameRules.gameRuleValueBool(e.getPlayer().getWorld(), GameRule.PROJECTILE_PICKUP));
    }

    @EventHandler
    public void onFoodLevelChange(FoodLevelChangeEvent e) {
        if (!(e.getEntity() instanceof Player)) return;
        e.setCancelled(!gameRules.gameRuleValueBool(e.getEntity().getWorld(), GameRule.PLAYER_HUNGER));
    }

    @EventHandler
    public void onDespawn(ItemDespawnEvent e) {
        e.setCancelled(!gameRules.gameRuleValueBool(e.getEntity().getWorld(), GameRule.ITEMS_DESPAWN));
    }

    @EventHandler
    public void onDeath(EntityDeathEvent e) {
        if (e.getEntity() instanceof Player) return;
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent e) {
        if (gameRules.gameRuleValueBool(e.getEntity().getWorld(), GameRule.PLAYERS_HEAD_DROP)) {
            Double chance = gameRules.gameRuleValueDouble(e.getEntity().getWorld(), GameRule.PLAYERS_HEAD_DROP_CHANCE);
            if (chance < 0 || chance >= ThreadLocalRandom.current().nextDouble(0, 100)) {
                ItemStack head = new ItemStack(Material.PLAYER_HEAD);
                SkullMeta meta =  (SkullMeta) head.getItemMeta();
                meta.setOwningPlayer(e.getEntity());
                head.setItemMeta(meta);
                e.getDrops().add(head);
            }
        }
    }

    @EventHandler
    public void EnderDragonDying(EnderDragonChangePhaseEvent e) {
        if (e.getNewPhase() == EnderDragon.Phase.DYING
                && e.getEntity().getDragonBattle() != null
                && e.getEntity().getDragonBattle().hasBeenPreviouslyKilled()
                && gameRules.gameRuleValueBool(e.getEntity().getWorld(), GameRule.NEW_DRAGON_EGGS)) {
            e.getEntity().getWorld().getBlockAt(0, e.getEntity().getWorld().getHighestBlockYAt(0,0) + 1, 0).setType(Material.DRAGON_EGG);
        }
    }
}
