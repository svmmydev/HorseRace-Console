package model;

import model.deck.Card;

import java.util.ArrayList;

public class Board {
    private Card[][] board;
    private int trackLength;


    public Board(ArrayList<Card> horses, int trackLength) {
        this.trackLength = trackLength;
        this.board = new Card[horses.size()][trackLength];
        initializeBoard(horses);
    }


    //Método para colocar los caballos al principio del tablero
    public void initializeBoard(ArrayList<Card> horses) {
        for (int i = 0; i < horses.size(); i++) {
            board[i][0] = horses.get(i);
        }
    }

    public void retrocederCaballo(int row) {
        for (int col = 0; col < trackLength - 1; col++) {
            if (board[row][col] != null) {
                board[row][col - 1] = board[row][col];
                board[row][col] = null;
                return;
            }
        }
    }

    //TODO agregar la metodo otra parámetro +1 o -1 que indique cuantas posiciones avanzar o retroceder
    //Mètodo para hacer avanzar o retroceder caballo
    public void avanzarCaballo(int row) {
        for (int col = 0; col < trackLength - 1; col++) {
            if (board[row][col] != null) {
                board[row][col + 1] = board[row][col];
                board[row][col] = null;
                return;
            }
        }
    }

    //Método para comprobar el estado del juego, si hay ganador
    public Card checkWinner() {
        for (int row = 0; row < board.length; row++) {
            if (board[row][trackLength - 1] != null) {
                return board[row][trackLength - 1];
            }
        }
        return null;
    }

    public Card[][] getBoard() {
        return board;
    }


    public int getTrackLength() {
        return trackLength;
    }
}
