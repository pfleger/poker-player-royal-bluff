package org.leanpoker.player;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

import java.util.ArrayList;
import java.util.List;

public class PlayerData {

    String name;
    int stack;
    String status;
    int bet;
    List<Card> holeCards;

    String version;
    int id;

    public PlayerData(JsonElement from) {
        stack = getAsInt(from, "stack");
        name = getAsString(from, "name");


        JsonArray jsonHoleCards = from.getAsJsonObject().getAsJsonArray("hole_cards");
        holeCards = new ArrayList<>();
        if (jsonHoleCards != null) {
            jsonHoleCards.forEach(e -> {
                Card card = new Card(e);
                holeCards.add(card);
            });
        }

    }


    private String getAsString(JsonElement request, String name) {
        return getJsonElement(request, name).getAsString();
    }

    private static int getAsInt(JsonElement request, String current_buy_in1) {
        return getJsonElement(request, current_buy_in1).getAsInt();
    }

    private static JsonElement getJsonElement(JsonElement request, String current_buy_in1) {
        return request.getAsJsonObject().get(current_buy_in1);
    }

    @Override
    public String toString() {
        return "PlayerData{" + "name='" + name + '\'' + ", stack=" + stack + ", status='" + status + '\'' + ", bet=" +
                bet + ", holeCards=" + holeCards + ", version='" + version + '\'' + ", id=" + id + '}';
    }

    public String getName() {
        return name;
    }

    public int getStack() {
        return stack;
    }

    public String getStatus() {
        return status;
    }

    public int getBet() {
        return bet;
    }

    public String getVersion() {
        return version;
    }

    public int getId() {
        return id;
    }
}
