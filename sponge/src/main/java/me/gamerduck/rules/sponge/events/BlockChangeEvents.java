package me.gamerduck.rules.sponge.events;

import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.block.ChangeBlockEvent;

public class BlockChangeEvents {

    @Listener
    public void blockMelt(ChangeBlockEvent.All e) {
//        if (e..getType().toString().contains("ICE")) {
//            e.setCancelled(!gameRules.gameRuleValueBool(e.getBlock().getWorld(), GameRule.LIGHT_MELT_ICE));
//        } else if (e.getBlock().getType().toString().contains("SNOW")) {
//            e.setCancelled(!gameRules.gameRuleValueBool(e.getBlock().getWorld(), GameRule.LIGHT_MELT_SNOW));
//        } else if (e.getBlock().getType().toString().contains("CORAL")) {
//            e.setCancelled(!gameRules.gameRuleValueBool(e.getBlock().getWorld(), GameRule.CORAL_DECAY));
//        }
    }

//    @EventHandler
//    public void onBlockChange(BlockEvent e) {
//        if (e.getSource().getType() == Material.SCULK_CATALYST)
//            e.setCancelled(!gameRules.gameRuleValueBool(e.getSource().getWorld(), GameRule.SCULK_SPREADING));
//    }

//    @Listener
//    public void onCropTrample(InteractBlockEvent e) {
//        if (!(e.getEntity() instanceof Player) && e.getBlock().getType() == Material.FARMLAND)
//            e.setCancelled(!gameRules.gameRuleValueBool(e.getBlock().getWorld(), GameRule.CROP_TRAMPLE));
//    }

//    @EventHandler
//    public void onCropTrample(PlayerInteractEvent e) {
//        if (e.getAction() == Action.PHYSICAL && e.getClickedBlock().getType() == Material.FARMLAND)
//            e.setCancelled(!gameRules.gameRuleValueBool(e.getClickedBlock().getWorld(), GameRule.CROP_TRAMPLE));
//    }

//    @EventHandler
//    public void onDoorBreak(EntityEvent e) {
//        if (e.getEntityType() == EntityType.ZOMBIE || e.getEntityType() == EntityType.ZOMBIE_VILLAGER) {
//            e.setCancelled(!gameRules.gameRuleValueBool(e.getEntity().getWorld(), GameRule.ZOMBIE_BREAK_DOORS));
//        }
//    }
}
