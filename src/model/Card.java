package org.practica.model;

/**
 * Clase abstracta Card que representa una carta en un juego de naipes.
 * <p>Esta clase sirve como base para las subclases NumberedCard y FacedCard,
 * que extenderán su funcionalidad para representar cartas numéricas y con figuras (cara).</p>
 */
public abstract class Card {
    protected CardSuit suit;
    protected float value; // El valor de la carta, se asignará al llamar al constructor en la subclase

    /**
     * Getter Para el atributo value de un objeto de tipo Card
     * @return float value
     */
    public float getValue() {
        return value;
    }

    /**
     * Devuelve un String formateado indicando el número/cara de la carta y su palo, así como su valor.
     * <p>Diseñado para ser usado por las subclases al sobreescribir este mismo método.</p>
     * @param numberOrFace String: el número o cara de la carta
     * @return String con el número/cara de la carta y su palo, así como su valor.
     */
    protected String toString(String numberOrFace) {
       return String.format("%7s of %6s, value %.1f",numberOrFace,suit, value);
    }


    public abstract String getCardCode();

}
