# IFT2015 Devoir 1
<div>
<b> Course </b>: IFT2015 - Data Structure <br>
<b> Professor </b>: François Major  <br>
<b> Language used:</b> Java <br>
<b> Main Data Structure Used </b>: Linked Positional List
</div>

# Details:

Implement a CardHand class that supports a person arranging a group of cards in
his or her hand. The simulator should represent the sequence of cards using a
single positional list ADT (use the one that developed in the class) so that cards of
the same suit are kept together. Implement this strategy by means of four
“Fingers” into the hand, one for each of the suits of hearts, clubs, spades, and
diamonds, so that adding a new card to the person’s hand or playing a correct
card from the hand can be done in constant time. The class should support the
following methods:
<ul>
<li> addCard(r,s): add a new card with rank r and suit s to the hand. </li>
<li> Play(s): remove and return a card of suit s from the player’s hand; if there is
no card of suit s, then remove and return an arbitrary card from the hand. </li>
<li> Iterator(): return an iterator for all cards currently in the hand. </li>
<li> suitIterator(s): return an iterator for all cards of suit s that are currently in
the hand. </li>
