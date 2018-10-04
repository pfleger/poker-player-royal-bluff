package org.leanpoker.player;

public enum Rank  {

    EIGHT(8),
    NINE(9),
    TEN(10),
    J(11),
    Q(12),
    K(13),
    A(14);

    int rankIndex;

    Rank(int rankIndex) {
        this.rankIndex = rankIndex;
    }

    public static Rank fromString(String rankString) {
        switch (rankString) {
            case "8":
                return EIGHT;
            case "9" :
                return NINE;
            case "10":
                return TEN;
            case "J":
                return J;
            case "Q":
                return Q;
            case "K":
                return K;
            case "A":
                return A;
            default:
                return EIGHT;
        }
    }

    public int compareToRank(Rank other) {
        return Integer.compare(this.rankIndex, other.rankIndex);
    }
}
