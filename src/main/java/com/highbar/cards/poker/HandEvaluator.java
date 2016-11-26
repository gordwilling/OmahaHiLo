package com.highbar.cards.poker;

import com.highbar.cards.Card;
import com.highbar.cards.CardRank;
import com.highbar.cards.Suit;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.*;

import static java.util.Arrays.asList;

public class HandEvaluator {

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
    public RankedHand rank(@NotNull Card a, @NotNull Card b, @NotNull Card c, @NotNull Card d, @NotNull Card e) {
        Set<Card> cards = new TreeSet<>();
        cards.addAll(asList(a, b, c, d, e));

        Optional<Map<CardRank, Integer>> sharedRanks = sharedRanks(cards);

        if (sharedRanks.isPresent()) {
            Map<CardRank, Integer> rankFrequencies = sharedRanks.get();

            // temporary!
            return new HighCard(cards);

        } else if (isStraight(cards)) {
            if (isFlush(cards)) {
                return new StraightFlush(cards);
            } else {
                return new Straight(cards);
            }
        } else if (isFlush(cards)) {
            return new Flush(cards);
        } else {
            return new HighCard(cards);
        }
    }

    public Optional<Map<CardRank, Integer>> sharedRanks(Set<Card> cards) {
        Map<CardRank, Integer> rankFrequencies = cards.stream().reduce(new HashMap<>(),
                this::rankFrequencyAccumulator, this::sumRankFrequencyAccumulators);

        return rankFrequencies.values().stream().filter(x -> x > 1).count() != 0
                ? Optional.of(rankFrequencies)
                : Optional.empty();
    }

    public boolean isStraight(Set<Card> cards) {
        return false;
    }

    public boolean isFlush(Set<Card> cards) {
        Map<Suit, Integer> suitFrequencies = cards.stream().reduce(new HashMap<>(),
                this::suitFrequencyAccumulator, this::sumSuitFrequencyAccumulators);
        return suitFrequencies.values().contains(cards.size());
    }

    /**
     * Increments the frequency count for rank of the given card
     *
     * @param rankFrequencies map containing the running tally of rank frequencies
     * @param card            the card whose rank frequency is to be incremented
     * @return the updated frequency map
     */
    @Contract(pure = true)
    @NotNull
    private HashMap<CardRank, Integer> rankFrequencyAccumulator(
            @NotNull HashMap<CardRank, Integer> rankFrequencies,
            @NotNull Card card) {
        int frequency = rankFrequencies.getOrDefault(card.rank(), 0);
        rankFrequencies.put(card.rank(), frequency + 1);
        return rankFrequencies;
    }

    /**
     * Sums the rank frequency values from two accumulators
     *
     * @param x the first accumulator
     * @param y the second accumulator
     * @return a new accumulator containing the result of summing rank frequency from the two accumulators
     */
    @Contract(pure = true)
    @NotNull
    private HashMap<CardRank, Integer> sumRankFrequencyAccumulators(
            @NotNull HashMap<CardRank, Integer> x,
            @NotNull HashMap<CardRank, Integer> y) {
        HashMap<CardRank, Integer> sum = new HashMap<>();
        for (CardRank cardRank : CardRank.values()) {
            sum.put(cardRank, x.getOrDefault(cardRank, 0) + y.getOrDefault(cardRank, 0));
        }
        return sum;
    }

    /**
     * Increments the frequency count for suit of the given card
     *
     * @param suitFrequencies map containing the running tally of suit frequencies
     * @param card            the card whose suit frequency is to be incremented
     * @return the updated frequency map
     */
    @Contract(pure = true)
    @NotNull
    private HashMap<Suit, Integer> suitFrequencyAccumulator(
            @NotNull HashMap<Suit, Integer> suitFrequencies,
            @NotNull Card card) {
        int frequency = suitFrequencies.getOrDefault(card.suit(), 0);
        suitFrequencies.put(card.suit(), frequency + 1);
        return suitFrequencies;
    }

    /**
     * Sums the suit frequency values from two accumulators
     *
     * @param x the first accumulator
     * @param y the second accumulator
     * @return a new accumulator containing the result of summing suit frequency from the two accumulators
     */
    @Contract(pure = true)
    @NotNull
    private HashMap<Suit, Integer> sumSuitFrequencyAccumulators(
            @NotNull HashMap<Suit, Integer> x,
            @NotNull HashMap<Suit, Integer> y) {
        HashMap<Suit, Integer> sum = new HashMap<>();
        for (Suit suit : Suit.values()) {
            sum.put(suit, x.getOrDefault(suit, 0) + y.getOrDefault(suit, 0));
        }
        return sum;
    }
}