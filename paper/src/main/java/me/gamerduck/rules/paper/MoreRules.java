package me.gamerduck.rules.paper;

import me.gamerduck.rules.common.GameRules;
import me.gamerduck.rules.paper.commands.GameRuleCommand;
import me.gamerduck.rules.paper.events.BlockChangeEvents;
import me.gamerduck.rules.paper.events.EntityEvents;
import me.gamerduck.rules.paper.events.ExplosionEvents;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.WorldLoadEvent;
import org.bukkit.event.world.WorldUnloadEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.awt.*;
import java.io.File;

import static net.kyori.adventure.text.minimessage.MiniMessage.miniMessage;


public class MoreRules extends JavaPlugin implements Listener {

    public static GameRules<World> gameRules;

    public MoreRules() {
        gameRules = new GameRules<World>();
    }

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
        getServer().getPluginManager().registerEvents(new ExplosionEvents(), this);
        getServer().getPluginManager().registerEvents(new BlockChangeEvents(), this);
        getServer().getPluginManager().registerEvents(new EntityEvents(), this);
        GameRuleCommand.inject();

        Bukkit.getWorlds().forEach(w ->
            gameRules.deSerializeData(w, new File(getDataFolder() + "/worlds", w.getUID().toString() + ".json")));
    }

    @Override
    public void onDisable() {
        Bukkit.getWorlds().forEach(w ->
                gameRules.serializeData(w, new File(getDataFolder() + "/worlds", w.getUID().toString() + ".json")));
    }

    @EventHandler
    public void onWorldLoad(WorldLoadEvent e) {
        Bukkit.getWorlds().forEach(w ->
                gameRules.deSerializeData(w, new File(getDataFolder() + "/worlds", w.getUID().toString() + ".json")));
    }

    @EventHandler
    public void onWorldUnLoad(WorldUnloadEvent e) {
        Bukkit.getWorlds().forEach(w ->
                gameRules.serializeData(w, new File(getDataFolder() + "/worlds", w.getUID().toString() + ".json")));
    }
}
