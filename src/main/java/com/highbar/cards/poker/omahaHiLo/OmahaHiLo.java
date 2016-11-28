package com.highbar.cards.poker.omahaHiLo;

import com.highbar.cards.Card;
import com.highbar.cards.Cards;
import com.highbar.cards.poker.HandRank;
import com.highbar.cards.poker.HighHandClassifier;
import com.highbar.cards.poker.Low8Classifier;
import com.highbar.cards.poker.RankedHand;
import com.highbar.util.Lists;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class OmahaHiLo {
    public static void main(String[] args) {
        String[] ss = {
                "HandA:Ac-Kd-Jd-3d HandB:5c-5d-6c-7d Board:Ah-Kh-5s-2s-Qd",
                "HandA:Ac-Kd-Jd-3d HandB:5c-5d-6c-6d Board:Ad-Kh-5s-2d-Qd",
                "HandA:Qc-Jd-Td-3d HandB:Tc-Jc-8h-6d Board:Ad-Kh-Qs-2d-3c",
                "HandA:Qh-4d-Tc-8s HandB:Qc-8c-7d-2h Board:Ad-As-3c-3d-5d",
                "HandA:Ah-2s-Qd-9S HandB:Ac-2d-6s-Jh Board:Kd-4h-Kh-5s-3c",
                "HandA:Ah-2s-Qd-9S HandB:Ac-2d-As-Jh Board:Kd-4h-Kh-5s-3c",
                "HandA:6d-6c-Kc-4d HandB:Jh-Js-Qs-8h Board:2s-3h-9c-As-Ac",
                "HandA:6d-Kh-Ac-4d HandB:Jh-2s-Ah-8h Board:Js-3h-9c-As-6c",
                "HandA:Qc-Jd-Td-3d HandB:3s-3h-8h-6d Board:Ad-Kh-Qs-Qd-3c"
        };

        Arrays.stream(ss).forEach(s -> {
            System.out.println(s);

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
            RankedHand aHigh = bestHighHand(handAPermutations, highHand);
            RankedHand bHigh = bestHighHand(handBPermutations, highHand);

            int hiResult = aHigh.compareTo(bHigh);

            if (hiResult > 0) printHiWinner('A', aHigh.rank());
            else if (hiResult < 0) printHiWinner('B', bHigh.rank());
            else System.out.print("=> Split Pot Hi (" + aHigh.rank() + ");");

            Low8Classifier lowHand = new Low8Classifier();
            Optional<RankedHand> aLow = bestLow8Hand(handAPermutations, lowHand);
            Optional<RankedHand> bLow = bestLow8Hand(handBPermutations, lowHand);

            if (aLow.isPresent() && bLow.isPresent()) {
                RankedHand a = aLow.get();
                RankedHand b = bLow.get();
                int loResult = a.compareTo(b);
                if (loResult < 0) printLoWinner(a, 'A');
                else if (loResult > 0) printLoWinner(b, 'B');
                else System.out.println("\t\tSplit Pot Lo (" + outString(a) + ")");
            }
            else if (aLow.isPresent()) printLoWinner(aLow.get(), 'A');
            else if (bLow.isPresent()) printLoWinner(bLow.get(), 'B');
            else System.out.println("\t\tNo hand qualified for Low");
            System.out.println();
        });
    }

    private static void printHiWinner(char handId, HandRank rank) {
        System.out.print("=> Hand" + handId + " wins Hi (" + rank + ");");
    }

    private static void printLoWinner(RankedHand a, char handId) {
        System.out.println("\t\tHand" + handId + " wins Lo (" + outString(a) + ")");
    }

    private static String outString(RankedHand a) {
        return a.toString().replaceAll("[cdhs-]", "");
    }

    private static Optional<RankedHand> bestLow8Hand(List<List<Card>> permutations, Low8Classifier lowHand) {
        List<RankedHand> low8s = permutations.stream()
                .map(lowHand::low8Hand)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .sorted()
                .collect(Collectors.toList());

        return low8s.size() != 0
                ? Optional.of(low8s.get(0))
                : Optional.empty();
    }

    private static RankedHand bestHighHand(List<List<Card>> permutations, HighHandClassifier highHand) {
        return permutations.stream()
                .map(highHand::highHand)
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList())
                .get(0);
    }
}
