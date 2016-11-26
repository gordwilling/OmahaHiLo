package com.highbar.cards;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

import static com.highbar.util.Parameters.require;

public class Card {
    private final CardRank rank;
    private final Suit suit;

    public Card(@NotNull CardRank rank, @NotNull Suit suit) {
        require(rank, suit);
        this.rank = rank;
        this.suit = suit;
    }

    @NotNull
    @Contract(pure = true)
    public CardRank rank() {
        return rank;
    }

    @NotNull
    @Contract(pure = true)
    public Suit suit() {
        return suit;
    }

    @Contract(pure = true)
    @Override
    public boolean equals(@Nullable Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return rank == card.rank && suit == card.suit;
    }

    @Contract(pure = true)
    @Override
    public int hashCode() {
        return Objects.hash(rank, suit);
    }

    @Override
    @NotNull
    @Contract(pure = true)
    public String toString() {
        return rank.toString() + suit.toString();
    }
}