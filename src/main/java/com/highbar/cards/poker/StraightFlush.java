package com.highbar.cards.poker;

import com.highbar.cards.Card;
import com.highbar.cards.Cards;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public final class StraightFlush extends RankedHand {

    private List<Card> cards;

    StraightFlush(@NotNull List<Card> cards, @NotNull Comparator<Card> comparator) {
        super(HandRank.StraightFlush, comparator);
        this.cards = new ArrayList<>(cards);
        this.cards.sort(comparator.reversed());
    }

    @Contract(pure = true)
    @Override
    public int compareTo(@NotNull RankedHand r) {
        int result = super.compareTo(r);
        if (result == 0) {
            StraightFlush that = (StraightFlush) r;
            // comparing the second card in each straight avoid ace ambiguity
            result = compare(this.cards.get(1), that.cards.get(1));
        }
        return result;
    }

    @Contract(pure = true)
    @NotNull
    @Override
    public String toString() {
        return Cards.toString(cards);
    }
}