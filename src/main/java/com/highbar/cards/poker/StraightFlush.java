package com.highbar.cards.poker;

import com.highbar.cards.Card;
import com.highbar.cards.Cards;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public final class StraightFlush extends RankedHand<StraightFlush> {

    private List<Card> cards;

    StraightFlush(@NotNull List<Card> cards, @NotNull Comparator<Card> comparator) {
        super(HandRank.StraightFlush, comparator);
        this.cards = new ArrayList<>();
        this.cards.addAll(cards);
        this.cards.sort(comparator.reversed());
    }

    @Contract(pure = true)
    @Override
    public int compareTo(@NotNull StraightFlush that) {
        return compare(this.cards.get(0), that.cards.get(0));
    }

    @Contract(pure = true)
    @NotNull
    @Override
    public String toString() {
        return Cards.toString(cards);
    }
}