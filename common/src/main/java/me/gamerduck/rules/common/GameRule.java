package me.gamerduck.rules.common;

import java.util.stream.Stream;

public enum GameRule {
    ENDERMEN_GRIEFING(Boolean.class, "doesEnderManGrief", true),
    CROP_TRAMPLE(Boolean.class, "doCropTrample", true),
    TNT_EXPLOSION(Boolean.class, "doesTntGrief", true),
    CRYSTAL_EXPLOSION(Boolean.class, "doesCrystalGrief", true),
    BED_EXPLOSION(Boolean.class, "doesBedGrief", true),
    RESPAWN_ANCHOR_EXPLOSION(Boolean.class, "doesRespawnAnchorGrief", true),
    CREEPER_GRIEFING(Boolean.class, "doesCreeperGrief", true),
    GHAST_GRIEFING(Boolean.class, "doesGhastGrief", true),
    CORAL_DECAY(Boolean.class, "doesCoralDecay", true),
    VILLAGER_WITCH_CONVERSIONS(Boolean.class, "doVillagerWitchConversion", true),
    VILLAGER_ZOMBIE_CONVERSIONS(Boolean.class, "doVillagerZombieConversion", true),
    ZOMBIE_VILLAGER_CONVERSIONS(Boolean.class, "doZombieVillagerConversion", true),
    PIG_PIGLIN_CONVERSIONS(Boolean.class, "doPigPiglinConversion", true),
    ANVIL_COST_TOO_MUCH(Boolean.class, "canAnvilCostTooMuch", true),
    SCULK_SPREADING(Boolean.class, "canSculkSpread", true),
    LIGHT_MELT_ICE(Boolean.class, "canLightMeltIce", true),
    LIGHT_MELT_SNOW(Boolean.class, "canLightMeltSnow", true),
    MOB_PICKUP(Boolean.class, "canMobsPickupItems", true),
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
        return Stream.of(values()).filter(rule -> rule.id().equalsIgnoreCase(id)).findFirst().orElseGet(null);
    }


}
