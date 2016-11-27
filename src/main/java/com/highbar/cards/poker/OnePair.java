package com.highbar.cards.poker;

import com.highbar.cards.Card;
import com.highbar.cards.Cards;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class OnePair extends RankedHand<OnePair> {

    private List<Card> pair;
    private List<Card> kickers;

    public OnePair(@NotNull List<Card> pair, @NotNull List<Card> kickers, @NotNull Comparator<Card> comparator) {
        super(HandRank.OnePair, comparator);
        this.pair = new ArrayList<>();
        this.kickers = new ArrayList<>();
        this.pair.addAll(pair);
        this.kickers.addAll(kickers);
        this.kickers.sort(comparator.reversed());
    }

    @Contract(pure = true)
    @Override
    public int compareTo(@NotNull OnePair that) {
        int result = compare(this.pair.get(0), that.pair.get(0));
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