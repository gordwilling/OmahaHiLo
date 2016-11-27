package com.highbar.cards.poker;

import com.highbar.cards.Card;
import com.highbar.cards.Cards;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class TwoPair extends RankedHand<TwoPair> {

    private SortedSet<Card> highPair;
    private SortedSet<Card> lowPair;
    private Card kicker;

    public TwoPair(@NotNull List<Card> pairOne,
                   @NotNull List<Card> pairTwo,
                   @NotNull Card kicker,
                   @NotNull Comparator<Card> comparator) {
        super(HandRank.TwoPair, comparator);
        this.highPair = new TreeSet<>(comparator);
        this.lowPair = new TreeSet<>(comparator);
        this.kicker = kicker;

        int comparison = compare(pairOne.get(0), pairTwo.get(0));
        if (comparison > 0) {
            this.highPair.addAll(pairOne);
            this.lowPair.addAll(pairTwo);
        } else if (comparison < 0) {
            this.highPair.addAll(pairTwo);
            this.lowPair.addAll(pairOne);
        } else {
            throw new IllegalStateException("Expected unreachable code");
        }
    }

    @Contract(pure = true)
    @Override
    public int compareTo(@NotNull TwoPair that) {
        int result = compare(this.highPair.first(), that.highPair.first());
        if (result == 0) {
            result = compare(this.lowPair.first(), that.lowPair.first());
            if (result == 0) {
                result = compare(this.kicker, that.kicker);
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