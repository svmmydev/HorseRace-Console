package model;

import model.deck.Card;
import model.deck.CardSuit;
import model.deck.CardsDeck;
import model.deck.FacedCard;
import java.util.ArrayList;
import static model.deck.CardFace.KING;
import static model.deck.CardFace.KNIGHT;

//Clase que gestiona el juego
public class GameHorsesRace {
    private Board board;
    private Card drawnCard;
    private final int raceLenght = 9;
    private CardsDeck cardsDeck;
    private Card card;
    private int currentTurn;
    private ArrayList<Card> cardsKnight;


    /**
     * Prepara el juego para comenzar un nueva partida.
     *Este mét-odo inicializa la baraja de cartas, modifica el mazo según las reglas
     * Configura el tablero para el juego, inicia el primer turno en 1
     */
    public void getReady() {
        cardsDeck = new CardsDeck();
        modificarBaraja();
        mostrarCartasCaballos();
        board = new Board(cardsKnight, raceLenght);
        currentTurn = 1;
        takeTurn();
    }

    /**
     * Este mét-odo saca una carta del mazo, mueve el caballo correspondiente al palo de la carta
     * extraída.
     * Incrementa el contador del turno.
     */
    public void takeTurn() {
        // TODO
        drawnCard = sacarCarta();
        moverCaballo(drawnCard);
        System.out.println(drawnCard);
        currentTurn++;
    }

    /**
     * Obtiene el estado del juego y dtermina si hay un ganador.
     * Si hay un ganador devuelve la carta ganadora.
     *
     * @return La carta ganadora si existe, null si no hay ganador.
     */
    public Card getWinner() {
        return board.checkWinner();
    }


    /**
     * Saca una carta de la baraja.
     * La carta extraída se devuelve para ser utilizada en el juego.
     *
     * @return Card La carta extraída del mazo.
     */
    public Card sacarCarta() {
        card = cardsDeck.getCardFromDeck();
        return card;
    }

    /**
     *Mueve el caballo correspondiente según la carta extraída.
     * Este mét-odo determina el caballo a mover basado en el palo de la carta extraída.
     * Dependiendo de si el turno implica avanzar o retroceder, llama al mét-odo adecuado.
     *
     * @param drawnCard La carta que se ha extraído.
     */
    public void moverCaballo(Card drawnCard) {
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
            }
        }
        if (horseIndex != -1) {
            if (seRetrocede()) {
                board.retrocederCaballo(horseIndex);
            } else {
                board.avanzarCaballo(horseIndex);
            }
        }
    }

    /**
     * Modifica la baraja según las reglas del juego "GameHorsesRace".
     * Este mét-odo realiza las siguientes acciones:
     * Mueve todas las cartas de caballos (KNIGHT) a una lista separada para su uso en el juego.
     * Elimina todas las cartas de reyes (KING) de la baraja, ya que no se necesitan en este juego.
     */
    public void modificarBaraja() {
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
     * Determina si es el turno en el cual se debe retroceder.
     *
     * @return boolean True si es el quinto turno, false en caso conrtario.
     */
    public boolean seRetrocede() {
        return (currentTurn % 5 == 0);
    }

    //Mostrar cartas de caballo
    public void mostrarCartasCaballos() {
        for (Card caballo : cardsKnight) {
            System.out.println(caballo);
        }
    }

    public ArrayList<Card> getHorses() {
        return new ArrayList<>(cardsKnight);
    }

    public Board getBoard() {
        return board;
    }

    public Card getDrawnCard() {
        return drawnCard;
    }
}





