package org.leanpoker.player;

import com.google.gson.JsonElement;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class Player {

    private static final String OUR_NAME = "Royal Bluff";

    static final String VERSION = "0.2";
    public static final int ALL_IN = 1000000;
    public static final int FOLD_VALUE = 0;
    public static String strategy = "4";

    public static int betRequest(JsonElement request) {
        log("betRequest", request);

        GameState gameState = new GameState(request);
        System.out.println(">>>>>>> GameState: " + gameState.toString());

        switch (strategy) {
            case "1":
                return betStrategy1(gameState);
            case "2":
                return betStrategy2(gameState);
            case "3":
                return betStrategy3(gameState);
            case "4":
                return betStrategy4(gameState);
            default:
                return betStrategy2(gameState);

        }
        //        return betStrategy2(gameState);
    }

    private static int betStrategy1(GameState gameState) {
        return ALL_IN;
    }

    private static int betStrategy2(GameState gameState) {
        Optional<PlayerData> we =
                gameState.getPlayers().stream().filter(e -> e.getName().equals(OUR_NAME)).findFirst();
        if (we.isPresent()) {
            PlayerData weData = we.get();
            List<Card> cards = weData.getHoleCards();
            cards.sort(new ReverseCardRankComparator());
            if (isPair(cards) || isTwoGreaterThan(cards, "10")) {
                return ALL_IN;
            };
        }
        return 10;
    }

    private static int betStrategy3(GameState gameState) {
        Optional<PlayerData> we =
                gameState.getPlayers().stream().filter(e -> e.getName().equals(OUR_NAME)).findFirst();
        if (we.isPresent()) {
            if (otherAllIn(gameState)) {
                return FOLD_VALUE;
            }

            PlayerData weData = we.get();
            List<Card> cards = weData.getHoleCards();
            cards.sort(new ReverseCardRankComparator());
            if (isPair(cards) || isTwoGreaterThan(cards, "10")) {
                return ALL_IN;
            };
        }
        return 10;
    }

    private static int betStrategy4(GameState gameState) {
        Optional<PlayerData> we = findUs(gameState);
        if (we.isPresent()) {
            if (otherAllIn(gameState)) {
                return FOLD_VALUE;
            }

            PlayerData weData = we.get();
            List<Card> ourCards = weData.getHoleCards();
            List<Card> allCards = new ArrayList<>(ourCards);
            allCards.addAll(gameState.getCommunityCards());
            ourCards.sort(new ReverseCardRankComparator());

            if (isPair(ourCards) || isTwoGreaterThan(ourCards, "10")) {
                return ALL_IN;
            } else if (isPair(ourCards, gameState.getCommunityCards())) {
                return ALL_IN;
            } else if (gameState.getRound() < 3) {
                return getCallValue(gameState);
            } else {
                return FOLD_VALUE;
            }

        }

        System.out.println("Last call value");
        return getCallValue(gameState);
    }

    private static Optional<PlayerData> findUs(GameState gameState) {
        return gameState.getPlayers().stream().filter(e -> e.getName().equals(OUR_NAME)).findFirst();
    }

    private static int getCallValue(GameState gameState) {
        return gameState.getCurrentBuy() - gameState.getPlayers().get(gameState.getInAction()).getBet();
    }

    private static boolean otherAllIn(GameState gameState) {
        int ourIndex = gameState.getInAction();
        long numberOfAllInPlayers = gameState.getPlayers().stream().filter(e -> {
            return e.getStatus().equals("active") && e.getStack() == 0;
        }).count();
        if (numberOfAllInPlayers> 1) {
            return true;
        }
        return false;
    }

    private static boolean isOneGreaterThan(List<Card> cards, String rank ) {
        boolean twoGreaterThan = false;
        boolean oneGreaterThan = false;
        for (Card card: cards        ) {
            if (card.getRank().equals("10") || card.getRank().equals("J")|| card.getRank().equals("Q")|| card.getRank().equals("K")|| card.getRank().equals("A")) {
               return true;
            }
        }
        return twoGreaterThan;
    }


    private static boolean isTwoGreaterThan(List<Card> cards, String rank ) {
        boolean twoGreaterThan = false;
        boolean oneGreaterThan = false;
        for (Card card: cards        ) {
            if (card.getRank().equals("10") || card.getRank().equals("J")|| card.getRank().equals("Q")|| card.getRank().equals("K")|| card.getRank().equals("A")) {
                if (oneGreaterThan) {
                    twoGreaterThan = true;
                } else {
                    oneGreaterThan = true;
                }
            }
        }
        return twoGreaterThan;
    }

    private static boolean isPair(List<Card> cards) {
        if (cards.size() == 2) {
            Card card1 = cards.get(0);
            Card card2 = cards.get(1);
            return isPair(card1, card2);
        }
        return false;

    }

    private static boolean isPair(Collection<Card> ourcards, Collection<Card> commonCards) {
        boolean pair = false;
        for (Card ourCard: ourcards             ) {
            pair= commonCards.stream().anyMatch(e-> e.getRank().equals(ourCard.getRank()));
            if (pair) {
                return true;
            }
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
