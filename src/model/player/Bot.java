package model.player;

import model.GameHorsesRace;
import model.GameManager;
import model.deck.Card;

import java.util.ArrayList;

public class Bot extends Player {

    public Bot(String userName, int bankroll) {
        super(userName, bankroll);
    }

    public void makeBet(GameManager gameManager, GameHorsesRace gameHorsesRace) {
        ArrayList<Card> availableCards = gameHorsesRace.getHorses();
        Card chosenCard = availableCards.get((int) (Math.random() * availableCards.size()));
        int maxBet = Math.min(gameManager.getMAX_BET(), this.getBankroll());
        int betAmount = generateRandomBetAmount(gameManager.getMIN_BET(), maxBet);
        setBet(new Bet(betAmount, chosenCard));
    }
    private int generateRandomBetAmount(int min, int max) {
        return min + (int)(Math.random() * (max - min + 1));
    }
}
