package org.leanpoker.player;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class GameState {
    String tournamentId;
    String gameId;
    int round;
    int betIndex;
    int smallBlind;
    int orbits;
    int inAction;
    int dealer;
    Collection<Card> communityCards;
    int currentBuy;
    int pot;
    List<PlayerData> players;

    public GameState(JsonElement request) {


        players = new ArrayList<>();
        JsonArray jsonPlayers = request.getAsJsonObject().getAsJsonArray("players");
        jsonPlayers.forEach(e-> {
            PlayerData data = new PlayerData(e);
            players.add(data);
        });


        tournamentId = getAsString(request, "tournament_id");
        gameId = getAsString(request, "game_id");
        round = getAsInt(request, "round");
        betIndex = getAsInt(request, "bet_index");
        smallBlind =

        currentBuy = getAsInt(request, "current_buy_in");
        pot = getAsInt(request, "pot");
        inAction = getAsInt(request,"in_action");

        JsonArray jsonCommunityCards = request.getAsJsonObject().getAsJsonArray("community_cards");
        communityCards = new ArrayList<>();
        jsonCommunityCards.forEach(e-> {
            Card card = new Card(e);
            communityCards.add(card);
        });

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
        return "GameState{" + "tournamentId='" + tournamentId + '\'' + ", gameId='" + gameId + '\'' + ", round=" +
                round + ", betIndex=" + betIndex + ", smallBlind=" + smallBlind + ", orbits=" + orbits + ", inAction=" +
                inAction + ", dealer=" + dealer + ", communityCards=" + communityCards + ", currentBuy=" + currentBuy +
                ", pot=" + pot + ", players=" + players + '}';
    }

    public String getTournamentId() {
        return tournamentId;
    }

    public String getGameId() {
        return gameId;
    }

    public int getRound() {
        return round;
    }

    public int getBetIndex() {
        return betIndex;
    }

    public int getSmallBlind() {
        return smallBlind;
    }

    public int getOrbits() {
        return orbits;
    }

    public int getInAction() {
        return inAction;
    }

    public int getDealer() {
        return dealer;
    }

    public Collection<Card> getCommunityCards() {
        return communityCards;
    }

    public int getCurrentBuy() {
        return currentBuy;
    }

    public int getPot() {
        return pot;
    }

    public List<PlayerData> getPlayers() {
        return players;
    }
}
