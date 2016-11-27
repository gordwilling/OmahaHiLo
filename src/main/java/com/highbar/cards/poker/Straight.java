package com.highbar.cards.poker;

import com.highbar.cards.Card;
import com.highbar.cards.Cards;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public final class Straight extends RankedHand<Straight> {

    private List<Card> cards;

    Straight(@NotNull List<Card> cards, @NotNull Comparator<Card> comparator) {
        super(HandRank.Straight, comparator);
        this.cards = new ArrayList<>(cards);
        this.cards.addAll(cards);
        this.cards.sort(comparator.reversed());
    }

    @Contract(pure = true)
    @Override
    public int compareTo(@NotNull Straight that) {
        return compare(this.cards.get(0), that.cards.get(0));
    }

    @Contract(pure = true)
    @NotNull
    @Override
    public String toString() {
        return Cards.toString(cards);
    }
}
