package com.highbar.cards.poker;

import com.highbar.cards.Card;
import com.highbar.cards.Cards;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class OnePair extends RankedHand<OnePair> {

    private SortedSet<Card> pair;
    private SortedSet<Card> kickers;

    public OnePair(@NotNull List<Card> pair, @NotNull List<Card> kickers, @NotNull Comparator<Card> comparator) {
        super(HandRank.OnePair, comparator);
        this.pair = new TreeSet<>(comparator);
        this.kickers = new TreeSet<>(comparator);
        this.pair.addAll(pair);
        this.kickers.addAll(kickers);
    }

    @Contract(pure = true)
    @Override
    public int compareTo(@NotNull OnePair that) {
        int result = compare(this.pair.first(), that.pair.first());
        if (result == 0) {
            result = Cards.compare(this.kickers, that.kickers, comparator());
        }
        return result;
    }

    @NotNull
    @Contract(pure = true)
    @Override
    public String toString() {
        List<Card> cards = new ArrayList<>(pair);
        cards.addAll(kickers);
        return Cards.toString(cards);
    }
}