package org.practica.model;

/**
 * La clase FacedCard representa cualquier carta con figura/cara de cualquier palo. Extiende a Card
 */
public class FacedCard extends Card{

    private CardFace face;

    /**
     * Constructor para crear las cartas con su palo y su valor. El valor será siempre 0.5f.
     *
     * @param cardFace CardFace: Figura/cara de carta
     * @param pal CardSuit: Palo de la carta
     */
    public FacedCard(CardFace cardFace, CardSuit pal) {
        super.value = 0.5f;
        super.suit = pal;
        this.face = cardFace;
    }

    /**
     *  ToString method to display card data
     * @return --> Num, Pal i valor
     */
    @Override
    public String toString() {
        return super.toString(this.face.toString());
    }

    @Override
    public String getCardCode() {
        return this.face.toString()+"_"+suit;
    }
}
