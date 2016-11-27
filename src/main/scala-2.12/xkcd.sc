import com.highbar.cards.Card
import com.highbar.cards.CardRank
import com.highbar.cards.Suit
import com.highbar.cards.poker.CardComparators
import com.highbar.cards.poker.HandEvaluator


val aceHighEvaluator = new HandEvaluator(CardComparators.aceHigh());

val h1 = aceHighEvaluator.rank(
  new Card(CardRank.Two, Suit.Diamonds),
  new Card(CardRank.Three, Suit.Diamonds),
  new Card(CardRank.Ace, Suit.Diamonds),
  new Card(CardRank.Five, Suit.Diamonds),
  new Card(CardRank.Four, Suit.Diamonds))

val h2 = aceHighEvaluator.rank(
  new Card(CardRank.Two, Suit.Diamonds),
  new Card(CardRank.Three, Suit.Diamonds),
  new Card(CardRank.Six, Suit.Diamonds),
  new Card(CardRank.Five, Suit.Diamonds),
  new Card(CardRank.Four, Suit.Diamonds))

println(h1.toString + "(" + h1.rank + ")")
println(h2.toString + "(" + h2.rank + ")")
