package com.highbar.cards.poker;

import com.highbar.cards.Card;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static java.util.Collections.emptyList;

public class Low8Classifier extends AbstractHandClassifier {

    public Low8Classifier() {
        super(CardComparators.aceLow());
    }

    @NotNull
    @Contract(pure = true)
    public Optional<RankedHand> low8Hand(@NotNull List<Card> hand) {
        if (hand.size() != 5) throw new IllegalArgumentException();
        List<Card> cards = new ArrayList<>();
        cards.addAll(hand);

        // the spec didn't mention it, but according to the Internet, Low 8
        // rules ignore flushes and straights. That means all hands are
        // ranked as High Card... Without this knowledge, this program was
        // generating results that differed from the spec.

        return noneHigherThan8(cards) && distinctRanks(cards)
                ? Optional.of(new HighCard(cards, comparator()))
                : Optional.empty();
    }

    @Contract(pure = true)
    private boolean distinctRanks(@NotNull List<Card> cards) {
        Map<Integer, List<List<Card>>> groupByN = groupByN(cards);
        List<List<Card>> singles = groupByN.getOrDefault(1, emptyList());
        return singles.size() == 5;
    }

    @Contract(pure = true)
    private boolean noneHigherThan8(@NotNull List<Card> cards) {
        return cards.stream().filter(x -> x.rank().ordinal() > 7).count() == 0;
    }
}