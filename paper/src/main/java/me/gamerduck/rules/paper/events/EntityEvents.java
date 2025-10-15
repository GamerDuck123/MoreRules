package me.gamerduck.rules.paper.events;

import me.gamerduck.rules.common.GameRule;
import net.minecraft.world.entity.monster.EnderMan;
import net.minecraft.world.entity.monster.Silverfish;
import net.minecraft.world.inventory.AnvilMenu;
import org.bukkit.Material;
import org.bukkit.craftbukkit.entity.CraftPlayer;
import org.bukkit.craftbukkit.inventory.CraftInventory;
import org.bukkit.craftbukkit.inventory.CraftInventoryAnvil;
import org.bukkit.damage.DamageType;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.*;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.player.PlayerPickupArrowEvent;
import org.bukkit.inventory.AnvilInventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.concurrent.ThreadLocalRandom;

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
    public void onPlayerDeath(PlayerDeathEvent e) {
        if (gameRules.gameRuleValueBool(e.getEntity().getWorld(), GameRule.PLAYERS_HEAD_DROP)) {
            Double chance = gameRules.gameRuleValueDouble(e.getEntity().getWorld(), GameRule.PLAYERS_HEAD_DROP_CHANCE);
            if (chance < 0 || chance >= ThreadLocalRandom.current().nextDouble(0, 100)) {
                ItemStack head = new ItemStack(Material.PLAYER_HEAD);
                head.editMeta(SkullMeta.class, meta -> {
                    meta.setOwningPlayer(e.getPlayer());
                });
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
