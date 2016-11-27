package com.highbar.cards.poker;

import com.highbar.cards.Card;
import com.highbar.cards.Cards;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class TwoPair extends RankedHand {

    private List<Card> highPair;
    private List<Card> lowPair;
    private Card kicker;

    public TwoPair(@NotNull List<Card> pairOne,
                   @NotNull List<Card> pairTwo,
                   @NotNull Card kicker,
                   @NotNull Comparator<Card> comparator) {
        super(HandRank.TwoPair, comparator);
        this.kicker = kicker;

        int comparison = compare(pairOne.get(0), pairTwo.get(0));
        if (comparison > 0) {
            this.highPair = new ArrayList<>(pairOne);
            this.lowPair = new ArrayList<>(pairTwo);
        } else if (comparison < 0) {
            this.highPair = new ArrayList<>(pairTwo);
            this.lowPair = new ArrayList<>(pairOne);
        } else {
            throw new IllegalStateException("Reached unreachable code");
        }
    }

    @Contract(pure = true)
    @Override
    public int compareTo(@NotNull RankedHand r) {
        int result = super.compareTo(r);
        if (result == 0) {
            TwoPair that = (TwoPair)r;
            result = compare(this.highPair.get(0), that.highPair.get(0));
            if (result == 0) {
                result = compare(this.lowPair.get(0), that.lowPair.get(0));
                if (result == 0) {
                    result = compare(this.kicker, that.kicker);
                }
            }
        }
        return result;
    }

    @NotNull
    @Contract(pure = true)
    @Override
    public String toString() {
        List<Card> cards = new ArrayList<>(highPair);
        cards.addAll(lowPair);
        cards.add(kicker);
        return Cards.toString(cards);
    }
}