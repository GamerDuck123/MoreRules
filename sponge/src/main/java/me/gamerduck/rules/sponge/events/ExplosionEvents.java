package me.gamerduck.rules.sponge.events;

public class ExplosionEvents {

//    @EventHandler
//    public void onExplosion(EntityExplodeEvent e) {
//        CraftWorld craftWorld = ((CraftWorld) e.getLocation().getWorld());
//        Level world = craftWorld.getHandle();
//        Entity entity = ((CraftEntity) e.getEntity()).getHandle();
//        double x = e.getLocation().getX();
//        double y = e.getLocation().getY();
//        double z = e.getLocation().getZ();
//        float yield = e.getYield();
//
//        switch (e.getEntityType()) {
//            case CREEPER -> {
//                if (!gameRules.gameRuleValueBool(craftWorld, GameRule.CREEPER_GRIEFING)
//                        || !gameRules.gameRuleValueBool(craftWorld, GameRule.CREEPER_DAMAGE)) {
//                    e.setCancelled(true);
//                    world.explode(entity, Explosion.getDefaultDamageSource(world, entity),
//                            new NoExplosionBehavior(craftWorld, GameRule.CREEPER_GRIEFING, GameRule.CREEPER_DAMAGE),
//                            x, y, z, yield, false, Level.ExplosionInteraction.MOB);
//                }
//            }
//            case TNT -> {
//                if (!gameRules.gameRuleValueBool(craftWorld, GameRule.TNT_GRIEFING)
//                        || !gameRules.gameRuleValueBool(craftWorld, GameRule.TNT_DAMAGE)) {
//                    e.setCancelled(true);
//                    world.explode(entity, Explosion.getDefaultDamageSource(world, entity),
//                            new NoExplosionBehavior(craftWorld, GameRule.TNT_GRIEFING, GameRule.TNT_DAMAGE),
//                            x, y, z, yield, false, Level.ExplosionInteraction.TNT);
//                }
//            }
//            case END_CRYSTAL -> {
//                if (!gameRules.gameRuleValueBool(craftWorld, GameRule.CRYSTAL_GRIEFING)
//                        || !gameRules.gameRuleValueBool(craftWorld, GameRule.CRYSTAL_DAMAGE)) {
//                    e.setCancelled(true);
//                    world.explode(entity, Explosion.getDefaultDamageSource(world, entity),
//                            new NoExplosionBehavior(craftWorld, GameRule.CRYSTAL_GRIEFING, GameRule.CRYSTAL_DAMAGE),
//                            x, y, z, yield, true, Level.ExplosionInteraction.STANDARD);
//                }
//            }
//            case FIREBALL, SMALL_FIREBALL -> {
//                Projectile proj = (Projectile) e.getEntity();
//                if (proj.getShooter() instanceof Ghast
//                        && !gameRules.gameRuleValueBool(craftWorld, GameRule.BED_GRIEFING)
//                        || !gameRules.gameRuleValueBool(craftWorld, GameRule.BED_DAMAGE)) {
//                    e.setCancelled(true);
//                    world.explode(entity, Explosion.getDefaultDamageSource(world, entity),
//                            new NoExplosionBehavior(craftWorld, GameRule.GHAST_GRIEFING, GameRule.GHAST_DAMAGE),
//                            x, y, z, yield, true, Level.ExplosionInteraction.MOB);
//                }
//            }
//            case WITHER_SKULL -> {
//                if (!gameRules.gameRuleValueBool(craftWorld, GameRule.WITHER_SKULL_GRIEFING)
//                        || !gameRules.gameRuleValueBool(craftWorld, GameRule.WITHER_SKULL_DAMAGE)) {
//                    e.setCancelled(true);
//                    world.explode(entity, Explosion.getDefaultDamageSource(world, entity),
//                            new NoExplosionBehavior(craftWorld, GameRule.WITHER_SKULL_GRIEFING, GameRule.WITHER_SKULL_DAMAGE),
//                            x, y, z, yield, true, Level.ExplosionInteraction.STANDARD);
//                }
//            }
//            case WITHER -> {
//                if (!gameRules.gameRuleValueBool(craftWorld, GameRule.WITHER_GRIEFING)
//                        || !gameRules.gameRuleValueBool(craftWorld, GameRule.WITHER_DAMAGE)) {
//                    e.setCancelled(true);
//                    world.explode(entity, Explosion.getDefaultDamageSource(world, entity),
//                            new NoExplosionBehavior(craftWorld, GameRule.WITHER_GRIEFING, GameRule.WITHER_DAMAGE),
//                            x, y, z, yield, true, Level.ExplosionInteraction.STANDARD);
//                }
//            }
//        }
//    }
//
//
//    @EventHandler
//    public void onBlockExplosion(BlockExplodeEvent e) {
//        Level world = ((CraftWorld) e.getBlock().getLocation().getWorld()).getHandle();
//        double x = e.getBlock().getLocation().getX();
//        double y = e.getBlock().getLocation().getY();
//        double z = e.getBlock().getLocation().getZ();
//        float yield = e.getYield();
//        if (e.getBlock().getType().toString().contains("BED")
//                && !gameRules.gameRuleValueBool(e.getBlock().getWorld(), GameRule.BED_GRIEFING)
//                || !gameRules.gameRuleValueBool(e.getBlock().getWorld(), GameRule.BED_DAMAGE)) {
//            e.setCancelled(true);
//            world.explode(null, Explosion.getDefaultDamageSource(world, null),
//                    new NoExplosionBehavior(world.getWorld(), GameRule.BED_GRIEFING, GameRule.BED_DAMAGE),
//                    x, y, z, yield, false, Level.ExplosionInteraction.STANDARD);
//        }
//        else if (e.getBlock().getType() == Material.RESPAWN_ANCHOR
//                && !gameRules.gameRuleValueBool(e.getBlock().getWorld(), GameRule.RESPAWN_ANCHOR_GRIEFING)
//                || !gameRules.gameRuleValueBool(e.getBlock().getWorld(), GameRule.RESPAWN_ANCHOR_DAMAGE)) {
//            e.setCancelled(true);
//            world.explode(null, Explosion.getDefaultDamageSource(world, null),
//                    new NoExplosionBehavior(world.getWorld(), GameRule.RESPAWN_ANCHOR_GRIEFING, GameRule.RESPAWN_ANCHOR_DAMAGE),
//                    x, y, z, yield, false, Level.ExplosionInteraction.STANDARD);
//        }
//    }

}
