package com.highbar.cards.poker.omahaHiLo;

import com.highbar.cards.Card;
import com.highbar.cards.CardParser;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DealParser {
    static public Deal parse(String s) {
        Pattern p = Pattern.compile(
                "HandA:(.*)-(.*)-(.*)-(.*)\\s" +
                        "HandB:(.*)-(.*)-(.*)-(.*)\\s" +
                        "Board:(.*)-(.*)-(.*)-(.*)-(.*)");

        Matcher m = p.matcher(s);
        if (m.find()) {
            if (m.groupCount() != 13) throw new IllegalArgumentException(s);
            List<Card> cards = new ArrayList<>();
            for (int i = 1; i <= m.groupCount(); i++) {
                String cardToken = m.group(i);
                cards.add(CardParser.parse(cardToken));
            }
            Set<Card> handA = new HashSet<>(cards.subList(0, 4));
            Set<Card> handB = new HashSet<>(cards.subList(4, 8));
            Set<Card> board = new HashSet<>(cards.subList(8, 13));
            return new Deal(handA, handB, board);
        } else throw new IllegalArgumentException(s);
    }
}
