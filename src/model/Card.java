package model;

/**
 * Class abstract that represent a card in a card game.
 * <p> This class run like a base of the subclass NumberedCard y FacedCard, that extend his funcionality to represent
 * numeric cards and cards with figures.</p>
 */
public abstract class Card {
    protected CardSuit suit;
    protected float value; // El valor de la carta, se asignará al llamar al constructor en la subclase

    /**
     * Getter to get atribute value of the object Card
     * @return  A float with card value
     */
    public float getValue() {
        return value;
    }

    /**
     * Return formatted String that show the card number/figure of the card, his suit and his value.
     * <p>Designed to be used by the subclasss to overwritte himself.</p>
     * @param numberOrFace String: the card number or card figure
     * @return String with the card number/figure of the card, his suit and his value.
     */
    protected String toString(String numberOrFace) {
       return String.format("%7s of %6s, value %.1f",numberOrFace,suit, value);
    }

    /**
     * Method to get the suit of the card and card Number or figure/face
     * @return String with the number or face/figure and suit
     */
    public abstract String getCardCode();

    /**
     * Returns a String with a Card description.
     * Examples: 4 of CUPS - KNIGHT of GOLD - 2 of SWORDS
     * @return String: Description format: "*face/number* of *suit*"
     */
    public abstract String getDescription();

}
