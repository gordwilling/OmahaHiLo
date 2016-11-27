package com.highbar.cards.poker;

import com.highbar.cards.Card;
import com.highbar.cards.Cards;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public final class FourOfAKind extends RankedHand {

    private List<Card> four;
    private Card kicker;

    public FourOfAKind(@NotNull List<Card> four, @NotNull Card kicker, @NotNull Comparator<Card> comparator) {
        super(HandRank.FourOfAKind, comparator);
        this.four = new ArrayList<>(four);
        this.kicker = kicker;
    }

    @Contract(pure = true)
    @Override
    public int compareTo(@NotNull RankedHand r) {
        int result = super.compareTo(r);
        if (result == 0) {
            FourOfAKind that = (FourOfAKind) r;
            result = compare(this.four.get(0), that.four.get(0));
        }
        return result;
    }

    @NotNull
    @Contract(pure = true)
    @Override
    public String toString() {
        List<Card> cards = new ArrayList<>(four);
        cards.add(kicker);
        return Cards.toString(cards);
    }
}