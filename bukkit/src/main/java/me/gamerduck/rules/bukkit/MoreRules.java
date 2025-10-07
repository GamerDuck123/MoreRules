package me.gamerduck.rules.bukkit;

import me.gamerduck.rules.bukkit.commands.GameRuleCommand;
import me.gamerduck.rules.bukkit.commands.GameRuleCompletions;
import me.gamerduck.rules.bukkit.events.BlockChangeEvents;
import me.gamerduck.rules.bukkit.events.EntityEvents;
import me.gamerduck.rules.bukkit.events.ExplosionEvents;
import me.gamerduck.rules.common.GameRules;
import me.gamerduck.rules.config.Config;
import me.lucko.commodore.Commodore;
import me.lucko.commodore.CommodoreProvider;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.PluginCommand;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.WorldLoadEvent;
import org.bukkit.event.world.WorldUnloadEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.nio.file.Paths;

public class MoreRules extends JavaPlugin implements Listener {

    public static GameRules<World> gameRules;

    @Override
    public void onEnable() {
        gameRules = new GameRules<World>();
        Config.load(Paths.get("plugins/MoreRules/morerules.properties"));

        getServer().getPluginManager().registerEvents(this, this);
        getServer().getPluginManager().registerEvents(new ExplosionEvents(), this);
        getServer().getPluginManager().registerEvents(new BlockChangeEvents(), this);
        getServer().getPluginManager().registerEvents(new EntityEvents(), this);

        PluginCommand cmd = getCommand("gamerule");
        cmd.setExecutor(new GameRuleCommand());

        if (CommodoreProvider.isSupported()) {
            Commodore commodore = CommodoreProvider.getCommodore(this);
            GameRuleCompletions.register(commodore, cmd);
        }
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