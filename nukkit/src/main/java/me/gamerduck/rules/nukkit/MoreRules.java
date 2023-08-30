package me.gamerduck.rules.nukkit;

import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.level.LevelLoadEvent;
import cn.nukkit.event.level.LevelUnloadEvent;
import cn.nukkit.level.Level;
import cn.nukkit.plugin.PluginBase;
import me.gamerduck.rules.common.GameRules;

import java.io.File;


public class MoreRules extends PluginBase implements Listener {

    public static GameRules<Level> gameRules;

    public MoreRules() {
    }

    @Override
    public void onEnable() {
        gameRules = new GameRules<Level>();
        getServer().getPluginManager().registerEvents(this, this);

        getServer().getLevels().forEach((a, w) ->
            gameRules.deSerializeData(w, new File(getDataFolder() + "/worlds", w.getName() + ".json")));
    }

    @Override
    public void onDisable() {
        getServer().getLevels().forEach((a, w) ->
                gameRules.serializeData(w, new File(getDataFolder() + "/worlds", w.getName() + ".json")));
    }

    @EventHandler
    public void onWorldLoad(LevelLoadEvent e) {
        Level level = e.getLevel();
        gameRules.deSerializeData(level, new File(getDataFolder() + "/worlds", level.getName() + ".json"));
    }

    @EventHandler
    public void onWorldUnLoad(LevelUnloadEvent e) {
        Level level = e.getLevel();
        gameRules.serializeData(level, new File(getDataFolder() + "/worlds", level.getName() + ".json"));
    }
}
