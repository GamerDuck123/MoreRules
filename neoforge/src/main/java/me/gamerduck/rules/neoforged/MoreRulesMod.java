package me.gamerduck.rules.neoforged;

import com.mojang.logging.LogUtils;
import me.gamerduck.rules.config.Config;
import net.minecraft.world.level.Level;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.level.LevelEvent;
import org.slf4j.Logger;

import java.io.File;
import java.nio.file.Paths;

import static me.gamerduck.rules.mixin.MixinsVariable.gameRules;


@Mod(MoreRulesMod.MODID)
public class MoreRulesMod {
    public static final String MODID = "morerules";
    public static final Logger LOGGER = LogUtils.getLogger();

    public MoreRulesMod() {
        NeoForge.EVENT_BUS.register(this);
        Config.load(Paths.get("mods/MoreRules/morerules.properties"));
    }

    @SubscribeEvent
    public void onWorldLoad(LevelEvent.Load event) {
        gameRules.deSerializeData((Level)event.getLevel(), new File(Config.get("storage-path"), event.getLevel().toString() + ".json"));
    }
    @SubscribeEvent
    public void onWorldUnload(LevelEvent.Unload event) {
        gameRules.serializeData((Level)event.getLevel(), new File(Config.get("storage-path"), event.getLevel().toString() + ".json"));
    }
}
