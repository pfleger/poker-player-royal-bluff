package org.leanpoker.player;

import java.util.Comparator;

public class ReverseCardRankComparator implements Comparator<Card> {

    @Override
    public int compare(Card o1, Card o2) {

        Rank rank1 = Rank.fromString(o1.getRank());
        Rank rank2 = Rank.fromString(o2.getRank());
        return rank2.compareToRank(rank1);
    }
}
