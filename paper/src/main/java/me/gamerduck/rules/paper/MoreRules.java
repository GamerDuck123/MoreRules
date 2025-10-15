package me.gamerduck.rules.paper;

import me.gamerduck.rules.common.GameRules;
import me.gamerduck.rules.config.Config;
import me.gamerduck.rules.paper.commands.GameRuleCommand;
import me.gamerduck.rules.paper.events.*;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.WorldLoadEvent;
import org.bukkit.event.world.WorldUnloadEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.nio.file.Paths;

public class MoreRules extends JavaPlugin implements Listener {

    public static GameRules<World> gameRules;

    public MoreRules() {
        gameRules = new GameRules<World>();
        Config.load(Paths.get("plugins/MoreRules/morerules.properties"));
    }

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
        getServer().getPluginManager().registerEvents(new ExplosionEvents(), this);
        getServer().getPluginManager().registerEvents(new BlockChangeEvents(), this);
        getServer().getPluginManager().registerEvents(new EntityEvents(), this);
        getServer().getPluginManager().registerEvents(new PlayerBowEvents(this), this);
        getServer().getPluginManager().registerEvents(new HeadDroppingEvent(), this);

        GameRuleCommand.inject();

        Bukkit.getWorlds().forEach(w ->
            gameRules.deSerializeData(w, new File(Config.get("storage-path"), w.getUID().toString() + ".json")));
    }

    @Override
    public void onDisable() {
        Bukkit.getWorlds().forEach(w ->
                gameRules.serializeData(w, new File(Config.get("storage-path"), w.getUID().toString() + ".json")));
    }

    @EventHandler
    public void onWorldLoad(WorldLoadEvent e) {
        gameRules.deSerializeData(e.getWorld(), new File(Config.get("storage-path"), e.getWorld().getUID().toString() + ".json"));
    }

    @EventHandler
    public void onWorldUnLoad(WorldUnloadEvent e) {
        gameRules.serializeData(e.getWorld(), new File(Config.get("storage-path"), e.getWorld().getUID().toString() + ".json"));
    }
}
