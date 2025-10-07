package me.gamerduck.rules.bukkit.events;

import me.gamerduck.rules.common.GameRule;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockFadeEvent;
import org.bukkit.event.block.BlockSpreadEvent;
import org.bukkit.event.entity.EntityBreakDoorEvent;
import org.bukkit.event.entity.EntityInteractEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import static me.gamerduck.rules.bukkit.MoreRules.gameRules;

public class BlockChangeEvents implements Listener {

    @EventHandler
    public void blockMelt(BlockFadeEvent e) {
        if (e.getBlock().getType().toString().contains("ICE")) {
            e.setCancelled(!gameRules.gameRuleValueBool(e.getBlock().getWorld(), GameRule.LIGHT_MELT_ICE));
        } else if (e.getBlock().getType().toString().contains("SNOW")) {
            e.setCancelled(!gameRules.gameRuleValueBool(e.getBlock().getWorld(), GameRule.LIGHT_MELT_SNOW));
        } else if (e.getBlock().getType().toString().contains("CORAL")) {
            e.setCancelled(!gameRules.gameRuleValueBool(e.getBlock().getWorld(), GameRule.CORAL_DECAY));
        }
    }

    @EventHandler
    public void onBlockChange(BlockSpreadEvent e) {
        if (e.getSource().getType() == Material.SCULK_CATALYST)
            e.setCancelled(!gameRules.gameRuleValueBool(e.getSource().getWorld(), GameRule.SCULK_SPREADING));
    }

    @EventHandler
    public void onCropTrample(EntityInteractEvent e) {
        if (!(e.getEntity() instanceof Player) && e.getBlock().getType() == Material.FARMLAND)
            e.setCancelled(!gameRules.gameRuleValueBool(e.getBlock().getWorld(), GameRule.CROP_TRAMPLE));
    }

    @EventHandler
    public void onCropTrample(PlayerInteractEvent e) {
        if (e.getAction() == Action.PHYSICAL && e.getClickedBlock().getType() == Material.FARMLAND)
            e.setCancelled(!gameRules.gameRuleValueBool(e.getClickedBlock().getWorld(), GameRule.CROP_TRAMPLE));
    }

    @EventHandler
    public void onDoorBreak(EntityBreakDoorEvent e) {
        if (e.getEntityType() == EntityType.ZOMBIE || e.getEntityType() == EntityType.ZOMBIE_VILLAGER) {
            e.setCancelled(!gameRules.gameRuleValueBool(e.getEntity().getWorld(), GameRule.ZOMBIE_BREAK_DOORS));
        }
    }
}
