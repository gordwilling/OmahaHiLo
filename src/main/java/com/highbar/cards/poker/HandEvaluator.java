package com.highbar.cards.poker;

import com.highbar.cards.Card;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.stream.Collectors;

import static com.highbar.cards.poker.CardComparators.aceHigh;
import static com.highbar.cards.poker.CardComparators.aceLow;
import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;

public class HandEvaluator {

    private Comparator<Card> comparator;

    public HandEvaluator(@NotNull Comparator<Card> comparator) {
        this.comparator = comparator;
    }

    /**
     * Ranking a hand consists of the following steps:
     * <table cellpadding="4" border="1" style="border-collapse:collapse">
     * <tr align="center"><td colspan="8">Are there any shared ranks? (Pair, 3-of-a-Kind, etc.)</td></tr>
     * <tr align="center"><td colspan="4" bgcolor="lightgray">No</td><td colspan="4">Yes</td></tr>
     * <tr align="center">
     * <td colspan="4" bgcolor="lightgray">Are the cards in Sequence?</td>
     * <td colspan="4" rowspan="5" align="left">
     * A shared rank eliminates the possibility of:
     * <ul>
     * <li>High Card (we have One Pair at least)</li>
     * <li>Straight (5 different ranks are required to make a straight)</li>
     * <li>Flush (5 different ranks are required to make a flush)</li>
     * <li>Straight Flush, as neither a Straight, nor Flush is possible</li>
     * </ul>
     * We can proceed using inspection to determine:
     * <ul>
     * <li>4-of-a-Kind</li>
     * <li>Full House</li>
     * <li>3-of-a-Kind</li>
     * <li>2 Pair</li>
     * <li>1 Pair</li>
     * </ul>
     * </td>
     * </tr>
     * <tr align="center">
     * <td colspan="2" bgcolor="lightgray">No</td><td colspan="2">Yes</td>
     * </tr>
     * <tr align="center">
     * <td colspan="2" bgcolor="lightgray">Do all suits match?</td><td colspan="2">Do all suits match?</td></tr>
     * <tr align="center">
     * <td bgcolor="lightgray">No</td><td>Yes</td><td bgcolor="lightgray">No</td><td>Yes</td></tr>
     * <tr align="center">
     * <td bgcolor="lightgray">High Card</td><td>Flush</td><td bgcolor="lightgray">Straight</td><td>Straight Flush</td></tr>
     * </table>
     *
     * @param a first card
     * @param b second card
     * @param c third card
     * @param d fourth card
     * @param e fifth card
     * @return the rank of the hand consisting of these five cards
     */
    @NotNull
    @Contract(pure = true)
    public RankedHand rank(@NotNull Card a, @NotNull Card b, @NotNull Card c, @NotNull Card d, @NotNull Card e) {
        SortedSet<Card> cards = emptyOrderedSet();
        cards.addAll(asList(a, b, c, d, e));

        Optional<? extends RankedHand> someOfAKind = someOfAKind(cards);

        if (someOfAKind.isPresent()) {
            return someOfAKind.get();
        } else if (isStraight(cards)) {
            if (isFlush(cards)) {
                return new StraightFlush(cards, comparator);
            } else {
                return new Straight(cards, comparator);
            }
        } else if (isFlush(cards)) {
            return new Flush(cards, comparator);
        } else {
            return new HighCard(cards, comparator);
        }
    }

    @NotNull
    @Contract(pure = true)
    Optional<? extends RankedHand> someOfAKind(@NotNull SortedSet<Card> cards) {

        // This next statement does three things:
        //
        // 1. Groups the cards in the set by their rank. We end up with a map
        //    from CardRank -> List<Card>
        //
        //    For example if our hand contains 2c-2d-2h-5c-7h, we get (excluding empty values)
        //
        //    2 -> [2c, 2d, 2h]
        //    5 -> [5c]
        //    7 -> [7h]
        //
        // 2. Removes map elements pointing to empty lists
        //
        // 3. Groups the List values by size:
        //
        //    1 -> [[5c], [7h]]
        //    3 -> [2c, 2d, 2h]
        //
        //    Where 1 maps to 2 1-Element lists (2 kickers), and 3 maps to 1 3-Element list (3-of-a-kind)
        //
        // The final Map will contain up to 4 keys (integer values 1-4) mapped to the list of lists

        Map<Integer, List<List<Card>>> groupByN = cards.stream()
                .collect(Collectors.groupingBy(Card::rank))
                .values().stream()
                .filter(x -> x.size() != 0)
                .collect(Collectors.groupingBy(List::size));

        List<Card> singles = flatten(groupByN.getOrDefault(1, emptyList()));
        List<List<Card>> pairs = groupByN.getOrDefault(2, emptyList());
        List<List<Card>> triplet = groupByN.getOrDefault(3, emptyList());
        List<List<Card>> quad = groupByN.getOrDefault(4, emptyList());

        if (singles.size() == 5) {
            // none-of-a-kind
            return Optional.empty();
        } else if (singles.size() == 3 && pairs.size() == 1) {
            // 1 pair, 3 kickers
            return Optional.of(new OnePair(flatten(pairs), singles, comparator));
        } else if (singles.size() == 1 && pairs.size() == 2) {
            // 2 pairs, 1 kicker
            return Optional.of(new TwoPair(pairs.get(0), pairs.get(1), singles.get(0), comparator));
        } else if (singles.size() == 2 && triplet.size() == 1) {
            // one triplet, two kickers
            return Optional.of(new ThreeOfAKind(triplet.get(0), singles, comparator));
        } else if (pairs.size() == 1 && triplet.size() == 1) {
            // one triplet, one pair
            return Optional.of(new FullHouse(triplet.get(0), pairs.get(0), comparator));
        } else if (singles.size() == 1 && quad.size() == 1) {
            // one quad, one kicker
            return Optional.of(new FourOfAKind(quad.get(0), singles.get(0), comparator));
        } else {
            throw new IllegalStateException("Expected unreachable code");
        }
    }

    @Contract(pure = true)
    boolean isFlush(@NotNull SortedSet<Card> cards) {
        return cards.stream().map(Card::suit).count() == 1;
    }

    @Contract(pure = true)
    boolean isStraight(@NotNull SortedSet<Card> cards) {
        return isStraight(cards, aceHigh()) || isStraight(cards, aceLow());
    }

    @Contract(pure = true)
    boolean isStraight(@NotNull SortedSet<Card> in, @NotNull Comparator<Card> comparator) {
        SortedSet<Card> cards = new TreeSet<>(comparator);
        cards.addAll(in);
        boolean isStraight = true;
        Iterator<Card> i = cards.iterator();
        int priorOrdinal = i.next().rank().ordinal();
        while (i.hasNext() && isStraight) {
            Card card = i.next();
            isStraight = priorOrdinal == card.rank().ordinal() - 1;
            priorOrdinal = card.rank().ordinal();
        }
        return isStraight;
    }

    @NotNull
    @Contract(pure = true)
    private List<Card> flatten(@NotNull List<List<Card>> cards) {
        return cards.stream().flatMap(List::stream).collect(Collectors.toList());
    }


    @NotNull
    @Contract(pure = true)
    private TreeSet<Card> emptyOrderedSet() {
        return new TreeSet<>(comparator);
    }
}