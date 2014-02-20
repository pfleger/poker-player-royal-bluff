package org.leanpoker.player;

import com.google.gson.JsonElement;

import java.util.Map;

public class Player {

    public static int betRequest(JsonElement request) {

        for (Map.Entry<String, JsonElement> entry : request.getAsJsonObject().entrySet()) {

            System.out.println(entry.getKey() + " : " + entry.getValue());

        }

        return 0;

    }

    public static void showdown(JsonElement game) {
    }
}
