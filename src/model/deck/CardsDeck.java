package model.deck;

import java.util.ArrayList;

/**
 * Represents a deck of cards for the game.
 * This class is responsible for creating a deck of cards,
 * managing the cards, and providing methods to draw and remove cards.
 */
public class CardsDeck {
    private ArrayList<Card> cardsDeck = new ArrayList<>(); // The Deck himself
    private int[] num = {1, 2, 3, 4, 5, 6, 7, 8, 9}; // The numbers who use to create Deck
    private CardSuit[] cardSuits = {CardSuit.GOLD, CardSuit.CLUBS, CardSuit.CUPS, CardSuit.SWORDS}; // The suits to create Deck
    private CardFace[] cardFaces = {CardFace.JACK, CardFace.KNIGHT, CardFace.KING}; // Figures/face to create Deck
    private ArrayList<Integer> cardNumber; //  To store the index of card who has been distributed yet

    /**
     * Constructor to create the cards deck every time you want to play
     * Create Cards from two vectors (number and suit) and add them to the deck ArrayList
     */
    public CardsDeck() {
        //  Object to safe a Card temporarily to use after
        Card card;
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
        cardNumber = new ArrayList<>();
    }


    /**
     * Returns the size of the card deck.
     *
     * @return The size of the cards deck.
     */
    public int getDeckSize() {
        return cardsDeck.size();
    }

    /**
     * Checks if the deck is empty by comparing the number of drawn cards
     * with the total number of cards in the deck.
     *
     * @return  true if all cards have been drawn and the deck is empty;
     *          false otherwise.
     */
    public boolean isDeckEmpty(){
        return cardNumber.size() == cardsDeck.size();
    }


    /**
     * Removes the specified card from the deck.
     *
     * @param card The card to be removed.
     */
    public void removeCard(Card card) {
        cardsDeck.remove(card);
    }


    /**
     * Sets the indices of the cards that have already been distributed.
     *
     * @param cardNumber The list of indices of cards that have been distributed.
     */
    public void setCardNumber(ArrayList<Integer> cardNumber) {
        this.cardNumber = cardNumber;
    }


    /**
     * Method Return a card from the card deck
     *
     * @return A card  from the deck
     */
    public Card getCardFromDeck() {
        Card drawCard;
        int cardIndex = checkDistributedCardNumbers();

        drawCard = cardsDeck.get(cardIndex);
        return drawCard;
    }


    /**
     * Helper method that returns a random card from Deck that has not been given before
     *
     * @return AN int number that represent the index from the deck
     */
    private int checkDistributedCardNumbers() {
        boolean found;
        int cardNumber;
        do {
            found = false;
            cardNumber = (int) (Math.random() * cardsDeck.size());
            if (!this.cardNumber.isEmpty()) {
                for (Integer x : this.cardNumber) {
                    if (cardNumber == x) {
                        found = true;
                        break;
                    }
                }
            }
        } while (found);
        this.cardNumber.add(cardNumber);
        return cardNumber;
    }


    /**
     * Returns the card at the specified index.
     *
     * @param i The index of the card.
     * @return The card at the specified index.
     */
    public Card getCardAt(int i) {
        return cardsDeck.get(i);
    }
}
