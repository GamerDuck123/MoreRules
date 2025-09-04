package me.gamerduck.rules.neoforged;

import com.mojang.logging.LogUtils;
import me.gamerduck.rules.common.GameRules;
import net.minecraft.world.level.Level;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.level.LevelEvent;
import org.slf4j.Logger;

import java.io.File;

import static me.gamerduck.rules.MixinsVariable.gameRules;

@Mod(MoreRulesMod.MODID)
public class MoreRulesMod {
    public static final String MODID = "morerules";
    public static final Logger LOGGER = LogUtils.getLogger();

    public MoreRulesMod() {
        NeoForge.EVENT_BUS.register(this);

//        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    @SubscribeEvent
    public void onWorldLoad(LevelEvent.Load event) {
        gameRules.deSerializeData((Level)event.getLevel(), new File("mods/MoreRules", event.getLevel().toString() + ".json"));
    }
    @SubscribeEvent
    public void onWorldUnload(LevelEvent.Unload event) {
        gameRules.serializeData((Level)event.getLevel(), new File("mods/MoreRules", event.getLevel().toString() + ".json"));
    }
}
