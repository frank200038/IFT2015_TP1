import java.util.*;

/**
 * By Yan Zhuang and Diego Gonzalez
 *
 */

public class CardHand {
    private PositionalList<Card> hand; // Hand of cards
    private List<Position<Card>> positions; // Pivot positions of each suit of cards in hand

    public CardHand()
    {
        hand = new LinkedPositionalList<>();
        positions = new ArrayList<>();
    }

    /**
     * Add new card to the suit.
     *
     * @param r Value of the new card
     * @param s Suit of the new card
     *
     */
    public void addCard(int r,CardSuit s){
        Card newCard = createCard(r,s);

        Position<Card> position = returnSuitPosition(s);

        // Add card depends on if such card suit already exists or not
        if(position == null){
            positions.add(hand.addLast(newCard));
        } else {
            positions.set(positions.indexOf(position),hand.addAfter(position,newCard));
        }

    }

    /**
     * Helper method to create a new card
     *
     * @param r Value of the new card
     * @param s Suit of the new card
     * @return A new {@link Card}
     */
    private Card createCard(int r,CardSuit s){

        return new Card(s,r);
    }


    /**
     * Return a card of the desired suit. If the suit does not exist, choose another suit randomly that exists, and
     * return a card of that suit.
     *
     * @param s Desired suit
     * @return Removed card of the suit
     * @throws EmptyHandException if there is no cards in hand
     */
    public Card play(CardSuit s) throws EmptyHandException {
        Card toReturn;

        // Starting position of the desired suit in hand or null if doesn't exist
        Position<Card> position = returnSuitPosition(s);

        if (hand.isEmpty()){
            throw new EmptyHandException("The Hand is empty! Can't Play");
        }

        // If such suit doesn't exist, get a random suit
        if (position == null) {
            int random = new Random().nextInt(positions.size());

            position = positions.get(random);
        }

        //update the actual suit of to-be-removed card
        CardSuit suit = getSuit(position);

        // If the to-be-removed card is the last card in the list
        // Or being the last card of its suit. Need to remove from the position list (The suit doesn't exist anymore)
        if(hand.before(position) == null || !getSuit(hand.before(position)).equals(suit))
            positions.remove(position);
        else
            // Otherwise, update the card position in positions list
            positions.set(positions.indexOf(position), hand.before(position));

        toReturn = hand.remove(position);

        return toReturn;
    }

    // Private inner class for suitIterator. Returns all cards of the suit.
    private class SuitIterator implements Iterator<Card>{

        private Position<Card> currentPos;
        private Card currentCard = null;
        private CardSuit s;

        public SuitIterator(Position<Card> pos,CardSuit s){
            this.currentPos = pos;
            this.s = s;
        }

        @Override
        public boolean hasNext() {
           return currentPos != null;
        }

        @Override
        public Card next() {
            if (this.currentPos.getElement() == null)
                throw new NoSuchElementException("No such element");

            currentCard = currentPos.getElement();

            // If the to-be-removed card is the last card in the list
            // Or being the last card of its suit. No need to iterate next card in such case
            if(hand.before(currentPos) == null || !getSuit(hand.before(currentPos)).equals(s))
                currentPos = null;
            else
                currentPos = hand.before(currentPos);

            return currentCard;
        }
    }

    //Internal class that implements CardIterator. Make sure no cards are printed when they are no cards in hand
    private class CardIterator implements Iterator<Card>{

        Iterator<Card> it = hand.iterator();

        @Override
        public boolean hasNext() {
            return !hand.isEmpty() && it.hasNext();
        }

        @Override
        public Card next() {
            return it.next();
        }
    }

    /**
     * Return the {@code iterator} that iterates all cards in the hand
     * @return the {@code iterator} of all cards in the hand
     */
    public Iterator iterator() {
        return new CardIterator();
    }

    /**
     * Return the {@link SuitIterator} that iterates all the cards of the same suit
     * @param s the desired suit
     * @return {@link SuitIterator} of all cards of the same suit
     * @throws NoSuchElementException
     */
    public Iterator suitIterator(CardSuit s) throws NoSuchElementException{
        Position<Card> pos = returnSuitPosition(s);

        if (pos != null){
            return new SuitIterator(pos,s);
        } else{
            throw new NoSuchElementException("No such card suit in hand");
        }

    }


    /**
     * Auxiliary method to obtain the suit of a card easily
     * @param p position(container/node) of the card
     * @return The card suit of the card
     */
    private CardSuit getSuit(Position<Card> p){
        return p.getElement().getSuit();
    }


    /**
     * Auxiliary method to verify if a given suit exists in {@code positions} (If such suit does exist in the hand)
     * @param s desired suit
     * @return the position of the desired suit from {@code positions}, {@code null} if it doesn't exist
     */
    private Position<Card> returnSuitPosition(CardSuit s){
        for (Position<Card> p: positions){
            if(getSuit(p).equals(s)){
                return p;
            }
        }
        return null;
    }
}
