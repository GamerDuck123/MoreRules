package me.gamerduck.rules.sponge;

import com.google.inject.Inject;
import me.gamerduck.rules.common.GameRules;
import me.gamerduck.rules.config.Config;
import org.apache.logging.log4j.Logger;
import org.spongepowered.api.Server;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.lifecycle.*;
import org.spongepowered.api.event.world.LoadWorldEvent;
import org.spongepowered.api.event.world.UnloadWorldEvent;
import org.spongepowered.api.world.server.ServerWorld;
import org.spongepowered.api.world.server.WorldManager;
import org.spongepowered.plugin.PluginContainer;
import org.spongepowered.plugin.builtin.jvm.Plugin;

import java.io.File;
import java.lang.invoke.MethodHandles;
import java.nio.file.Paths;

@Plugin("morerules")
public class MoreRules {

    @Inject
    private Logger logger;
    @Inject
    private PluginContainer pluginContainer;

    public static GameRules<ServerWorld> gameRules;


    @Listener
    public void onServerStart(final StartedEngineEvent<Server> event) {
        gameRules = new GameRules<ServerWorld>();
        Config.load(Paths.get("plugins/MoreRules/morerules.properties"));

        Sponge.server().worldManager().worlds().forEach(w ->
                gameRules.deSerializeData(w, new File(Config.get("storage-path"), w.uniqueId().toString() + ".json")));

        Sponge.eventManager().registerListeners(pluginContainer , this, MethodHandles.lookup());
    }

    @Listener
    public void onServerStop(final StoppingEngineEvent<Server> event) {
        Sponge.server().worldManager().worlds().forEach(w ->
                gameRules.serializeData(w, new File(Config.get("storage-path"), w.uniqueId().toString() + ".json")));
    }

    @Listener
    public void refresh(RefreshGameEvent event) {
        Config.load(Paths.get("plugins/MoreRules/morerules.properties"));
    }

    @Listener
    public void onWorldLoad(LoadWorldEvent e) {
        gameRules.deSerializeData(e.world(), new File(Config.get("storage-path"), e.world().uniqueId().toString() + ".json"));
    }

    @Listener
    public void onWorldUnLoad(UnloadWorldEvent e) {
        gameRules.serializeData(e.world(), new File(Config.get("storage-path"), e.world().uniqueId().toString() + ".json"));
    }
}
