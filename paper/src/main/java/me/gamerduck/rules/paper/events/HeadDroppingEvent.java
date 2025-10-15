package me.gamerduck.rules.paper.events;

import com.destroystokyo.paper.profile.CraftPlayerProfile;
import com.mojang.authlib.properties.Property;
import me.gamerduck.rules.common.GameRule;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

import static me.gamerduck.rules.paper.MoreRules.gameRules;

public class HeadDroppingEvent implements Listener {

    private ItemStack createSkull(String name, String b64) {
        ItemStack tempHead = new ItemStack(Material.PLAYER_HEAD);
        tempHead.editMeta(SkullMeta.class, meta -> {
            UUID randomUUID = UUID.randomUUID();
            CraftPlayerProfile playerProfile = new CraftPlayerProfile(randomUUID, randomUUID.toString().substring(0, 15));
            playerProfile.setProperty("textures", new Property("textures", b64));
            meta.displayName(Component.text(name));
            meta.setPlayerProfile(playerProfile);
        });
        return tempHead;
    }

    @EventHandler
    public void onDeath(EntityDeathEvent e) {
        if (e.getEntity() instanceof Player || !gameRules.gameRuleValueBool(e.getEntity().getWorld(), GameRule.MOB_HEAD_DROP)) return;
        Double chance = gameRules.gameRuleValueDouble(e.getEntity().getWorld(), GameRule.MOB_HEAD_DROP_CHANCE);
        if (chance < 0 || chance >= ThreadLocalRandom.current().nextDouble(0, 100)) {
            ItemStack head = switch(e.getEntityType()) {
                //https://minecraft-heads.com/custom-heads/head/121338-allay
                case ALLAY -> createSkull("Allay", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvODkyZmQ1OTcwM2NkZmU3ZGI1ZWEwYjM1YjY3OTIzMDhiMzVmYzM3MzY4NzYwYWE5OTc3MjZhZTEyZThiZDY5NiJ9fX0=");
                //https://minecraft-heads.com/custom-heads/head/74256-armadillo
                case ARMADILLO -> createSkull("Armadillo", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYzljMWU5NmNlOTg1NzI1ZTIyZWQ2Y2NmMGY0YzQ4MTBjNzI5YTI1MzhiOTdiZGEwNmZhZWIzYjkyNzk5Yzg3OCJ9fX0=");

                case AXOLOTL -> {
                    yield switch(((Axolotl) e.getEntity()).getVariant()) {
                        //https://minecraft-heads.com/custom-heads/head/46121-axolotl-lucy
                        case LUCY -> createSkull("Lucy Axolotl", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvN2I5MTBmYmMyMTZmNzI0ZDI5NjU1MTU1YjJhMzg1OGE4MGYyMzRhMGNmZWQ2MDllMjJmYzY3MDY4M2FiNzc3YSJ9fX0=");
                        //https://minecraft-heads.com/custom-heads/head/46415-wild-axolotl
                        case WILD -> createSkull("Wild Axolotl", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNTQ2MWM0ZjJjYjFlZGE4ZTE0Y2Q1OWVmYjBiZjg5ZWEyMDc2ZjU0YTA4YjhiNmQwZGIwYmQwNzk3ZDk5YmVlOCJ9fX0=");
                        //https://minecraft-heads.com/custom-heads/head/46416-gold-axolotl
                        case GOLD -> createSkull("Gold Axolotl", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvM2E2NTM0YzMyYWI4NTYwMjg1NTg0NzgxMTVkOTA0ZDUyZjVmMTU0ZDgwZWNiOTAzYjg2NmIxOTA5OWEyNWU0NiJ9fX0=");
                        //https://minecraft-heads.com/custom-heads/head/46417-axolotl-cyan
                        case CYAN -> createSkull("Cyan Axolotl", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMWU4YzcwZTZhNTYxNjFlY2RjNmI3NTY3NDA3MGZiMzA4NzE0YTJiMmI3MGUxZTZkNzZiYzkyMGNlNmNlN2RlNiJ9fX0=");
                        //https://minecraft-heads.com/custom-heads/head/46414-axolotl-blue
                        case BLUE -> createSkull("Blue Axolotl", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMzU3YjMyYzI5MzNiZWJjZDY1MDJjYmY0MDZmN2RmOTk2YzhlNDZlN2M2Y2JmZWZjNWRjZGMyZmJmNWJiNTRiYiJ9fX0=");
                    };
                }
                //https://minecraft-heads.com/custom-heads/head/29192-bat
                case BAT -> createSkull("Bat", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZjhjMzJiODg0NDNhMjliNTVmMTY3ZmY1NGFmNDI1YzE0N2FmYTVmZWJmNjMwZjFhNWQ0MDg5MDhkMWRmNWI3ZiJ9fX0=");
                //https://minecraft-heads.com/custom-heads/head/31266-bee
                case BEE -> createSkull("Bee", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZmEyY2I3NGMxMzI0NWQzY2U5YmFjYzhiMTYwMGFmMDJmZDdjOTFmNTAxZmVhZjk3MzY0ZTFmOGI2ZjA0ZjQ3ZiJ9fX0=");
                //https://minecraft-heads.com/custom-heads/head/47778-blaze
                case BLAZE -> createSkull("Blaze", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYjIwNjU3ZTI0YjU2ZTFiMmY4ZmMyMTlkYTFkZTc4OGMwYzI0ZjM2Mzg4YjFhNDA5ZDBjZDJkOGRiYTQ0YWEzYiJ9fX0=");
                //https://minecraft-heads.com/custom-heads/head/87691-bogged
                case BOGGED -> createSkull("Bogged", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYTNiOTAwM2JhMmQwNTU2MmM3NTExOWI4YTYyMTg1YzY3MTMwZTkyODJmN2FjYmFjNGJjMjgyNGMyMWViOTVkOSJ9fX0=");
                //https://minecraft-heads.com/custom-heads/head/69108-breeze
                case BREEZE -> createSkull("Breeze", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYTI3NTcyOGFmN2U2YTI5Yzg4MTI1YjY3NWEzOWQ4OGFlOTkxOWJiNjFmZGMyMDAzMzdmZWQ2YWIwYzQ5ZDY1YyJ9fX0=");
                //https://minecraft-heads.com/custom-heads/head/102135-camel
                case CAMEL -> createSkull("Camel", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvN2ViNmFkOTA4YjhkNTE1NWJjNGQyNDkyNzFlZjYwODRkNDU1ZGQwZTcwYTQwMDJlYjE0OGY5ZTIwYjlkZWIyYyJ9fX0=");

                case CAT -> {
                    yield switch(((Cat) e.getEntity()).getCatType().key().toString()) {
                        //https://minecraft-heads.com/custom-heads/head/23862-cat-black
                        case "all_black" -> createSkull("Black Cat", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMjJjMWU4MWZmMDNlODJhM2U3MWUwY2Q1ZmJlYzYwN2UxMTM2MTA4OWFhNDdmMjkwZDQ2YzhhMmMwNzQ2MGQ5MiJ9fX0=");
                        //https://minecraft-heads.com/custom-heads/head/23858-cat-tuxedo
                        case "black" -> createSkull("Tuxedo Cat", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNGZkMTBjOGU3NWY2NzM5OGM0NzU4N2QyNWZjMTQ2ZjMxMWMwNTNjYzVkMGFlYWI4NzkwYmNlMzZlZTg4ZjVmOCJ9fX0=");
                        //https://minecraft-heads.com/custom-heads/head/23861-cat-british-short-hair
                        case "british_shorthair" -> createSkull("British Shorthair Cat", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNTM4OWUwZDVkM2U4MWY4NGI1NzBlMjk3ODI0NGIzYTczZTVhMjJiY2RiNjg3NGI0NGVmNWQwZjY2Y2EyNGVlYyJ9fX0=");
                        //https://minecraft-heads.com/custom-heads/head/113127-calico-cat
                        case "calico" -> createSkull("Calico Cat", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZTBlZWFhODY5ZjUzZmE5ODkxOThmNTU5NTUyMGFlYzkzOTU1MDlhYmE5OTM1OTZhODY2NTRiM2EwZjZjYTRhNiJ9fX0=");
                        //https://minecraft-heads.com/custom-heads/head/25037-cat-jellie
                        case "jellie" -> createSkull("Jellie Cat", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYTBkYjQxMzc2Y2E1N2RmMTBmY2IxNTM5ZTg2NjU0ZWVjZmQzNmQzZmU3NWU4MTc2ODg1ZTkzMTg1ZGYyODBhNSJ9fX0=");
                        //https://minecraft-heads.com/custom-heads/head/24186-cat-persian
                        case "persian" -> createSkull("Persian Cat", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZmY0MGM3NDYyNjBlZjkxYzk2YjI3MTU5Nzk1ZTg3MTkxYWU3Y2UzZDVmNzY3YmY4Yzc0ZmFhZDk2ODlhZjI1ZCJ9fX0=");
                        //https://minecraft-heads.com/custom-heads/head/23855-cat-ragdoll
                        case "ragdoll" -> createSkull("Ragdoll Cat", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZGM3YTQ1ZDI1ODg5ZTNmZGY3Nzk3Y2IyNThlMjZkNGU5NGY1YmMxM2VlZjAwNzk1ZGFmZWYyZTgzZTBhYjUxMSJ9fX0=");
                        //https://minecraft-heads.com/custom-heads/head/23859-cat-orange-tabby
                        case "red" -> createSkull("Red Cat", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMjExM2RiZDNjNmEwNzhhMTdiNGVkYjc4Y2UwN2Q4MzZjMzhkYWNlNTAyN2Q0YjBhODNmZDYwZTdjYTdhMGZjYiJ9fX0=");
                        //https://minecraft-heads.com/custom-heads/head/24185-cat-siamese
                        case "siamese" -> createSkull("Siamese Cat", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZDViM2Y4Y2E0YjNhNTU1Y2NiM2QxOTQ0NDk4MDhiNGM5ZDc4MzMyNzE5NzgwMGQ0ZDY1OTc0Y2M2ODVhZjJlYSJ9fX0=");
                        //https://minecraft-heads.com/custom-heads/head/23856-cat-tabby
                        case "tabby" -> createSkull("Tabby Cat", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZGUyOGQzMGRiM2Y4YzNmZTUwY2E0ZjI2ZjMwNzVlMzZmMDAzYWU4MDI4MTM1YThjZDY5MmYyNGM5YTk4YWUxYiJ9fX0=");
                        //https://minecraft-heads.com/custom-heads/head/23857-cat-white
                        case "white" -> createSkull("White Cat", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMjFkMTVhYzk1NThlOThiODlhY2E4OWQzODE5NTAzZjFjNTI1NmMyMTk3ZGQzYzM0ZGY1YWFjNGQ3MmU3ZmJlZCJ9fX0==");
                        default -> null;
                    };
                }
                //https://minecraft-heads.com/custom-heads/head/63921-cave-spider
                case CAVE_SPIDER -> createSkull("Cave Spider", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZWVjNTU3NDYwM2YzMDQ4ZjIxYWQ1YTNjOTRkOTcxMTU3MDYwMTFmZTZiYTY3NzgxMDkxYjhhOWFjMTBhZjU0ZiJ9fX0=");

                case CHICKEN -> {
                    yield switch(((Chicken) e.getEntity()).getVariant().key().toString()) {
                        //https://minecraft-heads.com/custom-heads/head/112538-chicken-cold
                        case "cold" -> createSkull("Cold Chicken", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYTkxMWZjZDhiMjI2YTk4ZjhiNmQ2MDVmZjI3YTg4ZWQ5NzIzMGJiNzE2NzhlNGEwMDY2OTA5NWQ2OWQzZjJiNCJ9fX0=");
                        //https://minecraft-heads.com/custom-heads/head/336-chicken-temperate
                        case "temperate" -> createSkull("Chicken", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMTYzODQ2OWE1OTljZWVmNzIwNzUzNzYwMzI0OGE5YWIxMWZmNTkxZmQzNzhiZWE0NzM1YjM0NmE3ZmFlODkzIn19fQ==");
                        //https://minecraft-heads.com/custom-heads/head/112544-chicken-warm
                        case "warm" -> createSkull("Warm Chicken", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYzUxYzc0ZjZkMWVkNmJlZmJlNjM3NmNhMzIyYjUzYmI2ZjY4ZTExZDFjZTM5YzZiY2VkZWEyOTgyNTk5MzY5MyJ9fX0=");
                        default -> null;
                    };
                }
                //https://minecraft-heads.com/custom-heads/head/45953-cod
                case COD -> createSkull("Cod", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNGZlZWZmNGI3ZmNmY2U2OGIwZjc0ZGYwZGIwYWQwYzAxZjczMDFkMGM2ZDg5MzY5OWI0MDJiZDUwYmIzNzZiMCJ9fX0=");

                case COW -> {
                    yield switch(((Cow) e.getEntity()).getVariant().key().toString()) {
                        //https://minecraft-heads.com/custom-heads/head/115919-cow-cold
                        case "cold" -> createSkull("Cold Cow", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYWQwNjg4ODVkMmRmYTNhMzA5MzYzYWYwYTg3ZGNhNmZiZjYyOWY3MTMwNzRmOTY4NjIxOWQ3YmE3YWY3MDA4ZCJ9fX0=");
                        //https://minecraft-heads.com/custom-heads/head/114346-cow-temperate
                        case "temperate" -> createSkull("Cow", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvODdlNzE5M2EwYzNmZjgyYzE4OTI3ZDVhNzMwMTVmMDU3ZDA4N2ZmOTJjNGJjZTE1NTdiZTQ2MjNkMzA5NTBmZiJ9fX0=");
                        //https://minecraft-heads.com/custom-heads/head/112543-cow-warm
                        case "warm" -> createSkull("Warm Cow", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMjNiMzM3NmZhZjI4MWMxNGJhNDcyZDJlN2Y2YjZkMDE2OWU0YmMyMzc0ODM1YTlmOGQwNzg4ZTA0ZDgxMzQzZCJ9fX0=");
                        default -> null;
                    };
                }
                //https://minecraft-heads.com/custom-heads/head/119545-creaking
                case CREAKING -> createSkull("Creaking", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNDViNmRjZGFlMzMwMjQ4MDAyZWRhM2VjZDhlN2FmYzUxZTAyZjZlYjlhYTE1ZGQ1MWE5NDcxZGU3ZWJjMTNjYyJ9fX0=");
                //In Minecraft
                case CREEPER -> new ItemStack(Material.CREEPER_HEAD);
                //https://minecraft-heads.com/custom-heads/head/16799-dolphin
                case DOLPHIN -> createSkull("Dolphin", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOGU5Njg4Yjk1MGQ4ODBiNTViN2FhMmNmY2Q3NmU1YTBmYTk0YWFjNmQxNmY3OGU4MzNmNzQ0M2VhMjlmZWQzIn19fQ==");
                //https://minecraft-heads.com/custom-heads/head/18144-donkey
                case DONKEY -> createSkull("Donkey", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNjNhOTc2YzA0N2Y0MTJlYmM1Y2IxOTcxMzFlYmVmMzBjMDA0YzBmYWY0OWQ4ZGQ0MTA1ZmNhMTIwN2VkYWZmMyJ9fX0=");
                //https://minecraft-heads.com/custom-heads/head/15967-drowned
                case DROWNED -> createSkull("Drowned", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYzNmN2NjZjYxZGJjM2Y5ZmU5YTYzMzNjZGUwYzBlMTQzOTllYjJlZWE3MWQzNGNmMjIzYjNhY2UyMjA1MSJ9fX0=");
                //https://minecraft-heads.com/custom-heads/head/4173-elder-guardian
                case ELDER_GUARDIAN -> createSkull("Elder Guardian", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZTkyMDg5NjE4NDM1YTBlZjYzZTk1ZWU5NWE5MmI4MzA3M2Y4YzMzZmE3N2RjNTM2NTE5OWJhZDMzYjYyNTYifX19");
                //In Minecraft
                case ENDER_DRAGON -> new ItemStack(Material.DRAGON_HEAD);
                //https://minecraft-heads.com/custom-heads/head/83741-enderman
                case ENDERMAN -> createSkull("Enderman", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOGExMDhhMGE3YTM4Nzg1OWYyYzQ0ZmI5NzAyY2Y3M2RiYWZlZTNlY2ZkYzRmNWRlZjQ2YzBkNjUxYjdhNDlmNyJ9fX0=");
                //https://minecraft-heads.com/custom-heads/head/2737-endermite
                case ENDERMITE -> createSkull("Endermite", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMTczMDEyN2UzYWM3Njc3MTIyNDIyZGYwMDI4ZDllNzM2OGJkMTU3NzM4YzhjM2NkZGVjYzUwMmU4OTZiZTAxYyJ9fX0=");
                //https://minecraft-heads.com/custom-heads/head/75756-evoker
                case EVOKER -> createSkull("Evoker", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvODA2YWMwMmZkOWRhYzk2NmI3ZTU4MDY3MzZiNmZlYjkwZTJmM2IwNTc3OTY5ZTY3MzI5MWI4MzA3YzFlZjhlNSJ9fX0=");

                case FOX -> {
                    yield switch(((Fox) e.getEntity()).getFoxType()) {
                        // https://minecraft-heads.com/custom-heads/head/5091-fox
                        case RED -> createSkull("Fox", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNDkxNWI5OThhZGVmZmNjZmY4NTk1Nzc0ZWE0Yjg0M2Q5YzE2NWU0Y2JjZGMyMWU2ZmQ0OTNhNjEwNzU3MDc1ZCJ9fX0=");
                        //https://minecraft-heads.com/custom-heads/head/26326-snow-fox
                        case SNOW -> createSkull("Snowy Fox", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNDE0MzYzNzdlYjRjNGI0ZTM5ZmIwZTFlZDg4OTlmYjYxZWUxODE0YTkxNjliOGQwODcyOWVmMDFkYzg1ZDFiYSJ9fX0=");
                    };
                }

                case FROG -> {
                    yield switch(((Frog) e.getEntity()).getVariant().key().toString()) {
                        //https://minecraft-heads.com/custom-heads/head/51366-cold-frog
                        case "cold" -> createSkull("Cold Frog", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMjdiY2NjYzEyNWE0MTEwNDM0YTg1YzQwYWRhMDM5ZDA1MGYxNGVmN2RiMzRhMzQ0NDA2NzMxMGY4Y2U2OTYwNiJ9fX0=");
                        //https://minecraft-heads.com/custom-heads/head/3383-frog
                        case "temperate" -> createSkull("Frog", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZTBiMTU0ZWJhYTk5Mzc0OWM3YWZkODljZGY2NzY4NDUyMzA1ZDZhYjJjNmMwYzFjYjFiOTEzOWJjMzZkOWRlNCJ9fX0=");
                        //https://minecraft-heads.com/custom-heads/head/51364-warm-frog
                        case "warm" -> createSkull("Warm Frog", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMWU5MzEyYjViMmJhYjlhZDUxZWE0YjZhNDA3ZDZkMzkwYmI1MDQzNDA4NzU3Yjk3NmE3NTU2ODk4YWM0M2RlMCJ9fX0=");
                        default -> null;
                    };
                }
                //https://minecraft-heads.com/custom-heads/head/117238-ghast
                case GHAST -> createSkull("Ghast", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNTA0ODQzNDIxYzIxOGQwNjM0NDU1ZmRiMWE2YzVmN2FlNWI4NTA5OGE1MGIxMmI5ZWQ5ZDkzMTBjODRkYzYxYiJ9fX0=");
                // In minecraft
                case GIANT -> new ItemStack(Material.ZOMBIE_HEAD);
                //https://minecraft-heads.com/custom-heads/head/56663-glow-squid
                case GLOW_SQUID -> createSkull("Glow Squid", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNGNiMDdkOTA1ODg4Zjg0NzIyNTJmOWNmYTM5YWEzMTdiYWJjYWQzMGFmMDhjZmU3NTFhZGVmYTcxNmIwMjAzNiJ9fX0=");
                //https://minecraft-heads.com/custom-heads/head/113148-goat
                case GOAT -> createSkull("Goat", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYWQxN2YzZGFiMDFiYWRmZGU3MDU2ZGJmMDhjMmYxYjRkOTJlZDIzY2ViNjEzY2FiNDViOGM3YzUyOTRmOGUwOSJ9fX0=");
                //https://minecraft-heads.com/custom-heads/head/91879-guardian
                case GUARDIAN -> createSkull("Guardian", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYjhlNzI1Nzc5YzIzNGM1OTBjY2U4NTRkYjVjMTA0ODVlZDhkOGEzM2ZhOWIyYmRjMzQyNGI2OGJiMTM4MGJlZCJ9fX0=");
                //https://minecraft-heads.com/custom-heads/head/117418-happy-ghast
                case HAPPY_GHAST -> createSkull("Happy Ghast", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYTFhMzZjYjkzZDAxNjc1YzQ2MjJkZDVjOGQ4NzIxMTA5MTFlYzEyYzM3MmU4OWFmYThiYTAzODYyODY3ZjZmYiJ9fX0=");
                //https://minecraft-heads.com/custom-heads/head/89340-hoglin
                case HOGLIN -> createSkull("Hoglin", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvN2FkN2I1YWViMjIwYzA3OWUzMTljZDcwYWM4ODAwZTgwNzc0YTkzMTNjMjJmMzhlNzdhZmI4OTk5OWU2ZWM4NyJ9fX0=");
                case HORSE -> {
                    yield switch(((Horse) e.getEntity()).getColor()) {
                        //https://minecraft-heads.com/custom-heads/head/49656-white-horse
                        case WHITE -> createSkull("White Horse", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOGIwM2ViMTNkNzk4ZjM4MjM3MDMzNTdhYTZlOGZkMjlkZmYzMDEwODcxYjRlNTRmOTI4ZTI3MGQ3NDEwOTY5YiJ9fX0=");
                        //https://minecraft-heads.com/custom-heads/head/7649-horse
                        case CREAMY -> createSkull("Creamy Horse", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMjY4NzJlNDdhNTgzZjljYjFhZjI5MDM5NGI2ZmM3M2Y3NTEwN2ZlMGFiZmZmZTJkZmUxZGUyM2JkYWVkMjJhIn19fQ==");
                        //https://minecraft-heads.com/custom-heads/head/12238-horse
                        case CHESTNUT -> createSkull("Chestnut Horse", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMWQ0ZjQ0OTg2YTVlMTBjNjZiNDk5ZGM5YmRiZmQ3ZTBkN2MzYjk3MWM2Yzg5ZWU1MzViMjE5OTZkNGQifX19");
                        //https://minecraft-heads.com/custom-heads/head/7280-brown-horse
                        case BROWN -> createSkull("Brown Horse", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYmVkZjczZWExMmNlNmJkOTBhNGFlOWE4ZDE1MDk2NzQ5Y2ZlOTE4MjMwZGM4MjliMjU4MWQyMjNiMWEyYTgifX19");
                        //https://minecraft-heads.com/custom-heads/head/6825-horse
                        case BLACK -> createSkull("Black Horse", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNjRiN2ZjNWY3YTlkZGZkZDFhYTc5MzE3NDEwZmMxOTI5ZjkxYmRkZjk4NTg1OTM4YTJhNTYxOTlhNjMzY2MifX19");
                        //https://minecraft-heads.com/custom-heads/head/7278-gray-horse
                        case GRAY -> createSkull("Gray Horse", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZDY2NzZjNGQ2ZjBmNWVkNjA2YTM1NmYzY2M1YTI5ZDE0YWFmZTY1NzIxYmExYTFhOTVjNWFjNGM1ZTIzOWU1In19fQ==");
                        //https://minecraft-heads.com/custom-heads/head/7279-dark-brown-horse
                        case DARK_BROWN -> createSkull("Dark Brown Horse", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMjY2MWYyM2ZiNzY2MjRmZmJhYmJkYTMxY2E0YTM4YjQwNGZlNjNlZjM3ZDRiYTRlNGM1NDQxYTIxZTNhNiJ9fX0=");
                    };
                }
                //https://minecraft-heads.com/custom-heads/head/41306-husk
                case HUSK -> createSkull("Husk", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYzA5NjE2NGY4MTk1MGE1Y2MwZTMzZTg3OTk5Zjk4Y2RlNzkyNTE3ZjRkN2Y5OWE2NDdhOWFlZGFiMjNhZTU4In19fQ==");
                //https://minecraft-heads.com/custom-heads/head/12360-illusioner
                case ILLUSIONER -> createSkull("Illusioner", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNTEyNTEyZTdkMDE2YTIzNDNhN2JmZjFhNGNkMTUzNTdhYjg1MTU3OWYxMzg5YmQ0ZTNhMjRjYmViODhiIn19fQ==");
                //https://minecraft-heads.com/custom-heads/head/341-iron-golem
                case IRON_GOLEM -> createSkull("Iron Golem", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvODkwOTFkNzllYTBmNTllZjdlZjk0ZDdiYmE2ZTVmMTdmMmY3ZDQ1NzJjNDRmOTBmNzZjNDgxOWE3MTQifX19");

                case LLAMA ->  {
                    // Has multiple types
                    yield switch(((Llama) e.getEntity()).getColor()) {
                        //https://minecraft-heads.com/custom-heads/head/26964-llama-creamy
                        case CREAMY -> createSkull("Creamy Llama", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMmE1ZjEwZTZlNjIzMmYxODJmZTk2NmY1MDFmMWMzNzk5ZDQ1YWUxOTAzMWExZTQ5NDFiNWRlZTBmZWZmMDU5YiJ9fX0=");
                        //https://minecraft-heads.com/custom-heads/head/6746-llama-white
                        case WHITE -> createSkull("White Llama", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZWI4YTgyNWI5M2UxZTdlM2ZmNGEzOGNlNGQzODhkMThmOGRjNzI0OTQyOTY5OTA0MTI2OTE2MWJjZjk4MjNlIn19fQ==");
                        //https://minecraft-heads.com/custom-heads/head/6822-llama-brown
                        case BROWN -> createSkull("Brown Llama", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvODE4Y2Q0NTdmYmFmMzI3ZmEzOWYxMGI1YjM2MTY2ZmQwMTgyNjQwMzY4NjUxNjRjMDJkOWU1ZmY1M2Y0NSJ9fX0=");
                        //https://minecraft-heads.com/custom-heads/head/3930-llama-light-gray
                        case GRAY -> createSkull("Gray Llama", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvY2YyNGU1NmZkOWZmZDcxMzNkYTZkMWYzZTJmNDU1OTUyYjFkYTQ2MjY4NmY3NTNjNTk3ZWU4MjI5OWEifX19");
                    };
                }
                //https://minecraft-heads.com/custom-heads/head/323-magma-cube
                case MAGMA_CUBE -> createSkull("Magma Cube", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMzg5NTdkNTAyM2M5MzdjNGM0MWFhMjQxMmQ0MzQxMGJkYTIzY2Y3OWE5ZjZhYjM2Yjc2ZmVmMmQ3YzQyOSJ9fX0=");
                //https://minecraft-heads.com/custom-heads/head/339-mooshroom
                case MOOSHROOM -> createSkull("Mooshroom", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZDBiYzYxYjk3NTdhN2I4M2UwM2NkMjUwN2EyMTU3OTEzYzJjZjAxNmU3YzA5NmE0ZDZjZjFmZTFiOGRiIn19fQ==");
                //https://minecraft-heads.com/custom-heads/head/3918-mule
                case MULE -> createSkull("Mule", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYTA0ODZhNzQyZTdkZGEwYmFlNjFjZTJmNTVmYTEzNTI3ZjFjM2IzMzRjNTdjMDM0YmI0Y2YxMzJmYjVmNWYifX19");
                //https://minecraft-heads.com/custom-heads/head/340-ocelot
                case OCELOT -> createSkull("Ocelot", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNTY1N2NkNWMyOTg5ZmY5NzU3MGZlYzRkZGNkYzY5MjZhNjhhMzM5MzI1MGMxYmUxZjBiMTE0YTFkYjEifX19");

                case PANDA -> {
                    yield switch(((Panda) e.getEntity()).getMainGene()) {
                        //https://minecraft-heads.com/custom-heads/head/23596-panda
                        case NORMAL -> createSkull("Panda", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZGNhMDk2ZWVhNTA2MzAxYmVhNmQ0YjE3ZWUxNjA1NjI1YTZmNTA4MmM3MWY3NGE2MzljYzk0MDQzOWY0NzE2NiJ9fX0=");
                        //https://minecraft-heads.com/custom-heads/head/23593-panda-lazy
                        case LAZY -> createSkull("Lazy Panda", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNzgxOGI2ODFjYWNlMWM2NDE5MTlmNTNlZGFkZWNiMTQyMzMwZDA4OWE4MjZiNTYyMTkxMzhjMzNiN2E1ZTBkYiJ9fX0=");
                        //https://minecraft-heads.com/custom-heads/head/23793-panda-worried
                        case WORRIED -> createSkull("Worried Panda", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYzhlOTIxYzU3ZTU0ZGQwNzMzN2ZmYmE2MjllNzJjYWYzODUwZDgzNmI3NjU2MmIxYmMzYmM1OTQ5ZjJkNDFkIn19fQ==");
                        //https://minecraft-heads.com/custom-heads/head/23597-panda-playful
                        case PLAYFUL -> createSkull("Playful Panda", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYjY0NjNlNjRjZTI5NzY0ZGIzY2I0NjgwNmNlZTYwNmFmYzI0YmRmMGNlMTRiNjY2MGMyNzBhOTZjNzg3NDI2In19fQ==");
                        //https://minecraft-heads.com/custom-heads/head/23594-panda-brown
                        case BROWN -> createSkull("Brown Panda", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYzVkMGQ0NWJmOTkyYjA3MmNmNWM1MTNlMDZiZWVmZThiZGM4MDljODE1MGYzZDE0Yjg4Mzc5NmE3Yjc0ZTQwNiJ9fX0=");
                        //https://minecraft-heads.com/custom-heads/head/23592-panda-weak
                        case WEAK -> createSkull("Sick Panda", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNWMyZDI1ZTk1NjMzN2Q4Mjc5MWZhMGU2NjE3YTQwMDg2ZjAyZDZlYmZiZmQ1YTY0NTk4ODljZjIwNmZjYTc4NyJ9fX0=");
                        //https://minecraft-heads.com/custom-heads/head/23595-panda-aggresive
                        case AGGRESSIVE -> createSkull("Aggressive Panda", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMmUyZjM5M2YzYjI4YWU1NTc4ZDY4ZWE3MmYzNGIwYWE0ZjVmMmM1ODNhMjJkODk4MzQ2N2I5OGM0YjIzZDg2YyJ9fX0=");
                    };
                }

                case PARROT -> {
                    yield switch(((Parrot) e.getEntity()).getVariant()) {
                        //https://minecraft-heads.com/custom-heads/head/6534-parrot-red
                        case RED -> createSkull("Red Parrot", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYTRiYThkNjZmZWNiMTk5MmU5NGI4Njg3ZDZhYjRhNTMyMGFiNzU5NGFjMTk0YTI2MTVlZDRkZjgxOGVkYmMzIn19fQ==");
                        //https://minecraft-heads.com/custom-heads/head/6821-parrot-blue
                        case BLUE -> createSkull("Blue Parrot", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYjc4ZTFjNWY0OGE3ZTEyYjI2Mjg1MzU3MWVmMWY1OTdhOTJlZjU4ZGE4ZmFhZmUwN2JiN2MwZTY5ZTkzIn19fQ==");
                        //https://minecraft-heads.com/custom-heads/head/6535-parrot-green
                        case GREEN -> createSkull("Green Parrot", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYWI5YTM2YzU1ODlmM2EyZTU5YzFjYWE5YjNiODhmYWRhNzY3MzJiZGI0YTc5MjYzODhhOGMwODhiYmJjYiJ9fX0=");
                        //https://minecraft-heads.com/custom-heads/head/6537-parrot
                        case CYAN -> createSkull("Cyan Parrot", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMmI5NGYyMzZjNGE2NDJlYjJiY2RjMzU4OWI5YzNjNGEwYjViZDVkZjljZDVkNjhmMzdmOGM4M2Y4ZTNmMSJ9fX0=");
                        //https://minecraft-heads.com/custom-heads/head/6536-parrot-gray
                        case GRAY -> createSkull("Gray Parrot", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvM2Q2ZjRhMjFlMGQ2MmFmODI0Zjg3MDhhYzYzNDEwZjFhMDFiYmI0MWQ3ZjRhNzAyZDk0NjljNjExMzIyMiJ9fX0=");
                    };
                }
                //https://minecraft-heads.com/custom-heads/head/120109-phantom
                case PHANTOM -> createSkull("Phantom", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYjExMDlkOWM2Nzg2Yzc0N2M4N2ZjMzZjOWEyNzFkNTFmZjIwMTYwM2MzOGQyNjE1YWM0ODAzOTA4NjgwZTk4OCJ9fX0=");

                case PIG ->  {
                    yield switch(((Pig) e.getEntity()).getVariant().key().toString()) {
                        //https://minecraft-heads.com/custom-heads/head/112536-pig-cold
                        case "cold" -> createSkull("Cold Pig", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYmExOGQ0MDQzY2Q2YzkwMzg2Njc4ODkxNGZkNTM0MzE1MjgxYWY5ZjI1OWUzNDgzN2UzZTE3NWU1NDVjMmVkZSJ9fX0=");
                        //https://minecraft-heads.com/custom-heads/head/337-pig-temperate
                        case "temperate" -> createSkull("Pig", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNjIxNjY4ZWY3Y2I3OWRkOWMyMmNlM2QxZjNmNGNiNmUyNTU5ODkzYjZkZjRhNDY5NTE0ZTY2N2MxNmFhNCJ9fX0=");
                        //https://minecraft-heads.com/custom-heads/head/112537-pig-warm
                        case "warm" -> createSkull("Warm Pig", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvN2JlYmExYTJkNTZlODRmOGU1MWZlZDY2NTlmMmNiN2MxNGZlZDQzODU5YWY1ODQ3Mzc4OTdiZjcwYzAzOTQ3NSJ9fX0=");
                        default -> null;
                    };
                }
                //In minecraft
                case PIGLIN -> new ItemStack(Material.PIGLIN_HEAD);
                //https://minecraft-heads.com/custom-heads/head/38372-piglin-brute
                case PIGLIN_BRUTE -> createSkull("Piglin Brute", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvM2UzMDBlOTAyNzM0OWM0OTA3NDk3NDM4YmFjMjllM2E0Yzg3YTg0OGM1MGIzNGMyMTI0MjcyN2I1N2Y0ZTFjZiJ9fX0=");
                //https://minecraft-heads.com/custom-heads/head/25149-pillager
                case PILLAGER -> createSkull("Pillager", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNGFlZTZiYjM3Y2JmYzkyYjBkODZkYjVhZGE0NzkwYzY0ZmY0NDY4ZDY4Yjg0OTQyZmRlMDQ0MDVlOGVmNTMzMyJ9fX0=");
                //https://minecraft-heads.com/custom-heads/head/5393-polar-bear
                case POLAR_BEAR -> createSkull("Polar Bear", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMjI0YWU5NGVmNTYyNTczMmIxNTJlOTAwMDgzYzNiMzk4ZmRlOTdiNDIyNjRhODFmOTliOTk5ZDk3OTEyYTc5MCJ9fX0=");
                //https://minecraft-heads.com/custom-heads/head/3489-pufferfish
                case PUFFERFISH -> createSkull("Pufferfish", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNmRmOGMzMTY5NjI5NDliYTNiZTQ0NWM5NGViZjcxNDEwODI1MmQ0NjQ1OWI2NjExMGY0YmMxNGUwZTFiNTlkYyJ9fX0=");

                case RABBIT ->  {
                    yield switch(((Rabbit) e.getEntity()).getRabbitType()) {
                        //https://minecraft-heads.com/custom-heads/head/49677-brown-rabbit
                        case BROWN -> createSkull("Brown Rabbit", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYzFkYjM4ZWYzYzFhMWQ1OWY3NzlhMGNkOWY5ZTYxNmRlMGNjOWFjYzc3MzRiOGZhY2MzNmZjNGVhNDBkMDIzNSJ9fX0=");
                        //https://minecraft-heads.com/custom-heads/head/49681-white-rabbit
                        case WHITE -> createSkull("White Rabbit", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYTBkY2RkYzIzNjk3MmVkY2Q0OGU4MjViNmIwMDU0YjdiNmUxYTc4MWU2ZjEyYWUwNGMxNGEwNzgyN2NhOGRjYyJ9fX0=");
                        //https://minecraft-heads.com/custom-heads/head/49678-black-rabbit
                        case BLACK -> createSkull("Black Rabbit", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMTlhNjc1ZWRiM2NiYTBmMzQzNmFlOTQ3M2NmMDM1OTJiN2E0OWQzODgxMzU3OTA4NGQ2MzdlNzY1OTk5OWI4ZSJ9fX0=");
                        //https://minecraft-heads.com/custom-heads/head/49674-black-and-white-rabbit
                        case BLACK_AND_WHITE -> createSkull("Black and White Rabbit", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMzJmMzllMGE2MDMzODZjYTFlZTM2MjM2ZTBiNDkwYTE1NDdlNmUyYTg5OTExNjc0NTA5MDM3ZmI2ZjcxMTgxMCJ9fX0=");
                        //https://minecraft-heads.com/custom-heads/head/49676-gold-rabbit
                        case GOLD -> createSkull("Gold Rabbit", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMmE2MzYxZmVhMjRiMTExZWQ3OGMxZmVmYzI5NTIxMmU4YTU5YjBjODhiNjU2MDYyNTI3YjE3YTJkNzQ4OWM4MSJ9fX0=");
                        //https://minecraft-heads.com/custom-heads/head/49680-toast-rabbit
                        case SALT_AND_PEPPER -> createSkull("Salt and Pepper Rabbit", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZDUxMzJmMjg4NjVjZTRkYzA2MDYzNjkzOTQ0NzQ0MGZmMDQzMzk3N2YzYjZjNzNhZGM2NzRkYjJmYjRkYjY5NiJ9fX0=");
                        //https://minecraft-heads.com/custom-heads/head/49679-killer-bunny
                        case THE_KILLER_BUNNY -> createSkull("The Killer Rabbit", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZWY5ODMzN2I4MjQyMjI5ZDk1ZGEyMzA5MDc1NTc4OTc3OGIxOGJmNWQwN2Q2MWY2MjBjZGJkYmJkMjlmYTYxNSJ9fX0=");
                    };
                }
                //https://minecraft-heads.com/custom-heads/head/28196-ravager
                case RAVAGER -> createSkull("Ravager", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvY2QyMGJmNTJlYzM5MGEwNzk5Mjk5MTg0ZmM2NzhiZjg0Y2Y3MzJiYjFiZDc4ZmQxYzRiNDQxODU4ZjAyMzVhOCJ9fX0=");
                //https://minecraft-heads.com/custom-heads/head/31623-salmon-head
                case SALMON -> createSkull("Salmon", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMjBlYTlhMjIzNjIwY2RiNTRiMzU3NDEzZDQzYmQ4OWM0MDA4YmNhNmEyMjdmM2I3ZGI5N2Y3NzMzZWFkNWZjZiJ9fX0=");

                case SHEEP ->  {
                    yield switch(((Sheep) e.getEntity()).getColor()) {
                        //https://minecraft-heads.com/custom-heads/head/334-sheep-white
                        case WHITE -> createSkull("White Sheep", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZjMxZjljY2M2YjNlMzJlY2YxM2I4YTExYWMyOWNkMzNkMThjOTVmYzczZGI4YTY2YzVkNjU3Y2NiOGJlNzAifX19");
                        //https://minecraft-heads.com/custom-heads/head/3899-sheep-orange
                        case ORANGE -> createSkull("Orange Sheep", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZjA5ODM5N2EyNzBiNGMzZDJiMWU1NzRiOGNmZDNjYzRlYTM0MDkwNjZjZWZlMzFlYTk5MzYzM2M5ZDU3NiJ9fX0=");
                        //https://minecraft-heads.com/custom-heads/head/3900-sheep-magenta
                        case MAGENTA -> createSkull("Magenta Sheep", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMTgzNjU2NWM3ODk3ZDQ5YTcxYmMxODk4NmQxZWE2NTYxMzIxYTBiYmY3MTFkNDFhNTZjZTNiYjJjMjE3ZTdhIn19fQ==");
                        //https://minecraft-heads.com/custom-heads/head/3916-sheep-light-blue
                        case LIGHT_BLUE -> createSkull("Light Blue Sheep", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMWM4YTk3YTM4ODU2NTE0YTE2NDEzZTJjOTk1MjEyODlmNGM1MzYzZGM4MmZjOWIyODM0Y2ZlZGFjNzhiODkifX19");
                        //https://minecraft-heads.com/custom-heads/head/3902-sheep-yellow
                        case YELLOW -> createSkull("Yellow Sheep", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMjZhNDExMmRmMWU0YmNlMmE1ZTI4NDE3ZjNhYWZmNzljZDY2ZTg4NWMzNzI0NTU0MTAyY2VmOGViOCJ9fX0=");
                        //https://minecraft-heads.com/custom-heads/head/3903-sheep-lime
                        case LIME -> createSkull("Lime Sheep", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOTJhMjQ0OGY1OGE0OTEzMzI0MzRlODVjNDVkNzg2ZDg3NDM5N2U4MzBhM2E3ODk0ZTZkOTI2OTljNDJiMzAifX19");
                        //https://minecraft-heads.com/custom-heads/head/3915-sheep-pink
                        case PINK -> createSkull("Pink Sheep", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMmFjNzRhMmI5YjkxNDUyZTU2ZmExZGRhNWRiODEwNzc4NTZlNDlmMjdjNmUyZGUxZTg0MWU1Yzk1YTZmYzVhYiJ9fX0=");
                        //https://minecraft-heads.com/custom-heads/head/3905-sheep-gray
                        case GRAY -> createSkull("Gray Sheep", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNDI4N2ViNTAxMzkxZjI3NTM4OWYxNjZlYzlmZWJlYTc1ZWM0YWU5NTFiODhiMzhjYWU4N2RmN2UyNGY0YyJ9fX0=");
                        //https://minecraft-heads.com/custom-heads/head/3906-sheep-light-gray
                        case LIGHT_GRAY -> createSkull("Light Gray Sheep", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvY2UxYWM2ODM5OTNiZTM1NTEyZTFiZTMxZDFmNGY5OGU1ODNlZGIxNjU4YTllMjExOTJjOWIyM2I1Y2NjZGMzIn19fQ==");
                        //https://minecraft-heads.com/custom-heads/head/3907-sheep-cyan
                        case CYAN -> createSkull("Cyan Sheep", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNDZmNmM3ZTdmZDUxNGNlMGFjYzY4NTkzMjI5ZTQwZmNjNDM1MmI4NDE2NDZlNGYwZWJjY2NiMGNlMjNkMTYifX19");
                        //https://minecraft-heads.com/custom-heads/head/3908-sheep-purple
                        case PURPLE -> createSkull("Purple Sheep", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYWU1Mjg2N2FmZWYzOGJiMTRhMjZkMTQyNmM4YzBmMTE2YWQzNDc2MWFjZDkyZTdhYWUyYzgxOWEwZDU1Yjg1In19fQ==");
                        //https://minecraft-heads.com/custom-heads/head/3909-sheep-blue
                        case BLUE -> createSkull("Blue Sheep", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZDllYzIyODE4ZDFmYmZjODE2N2ZiZTM2NzI4YjI4MjQwZTM0ZTE2NDY5YTI5MjlkMDNmZGY1MTFiZjJjYTEifX19");
                        //https://minecraft-heads.com/custom-heads/head/3910-sheep-brown
                        case BROWN -> createSkull("Brown Sheep", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYTU1YWQ2ZTVkYjU2OTJkODdmNTE1MTFmNGUwOWIzOWZmOWNjYjNkZTdiNDgxOWE3Mzc4ZmNlODU1M2I4In19fQ==");
                        //https://minecraft-heads.com/custom-heads/head/3914-sheep-green
                        case GREEN -> createSkull("Green Sheep", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYTAxNzIxNWM3ZjhkYjgyMDQwYWEyYzQ3Mjk4YjY2NTQxYzJlYjVmN2Y5MzA0MGE1ZDIzZDg4ZjA2ODdkNGIzNCJ9fX0=");
                        //https://minecraft-heads.com/custom-heads/head/118008-sheep-red
                        case RED -> createSkull("Red Sheep", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMjFjMjUyYWEwNmIzODQwZmY2NGIxODI1MDVjMWFhOGVhYWY2YWZhYzk2NzQ3OWQyOGI3ZGNmZWQ1MzZlNGQ5NiJ9fX0=");
                        //https://minecraft-heads.com/custom-heads/head/3913-sheep-black
                        case BLACK -> createSkull("Black Sheep", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMzI2NTIwODNmMjhlZDFiNjFmOWI5NjVkZjFhYmYwMTBmMjM0NjgxYzIxNDM1OTUxYzY3ZDg4MzY0NzQ5ODIyIn19fQ==");
                        case null -> null;
                    };
                }

                case SHULKER -> {
                    yield switch(((Shulker) e.getEntity()).getColor()) {
                        //https://minecraft-heads.com/custom-heads/head/6436-shulker-white
                        case WHITE -> createSkull("White Shulker", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNmI5NGIwYWNiMzE3N2I0Y2RiMDE3ZmUzMWNkNWMyNDcyNjJkZWY1M2JmODMzODFjNmM4MmQ3MmM1NmFjIn19fQ==");
                        //https://minecraft-heads.com/custom-heads/head/6440-shulker-orange
                        case ORANGE -> createSkull("Orange Shulker", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZjU3MTFkOTU0YmQ1NjIzNmQxYTlmOTliZTg4MGM1ZDM4OTkwZGY2YmVmNzJlNzNmNzQ1YjA0OTk1ZGJmNiJ9fX0=");
                        //https://minecraft-heads.com/custom-heads/head/6441-shulker-magenta
                        case MAGENTA -> createSkull("Magenta Shulker", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNjlmZTI5MTY3NmM3YjNmOTZmM2NkZjYzYzQ3YjUzZmI0NWQ3M2JkNmZlMmNlMjJkZTEwNzQ5ZWIxNDI2YSJ9fX0=");
                        //https://minecraft-heads.com/custom-heads/head/6443-shulker-light-blue
                        case LIGHT_BLUE -> createSkull("Light Blue Shulker", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYzIxY2RlYzJjZjRlYmVlZjM1ZDU4YjE4NGI4MzI1OThiYzg5MGEwYWU1YzJkNTRlZTliZTU4NmQwIn19fQ==");
                        //https://minecraft-heads.com/custom-heads/head/6435-shulker-yellow
                        case YELLOW -> createSkull("Yellow Shulker", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOGNiZjU1ODY4MzZiN2IzNDI5MzJlMWQyM2VmYzI0OTBjZjU5YzY5YWNjZjFlMDVlOWVkNTc2Y2FlZDhiNzg3NyJ9fX0=");
                        //https://minecraft-heads.com/custom-heads/head/6442-shulker-lime
                        case LIME -> createSkull("Lime Shulker", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMTcxNDQ5NTdjMmM1YmJmZTQ0N2Y0YjJkMzZhMjQ2ZWExYjAyM2RhNGNiZDFhYTJkYmJiMTVlOTQ5ODEyNDgifX19");
                        //https://minecraft-heads.com/custom-heads/head/6439-shulker-pink
                        case PINK -> createSkull("Pink Shulker", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvM2Y4NDdjMTJkNTdmZTY1NTVlOGY5YjQ3ZTU2MmVjZjE2ODNmYjZjMzVmOTJjZTJjZWQyYWU2OGRlNjI4NzUwIn19fQ==");
                        //https://minecraft-heads.com/custom-heads/head/6445-shulker-gray
                        case GRAY -> createSkull("Gray Shulker", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYTc5ODQzMmZiZWIyYmMyZDc1N2IxZDNjM2IzNTU4ZTY5OTAzOTJkZDA5MWVhNGVmMzgxYjJlMDE5Yzk0NjIifX19");
                        //https://minecraft-heads.com/custom-heads/head/6437-shulker-light-gray
                        case LIGHT_GRAY -> createSkull("Light Gray Shulker", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYmZhMTdkNDFlYTE4M2JlZTUzZDU0NmM3YmVjNGNjZjZhNTRkNGY1MDhmZGU2ZWJmM2U1ZDM4OGQ0Y2JlYWNiOSJ9fX0=");
                        //https://minecraft-heads.com/custom-heads/head/6446-shulker-cyan
                        case CYAN -> createSkull("Cyan Shulker", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMjZmMTczZDRlM2U4YmQ2ZjliZTBiZjliNzQ1YmQxY2M3YTI5ZmY4MzZlZDJkNDg2YzViOTkyOTJjODVjYyJ9fX0=");
                        //https://minecraft-heads.com/custom-heads/head/38317-shulker
                        case PURPLE -> createSkull("Shulker", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNTM3YTI5NGY2YjdiNGJhNDM3ZTVjYjM1ZmIyMGY0Njc5MmU3YWMwYTQ5MGE2NjEzMmE1NTcxMjRlYzVmOTk3YSJ9fX0=");
                        //https://minecraft-heads.com/custom-heads/head/6448-shulker-blue
                        case BLUE -> createSkull("Blue Shulker", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOTQzMDk2NmQ1Y2ViYmQ3ODcxNDc2OTlhMjk3NDM3NTFiM2NlNGJiODE0ZTJkYjU2NGZlOTIxNDJkMTE5Y2QxIn19fQ==");
                        //https://minecraft-heads.com/custom-heads/head/6447-shulker-brown
                        case BROWN -> createSkull("Brown Shulker", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYzk2ZDdmYjg3NDQ3ZmZjMDU0ZmIxMDliODRkNjIyNWQ0MTAyOWIxZTY3MTBjN2RlNTdmNjYxYWVmYTZmIn19fQ==");
                        //https://minecraft-heads.com/custom-heads/head/6444-shulker-green
                        case GREEN -> createSkull("Green Shulker", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMzMxYWJlMmJiMmI5MTQxZjEwMThlYjE1MzlkMmNmZWMxM2Q5MjMxYjUzYTQ2Y2FmOGRjMjFhYTI3MDUwNDkifX19");
                        //https://minecraft-heads.com/custom-heads/head/6438-shulker-red
                        case RED -> createSkull("Red Shulker", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNTk5OTQwNDA0MzNhZjBmMDE1YmU0ZDY5NjhjM2Q1NWUwNDRjOThkYWMyYzRjNmE2ZWEwZWZhYzdhNmRkYiJ9fX0=");
                        //https://minecraft-heads.com/custom-heads/head/6449-shulker-black
                        case BLACK -> createSkull("Black Shulker", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYzU0NTVhYWFkYjIzMTdiNWYyOWU5ODk4MWFhNTdmNTc5NTcwNTA2OWQ4ZjQxNWMwZDY4YTkyYTc5MTQxM2IzYSJ9fX0=");
                        case null -> null;
                    };
                }
                //https://minecraft-heads.com/custom-heads/head/3936-silverfish
                case SILVERFISH -> createSkull("Silverfish", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZGE5MWRhYjgzOTFhZjVmZGE1NGFjZDJjMGIxOGZiZDgxOWI4NjVlMWE4ZjFkNjIzODEzZmE3NjFlOTI0NTQwIn19fQ==");
                //In Minecraft
                case SKELETON -> new ItemStack(Material.SKELETON_SKULL);
                //https://minecraft-heads.com/custom-heads/head/6013-skeleton-horse
                case SKELETON_HORSE -> createSkull("Skeleton Horse", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNDdlZmZjZTM1MTMyYzg2ZmY3MmJjYWU3N2RmYmIxZDIyNTg3ZTk0ZGYzY2JjMjU3MGVkMTdjZjg5NzNhIn19fQ==");
                //https://minecraft-heads.com/custom-heads/head/2747-slime
                case SLIME -> createSkull("Slime", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvODZjMjdiMDEzZjFiZjMzNDQ4NjllODFlNWM2MTAwMjdiYzQ1ZWM1Yjc5NTE0ZmRjOTZlMDFkZjFiN2UzYTM4NyJ9fX0=");
                //https://minecraft-heads.com/custom-heads/head/60630-sniffer
                case SNIFFER -> createSkull("Sniffer", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvM2Q2YzlmNDM1MTBjYjkwZDI0NDkzZTA3YjdjZjhjYTlmNTQxMzJkMDlhMjU3ZjIwYjcwNDgwMjJlM2IxYjcwNyJ9fX0=");
                // https://minecraft-heads.com/custom-heads/head/109991-snow-golem
                case SNOW_GOLEM -> createSkull("Snow Golem", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZTZmMjBhZWM1MjhjMzk2OGRkODE2NGY5ZDkzMzZiMDgxYjNhMmM3ZWNmMTg5Y2Y3M2RmNmY5MjVlNWE0ZWQxNCJ9fX0=");
                //https://minecraft-heads.com/custom-heads/head/317-spider
                case SPIDER -> createSkull("Spider", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvY2Q1NDE1NDFkYWFmZjUwODk2Y2QyNThiZGJkZDRjZjgwYzNiYTgxNjczNTcyNjA3OGJmZTM5MzkyN2U1N2YxIn19fQ==");
                //https://minecraft-heads.com/custom-heads/head/338-squid
                case SQUID -> createSkull("Squid", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMDE0MzNiZTI0MjM2NmFmMTI2ZGE0MzRiODczNWRmMWViNWIzY2IyY2VkZTM5MTQ1OTc0ZTljNDgzNjA3YmFjIn19fQ==");
                //https://minecraft-heads.com/custom-heads/head/3244-stray
                case STRAY -> createSkull("Stray", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNzhkZGY3NmU1NTVkZDVjNGFhOGEwYTVmYzU4NDUyMGNkNjNkNDg5YzI1M2RlOTY5ZjdmMjJmODVhOWEyZDU2In19fQ==");
                //https://minecraft-heads.com/custom-heads/head/35431-strider
                case STRIDER -> createSkull("Strider", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMThhOWFkZjc4MGVjN2RkNDYyNWM5YzA3NzkwNTJlNmExNWE0NTE4NjY2MjM1MTFlNGM4MmU5NjU1NzE0YjNjMSJ9fX0=");
                //https://minecraft-heads.com/custom-heads/head/51348-tadpole
                case TADPOLE ->  createSkull("Tadpole", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOTg3MDM1ZjUzNTIzMzRjMmNiYTZhYzRjNjVjMmI5MDU5NzM5ZDZkMGU4MzljMWRkOThkNzVkMmU3Nzk1Nzg0NyJ9fX0=");

                case TRADER_LLAMA -> {
                    yield switch(((TraderLlama) e.getEntity()).getColor()) {
                        //https://minecraft-heads.com/custom-heads/head/26963-trader-llama-creamy
                        case CREAMY -> createSkull("Creamy Trader Llama", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZTg5YTJlYjE3NzA1ZmU3MTU0YWIwNDFlNWM3NmEwOGQ0MTU0NmEzMWJhMjBlYTMwNjBlM2VjOGVkYzEwNDEyYyJ9fX0=");
                        //https://minecraft-heads.com/custom-heads/head/26961-trader-llama-white
                        case WHITE -> createSkull("White Trader Llama", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNzA4N2E1NTZkNGZmYTk1ZWNkMjg0NGYzNTBkYzQzZTI1NGU1ZDUzNWZhNTk2ZjU0MGQ3ZTc3ZmE2N2RmNDY5NiJ9fX0=");
                        //https://minecraft-heads.com/custom-heads/head/26960-trader-llama-brown
                        case BROWN -> createSkull("Brown Trader Llama", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvODQyNDc4MGIzYzVjNTM1MWNmNDlmYjViZjQxZmNiMjg5NDkxZGY2YzQzMDY4M2M4NGQ3ODQ2MTg4ZGI0Zjg0ZCJ9fX0=");
                        //https://minecraft-heads.com/custom-heads/head/2696    2-trader-llama-light-gray
                        case GRAY -> createSkull("Gray Trader Llama", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYmU0ZDhhMGJjMTVmMjM5OTIxZWZkOGJlMzQ4MGJhNzdhOThlZTdkOWNlMDA3MjhjMGQ3MzNmMGEyZDYxNGQxNiJ9fX0=");
                    };
                }
                //https://minecraft-heads.com/custom-heads/head/118226-bucket-of-tropical-fish
                case TROPICAL_FISH -> createSkull("Tropical Fish", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYTU1NDMxZTliMDBlYzMzYjcwM2UxNjRkMTI1YzU1YmRhYjMwYTBiODc5YzBmMjBkZDAwYmYwYmUwMTI0OWUyNiJ9fX0=");

                //https://minecraft-heads.com/custom-heads/head/17929-sea-turtle
                case TURTLE ->  createSkull("Turtle", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMGE0MDUwZTdhYWNjNDUzOTIwMjY1OGZkYzMzOWRkMTgyZDdlMzIyZjlmYmNjNGQ1Zjk5YjU3MThhIn19fQ==");
                //https://minecraft-heads.com/custom-heads/head/3080-vex
                case VEX ->  createSkull("Vex", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYzJlYzVhNTE2NjE3ZmYxNTczY2QyZjlkNWYzOTY5ZjU2ZDU1NzVjNGZmNGVmZWZhYmQyYTE4ZGM3YWI5OGNkIn19fQ==");
                case VILLAGER -> createSkull("Villager", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYjczMTBiMDNlZmJlZmRkZDRjMzlmYjhkZWUzOGRiMzUwNGE1YTZlNzkwMGI1MDZjMDQzNTE4M2MxMGVkZjc1NSJ9fX0=");
                //https://minecraft-heads.com/custom-heads/head/3079-vindicator
                case VINDICATOR ->  createSkull("Vindicator", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNmRlYWVjMzQ0YWIwOTViNDhjZWFkNzUyN2Y3ZGVlNjFiMDYzZmY3OTFmNzZhOGZhNzY2NDJjODY3NmUyMTczIn19fQ==");
                //https://minecraft-heads.com/custom-heads/head/25676-wandering-trader
                case WANDERING_TRADER ->  createSkull("Wandering Trader", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNWYxMzc5YTgyMjkwZDdhYmUxZWZhYWJiYzcwNzEwZmYyZWMwMmRkMzRhZGUzODZiYzAwYzkzMGM0NjFjZjkzMiJ9fX0=");
                //https://minecraft-heads.com/custom-heads/head/47665-warden
                case WARDEN ->  createSkull("Warden", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNTRhZmIzMDc5NDQzM2YzMmM5N2IwMmMxZDIyODMyNzMzM2YyNTlmNTZjNjhjMDIwMjdiMmExNmRmYjRhYTY2ZCJ9fX0=");
                //https://minecraft-heads.com/custom-heads/head/3864-witch
                case WITCH ->  createSkull("Witch", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMjBlMTNkMTg0NzRmYzk0ZWQ1NWFlYjcwNjk1NjZlNDY4N2Q3NzNkYWMxNmY0YzNmODcyMmZjOTViZjlmMmRmYSJ9fX0=");
                //https://minecraft-heads.com/custom-heads/head/320-wither
                case WITHER ->  createSkull("Wither", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvY2RmNzRlMzIzZWQ0MTQzNjk2NWY1YzU3ZGRmMjgxNWQ1MzMyZmU5OTllNjhmYmI5ZDZjZjVjOGJkNDEzOWYifX19");
                //In Minecraft
                case WITHER_SKELETON -> new ItemStack(Material.WITHER_SKELETON_SKULL);

                case WOLF -> {
                    yield switch(((Wolf) e.getEntity()).getVariant().key().toString()) {
                        //https://minecraft-heads.com/custom-heads/head/89355-ashen-wolf
                        case "ashen" -> createSkull("Ashen Wolf", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNDcwNjA4ZDQzNmY1YWEyMTMwNDdiNTRhNDQzYTA5MWRkMmQ3MWNiY2Y2ZTczMzM4YjIzNTJjZTExMjUxZGZmMSJ9fX0=");
                        //https://minecraft-heads.com/custom-heads/head/89358-black-wolf
                        case "black" -> createSkull("Black Wolf", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZjY5NzY1MDMzNDBlYjAzMGFiNzdmMzAwYTJlZmExMmYyMTc5ZGYyY2E5Y2EwZDI5MmQ5ODNjNTBlZDkzMzMwOSJ9fX0=");
                        //https://minecraft-heads.com/custom-heads/head/101523-chestnut-wolf
                        case "chestnut" -> createSkull("Chestnut Wolf", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMTNhODhhNmQ4NTdmNDkzMDNkNDNkYzU5ZjA5ZmViZTBjNTQ5MzAzNmU0ZjU1NTQ1MDhiOWFhODk4NWVmZDczYSJ9fX0=");
                        //https://minecraft-heads.com/custom-heads/head/89364-pale-wolf
                        case "pale" -> createSkull("Pale Wolf", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNzJjZTE2MWUzMjA1ZDg5ZTdlNGQzZWMwNGQyNWFiZmVhNjIzMWEyMjc3YTJiZDc2ZjQ2OTNmNGNlNjE4OWEyZCJ9fX0=");
                        //https://minecraft-heads.com/custom-heads/head/89367-rusty-wolf
                        case "rusty" -> createSkull("Rusty Wolf", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZWUwM2M4ZjFkZjk2YTdhMzBlMTE4YzM1ZTlkNzE3NGYxZDlhNjQ5NWQ0ZDg4NjkzYWQwZWVlZTNlNzM0MjNjMCJ9fX0=");
                        //https://minecraft-heads.com/custom-heads/head/89370-snowy-wolf
                        case "snowy" -> createSkull("Snowy Wolf", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNTBiOGU4YTE1ZGZmMTZiZTQ5NGM2ODdmMTM3OTNlZTM1YWM5OTJhMmNhY2ZmMjVmMzI3YmQyNDA0MTY4NTFmMiJ9fX0=");
                        //https://minecraft-heads.com/custom-heads/head/89373-striped-wolf
                        case "spotted" -> createSkull("Spotted Wolf", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYjNmMmRlNTI1MzI4ZTBhNzE2MmI0MGZiYTJhNWJjNmFhNGRlZWJiZGQ2OTk2MTE0NjkxY2UxZDdlZjUzN2NjZiJ9fX0=");
                        //https://minecraft-heads.com/custom-heads/head/96962-woods-wolf
                        case "woods" -> createSkull("Woods Wolf", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMWY3MmJiODk5ODY5Yjc2ZTFhMmIwZDUxMzczNmI4ZDZiZGM1MTM4ZjM3NGE4NTc3ZTFjNWM3NmI3MmZkNDIxOCJ9fX0=");
                        default -> throw new IllegalStateException("Unexpected value: " + ((Wolf) e.getEntity()).getVariant().key().toString());
                    };
                }
                //https://minecraft-heads.com/custom-heads/head/35932-zoglin
                case ZOGLIN ->  createSkull("Zoglin", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZTY3ZTE4NjAyZTAzMDM1YWQ2ODk2N2NlMDkwMjM1ZDg5OTY2NjNmYjllYTQ3NTc4ZDNhN2ViYmM0MmE1Y2NmOSJ9fX0=");

                case ZOMBIE -> new ItemStack(Material.ZOMBIE_HEAD);
                //https://minecraft-heads.com/custom-heads/head/2913-zombie-horse
                case ZOMBIE_HORSE ->  createSkull("Zombie Horse", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZDIyOTUwZjJkM2VmZGRiMThkZTg2ZjhmNTVhYzUxOGRjZTczZjEyYTZlMGY4NjM2ZDU1MWQ4ZWI0ODBjZWVjIn19fQ==");
                //https://minecraft-heads.com/custom-heads/head/31556-zombie-villager
                case ZOMBIE_VILLAGER -> createSkull("Zombie Villager", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYzQ1YzExZTAzMjcwMzU2NDljYTA2MDBlZjkzODkwMGUyNWZkMWUzODAxNzQyMmJjOTc0MGU0Y2RhMmNiYTg5MiJ9fX0=");
                //https://minecraft-heads.com/custom-heads/head/35073-zombified-piglin
                case ZOMBIFIED_PIGLIN ->  createSkull("Zombified Piglin", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvN2VhYmFlY2M1ZmFlNWE4YTQ5Yzg4NjNmZjQ4MzFhYWEyODQxOThmMWEyMzk4ODkwYzc2NWUwYThkZTE4ZGE4YyJ9fX0=");

                default -> null;
            };
            if (head != null) e.getDrops().add(head);
        }
    }
}
