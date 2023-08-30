package me.gamerduck.rules.common;

import com.google.gson.*;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.util.HashMap;

public class GameRules<W> {

    private final Gson gson = new GsonBuilder()
            .registerTypeHierarchyAdapter(RuleWorld.class, new RuleWorldSerializer())
            .enableComplexMapKeySerialization()
            .setPrettyPrinting().create();
    private final HashMap<W, RuleWorld<W>> worldsCache = new HashMap<>();

    public HashMap<W, RuleWorld<W>> worldsCache() {return worldsCache;}

    public RuleWorld<W> loadData(W world, HashMap<GameRule, Object> data) {
        RuleWorld w = new RuleWorld<W>(world);
        w.loadData(data);
        worldsCache.put(world, w);
        return worldsCache.get(world);
    }

    public RuleWorld<W> loadDefaults(W world) {
        RuleWorld w = new RuleWorld<W>(world);
        w.loadDefaults();
        worldsCache.put(world, w);
        return worldsCache.get(world);
    }

    public RuleWorld<W> serializeData(W world, File file) {
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        try {
            var writer = Files.newBufferedWriter(file.toPath());
            gson.toJson(worldsCache.get(world), writer);
            writer.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return worldsCache.remove(world);
    }

    public RuleWorld<W> deSerializeData(W world, File file) {
        if (!file.exists())
            return loadDefaults(world);
        try {
            var reader = Files.newBufferedReader(file.toPath());
            worldsCache.put(world, gson.fromJson(reader, RuleWorld.class));
            reader.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return worldsCache.get(world);
    }

    public Object gameRuleValue(W world, GameRule gameRule) {
        return worldsCache.get(world).value(gameRule);
    }
    public Boolean gameRuleValueBool(W world, GameRule gameRule) {
        return worldsCache.get(world).valueBool(gameRule);
    }
    public Integer gameRuleValueInt(W world, GameRule gameRule) {
        return worldsCache.get(world).valueInt(gameRule);
    }
    public Float gameRuleValueFloat(W world, GameRule gameRule) {
        return worldsCache.get(world).valueFloat(gameRule);
    }
    public Double gameRuleValueDouble(W world, GameRule gameRule) {
        return worldsCache.get(world).valueDouble(gameRule);
    }

    public Object gameRuleValue(W world, GameRule gameRule, Object value) {
        worldsCache.get(world).value(gameRule, value);
        return value;
    }


    private class RuleWorldSerializer implements JsonSerializer<RuleWorld<W>>, JsonDeserializer<RuleWorld<W>> {

        @Override
        public RuleWorld<W> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            var obj = json.getAsJsonObject();
            var array = obj.get("rules").getAsJsonArray();
            RuleWorld<W> world = new RuleWorld<>();
            HashMap<GameRule, Object> tempValues = new HashMap<GameRule, Object>();
            array.forEach(element -> {
                var ruleObj = element.getAsJsonObject();
                String gameRule = ruleObj.get("gameRule").getAsString();
                String valueType = ruleObj.get("valueType").getAsString();
                String value = ruleObj.get("value").getAsString();
                Object finalValue = value;
                if (valueType.contains("Boolean") || valueType.contains("boolean")) finalValue = Boolean.valueOf(value);
                else if (valueType.contains("Integer") || valueType.contains("int")) finalValue = Integer.valueOf(value);
                else if (valueType.contains("Double") || valueType.contains("double")) finalValue = Double.valueOf(value);
                else if (valueType.contains("Float") || valueType.contains("float")) finalValue = Float.valueOf(value);
                if (GameRule.fromId(gameRule) == null) return;
                tempValues.put(GameRule.fromId(gameRule), finalValue);
            });
            world.loadData(tempValues);
            return world;
        }

        @Override
        public JsonElement serialize(RuleWorld<W> src, Type typeOfSrc, JsonSerializationContext context) {
            var obj = new JsonObject();
            var array = new JsonArray();
            src.values.forEach((rule, value) -> {
                var ruleObj = new JsonObject();
                ruleObj.add("gameRule", new JsonPrimitive(rule.id()));
                ruleObj.add("valueType", new JsonPrimitive(value.getClass().getName()));
                ruleObj.add("value", new JsonPrimitive(value.toString()));
                array.add(ruleObj);
            });
            obj.add("rules", array);
            return obj;
        }
    }

}
