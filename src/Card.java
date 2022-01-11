public class Card {
    private final CardSuit suit;
    private final int value;

    public Card(CardSuit suit, int value){
        this.suit = suit;
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public CardSuit getSuit() {return suit; }

    public String toString(){
        String showVal;

        switch (value){
            case 1: showVal = "ACE"; break;
            case 11: showVal = "JACK"; break;
            case 12: showVal = "QUEEN"; break;
            case 13: showVal = "KING"; break;
            default: showVal = value + ""; break;
        }

        return "Card Value = " + showVal + " , Card Suit = " + this.suit;
    }
}
