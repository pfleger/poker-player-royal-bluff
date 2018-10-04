package org.leanpoker.player;

import com.google.gson.JsonElement;

public class Card {
    String rank;
    String suit;

    public static final String SUIT_HEARTS = "hearts";
    public static final String SUIT_SPADES = "spades";
    public static final String SUIT_CLUBS = "clubs";
    public static final String SUIT_DIAMONDS = "diamonds";


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
