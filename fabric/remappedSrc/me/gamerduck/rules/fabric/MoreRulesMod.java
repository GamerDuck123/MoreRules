package me.gamerduck.rules.fabric;

import me.gamerduck.rules.common.GameRules;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerWorldEvents;
import net.minecraft.world.World;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

public class MoreRulesMod implements ModInitializer {
    public static final Logger LOGGER = LoggerFactory.getLogger("more-rules");

	public static final GameRules<World> gameRules = new GameRules<World>();

	@Override
	public void onInitialize() {
		ServerWorldEvents.LOAD.register((server, world) -> {
			gameRules.deSerializeData(world, new File("mods/MoreRules", world.getRegistryKey().toString() + ".json"));
		});
		ServerWorldEvents.UNLOAD.register((server, world) -> {
			gameRules.serializeData(world, new File("mods/MoreRules", world.getRegistryKey().toString() + ".json"));
		});
	}


}