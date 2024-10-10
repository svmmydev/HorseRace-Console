package model;

public class GameBoard {
    //fields
    private Card[][] board;
    private Card lastCard;


    //constructor
    public GameBoard(Card[] cards, int rows) {

        board = new Card[cards.length][rows];
        for (int i = 0; i < cards.length; i++) {
            board[i][0] = cards[i];
        }

    }
    public boolean executeMovement(Card card, boolean forward) {
        int cardColumn = -1;
        int cardRow = -1;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == card) {
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
        int newRow = forward ? cardRow + 1 : cardRow - 1;
        if (newRow >= board[0].length) {
            lastCard = card;
            printBoard(card);
            return true;
        }
        board[cardColumn][cardRow] = null;
        if (newRow >= 0) {
            board[cardColumn][newRow] = card;
        }
        printBoard(card);
        return false;
    }
    private void printBoard(Card card) {
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
        System.out.println("Última carta jugada: " + card);
    }
}




