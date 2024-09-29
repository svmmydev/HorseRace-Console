package org.practica.model;

/**
 * La clase NumeredCard representa cualquier carta con número de cualquier palo. Extiende a Card
 */
public class NumeredCard extends Card{

    private int num;
    /**
     * Constructor para crear las cartas con su palo y su valor. El valor será igual al número de la carta.
     *
     * @param num int: Numero de carta
     * @param cardSuit CardSuit: Palo de la carta
     */
    public NumeredCard(int num, CardSuit cardSuit) {
        this.num = num;
        super.suit = cardSuit;
        super.value = num;
    }

    /**
     *  ToString method to display card data
     * @return --> Num, Pal i valor
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
