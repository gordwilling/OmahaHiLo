package com.highbar.cards.poker;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

enum HandRank {
    HighCard("High Card"),
    OnePair("One Pair"),
    TwoPair("Two Pair"),
    ThreeOfAKind("3-of-a-Kind"),
    Straight("Straight"),
    Flush("Flush"),
    FullHouse("Full House"),
    FourOfAKind("4-of-a-Kind"),
    StraightFlush("Straight Flush");

    private String prettyName;

    HandRank(@NotNull String prettyName) {
        this.prettyName = prettyName;
    }

    @NotNull
    @Contract(pure = true)
    @Override
    public String toString() {
        return prettyName;
    }
}
