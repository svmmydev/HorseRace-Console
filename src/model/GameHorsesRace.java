package model;

import model.deck.Card;
import model.deck.CardSuit;
import model.deck.CardsDeck;
import model.deck.FacedCard;

import java.util.ArrayList;

import static model.deck.CardFace.KING;
import static model.deck.CardFace.KNIGHT;

/**
 * /**
 * * This class manages the horses race game.
 * * It controls the board, the cards, and the current turn of the game.
 * * It also handles the card deck and the knights' cards.
 */

public class GameHorsesRace {
    private Board board;
    private Card lastDrawnCard;
    private CardsDeck cardsDeck;
    private int currentTurn;
    private ArrayList<Card> cardsKnight;
    private final int RACE_LENGTH = 9;



    /**
     * Prepares the game to start a new round.
     * This method initializes the card deck, modifies the deck according to the rules,
     * sets up the board for the game, and starts the first turn at 1.
     */
    public void getReady() {
        cardsDeck = new CardsDeck();
        modifyDeck();
        showKnightCards();
        board = new Board(cardsKnight, RACE_LENGTH);
        currentTurn = 1;
    }

    /**
     * This method initializes the card deck, modifies the deck according to the rules.
     */
    public void prepareDeck() {
        cardsDeck = new CardsDeck();
        modifyDeck();
    }


    /**
     * This method draws a card from the deck, moves the horse corresponding to the suit of the drawn card,
     * and increments the turn counter.
     */
    public boolean takeTurn() {
        lastDrawnCard = drawCard();
        boolean directionMove = moveHorse(lastDrawnCard);
        currentTurn++;
        return directionMove;
    }


    /**
     * Gets the state of the game and determines if there is a winner.
     * If there is a winner, it returns the winning card.
     *
     * @return The winning card if it exists, null if there is no winner.
     */
    public Card getWinner() {
        return board.checkWinner();
    }


    /**
     * Draws a card from the deck.
     * The drawn card is returned for use in the game.
     *
     * @return Card The card drawn from the deck.
     */
    public Card drawCard() {
        return cardsDeck.getCardFromDeck();
    }


    /**
     * Moves the corresponding horse based on the drawn card.
     * This method determines which horse to move based on the suit of the drawn card.
     * Depending on whether the turn involves advancing or retreating, it calls the appropriate method.
     *
     * @param drawnCard The card that has been drawn.
     */
    public boolean moveHorse(Card drawnCard) {
        CardSuit suit = drawnCard.getSuit();
        int horseIndex = -1;
        switch (suit) {
            case GOLD: {
                horseIndex = 0;
                break;
            }
            case SWORDS: {
                horseIndex = 1;
                break;
            }
            case CUPS: {
                horseIndex = 2;
                break;
            }
            case CLUBS: {
                horseIndex = 3;
                break;
            }
        }
        if (isRetreating()) {
                board.retreatHorse(horseIndex);
                return false;
        }

        board.advanceHorse(horseIndex);
        return true;
    }


    /**
     * Modifies the deck according to the rules of the "GameHorsesRace".
     * This method performs the following actions:
     * Moves all knight cards (KNIGHT) to a separate list for use in the game.
     * Removes all king cards (KING) from the deck, as they are not needed in this game.
     */
    private void modifyDeck() {
        cardsKnight = new ArrayList<>();
        for (int i = cardsDeck.getDeckSize() - 1; i >= 0; i--) {
            Card card = cardsDeck.getCardAt(i);
            if (card instanceof FacedCard && ((FacedCard) card).getFace() == KNIGHT) {
                cardsKnight.add(card);
                cardsDeck.removeCard(card);
            } else if (card instanceof FacedCard && ((FacedCard) card).getFace() == KING) {
                cardsDeck.removeCard(card);
            }
        }
    }


    /**
     * Determines if it is the turn in which retreating should occur.
     *
     * @return boolean True if it is the fifth turn, false otherwise.
     */
    public boolean isRetreating() {
        return (currentTurn % 5 == 0);
    }


    /**
     * Displays all knight cards in the knight cards list.
     * TODO mover sout a clase ConsoleView
     */
    public void showKnightCards() {
        for (Card horse : cardsKnight) {
            System.out.println(horse);
        }
    }


    /**
     * Gets a copy of the list of knight cards.
     *
     * @return A list of knight cards.
     */
    public ArrayList<Card> getHorses() {
        return new ArrayList<>(cardsKnight);
    }


    /**
     * Get the game board.
     *
     * @return The current board.
     */
    public Board getBoard() {
        return board;
    }


    /**
     * Gets the card that has been draw in the current turn.
     *
     * @return The drawn card.
     */
    public Card getLastDrawnCard() {
        return lastDrawnCard;
    }
}





