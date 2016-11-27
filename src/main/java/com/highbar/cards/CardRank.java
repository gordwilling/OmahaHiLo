package com.highbar.cards;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Optional;

public enum CardRank implements HasSymbol {
    Ace("A"),
    Two("2"),
    Three("3"),
    Four("4"),
    Five("5"),
    Six("6"),
    Seven("7"),
    Eight("8"),
    Nine("9"),
    Ten("T"),
    Jack("J"),
    Queen("Q"),
    King("K");

    private final String symbol;

    CardRank(@NotNull String symbol) {
        this.symbol = symbol;
    }

    @NotNull
    @Contract(pure = true)
    @Override
    public String symbol() {
        return symbol;
    }

    @NotNull
    @Contract(pure = true)
    public static CardRank from(@NotNull String s) {
        Optional<CardRank> opt = Arrays.stream(values()).filter(x -> x.symbol.equals(s)).findFirst();
        if (opt.isPresent()) return opt.get();
        throw new IllegalArgumentException(s);
    }

    @NotNull
    @Contract(pure = true)
    @Override
    public String toString() {
        return symbol;
    }
}