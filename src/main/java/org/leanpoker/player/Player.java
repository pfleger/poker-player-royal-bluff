package org.leanpoker.player;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

public class Player {

    static final String VERSION = "0.2";

    public static int betRequest(JsonElement request) {
        log("betRequest", request);
        JsonArray players = request.getAsJsonObject().getAsJsonArray("players");
        int current_buy_in = getAsInt(request, "current_buy_in");
        int pot = getAsInt(request, "pot");

        GameState gameState = new GameState(request);
        System.out.println(">>>>>>> GameState: " + gameState.toString());

        return 1000000;
    }

    private static void log(String type, JsonElement request){
        System.out.println("Type: " + type + " JsonElement: " + request.toString());
    }

    private static int getAsInt(JsonElement request, String current_buy_in1) {
        return getJsonElement(request, current_buy_in1).getAsInt();
    }

    private static JsonElement getJsonElement(JsonElement request, String current_buy_in1) {
        return request.getAsJsonObject().get(current_buy_in1);
    }

    public static void showdown(JsonElement game) {
        log("showdown", game);
    }
}
