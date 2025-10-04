package me.gamerduck.rules.fabric;

import com.mojang.brigadier.CommandDispatcher;
import me.gamerduck.rules.config.Config;
import me.gamerduck.rules.mixin.MixinsVariable;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerWorldEvents;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.nio.file.Paths;

public class MoreRulesMod implements ModInitializer {
    public static final Logger LOGGER = LoggerFactory.getLogger("more-rules");

	@Override
	public void onInitialize() {
        Config.load(Paths.get("mods/MoreRules/morerules.properties"));

		ServerWorldEvents.LOAD.register((server, world) -> {
			MixinsVariable.gameRules.deSerializeData(world, new File(Config.get("storage-path"), world.toString() + ".json"));
		});
		ServerWorldEvents.UNLOAD.register((server, world) -> {
			MixinsVariable.gameRules.serializeData(world, new File(Config.get("storage-path"), world.toString() + ".json"));
		});
	}


}