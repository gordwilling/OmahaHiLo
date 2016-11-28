package com.highbar.cards.poker.omahaHiLo;

import com.highbar.cards.Card;
import com.highbar.cards.Cards;
import com.highbar.cards.poker.HighHandClassifier;
import com.highbar.cards.poker.Low8HandClassifier;
import com.highbar.cards.poker.RankedHand;
import com.highbar.util.Lists;

import java.util.*;
import java.util.stream.Collectors;

public class OmahaHiLo {
    public static void main(String[] args) {
        String s = "HandA:Ac-Kd-Jd-3d HandB:5c-5d-6c-7d Board:Ah-Kh-5s-2s-Qd";
        Deal d = DealParser.parse(s);
        List<Card> handA = d.handA();
        List<Card> handB = d.handB();
        List<Card> board = d.board();

        List<List<Card>> handAOptions = Lists.choose2(handA);
        List<List<Card>> handBOptions = Lists.choose2(handB);
        List<List<Card>> boardOptions = Lists.choose3(board);

        List<List<Card>> handAPermutations = Cards.permute(handAOptions, boardOptions);
        List<List<Card>> handBPermutations = Cards.permute(handBOptions, boardOptions);

        HighHandClassifier highHand = new HighHandClassifier();
        RankedHand aHigh = handAPermutations.stream()
                .map(highHand::highHand)
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList())
                .get(0);

        RankedHand bHigh = handBPermutations.stream()
                .map(highHand::highHand)
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList())
                .get(0);

        Low8HandClassifier lowHand = new Low8HandClassifier();

        List<RankedHand> aLows = handAPermutations.stream()
                .map(lowHand::low8Hand)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .sorted()
                .collect(Collectors.toList());

        List<RankedHand> bLows = handBPermutations.stream()
                .map(lowHand::low8Hand)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .sorted()
                .collect(Collectors.toList());


        System.out.println(aHigh + "(" + aHigh.rank() + ")");
        System.out.println(bHigh + "(" + bHigh.rank() + ")");

        System.out.println("Number of low8 qualifiers for A: " + aLows.size());
        System.out.println("Number of low8 qualifiers for B: " + bLows.size());

        if (aLows.size() != 0 && bLows.size() != 0) {
            RankedHand aLow = aLows.get(0);
            RankedHand bLow = bLows.get(0);

        }

        System.out.println(aLows.get(0) + "(" + aLows.get(0).rank() + ")");
        System.out.println(bLows.get(0) + "(" + bLows.get(0).rank() + ")");

    }
}
