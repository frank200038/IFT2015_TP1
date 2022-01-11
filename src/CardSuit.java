public enum CardSuit {
    HEART(0),
    DIAMOND(0),
    CLUB(1),
    SPADE(1);


    private int index;

    CardSuit(int index){
        this.index = index;
    }

    public int index() {
        return index;
    }
}
