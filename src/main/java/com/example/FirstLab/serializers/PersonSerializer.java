package com.example.FirstLab.serializers;

import com.example.FirstLab.models.Client;
import com.google.gson.*;


import java.lang.reflect.Type;

public class PersonSerializer implements JsonSerializer<Client> {
    public JsonElement serialize(final Client person, final Type type, final JsonSerializationContext context) {
        JsonObject result = new JsonObject();
        result.add("id", new JsonPrimitive(person.getId()));
        result.add("name", new JsonPrimitive(person.getName()));
        return result;
    }
}