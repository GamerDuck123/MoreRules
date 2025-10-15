package me.gamerduck.rules.paper.events;

import me.gamerduck.rules.common.GameRule;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import static me.gamerduck.rules.paper.MoreRules.gameRules;

public class PlayerBowEvents implements Listener {

    private final JavaPlugin plugin;
    public PlayerBowEvents(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onBowUse(PlayerInteractEvent e) {
        Player player = e.getPlayer();

        if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
            ItemStack bow = e.getItem();
            if (bow == null || bow.getType() != Material.BOW) return;

            if (bow.containsEnchantment(Enchantment.INFINITY)
                    && !gameRules.gameRuleValueBool(e.getPlayer().getWorld(), GameRule.INFINITY_NEED_ARROW)
                    && !player.getInventory().contains(Material.ARROW)) {

                // Add a temporary arrow so the player can draw AND shoot
                ItemStack fakeArrow = new ItemStack(Material.ARROW);
                ItemMeta meta = fakeArrow.getItemMeta();
                meta.setDisplayName("§7[Fake Infinity Arrow]");
                fakeArrow.setItemMeta(meta);
                player.getInventory().addItem(fakeArrow);
            }
        }
    }

    @EventHandler
    public void onBowShoot(EntityShootBowEvent e) {
        if (!(e.getEntity() instanceof Player player) ||
                gameRules.gameRuleValueBool(player.getWorld(), GameRule.INFINITY_NEED_ARROW)) return;
        ItemStack bow = e.getBow();
        if (bow == null) return;

        // Only apply to Infinity bows
        if (!bow.containsEnchantment(Enchantment.INFINITY)) return;

        Bukkit.getScheduler().runTaskLater(plugin, () -> {
            removeFakeArrow(player);
        }, 1L);
    }

    @EventHandler
    public void onItemSwitch(PlayerItemHeldEvent e) {
        Player player = e.getPlayer();

        // Clean up fake arrow if the player switches off the bow before shooting
        ItemStack oldItem = player.getInventory().getItem(e.getPreviousSlot());
        if (!gameRules.gameRuleValueBool(player.getWorld(), GameRule.INFINITY_NEED_ARROW)
                && oldItem != null && oldItem.getType() == Material.BOW
                && oldItem.containsEnchantment(Enchantment.INFINITY)) {
            removeFakeArrow(player);
        }
    }


    @EventHandler
    public void onItemSwitch(PlayerDropItemEvent e) {
        ItemStack droppedItem = e.getItemDrop().getItemStack();
        if (!gameRules.gameRuleValueBool(e.getPlayer().getWorld(), GameRule.INFINITY_NEED_ARROW)
                && droppedItem != null && droppedItem.getType() == Material.BOW
                && droppedItem.containsEnchantment(Enchantment.INFINITY)) {
            removeFakeArrow(e.getPlayer());
        }
    }

    private void removeFakeArrow(Player player) {
        Inventory inv = player.getInventory();

        for (int i = 0; i < inv.getSize(); i++) {
            ItemStack item = inv.getItem(i);
            if (item != null
                    && item.getType() == Material.ARROW
                    && item.hasItemMeta()
                    && "§7[Fake Infinity Arrow]".equals(item.getItemMeta().getDisplayName())) {
                inv.clear(i);
                break;
            }
        }
    }
}
