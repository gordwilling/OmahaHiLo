import java.util
import java.util.stream.Collectors

import com.highbar.cards.Card
import com.highbar.cards.CardRank
import com.highbar.cards.Suit

val xs = new util.ArrayList[Card]()
xs.add(new Card(CardRank.Three, Suit.Diamonds))
xs.add(new Card(CardRank.Two, Suit.Spades))
xs.add(new Card(CardRank.Three, Suit.Spades))
xs.add(new Card(CardRank.Two, Suit.Hearts))
xs.add(new Card(CardRank.Two, Suit.Clubs))

val map = xs.stream().collect(Collectors.groupingBy(x => x.rank))



