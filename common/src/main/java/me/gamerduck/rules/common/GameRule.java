package me.gamerduck.rules.common;

import java.util.stream.Stream;
public enum GameRule {
    ENDERMEN_GRIEFING(Boolean.class, "doesEnderManGrief", true),
    CROP_TRAMPLE(Boolean.class, "doCropTrample", true),
    TNT_GRIEFING(Boolean.class, "doesTntGrief", true),
    CRYSTAL_GRIEFING(Boolean.class, "doesCrystalGrief", true),
    BED_GRIEFING(Boolean.class, "doesBedGrief", true),
    RESPAWN_ANCHOR_GRIEFING(Boolean.class, "doesRespawnAnchorGrief", true),
    CREEPER_GRIEFING(Boolean.class, "doesCreeperGrief", true),
    GHAST_GRIEFING(Boolean.class, "doesGhastGrief", true),
    CORAL_DECAY(Boolean.class, "doesCoralDecay", true),
    VILLAGER_WITCH_CONVERSIONS(Boolean.class, "doVillagerWitchConversion", true),
    VILLAGER_ZOMBIE_CONVERSIONS(Boolean.class, "doVillagerZombieConversion", true),
    ZOMBIE_VILLAGER_CONVERSIONS(Boolean.class, "doZombieVillagerConversion", true),
    PIG_PIGLIN_CONVERSIONS(Boolean.class, "doPigPiglinConversion", true),
    SCULK_SPREADING(Boolean.class, "canSculkSpread", true),
    LIGHT_MELT_ICE(Boolean.class, "canLightMeltIce", true),
    LIGHT_MELT_SNOW(Boolean.class, "canLightMeltSnow", true),
    MOB_PICKUP(Boolean.class, "canMobsPickupItems", true),
    ZOMBIE_BREAK_DOORS(Boolean.class, "canZombiesBreakDoors", true),
    TNT_DAMAGE(Boolean.class, "doesTntDamage", true),
    CRYSTAL_DAMAGE(Boolean.class, "doesCrystalDamage", true),
    BED_DAMAGE(Boolean.class, "doesBedDamage", true),
    RESPAWN_ANCHOR_DAMAGE(Boolean.class, "doesRespawnAnchorDamage", true),
    CREEPER_DAMAGE(Boolean.class, "doesCreeperDamage", true),
    GHAST_DAMAGE(Boolean.class, "doesGhastDamage", true),
    WITHER_GRIEFING(Boolean.class, "doesWitherGrief", true),
    WITHER_DAMAGE(Boolean.class, "doesWitherDamage", true),
    WITHER_SKULL_GRIEFING(Boolean.class, "doesWitherSkullGrief", true),
    WITHER_SKULL_DAMAGE(Boolean.class, "doesWitherSkullDamage", true),
    DRAGON_GRIEFING(Boolean.class, "doesDragonGrief", true),
    DRAGON_DAMAGE(Boolean.class, "doesDragonDamage", true),
    SLIMES_SPLIT(Boolean.class, "doSlimesSplit", true),
    SILVERFISH_INFEST(Boolean.class, "doSilverfishInfest", true),
    LEAVES_DECAY(Boolean.class, "doLeavesDecay", true),
    PHANTOMS_SPAWN(Boolean.class, "doPhantomsSpawn", true),
    FIRE_DAMAGE(Boolean.class, "doesFireDamage", true),
    MOSS_SPREAD(Boolean.class, "doesMossSpread", true),
    GRASS_SPREAD(Boolean.class, "doesGrassSpread", true),
    MYCELIUM_SPREAD(Boolean.class, "doesMyceliumSpread", true),
    VINES_SPREAD(Boolean.class, "doVinesSpread", true),
    PROJECTILE_PICKUP(Boolean.class, "canPickupProjectiles", true),
    PLAYER_HUNGER(Boolean.class, "doesPlayerHunger", true),
    PLAYER_FALL_DAMAGE(Boolean.class, "doesPlayerFallDamage", true),
    PLAYER_DROWN(Boolean.class, "doesPlayerDrown", true),
    PET_FRIENDLY_FIRE(Boolean.class, "doPetFriendlyFire", true),
    ITEMS_DESPAWN(Boolean.class, "doItemsDespawn", true),
    NEW_DRAGON_EGGS(Boolean.class, "doDragonEggsDropOnRespawn", false),
    ENDER_PEARL_DAMAGE(Boolean.class, "doEnderPearlsDamage", true),
    INFINITY_NEED_ARROW(Boolean.class, "doesInfinityNeedArrow", true),
    PLAYERS_HEAD_DROP(Boolean.class, "doPlayerHeadsDrop", false),
    PLAYERS_HEAD_DROP_CHANCE(Double.class, "playerHeadDropChance", -1.0d),
    MOB_HEAD_DROP(Boolean.class, "doMobHeadDrop", false),
    MOB_HEAD_DROP_CHANCE(Double.class, "mobHeadDropChance", -1.0d),
    OBSIDIAN_GENERATE(Boolean.class, "doesObsidianGenerate", true),
    STONE_GENERATE(Boolean.class, "doesStoneGenerate", true),
    COBBLESTONE_GENERATE(Boolean.class, "doesCobblestoneGenerate", true),
    BASALT_GENERATE(Boolean.class, "doesBasaltGenerate", true),
    ;

    private Class<?> type;
    private String id;
    private Object defaultValue;

    <T> GameRule(Class<T> type, String id, T defaultValue) {
        this.type = type;
        this.id = id;
        this.defaultValue = defaultValue;
    }

    public Class<?> type() {return type;}
    public String id() {return id;}
    public Object defaultValue() {return defaultValue;}

    public static GameRule fromId(String id) {
        return Stream.of(values()).filter(rule -> rule.id().equalsIgnoreCase(id)).findFirst().orElseGet(() -> null);
    }


}
