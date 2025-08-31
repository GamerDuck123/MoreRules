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

// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(MoreRulesMod.MODID)
public class MoreRulesMod {
    // Define mod id in a common place for everything to reference
    public static final String MODID = "test";
    // Directly reference a slf4j logger
    public static final Logger LOGGER = LogUtils.getLogger();

    public static final GameRules<Level> gameRules = new GameRules<Level>();
    public MoreRulesMod() {
        NeoForge.EVENT_BUS.register(this);

//        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    @SubscribeEvent
    public void onWorldLoad(LevelEvent.Load event) {
//        gameRules.deSerializeData(event.getLevel(), new File("mods/MoreRules", world.getRegistryKey().toString() + ".json"));
    }
    @SubscribeEvent
    public void onWorldUnload(LevelEvent.Unload event) {
//        gameRules.serializeData(event.getLevel(), new File("mods/MoreRules", event.getLevel().registryAccess().getRegistryKey().toString() + ".json"));
    }
}
