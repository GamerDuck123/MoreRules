package me.gamerduck.rules.fabric;

import me.gamerduck.rules.MixinsVariable;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerWorldEvents;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

public class MoreRulesMod implements ModInitializer {
    public static final Logger LOGGER = LoggerFactory.getLogger("more-rules");

	@Override
	public void onInitialize() {
		ServerWorldEvents.LOAD.register((server, world) -> {
			MixinsVariable.gameRules.deSerializeData(world, new File("mods/MoreRules", world.toString() + ".json"));
		});
		ServerWorldEvents.UNLOAD.register((server, world) -> {
			MixinsVariable.gameRules.serializeData(world, new File("mods/MoreRules", world.toString() + ".json"));
		});
	}


}