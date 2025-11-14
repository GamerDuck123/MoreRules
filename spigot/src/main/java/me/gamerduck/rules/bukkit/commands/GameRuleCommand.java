package me.gamerduck.rules.bukkit.commands;

import me.gamerduck.rules.common.GameRule;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static me.gamerduck.rules.bukkit.MoreRules.gameRules;

public class GameRuleCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length < 1) {
            sender.sendMessage("Usage: /gamerule <rule> [value]");
            return true;
        }

        String ruleId = args[0];

        GameRule customRule = GameRule.fromId(ruleId);
        if (customRule != null) {
            if (args.length == 1) {
                boolean value = (boolean) gameRules.gameRuleValue(getWorld(sender), customRule);
                sender.sendMessage(String.format("Gamerule %s is currently set to: %s", ruleId, value));
                return true;
            }
            try {
                Object value;
                if (customRule.defaultValue() instanceof Integer) value = Integer.parseInt(args[1]);
                else if (customRule.defaultValue() instanceof Double) value = Double.parseDouble(args[1]);
                else if (customRule.defaultValue() instanceof Float) value = Float.parseFloat(args[1]);
                else value = Boolean.parseBoolean(args[1]);

                gameRules.gameRuleValue(getWorld(sender), customRule, value);
                sender.sendMessage(String.format("Gamerule %s is now set to: %s", ruleId, value));
                return true;
            } catch (Exception e) {
                sender.sendMessage(String.format("Improper value for that gamerule", ruleId));
                return true;
            }
        }

        World world = getWorld(sender);
        if (world == null) {
            sender.sendMessage("§cCould not resolve a world for this command.");
            return true;
        }

        if (args.length == 1) {
            String val = world.getGameRuleValue(ruleId);
            if (val == null) {
                sender.sendMessage("Unknown gamerule: " + ruleId);
            } else {
                sender.sendMessage(String.format("Gamerule %s is currently set to: %s", ruleId, val));
            }
            return true;
        }

        world.setGameRuleValue(ruleId, args[1]);
        sender.sendMessage(String.format("Gamerule %s is now set to: %s", ruleId, args[1]));
        return true;
    }

    private World getWorld(CommandSender sender) {
        if (sender instanceof Player) return ((Player) sender).getWorld();
        return Bukkit.getWorlds().get(0); // default to first world for console
    }
}
