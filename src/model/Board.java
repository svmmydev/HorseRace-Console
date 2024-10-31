package model;

import model.deck.Card;

import java.util.ArrayList;

/**
 * Represents a game board for the horse racing game.
 * <p>
 * The Board is initialized with a specified number of horses and a track length.
 * It manages the positions of the horses on the track, allowing them to advance
 * or retreat as the game progresses. It also checks for a winner when a horse
 * reaches the end of the track.
 */
public class Board {
    private Card[][] board;
    private int trackLength;


    /**
     * Construct a Board with the specified horses and track length.
     *
     * @param horses The list of horses to place on the board.
     * @param trackLength The length of the track.
     */
    public Board(ArrayList<Card> horses, int trackLength) {
        this.trackLength = trackLength;
        this.board = new Card[horses.size()][trackLength];
        initializeBoard(horses);
    }


    /**
     * Initializes the board by placing the horses at the starting position.
     *
     * @param horses The list of horses to be placed on the board.
     */
    private void initializeBoard(ArrayList<Card> horses) {
        for (int i = 0; i < horses.size(); i++) {
            board[i][0] = horses.get(i);
        }
    }

    /**
     * Retreats the horse in the specified row by moving it one position backward.
     *
     * @param row The row of the horse to be retreated.
     */
    public void retreatHorse(int row) {
        // Check if the horse is in the first position
        if (board[row][0] != null) {
            return;
        }

        for (int col = 1; col < trackLength; col++) {
            if (board[row][col] != null) {
                board[row][col - 1] = board[row][col];
                board[row][col] = null;
                return;
            }
        }
    }

    /**
     * Advances the horse in the specified row by moving it ine position forward.
     *
     * @param row The row of the horse to be advanced.
     */
    public void advanceHorse(int row) {
        for (int col = 0; col < trackLength - 1; col++) {
            if (board[row][col] != null) {
                board[row][col + 1] = board[row][col];
                board[row][col] = null;
                return;
            }
        }
    }

    /**
     * Check the state of the game to see if there is a winner.
     *
     * @return The winning card if there is a winner, null otherwise
     */
    public Card checkWinner() {
        for (Card[] cards : board) {
            if (cards[trackLength - 1] != null) {
                return cards[trackLength - 1];
            }
        }
        return null;
    }

    /**
     * Gets the current state of the board
     *
     * @return A 2D array representing the board.
     */
    public Card[][] getBoard() {
        return board;
    }

    /**
     * Gets the length of the track.
     *
     * @return The length of the track.
     */
    public int getTrackLength() {
        return trackLength;
    }
}
