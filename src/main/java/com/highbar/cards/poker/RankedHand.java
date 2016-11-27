package com.highbar.cards.poker;

import com.highbar.cards.Card;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.Comparator;

import static com.highbar.util.Parameters.require;

public abstract class RankedHand<T extends RankedHand> implements Comparable<T> {
    private HandRank rank;
    private Comparator<Card> comparator;

    RankedHand(@NotNull HandRank rank, @NotNull Comparator<Card> comparator) {
        require(rank, comparator);
        this.rank = rank;
        this.comparator = comparator;
    }

    @NotNull
    @Contract(pure = true)
    public HandRank rank() {
        return rank;
    }

    @NotNull
    @Contract(pure = true)
    public Comparator<Card> comparator() {
        return comparator;
    }

    @Contract(pure = true)
    public int compare(Card x, Card y) {
        return comparator.compare(x, y);
    }

    @Override
    @Contract(pure = true)
    @NotNull
    public abstract String toString();
}
