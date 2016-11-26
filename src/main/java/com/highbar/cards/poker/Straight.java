package com.highbar.cards.poker;

import com.highbar.cards.Card;
import com.highbar.cards.Cards;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.Set;
import java.util.TreeSet;

public final class Straight extends RankedHand<Straight> {

    private TreeSet<Card> cards;

    Straight(Set<Card> cards) {
        super(HandRank.Straight);
        this.cards = new TreeSet<>(cards);
    }

    @Contract(pure = true)
    @NotNull
    @Override
    public String toString() {
        return Cards.toString(cards);
    }

    @Contract(pure = true)
    @Override
    public int compareTo(@NotNull Straight that) {
        throw new UnsupportedOperationException();
    }
}
