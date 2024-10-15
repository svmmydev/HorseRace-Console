package model;


import model.deck.Card;
import model.deck.CardSuit;
import model.deck.CardsDeck;
import model.deck.FacedCard;

import java.util.ArrayList;
import java.util.Scanner;

import static model.deck.CardFace.KING;
import static model.deck.CardFace.KNIGHT;

//Clase que gestiona el juego
public class GameHorsesRace {
    private final int raceLenght = 8;
    private CardsDeck cardsDeck;
    private Card card;
    private int rondas;
    private ArrayList<Card> cardsKnight;
    private Card[][] board = new Card[4][9];
    Scanner input = new Scanner(System.in);


    public void gameRaceLogic() {

        //Creando baraja y mostrándola (Está completa)
        cardsDeck = new CardsDeck();
        System.out.println();
        cardsDeck.getCardsDeck();

        //Guardando caballos en arraylist y mostrándola (4 caballos)
        //Eliminando caballos y reyes de la baraja deseada para el juego
        modificarBaraja();
        System.out.println();

        //Mostrando de nuevo la baraja para comprobar que ya no tienen las cartas no deseadas
        mostrarCartasCaballos();
        System.out.println();

        //Colocando caballos en el tablero
        cardsDeck.getCardsDeck();
        colocarCaballosTablero();
        iniciarPartida();

    }

    public void iniciarPartida() {
        Card[] contestants = new Card[cardsKnight.size()]; // Set up a new array where the horses will be allocated
        contestants = cardsKnight.toArray(contestants); // fill the array with the horses that were in an ArrayList
        GameBoard gameBoard = new GameBoard(contestants, raceLenght); // Create a new GameBoard
        for (int i = 0; i < cardsDeck.getDeckSize(); i++) {
            Card cardWin = sacarCarta();
            input.nextLine();
            System.out.println("Crupier: " + cardWin);

            if (gameBoard.executeMovement(cardWin, i%5!=4)) { // (i%5!=4) will return false at i=4,9,14...
                break; // breaks loop if we have a winner horse
            }
        }
        //moverCaballo(cardWin);
    }

    //Método para sacar cartas del mazo
    public Card sacarCarta() {
            card = cardsDeck.getCardFromDeck();
            return card;
        }


    /**
     * Modifica la baraja según las reglas del juego "GameHorsesRace".
     * Este método realiza las siguientes acciones:
     * <ul>
     *   <li>Mueve todas las cartas de caballos (KNIGHT) a una lista separada para su uso en el juego.</li>
     *   <li>Elimina todas las cartas de reyes (KING) de la baraja, ya que no se necesitan en este juego.</li>
     * </ul>
     */
    public void modificarBaraja() {
        cardsKnight = new ArrayList<>();
        for (int i = cardsDeck.getDeckSize() - 1; i >= 0 ; i--) {
            Card card = cardsDeck.getCardAt(i);
            if (card instanceof FacedCard && ((FacedCard) card).getFace() == KNIGHT) {
                cardsKnight.add(card);
                cardsDeck.removeCard(card);
            } else if (card instanceof FacedCard && ((FacedCard) card).getFace() == KING) {
                cardsDeck.removeCard(card);
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

    //TODO agregar la metodo otra parámetro +1 o -1 que indique cuantas posiciones avanzar o retroceder
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

    public ArrayList<Card> getHorses() {
        return new ArrayList<>(cardsKnight);
    }

    public void getReady() { // TODO
    }
}


//objeto tablero/int de posiciones para cada caballo, valor entero que se pasa a la clases tablero para que pinte la posicion en el tablero
//array de objetos?

    /*


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




