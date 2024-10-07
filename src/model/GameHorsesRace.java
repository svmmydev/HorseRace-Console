package model;


import java.util.ArrayList;
import java.util.Scanner;

import static model.CardFace.KNIGHT;

//Clase que gestiona el juego
public class GameHorsesRace {
    private CardsDeck cardsDeck;
    private Card card;
    private int rondas;
    private ArrayList<Card> cardsKnight;
    private Card[][] board = new Card[4][9];
    Scanner input = new Scanner(System.in);


    public void gameRaceLogic() {

        cardsDeck = new CardsDeck();
        obtenerCartasCaballo();
        mostrarCartasCaballos();
        colocarCaballosTablero();
        //iniciarPartida();
        sacarCarta();
    }

    public void iniciarPartida() {

            //Card cardWin = sacarCarta();
            //moverCaballo(cardWin);
    }

    //Método para sacar cartas del mazo
    public Card sacarCarta() {
        for (int i = 0; i < cardsDeck.getDeckSize(); i++) {
            card = cardsDeck.getCardFromDeck();
            System.out.println("Ronda: " + (i + 1));
            System.out.println("El crupier ha sacado: ");
            System.out.println(card);
            return card;
        }
        return null;
    }

    //Método para obtener las cartas del tipo Caballo y guardarlas en un array
    public void obtenerCartasCaballo() {
        cardsKnight = new ArrayList<>();
        for (int i = 0; i < cardsDeck.getDeckSize(); i++) {
            Card card = cardsDeck.getCardAt(i);
            if (card instanceof FacedCard && ((FacedCard) card).getFace() == KNIGHT) {
                cardsKnight.add(card);
            }
        }
    }

    //Método para colocar los caballos al principio del tablero
    public void colocarCaballosTablero() {
        for (int i = 0; i < cardsKnight.size(); i++) {
            board[i][0] = cardsKnight.get(i);
        }
    }

    //Método para mover caballos segun la carta que sale
    public void moverCaballo(Card cardWin) {
        CardSuit suit = cardWin.getSuit();
        switch (suit) {
            case GOLD: {
                avanzarRetrocederCaballo(0, posicionActual(0));
                break;
            }
            case SWORDS: {
                avanzarRetrocederCaballo(1, posicionActual(1));
                break;
            }
            case CUPS: {
                avanzarRetrocederCaballo(2, posicionActual(2));
                break;
            }
            case CLUBS: {
                avanzarRetrocederCaballo(3, posicionActual(3));
            }
        }
    }

    //Metodo para recuperar posicion del caballo
    public int posicionActual(int row) {
        for (int i = 0; i < board[row].length; i++) {
            if (board[row][i] != null) {
                return i;
            }
        }
        return -1;
    }

    //Mètodo para hacer avanzar o retroceder caballo
    public Card avanzarRetrocederCaballo(int row, int columnActual) {
        if (columnActual < board[row].length - 1) {
            board[row][columnActual + 1] = board[row][columnActual];
            board[row][columnActual] = null;
            return null;
        } else {
            return board[row][columnActual];
        }
    }


    public void mostrarCartasCaballos() {
        for (Card caballo : cardsKnight) {
            System.out.println(caballo);
        }
    }
}


//objeto tablero/int de posiciones para cada caballo, valor entero que se pasa a la clases tablero para que pinte la posicion en el tablero
//array de objetos?

    /*
    Llamar a metodo que generera mazo
    Metodo que inserta a elementos caballos y reyes en el array que controla que cartas salieron del mazo

    Bucle
    Llamar a metodo que saca carta ganadora por cada ronda
    que obtiene carta ganadora, controla 5 ronda carta ganadora hace retroceder al caballo
    devuelve tipo de carta ganadroa y hace avanzar o retorceder a caballo del mismo tipo
    El metodo devuelve el tipo de carta,


    Metodo que con carta ganadora, avanza una posicion al caballo que tiene mismo tipo
    devolver caballo que completa todas las posiciones antes a la clase game manager:
             GameHorsesRace gameHorsesRace = new GameHorsesRace();
            //Card winnerHorse = gameHorsesRace.¿¿getWinner??();

    llamar a metodos de la clase tablero y pasarles los parametros(posicion caballo), para que dicho método printe
    el tablero por pantalla con posiciones de caballo
    */




