package model.player;

import model.deck.Card;

import java.util.ArrayList;

public class Bot extends Player {

    public Bot(String userName, int bankroll) {
        super(userName, bankroll);
    }

    public void makeBet(ArrayList<Card> betOptions, int maxBet, int minBet ) {
        Card chosenCard = betOptions.get(generateRandomChoice(0, betOptions.size()-1));
        maxBet = Math.min(maxBet, this.getBankroll());
        int betAmount = generateRandomChoice(minBet, maxBet);
        setBet(new Bet(betAmount, chosenCard));
    }
    private int generateRandomChoice(int min, int max) {
        return min + (int)(Math.random() * (max - min + 1));
    }
}
