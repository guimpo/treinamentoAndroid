package com.example.paulo.myapiapplication.Utils;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.google.gson.*;

import java.lang.reflect.Type;

public abstract class JsonUtils {
    public static Gson getGson(Type type) {
        return new GsonBuilder()
                .registerTypeAdapter(type, new GsonRootSerializer())
                .create();
    }

    static class GsonRootSerializer implements JsonSerializer<Object> {
        @Override
        public JsonElement serialize(Object src, Type typeOfSrc, JsonSerializationContext context) {
            Gson gson = new Gson();
            JsonElement je = gson.toJsonTree(src);
            JsonObject jo = new JsonObject();
            jo.add(src.getClass().getAnnotation(JsonRootName.class).value(), je);
            return jo;
        }
    }
}
