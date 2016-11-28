package com.highbar.cards.poker;

import com.highbar.cards.Card;
import org.jetbrains.annotations.NotNull;

import java.util.Comparator;
import java.util.List;

final class StraightFlush extends Straight {

    StraightFlush(@NotNull List<Card> cards, @NotNull Comparator<Card> comparator) {
        super(HandRank.StraightFlush, cards, comparator);
    }
}