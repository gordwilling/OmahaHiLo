package com.highbar.cards.poker;

import com.highbar.cards.Card;
import com.highbar.cards.CardRank;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.stream.Collectors;

import static com.highbar.cards.poker.CardComparators.aceHigh;
import static com.highbar.cards.poker.CardComparators.aceLow;
import static java.util.Collections.emptyList;

/**
 * Classifying a hand consists of the following steps:
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
 */
public abstract class AbstractHandClassifier {

    Comparator<Card> comparator;

    AbstractHandClassifier(@NotNull Comparator<Card> comparator) {
        this.comparator = comparator;
    }

    @NotNull
    @Contract(pure = true)
    Comparator<Card> comparator() {
        return comparator;
    }

    /**
     * The function organizes cards by the size of their grouping. For example, all of the
     * singles are in a list mapped to Integer 1.  All of pairs are in a list mapped to the
     * Integer 2. All of the 3-of-a-kinds are in a list mapped to the Integer 3, etc...
     * <p/>
     * It takes 3 steps to coerce the data into this structure:
     * <ol>
     *     <li>
     *       Group the cards in the list by their rank. We end up with a map
     *       from CardRank -> List<Card>
     *               For example if our hand contains 2c-2d-2h-5c-7h, we get (excluding empty values)
     *               <br/>2 -> [2c, 2d, 2h]
     *               <br/>5 -> [5c]
     *               <br/>7 -> [7h]
     *     </li>
     *     <li>
     *       Remove elements pointing to empty list values
     *     </li>
     *     <li>
     *       Group the list values by size:
     *               <br/>1 -> [[5c], [7h]]
     *               <br/>3 -> [2c, 2d, 2h]
     *     </li>
     * </ol>
     *  In this example we see a 3-of-a-kind (2c, 2d, 2h) and two kickers (5c, 7h)
     */
    @Contract(pure = true)
    @NotNull
    Map<Integer, List<List<Card>>> groupByN(@NotNull List<Card> cards) {
        return cards.stream()
                .collect(Collectors.groupingBy(Card::rank))
                .values().stream()
                .filter(x -> x.size() != 0)
                .collect(Collectors.groupingBy(List::size));
    }

    @NotNull
    @Contract(pure = true)
    Optional<RankedHand> someOfAKind(@NotNull List<Card> cards) {
        Map<Integer, List<List<Card>>> groupByN = groupByN(cards);

        List<Card> singles = flatten(groupByN.getOrDefault(1, emptyList()));
        List<List<Card>> pairs = groupByN.getOrDefault(2, emptyList());
        List<List<Card>> triplets = groupByN.getOrDefault(3, emptyList());
        List<List<Card>> quads = groupByN.getOrDefault(4, emptyList());

        if (singles.size() == 5) {
            // none-of-a-kind
            return Optional.empty();
        } else if (singles.size() == 3 && pairs.size() == 1) {
            // 1 pair, 3 kickers
            return Optional.of(new OnePair(flatten(pairs), singles, comparator()));
        } else if (singles.size() == 1 && pairs.size() == 2) {
            // 2 pairs, 1 kicker
            return Optional.of(new TwoPair(pairs.get(0), pairs.get(1), singles.get(0), comparator()));
        } else if (singles.size() == 2 && triplets.size() == 1) {
            // one triplet, two kickers
            return Optional.of(new ThreeOfAKind(triplets.get(0), singles, comparator()));
        } else if (pairs.size() == 1 && triplets.size() == 1) {
            // one triplet, one pair
            return Optional.of(new FullHouse(triplets.get(0), pairs.get(0), comparator()));
        } else if (singles.size() == 1 && quads.size() == 1) {
            // one quad, one kicker
            return Optional.of(new FourOfAKind(quads.get(0), singles.get(0), comparator()));
        } else {
            throw new IllegalStateException("Reached unreachable code");
        }
    }

    @Contract(pure = true)
    @NotNull
    RankedHand noneOfAKind(List<Card> cards) {
        if (isStraight(cards)) {
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

    @Contract(pure = true)
    private boolean isFlush(@NotNull List<Card> cards) {
        return cards.stream().map(Card::suit).distinct().count() == 1;
    }

    @Contract(pure = true)
    private boolean isStraight(@NotNull List<Card> cards) {
        return isStraight(cards, aceHigh()) || isStraight(cards, aceLow());
    }

    @Contract(pure = true)
    private boolean isStraight(@NotNull List<Card> in, @NotNull Comparator<Card> comparator) {
        List<Card> cards = new ArrayList<>(in);
        cards.sort(comparator);
        boolean isStraight = true;
        Iterator<Card> i = cards.iterator();
        int priorOrdinal = i.next().rank().ordinal();
        while (i.hasNext() && isStraight) {
            Card card = i.next();
            // comparing the ordinal doesn't work for an Ace-high straight
            // cheat a bit until something more elegant presents itself...
            if (card.rank() == CardRank.Ace) {
                isStraight = priorOrdinal == CardRank.King.ordinal();
            } else {
                isStraight = priorOrdinal == card.rank().ordinal() - 1;
            }
            priorOrdinal = card.rank().ordinal();
        }
        return isStraight;
    }

    @NotNull
    @Contract(pure = true)
    private List<Card> flatten(@NotNull List<List<Card>> cards) {
        return cards.stream().flatMap(List::stream).collect(Collectors.toList());
    }
}
