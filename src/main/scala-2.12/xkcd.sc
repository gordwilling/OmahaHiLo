import java.util

import com.highbar.cards.Card
import com.highbar.cards.CardRank
import com.highbar.cards.Suit
import com.highbar.cards.poker.HighHandClassifier
import com.highbar.util.Lists


val aceHighEvaluator = new HighHandClassifier()

val h1 = aceHighEvaluator.highHand(
  new Card(CardRank.Jack, Suit.Diamonds),
  new Card(CardRank.King, Suit.Diamonds),
  new Card(CardRank.Two, Suit.Clubs),
  new Card(CardRank.Two, Suit.Diamonds),
  new Card(CardRank.Five, Suit.Diamonds))

val h2 = aceHighEvaluator.highHand(
  new Card(CardRank.Queen, Suit.Diamonds),
  new Card(CardRank.Queen, Suit.Diamonds),
  new Card(CardRank.Jack, Suit.Diamonds),
  new Card(CardRank.Jack, Suit.Hearts),
  new Card(CardRank.Ten, Suit.Diamonds))

println(h1.toString + "(" + h1.rank + ")")
println(h2.toString + "(" + h2.rank + ")")
println(h1.compareTo(h2))

val _4C2 = Lists.choose2(util.Arrays.asList("A", "B", "C", "D"))
_4C2.forEach(println)

val _5C3 = Lists.choose3(util.Arrays.asList("A", "B", "C", "D", "E"))
_5C3.forEach(println)

