package com.highbar.cards.poker;

import com.highbar.cards.Card;
import com.highbar.cards.Cards;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.Set;
import java.util.TreeSet;

public final class HighCard extends RankedHand<HighCard> {

    private TreeSet<Card> cards;

    HighCard(@NotNull Set<Card> cards) {
        super(HandRank.HighCard);
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
    public int compareTo(@NotNull HighCard that) {
        throw new UnsupportedOperationException();
    }
}