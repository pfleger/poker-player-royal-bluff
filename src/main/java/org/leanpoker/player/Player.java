package org.leanpoker.player;

import com.google.gson.JsonElement;

import java.util.Optional;

public class Player {

    private static final String OUR_NAME = "Royal Bluff";

    static final String VERSION = "0.2";
    public static final int ALL_IN = 1000000;

    public static int betRequest(JsonElement request) {
        log("betRequest", request);
//        JsonArray players = request.getAsJsonObject().getAsJsonArray("players");
//        int current_buy_in = getAsInt(request, "current_buy_in");
//        int pot = getAsInt(request, "pot");

        GameState gameState = new GameState(request);
        System.out.println(">>>>>>> GameState: " + gameState.toString());

        Optional<PlayerData> we =
                gameState.getPlayers().stream().filter(e -> e.getName().equals(OUR_NAME)).findFirst();
        if (we.isPresent()) {
            PlayerData weData = we.get();
            Card[] cards = weData.getHoleCards().toArray(new Card[0]);
            if (isPair(cards)) {
                return ALL_IN;
            };
        }

        return 10;
    }

    private static boolean isPair(Card[] cards) {
        if (cards.length == 2) {
            Card card1 = cards[0];
            Card card2 = cards[1];
            return isPair(card1, card2);
        }
        return false;

    }

    private static boolean isPair(Card card1, Card card2) {
        return card1.getRank().equals(card2.getRank());
    }

    private static void log(String type, JsonElement request){
        System.out.println("Type: " + type + " JsonElement: " + request.toString());
    }

//    private static int getAsInt(JsonElement request, String current_buy_in1) {
//        return getJsonElement(request, current_buy_in1).getAsInt();
//    }
//
//    private static JsonElement getJsonElement(JsonElement request, String current_buy_in1) {
//        return request.getAsJsonObject().get(current_buy_in1);
//    }

    public static void showdown(JsonElement game) {
        log("showdown", game);
    }
}
