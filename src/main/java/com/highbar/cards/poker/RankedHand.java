package com.highbar.cards.poker;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import static com.highbar.util.Parameters.require;

public abstract class RankedHand<T extends RankedHand> implements Comparable<T> {
    HandRank rank;

    public RankedHand(@NotNull HandRank rank) {
        require(rank);
        this.rank = rank;
    }

    @NotNull
    @Contract(pure = true)
    public HandRank rank() {
        return rank;
    }

    @Override
    @Contract(pure = true)
    @NotNull
    public abstract String toString();
}
