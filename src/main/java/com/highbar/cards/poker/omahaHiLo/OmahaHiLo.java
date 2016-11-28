package com.highbar.cards.poker.omahaHiLo;

import com.highbar.cards.Card;
import com.highbar.cards.Cards;
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

            Low8Classifier lowHand = new Low8Classifier();

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

            System.out.println(d.toString());

            int hiResult = aHigh.compareTo(bHigh);
            if (hiResult > 0) {
                System.out.print("=> HandA wins Hi (" + aHigh.rank() + ");");
            } else if (hiResult < 0) {
                System.out.print("=> HandB wins Hi (" + bHigh.rank() + ");");
            } else {
                System.out.print("=> Split Pot Hi (" + aHigh.rank() + ");");
            }


            if (aLows.size() != 0 && bLows.size() != 0) {
                RankedHand aLow = aLows.get(0);
                RankedHand bLow = bLows.get(0);
                int loResult = aLow.compareTo(bLow);
                if (loResult < 0) {
                    System.out.println("\t\tHandA wins Lo (" + aLow.toString().replaceAll("[cdhs-]", "") + ")");
                } else if (loResult > 0) {
                    System.out.println("\t\tHandB wins Lo (" + bLow.toString().replaceAll("[cdhs-]", "") + ")");
                } else {
                    System.out.println("\t\tSplit Pot Lo (" + bLow.toString().replaceAll("[cdhs-]", "") + ")");
                }
            } else if (aLows.size() != 0) {
                RankedHand aLow = aLows.get(0);
                System.out.println("\t\tHandA wins Lo (" + aLow.toString().replaceAll("[cdhs-]", "") + ")");
            } else if (bLows.size() != 0) {
                RankedHand bLow = bLows.get(0);
                System.out.println("\t\tHandB wins Lo (" + bLow.toString().replaceAll("[cdhs-]", "") + ")");
            } else {
                System.out.println("\t\tNo hand qualified for Low");
            }
            System.out.println();
        });
    }
}
