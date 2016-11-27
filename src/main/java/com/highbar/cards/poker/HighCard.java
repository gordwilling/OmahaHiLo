package com.highbar.cards.poker;

import com.highbar.cards.Card;
import com.highbar.cards.Cards;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.Comparator;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

public final class HighCard extends RankedHand<HighCard> {

    private SortedSet<Card> cards;

    HighCard(@NotNull Set<Card> cards, @NotNull Comparator<Card> comparator) {
        super(HandRank.HighCard, comparator);
        this.cards = new TreeSet<>(comparator);
        this.cards.addAll(cards);
    }

    @Contract(pure = true)
    @Override
    public int compareTo(@NotNull HighCard that) {
        return Cards.compare(this.cards, that.cards, comparator());
    }

    @Contract(pure = true)
    @NotNull
    @Override
    public String toString() {
        return Cards.toString(cards);
    }
}