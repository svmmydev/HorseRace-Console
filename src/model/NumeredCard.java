package model;

/**
 * Class NumeredCard represent a card of any card´s suit with any number. Extend Card
 */
public class NumeredCard extends Card{

    private int num;
    /**
     * Constructor to create Cards with his suit and his number. the card value is the same like a number.
     *
     * @param num int: card number
     * @param cardSuit the suit of the card
     */
    public NumeredCard(int num, CardSuit cardSuit) {
        this.num = num;
        super.suit = cardSuit;
        super.value = num;
    }

    public String getDescription() {
        return this.num + " of " + super.suit;
    }

    /**
     *  ToString method to display card data(Number , Suit , Value)
     * @return String with  Num, suit and value of the card
     */
    @Override
    public String toString() {
        return super.toString(String.valueOf(this.num));
    }


    @Override
    public String getCardCode() {
        return this.num+"_"+suit;
    }

}
