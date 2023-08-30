package me.gamerduck.rules.common;

import java.util.HashMap;
import java.util.stream.Stream;

public class RuleWorld<W> {

    public HashMap<GameRule, Object> values = new HashMap<>();

    public W world;

    public RuleWorld(W world) {
        loadDefaults();
        this.world = world;
    }
    public RuleWorld() {
    }

    public W world(W world) {return this.world = world;}
    public W world() {return this.world;}

    public Object value(GameRule rule) {
        if (!values.containsKey(rule)) values.put(rule, rule.defaultValue());
        return values.get(rule);
    }
    public Boolean valueBool(GameRule rule) {
        return (Boolean) value(rule);
    }
    public Integer valueInt(GameRule rule) {
        return (Integer) value(rule);
    }
    public Double valueDouble(GameRule rule) {
        return (Double) value(rule);
    }
    public Float valueFloat(GameRule rule) {
        return (Float) value(rule);
    }

    public void value(GameRule rule, Object value) {
        values.put(rule, value);
    }

    public void loadDefaults() {
        Stream.of(GameRule.values()).forEach(rule -> values.put(rule, rule.defaultValue()));
    }

    public void loadData(HashMap<GameRule, Object> data) {
        this.values.putAll(data);
    }

}
