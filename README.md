# OmahaHiLo

## Description

Two players are dealt hands of 4 cards each, and share a community board of 5 cards.

###### High Hand
Each player selects two cards from his hand and three cards from the board to create the best hand using high 
hand rules, after which the winner is selected.

###### Low Hand
Each player selects two cards from his hand and three cards from the board to create the best hand using low
8 rules, after which the winner is selected.

###### Program Input

A text file containing multiple lines in the following format is read by the program. Each line indicates the cards in 
play for a single round

```
HandA:Ac-Kd-Jd-3d HandB:5c-5d-6c-7d Board:Ah-Kh-5s-2s-Qd
```
## The Algorithm

###### Parsing the input

A line of input is parsed in two steps.

1. The 3 hands are parsed from the line of input, resulting in three strings, for example
 
```
   Ac-Kd-Jd-3d
   5c-5d-6c-7d
   Ah-Kh-5s-2s-Qd
```
   
2. These strings are then parsed, resulting in three lists of cards:
```
   [Ac, Kd, Jd, 3d]
   [5c, 5d, 6c, 7d]
   [Ah, Kh, 5s, 2s, Qd]
```   
###### Classifying the Poker Hand
   
Poker hands are evaluated according to rank, where the ranks are (from highest to lowest):
   
   - Straight Flush
   - 4-of-a-Kind
   - Full House
   - Flush
   - Straight
   - 3-of-a-Kind
   - Two Pair
   - One Pair
   - High Card
   
Two hands of the same rank are compared by examining the rankings of the individual elements in each hand.
For example, pair 9d-9c is higher than pair 2d-2c. Suit does not affect the ranking.  
   
Given a list of 5 cards, classifying the hand involves two 'groupBy' operations:
   
   1. First, group by Rank. For example:
```
      List [Ac, Kd, Ah, Kc, Qs] 
```
is converted into a map:
```
      A -> [Ac, Ah]      
      K -> [Kh, Kc]       
      Q -> [Qs]
```
   2. Then, group the values in that map by their size. For example:
```   
      1 -> [[Qs]]      
      2 -> [[Ac, Ah], [Kh, Kc]]      
      3 -> []      
      4 -> []
```
   In Java 8, both operations can be done in one expression:
      
  ```java
    Map<Integer, List<List<Card>>> groupByN(@NotNull List<Card> cards) {
        return cards.stream()
                .collect(Collectors.groupingBy(Card::rank))
                .values().stream()
                .filter(x -> x.size() != 0)
                .collect(Collectors.groupingBy(List::size));  
  ```
  
  As a side note, it is done more succinctly in Scala:
  
  ```scala
    def groupByN(cards: List[Card]) = cards.groupBy(_.rank).values.filter(_.nonEmpty).groupBy(_.size)
  ```  
  
Because there are only 4 suits of cards, there can be no more than 4-of-a-Kind, so the groupBy-_Group-Size_
map will have a maximum of 4 entries with keys 1 through 4. In the example, the resultant map indicates
a pair of Aces, a pair of Kings, and a Queen kicker. 

With the data in this arrangement, all poker hands can be identified using a simple decision tree:
      
   - Does group 1 contain 5 cards?
   
     - Yes
     
       - Are the cards in sequence?
        
          - Yes
          
            - Do the cards have the same suit?
            
              - Yes
              
                - **_Straight Flush_**
                
              - No 
              
                - **_Straight_**
          - No
          
            - Do the cards have the same suit?
            
              - Yes
              
                - **_Flush_**
                  
              - No
                
                - **_High Card_**
     - No
     
       - Does group 1 contain 3 elements and group 2 contain 1 element?
        
         - **_1 Pair_**           
        
       - Does group 1 contain 1 element and group 2 contain 2 elements?
           
         - **_2 Pair_**
         
       - Does group 1 contain 2 elements and group 3 contain 1 element?
         
         - **_3-of-a-Kind_**
         
       - Does group 2 contain 1 element and group 3 contain 1 element?
       
         - **_Full House_**
         
       - Does group 1 contain 1 element and group 4 contain 1 element?
       
         - **_Four-of-a-Kind_**
       
For Low 8 Rules, all cards must be different ranks, hands containing anything above an 8 do not 
qualify, and straights and flushes are ignored.  The decision tree for Low 8, then, is:    
   
   - Does group 1 contain 5 cards?
   
     - Yes
     
       - Are all cards 8 or lower?
        
          - Yes
          
            - **_High Card_**
            
          - No
          
            - **_Disqualified_**

     - No
     
       - **_Disqualified_**       
        
   
###### Selecting the Best Hand 

Every possible combination of 2 cards from the player hand with 3 cards from the board is tested to reveal the 
best hand. This involves (4 choose 2) combinations of player cards times (5 choose 3) combinations of board 
cards:

  (4 choose 2) x (5 choose 3) = 6 x 10 = 60 combinations    
   
Each type of Poker Hand is represented by an Enumeration, which packs a free comparator. In addition, comparators have 
been written to identify the highest hand between two hands of the same rank. Each of these comparators in turn, is 
configured by one of two single-card comparators: one for Ace high, and one for Ace low.  In High Hand rules, straights 
require special handling because an Ace can be used in a high or low straight.  
         
For High Hand, identifying the best hand involves sorting the list of candidates in reverse order, which results in the 
highest hand rising to the top of the list.
        
In Low 8, the list is sorted in order, and the lowest hand (the best hand) rises to the top of the list. One 
complication in Low 8, however, is that none of the 60 candidate choices my qualify as Low 8 hands, in which case
the player is disqualified from the round.
