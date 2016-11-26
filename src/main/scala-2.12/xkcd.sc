import java.text.MessageFormat
import java.util.regex.Pattern

import com.highbar.cards.CardParser
import com.highbar.cards.Deck
import com.highbar.cards.poker.Dealer

val dealer = new Dealer
val deck = new Deck
val deal = dealer.omahaHiLo(deck)



val rp = CardParser.CardRankPattern
val sp = CardParser.SuitPattern
val cp = s"($rp)($sp)"


val t = MessageFormat.format("one {1} {2}", "this", "that")

val p = Pattern.compile("HandA:(.*?)-(.*?)-(.*?)-(.*?)\\sHandB:(.*?)-(.*?)-(.*?)-(.*?)\\sBoard:(.*?)-(.*?)-(.*?)-(.*?)-(.*)")
val m = p.matcher("HandA:Ac-Kd-Qd-8h-10c")
m.find()
val r1 = m.group(1)
val r2 = m.group(4)
val r3 = m.group(7)
val r4 = m.group(10)







