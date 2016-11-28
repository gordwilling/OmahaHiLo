package com.highbar.cards;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.text.MessageFormat.format;

public class CardParser {

    private static String CardRankPattern = patternFrom(CardRank.values());

    private static String SuitPattern = patternFrom(Suit.values());

    @Contract(pure = true)
    @NotNull
    public static Card parse(@NotNull String s) {
        Pattern p = Pattern.compile(
                format("({0})({1})", CardRankPattern, SuitPattern),
                Pattern.CASE_INSENSITIVE);

        Matcher m = p.matcher(s);

        if (m.find()) {
            String rankToken = m.group(1);
            String suitToken = m.group(2);
            CardRank rank = CardRank.from(rankToken);
            Suit suit = Suit.from(suitToken);
            return new Card(rank, suit);
        } else throw new IllegalArgumentException(s);
    }

    @Contract(pure = true)
    @NotNull
    private static String patternFrom(@NotNull HasSymbol[] hs) {
        if (hs.length == 1) return hs[0].symbol();
        Optional<String> pattern = Arrays.stream(hs)
                .map(HasSymbol::symbol)
                .reduce((x, y) -> x + "|" + y);
        return pattern.isPresent() ? pattern.get() : "";
    }
}