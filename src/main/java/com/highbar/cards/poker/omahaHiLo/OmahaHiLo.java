package com.highbar.cards.poker.omahaHiLo;

import com.highbar.cards.Card;
import com.highbar.cards.Cards;
import com.highbar.cards.poker.HandRank;
import com.highbar.cards.poker.HighHandClassifier;
import com.highbar.cards.poker.Low8Classifier;
import com.highbar.cards.poker.RankedHand;
import com.highbar.util.Lists;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class OmahaHiLo {
    public static void main(String[] args) {

        try (BufferedReader in = Files.newBufferedReader(Paths.get(args[0]));
             PrintWriter out = new PrintWriter(new FileWriter(args[1]))) {

            System.out.println("Started");
            long startMillis = System.currentTimeMillis();

            in.lines().filter(x -> x.length() != 0).forEach(x -> {

                Deal d = DealParser.parse(x);
                out.println(x);

                List<Card> handA = d.handA();
                List<Card> handB = d.handB();
                List<Card> board = d.board();

                List<List<Card>> handAOptions = Lists.nChoose2(handA);
                List<List<Card>> handBOptions = Lists.nChoose2(handB);
                List<List<Card>> boardOptions = Lists.choose3(board);

                List<List<Card>> handAPermutations = Cards.permute(handAOptions, boardOptions);
                List<List<Card>> handBPermutations = Cards.permute(handBOptions, boardOptions);

                HighHandClassifier highHand = new HighHandClassifier();
                RankedHand aHigh = bestHighHand(handAPermutations, highHand);
                RankedHand bHigh = bestHighHand(handBPermutations, highHand);

                int hiResult = aHigh.compareTo(bHigh);

                if (hiResult > 0) printHiWinner(out, 'A', aHigh.rank());
                else if (hiResult < 0) printHiWinner(out, 'B', bHigh.rank());
                else out.print("=> Split Pot Hi (" + aHigh.rank() + ");");

                Low8Classifier lowHand = new Low8Classifier();
                Optional<RankedHand> aLow = bestLow8Hand(handAPermutations, lowHand);
                Optional<RankedHand> bLow = bestLow8Hand(handBPermutations, lowHand);

                if (aLow.isPresent() && bLow.isPresent()) {
                    RankedHand a = aLow.get();
                    RankedHand b = bLow.get();
                    int loResult = a.compareTo(b);
                    if (loResult < 0) printLoWinner(out, a, 'A');
                    else if (loResult > 0) printLoWinner(out, b, 'B');
                    else out.println("\t\tSplit Pot Lo (" + outString(a) + ")");
                }
                else if (aLow.isPresent()) printLoWinner(out, aLow.get(), 'A');
                else if (bLow.isPresent()) printLoWinner(out, bLow.get(), 'B');
                else out.println("\t\tNo hand qualified for Low");
                out.println();
            });

            long durationMillis = System.currentTimeMillis() - startMillis;
            System.out.println("Finished in " + durationMillis + " milliseconds");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void printHiWinner(PrintWriter out, char handId, HandRank rank) {
        out.print("=> Hand" + handId + " wins Hi (" + rank + ");");
    }

    private static void printLoWinner(PrintWriter out, RankedHand a, char handId) {
        out.println("\t\tHand" + handId + " wins Lo (" + outString(a) + ")");
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
