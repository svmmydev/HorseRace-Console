package org.practica.model;

import java.util.ArrayList;

    public class CardsDeck {
        private ArrayList<Card> cardsDeck = new ArrayList<>(); // La baraja en sí
        private int [] num = {1, 2, 3, 4, 5, 6, 7}; // Los números que se usaran para crearla
        private CardSuit[] cardSuits = {CardSuit.GOLD, CardSuit.CLUBS, CardSuit.CUPS, CardSuit.SWORDS}; // Los palos que se usaran para crearla
        private CardFace[] cardFaces = {CardFace.JACK, CardFace.KNIGHT, CardFace.KING}; // Las figuras/rostros que se usaran para crearla
        private Card card; // Usada para guardar temporalmente una carta cuando realizamos operaciones con ella
        private ArrayList<Integer> numCartes; // Almacena los índices de las cartas que ya han sido repartidas del mazo

        /**
         * Method to create the cards deck every time you want to play
         * Create Cards from two vectors (number and suit) and add them to the deck ArrayList
         */
        public CardsDeck () {
            for (int i = 0; i < num.length; i++) {
                for (int j = 0; j < cardSuits.length; j++) {
                    card = new NumeredCard(num[i], cardSuits[j]);
                    cardsDeck.add(card);
                }
            }
            for (CardFace face : cardFaces){
                for (int j = 0; j < cardSuits.length; j++) {
                    card = new FacedCard(face, cardSuits[j]);
                    cardsDeck.add(card);
                }
            }

            numCartes = new ArrayList<>();
            // display deck
        /*for (Card i: cardsDeck){
            System.out.println(i);
        }*/

        }

    /**
     * Metode per repartir un nova carta aleatoria a la ma
     *
     * @return --> Carta donada
     */
    public org.practica.model.Card getCardFromDeck(){
        Card cartadonada;
        int numcarta = comprovarNumCartes();

        cartadonada = cardsDeck.get(numcarta) ;
        return cartadonada;
    }

    /*
    Opiniones sobre este método:
        - Si se terminan las cartas a sacar entra en bucle infinito. Entiendo que no se contempla porque el juego debería terminar antes
        - No veo la ventaja en usar +1 en el Random, sin él podríamos devolver numcarta sin el -1 y funcionaría igual
     */
    /**
     * Helper method that returns a random card from Deck that has not been given before
     * @return --> Card not given before
     */
    private int comprovarNumCartes (){
        boolean trobada ;
        int numcarta;
        do{
            trobada = false;
            numcarta = (int) (Math.random() * 40 + 1);
            if (numCartes.isEmpty()){
                trobada = false;
            }else {
                for (Integer x : numCartes) {
                    if (numcarta == x) {
                        //System.out.println("carta"+x+" cartaRandom"+numcarta);
                        trobada = true;
                    }
                }
            }
            //System.out.println(numcarta);
        }while(trobada);
        numCartes.add(numcarta-1);
        return numcarta-1;
    }
}
