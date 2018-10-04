package org.leanpoker.player;

import com.google.gson.JsonElement;

public class Card {
    String rank;
    String suit;

    public Card(JsonElement from) {
        rank = JsonHelper.getAsString(from, "rank");
        suit = JsonHelper.getAsString(from, "suit");

    }

    @Override
    public String toString() {
        return "Card{" + "rank='" + rank + '\'' + ", suit='" + suit + '\'' + '}';
    }

    public String getSuit() {
        return suit;
    }

    public String getRank() {

        return rank;
    }
}
