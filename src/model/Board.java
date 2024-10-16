package model;

import model.deck.Card;

public class Board {
    Card[][] board;
    int trackLength;

    public Card[][] getBoard() {
        return board;
    }

    public int getTrackLength() {
        return trackLength;
    }
}
