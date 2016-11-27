package com.highbar.cards.poker;

import com.highbar.cards.Card;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.Comparator;

import static com.highbar.util.Parameters.require;

public abstract class RankedHand implements Comparable<RankedHand> {
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

    @Contract(pure = true)
    @Override
    public int compareTo(RankedHand that) {
        return this.rank().compareTo(that.rank());
    }

    @Override
    @Contract(pure = true)
    @NotNull
    public abstract String toString();
}
