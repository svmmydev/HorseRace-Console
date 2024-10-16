package model;

import model.deck.Card;
import model.deck.CardSuit;

public class DeprecatedGameBoard {
    //fields
    private Card[][] board;
    private Card lastCard;


    //constructor
    public DeprecatedGameBoard(Card[] cards, int rows) {

        board = new Card[cards.length][rows];
        for (int i = 0; i < cards.length; i++) {
            board[i][0] = cards[i];
        }
        printBoard();

    }
    public boolean executeMovement(Card card, boolean forward) {
        int cardColumn = -1;
        int cardRow = -1;
        lastCard = card;
        // Gets the suit that determines what cards should move
        CardSuit suitToMove = card.getSuit();

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] != null && board[i][j].getSuit() == suitToMove) { // Adds extra conditions based on suit
                    cardColumn = i;
                    cardRow = j;
                    break;
                }
            }
            if (cardColumn != -1) break; // Sale del bucle cuando se encuentra la carta
        }
        if (cardColumn == -1 || cardRow == -1) {
            return false;
        }
        int newRow = forward ? cardRow + 1 : cardRow - 1; // Calculates new position based on boolean parameter
        if (newRow >= board[0].length) {
            printBoard();
            return true;
        }
        board[cardColumn][cardRow] = null;
        if (newRow >= 0) {
            board[cardColumn][newRow] = card;
        }
        printBoard();
        return false;
    }
    private void printBoard() {
        System.out.println("Estado actual del tablero:");
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] != null) {
                    System.out.print("C "); // C de carta
                } else {
                    System.out.print(". "); // Punto vacío
                }
            }
            System.out.println();
        }
        System.out.println("Última carta jugada: " + lastCard);
    }
}




