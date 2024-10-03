package model;

import java.util.ArrayList;

    public class CardsDeck {
        private ArrayList<Card> cardsDeck = new ArrayList<>(); // The Deck himself
        private int [] num = {1, 2, 3, 4, 5, 6, 7}; // The numbers who use to create Deck
        private CardSuit[] cardSuits = {CardSuit.GOLD, CardSuit.CLUBS, CardSuit.CUPS, CardSuit.SWORDS}; // The suits to create Deck
        private CardFace[] cardFaces = {CardFace.JACK, CardFace.KNIGHT, CardFace.KING}; // Figures/face to create Deck
        private Card card; //  Object to safe a Card temporarily to use after
        private ArrayList<Integer> numCartes; //  To store the index of card who has been distribute yet

        /**
         * Constructor to create the cards deck every time you want to play
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
     * Method Return a card from the card deck
     *
     *
     * @return A card  from the deck
     */
    public Card getCardFromDeck(){
        Card cartadonada;
        int numcarta = comprovarNumCartes();

        cartadonada = cardsDeck.get(numcarta) ;
        return cartadonada;
    }



    /**
     * Helper method that returns a random card from Deck that has not been given before
     * @return A int number that represent the index from the deck
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
