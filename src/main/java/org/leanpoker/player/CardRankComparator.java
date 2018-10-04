package org.leanpoker.player;

import java.util.Comparator;

public class CardRankComparator implements Comparator<Card> {

    @Override
    public int compare(Card o1, Card o2) {

        Rank rank1 = Rank.fromString(o1.getRank());
        Rank rank2 = Rank.fromString(o2.getRank());
        return rank1.compareToRank(rank2);
    }
}
