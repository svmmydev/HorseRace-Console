package model.deck;

import java.util.ArrayList;

public class CardsDeck {
    private ArrayList<Card> cardsDeck = new ArrayList<>(); // The Deck himself
    private int[] num = {1, 2, 3, 4, 5, 6, 7, 8, 9}; // The numbers who use to create Deck
    private CardSuit[] cardSuits = {CardSuit.GOLD, CardSuit.CLUBS, CardSuit.CUPS, CardSuit.SWORDS}; // The suits to create Deck
    private CardFace[] cardFaces = {CardFace.JACK, CardFace.KNIGHT, CardFace.KING}; // Figures/face to create Deck
    private Card card; //  Object to safe a Card temporarily to use after
    private ArrayList<Integer> numCartes; //  To store the index of card who has been distribute yet

    /**
     * Constructor to create the cards deck every time you want to play
     * Create Cards from two vectors (number and suit) and add them to the deck ArrayList
     */

    public CardsDeck() {
        for (int i = 0; i < num.length; i++) {
            for (int j = 0; j < cardSuits.length; j++) {
                card = new NumeredCard(num[i], cardSuits[j]);
                cardsDeck.add(card);
            }
        }
        for (CardFace face : cardFaces) {
            for (int j = 0; j < cardSuits.length; j++) {
                card = new FacedCard(face, cardSuits[j]);
                cardsDeck.add(card);
            }
        }

        numCartes = new ArrayList<>();
    }

    /**
     * Método para devolver el tamaño del mazo de cartas
     *
     * @return Devuelve el tamaño del ArrayList de cartas
     */
    public int getDeckSize() {
        return cardsDeck.size();
    }


    public CardFace[] getCardFaces() {
        return cardFaces;
    }

    public void removeCard(Card card) {
        cardsDeck.remove(card);
    }


    //Metodo para agregar los caballos al array de cartas que ya salieron
    public void setNumCartes(ArrayList<Integer> numCartes) {
        this.numCartes = numCartes;
    }


    //TODO realizar bucle < numero de cartas del mazo, devuelve carta que saco el crupier

    /**
     * Method Return a card from the card deck
     *
     * @return A card  from the deck
     */
    public Card getCardFromDeck() {
        Card cartadonada;
        int numcarta = comprovarNumCartes();

        cartadonada = cardsDeck.get(numcarta);
        return cartadonada;
    }


    /**
     * Helper method that returns a random card from Deck that has not been given before
     *
     * @return A int number that represent the index from the deck
     */
    private int comprovarNumCartes() {
        boolean found;
        int numcarta;
        do {
            found = false;
            numcarta = (int) (Math.random() * cardsDeck.size());
            if (!numCartes.isEmpty()) {
                for (Integer x : numCartes) {
                    if (numcarta == x) {
                        found = true;
                        break;
                    }
                }
            }
        } while (found);
        numCartes.add(numcarta);
        return numcarta;
    }

    //Metodo que devuelve la carta del indice indicado
    public Card getCardAt(int i) {
        return cardsDeck.get(i);
    }

    public void getCardsDeck() {
        for (Card i: cardsDeck){
            System.out.println(i);
        }
    }
}
