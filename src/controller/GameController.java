package controller;

import view.ConsoleView;
import model.GameHorsesRace;
import model.GameManager;
import model.deck.Card;

public class GameController {
    private GameManager gameManager;
    private GameHorsesRace gameHorsesRace;
    private ConsoleView consoleView;

    public GameController(GameManager gameManager, GameHorsesRace gameHorsesRace, ConsoleView consoleView) {
        this.gameManager = gameManager;
        this.gameHorsesRace = gameHorsesRace;
        this.consoleView = consoleView;
    }

    public void init() {

        consoleView.displayWelcomeMessage();

        gameManager.setupPlayers(consoleView.getHumanNames(), consoleView.getNumberOfBots());

        while (!gameManager.isGameOver()) {
            setUpBets(); // set up the bets in the list of players that gameManager have
            consoleView.displayPlayersRanking(gameManager.getPlayers());

            // Game Start!
            gameHorsesRace.getReady(); // set up the Board and the Deck
            do {

            } while (gameHorsesRace.getWinner()==null);

        }


    }

    private void setUpBets() {
        for (int i=0; i<gameManager.playerCount(); i++) {
            if (gameManager.indexPlayerIsHuman(i)) {

                Card betCard = consoleView.askForBetCard(gameHorsesRace.getHorses());
                int maxBet = Math.min(gameManager.getMAX_BET(), gameManager.getIndexPlayerBankroll(i));
                int betAmount = consoleView.askForBetAmount(gameManager.getMIN_BET(), maxBet);

                gameManager.indexPlayerMakeBet(i, betAmount, betCard);
            } else {
                gameManager.indexPlayerMakeBet(i, gameHorsesRace);
            }
        }
    }
}
