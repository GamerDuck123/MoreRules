package me.gamerduck.rules.nukkit.events;

import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.block.BlockFadeEvent;
import me.gamerduck.rules.common.GameRule;

import static me.gamerduck.rules.nukkit.MoreRules.gameRules;

public class BlockUpdateEvents implements Listener {

    @EventHandler
    public void blockMelt(BlockFadeEvent e) {
        if (e.getBlock().getName().toUpperCase().contains("ICE")) {
            e.setCancelled(!gameRules.gameRuleValueBool(e.getBlock().getLevel(), GameRule.LIGHT_MELT_ICE));
        } else if (e.getBlock().getName().toUpperCase().contains("SNOW")) {
            e.setCancelled(!gameRules.gameRuleValueBool(e.getBlock().getLevel(), GameRule.LIGHT_MELT_SNOW));
        } else if (e.getBlock().getName().toUpperCase().contains("CORAL")) {
            e.setCancelled(!gameRules.gameRuleValueBool(e.getBlock().getLevel(), GameRule.CORAL_DECAY));
        }
    }

}
