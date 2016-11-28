package com.highbar.cards.poker.omahaHiLo;

import com.highbar.cards.Card;
import com.highbar.cards.Cards;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.*;

import static com.highbar.util.Parameters.require;

/**
 * One deal of two-player Omaha Hi/Lo consisting of two 4-card holes and a 5-card common board
 */
public class Deal {
    private Set<Card> handA;
    private Set<Card> handB;
    private Set<Card> board;

    Deal(@NotNull Set<Card> handA, @NotNull Set<Card> handB, @NotNull Set<Card> board) {
        require(handA, handB, board);
        if (handA.size() != 4) throw new IllegalArgumentException();
        if (handB.size() != 4) throw new IllegalArgumentException();
        if (board.size() != 5) throw new IllegalArgumentException();
        this.handA = Collections.unmodifiableSet(handA);
        this.handB = Collections.unmodifiableSet(handB);
        this.board = Collections.unmodifiableSet(board);
    }

    @Contract(pure = true)
    @NotNull
    List<Card> handA() {
        return new ArrayList<>(handA);
    }

    @Contract(pure = true)
    @NotNull
    List<Card> handB() {
        return new ArrayList<>(handB);
    }

    @Contract(pure = true)
    @NotNull
    List<Card> board() {
        return new ArrayList<>(board);
    }

    @Contract(pure = true)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Deal that = (Deal) o;
        return Objects.equals(handA, that.handA) &&
                Objects.equals(handB, that.handB) &&
                Objects.equals(board, that.board);
    }

    @Contract(pure = true)
    @Override
    public int hashCode() {
        return Objects.hash(handA, handB, board);
    }

    @Contract(pure = true)
    @NotNull
    public String toString() {
        return "HandA:" + Cards.toString(handA) + " HandB:" + Cards.toString(handB) + " Board:" + Cards.toString(board);
    }
}
