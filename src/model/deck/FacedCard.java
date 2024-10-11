package model.deck;

/**
 * FacedCard Class represent any card with a figure of the deck. Extend to Card.
 */
public class FacedCard extends Card {

    private CardFace face;

    /**
     *
     * Constructor to creat the card with his suit and his value, who is 0.5 always.
     * @param cardFace CardFace: Figure/ face of the card.
     * @param pal CardSuit: The suit of the card
     */
    public FacedCard(CardFace cardFace, CardSuit pal) {
        super.value = 0.5f;
        super.suit = pal;
        this.face = cardFace;
    }

    public CardFace getFace() {
        return face;
    }

    public String getDescription() {
        return this.face + " of " + super.suit;
    }

    /**
     *  ToString method to display card data(Number , Suit , Value)
     * @return String with  Num, suit and value of the card
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
