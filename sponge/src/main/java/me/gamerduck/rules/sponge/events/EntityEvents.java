package me.gamerduck.rules.sponge.events;

public class EntityEvents {

//    @EventHandler
//    public void onSlimeSplitEvent(SlimeSplitEvent e) {
//        if (!gameRules.gameRuleValueBool(e.getEntity().getWorld(), GameRule.SLIMES_SPLIT)) {
//            e.setCancelled(true);
//        }
//    }
//
//    @EventHandler
//    public void onEntityChangeBlockEvent(EntityChangeBlockEvent e) {
//        if (e.getEntity() instanceof EnderMan)
//            e.setCancelled(!gameRules.gameRuleValueBool(e.getEntity().getWorld(), GameRule.ENDERMEN_GRIEFING));
//        if (e.getEntity() instanceof Silverfish)
//            e.setCancelled(!gameRules.gameRuleValueBool(e.getEntity().getWorld(), GameRule.SILVERFISH_INFEST));
//    }
//
//    @EventHandler
//    public void onEntityConvert(EntityTransformEvent e) {
//        if (e.getEntityType() == EntityType.PIG && e.getTransformedEntity().getType() == EntityType.ZOMBIFIED_PIGLIN)
//            e.setCancelled(!gameRules.gameRuleValueBool(e.getEntity().getWorld(), GameRule.PIG_PIGLIN_CONVERSIONS));
//        else if (e.getEntityType() == EntityType.VILLAGER && e.getTransformedEntity().getType() == EntityType.ZOMBIE_VILLAGER)
//            e.setCancelled(!gameRules.gameRuleValueBool(e.getEntity().getWorld(), GameRule.VILLAGER_ZOMBIE_CONVERSIONS));
//        else if (e.getEntityType() == EntityType.ZOMBIE_VILLAGER && e.getTransformedEntity().getType() == EntityType.VILLAGER)
//            e.setCancelled(!gameRules.gameRuleValueBool(e.getEntity().getWorld(), GameRule.ZOMBIE_VILLAGER_CONVERSIONS));
//        else if (e.getEntityType() == EntityType.VILLAGER && e.getTransformedEntity().getType() == EntityType.WITCH)
//            e.setCancelled(!gameRules.gameRuleValueBool(e.getEntity().getWorld(), GameRule.VILLAGER_WITCH_CONVERSIONS));
//    }
//
//    @EventHandler
//    public void onPickup(EntityPickupItemEvent e) {
//        if (e.getEntity() instanceof Player || e.getEntity() instanceof Villager) return;
//        e.setCancelled(!gameRules.gameRuleValueBool(e.getEntity().getWorld(), GameRule.MOB_PICKUP));
//    }
}
