package com.highbar.cards.poker;

import com.highbar.cards.Card;
import com.highbar.cards.Cards;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public final class HighCard extends RankedHand {

    private List<Card> cards;

    HighCard(@NotNull List<Card> cards, @NotNull Comparator<Card> comparator) {
        super(HandRank.HighCard, comparator);
        this.cards = new ArrayList<>(cards);
        this.cards.sort(comparator.reversed());
    }

    @Contract(pure = true)
    @Override
    public int compareTo(@NotNull RankedHand r) {
        int result = super.compareTo(r);
        if (result == 0) {
            HighCard that = (HighCard) r;
            result = Cards.compare(this.cards, that.cards, comparator());
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