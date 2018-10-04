package org.leanpoker.player;

import com.google.gson.JsonElement;

public class JsonHelper {

    public static String getAsString(JsonElement request, String name) {
        return getJsonElement(request, name).getAsString();
    }

    public static int getAsInt(JsonElement request, String current_buy_in1) {
        return getJsonElement(request, current_buy_in1).getAsInt();
    }

    public static JsonElement getJsonElement(JsonElement request, String current_buy_in1) {
        return request.getAsJsonObject().get(current_buy_in1);
    }


}
