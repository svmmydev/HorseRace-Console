package controller;

import model.PlayerManager;
import model.player.Human;
import view.ConsoleView;
import model.GameHorsesRace;
import model.deck.Card;

/**
 * The GameController class serves as the main controller for the horses racing game.
 *  It coordinates interactions between the model classes (PlayerManager, GameHorsesRace) and the view classes (ConsoleView).
 *  This class follows the Model-View-Controller (MVC) design pattern.
 */
public class GameController {
    private PlayerManager playerManager;
    private GameHorsesRace gameHorsesRace;
    private ConsoleView consoleView;

    /**
     * Constructs a GameController with the specified PlayerManager, GameHorsesRace,
     * and ConsoleView instances.
     * @param playerManager the PlayerManager instance to manage player data and actions
     * @param gameHorsesRace the GameHorsesRace instance to handle the horse racing logic
     * @param consoleView the ConsoleView instance to manage console input and output
     */
    public GameController(PlayerManager playerManager, GameHorsesRace gameHorsesRace, ConsoleView consoleView) {
        this.playerManager = playerManager;
        this.gameHorsesRace = gameHorsesRace;
        this.consoleView = consoleView;
    }

    /**
     * The entry point of the Horse Racing Game:
     * Through the model and the view classes, manages the flow of the game, and coordinates both.
     * This allows for a structured and enjoyable game.
     */
    public void init() {
        consoleView.displayWelcomeMessage();

        // asks for user input to set up both the human and bot players.
        preparePlayers();

        while (!playerManager.isGameOver()) {
            gameHorsesRace.prepareDeck(); // Prepares deck and possible bets por the players
            setUpBets(); // set up the bets for current round.
            consoleView.displayPlayersRanking(playerManager.getPlayers());

            Card winner = playRace(); // Run race and get winner card

            handleWinnerHorseRepercussions(winner); // Applies the repercussions of the winner card to the players.

            if (!consoleView.askIfKeepPlaying()) { // If user wants to end the game, this is the point.
                consoleView.sayGoodBye();
                return;
            }
        }
        concludeGame(); // Determines final results and close game.
    }

    /**
     * Through consoleView object, gets user inputs to put in place the players' bets for the next race.
     */
    private void setUpBets() {
        // Iterate through all the players
        for (int i = 0; i< playerManager.playerCount(); i++) {

            if (playerManager.indexPlayerIsHuman(i)) {// if player is Human, ask por user input to set his bet.

                Card betCard = consoleView.askForBetCardToPlayer(gameHorsesRace.getHorses(),playerManager.getIndexPlayer(i));
                int maxBet = Math.min(playerManager.getMAX_BET(), playerManager.getIndexPlayerBankroll(i));
                int betAmount = consoleView.askForBetAmountToPlayer(playerManager.getIndexPlayer(i), playerManager.getMIN_BET(), maxBet);
                playerManager.indexHumanPlayerMakeBet(i, betAmount, betCard);

            } else { // If the player is Bot, pass down info, so it can make a bet
                playerManager.indexBotPlayerMakeBet(i, gameHorsesRace.getHorses());
                ConsoleView.showPlayerBet(playerManager.getIndexPlayer(i));
            }
        }
    }

    /**
     * Prepares the players for the game by gathering input through the console for human and bot players.
     * Ensures there is enough players to play a game but not more that the maximum allowed.
     */
    private void preparePlayers() {
        // Get an array with the names of the Human players from user input
        String[] humanNames = consoleView.getHumanNames(playerManager.getMIN_HUMANS(),playerManager.getMAX_PLAYERS());

        // Calculate how many Bot players can we have in the game based on how many humans we already have.
        int maxNumberOfBots = playerManager.getMAX_PLAYERS() - humanNames.length;

        // Calculate the minimum amount of bots we need to reach the minimum players quota
        int minNumberOfBots =  Math.max(0, playerManager.getMIN_PLAYERS() - humanNames.length);

        // get the amount of bots that will play from user input.
        int numberOfBots = consoleView.getNumberOfBots(minNumberOfBots, maxNumberOfBots);

        // Create the Players
        playerManager.setupPlayers(humanNames, numberOfBots);
    }

    /**
     * Handles the flow of the race and return the winning Card.
     * @return Card - The card that won the race
     */
    private Card playRace() {
        Card winner;

        gameHorsesRace.getReady(); // Set up the Board and the Deck.
        consoleView.displayBoard(gameHorsesRace.getBoard(), null);

        do {

            gameHorsesRace.takeTurn(); // take a turn and make the necessary changes to the state of the game.
            consoleView.displayBoard(gameHorsesRace.getBoard(), gameHorsesRace.getDrawnCard());

            winner = gameHorsesRace.getWinner(); // Checks if there is a winner after the turn.

        } while (winner==null); // It will continue while there is no winner.
        return  winner;
    }

    /**
     * Checks if any players won the bet and distributes the winnings.
     * @param winner the Card that determines who won the bet.
     */
    private void handleWinnerHorseRepercussions(Card winner) {
        ConsoleView.displayRaceWinner(winner); // Display the winning horse to the players.

        // Distribute bets based on the winner, also display if there are no winning bets.
        if (!playerManager.distributeBetsAfterRace(winner)) consoleView.displayNoWinningBets();
        // removes players with not enough bankroll to keep playing and display remaining players.
        if (playerManager.removeLosers()) consoleView.displaySomePlayersLostMessage();
        consoleView.displayPlayersRanking(playerManager.getPlayers());
    }

    /**
     * Checks if there is a human winner and sends an appropriate output for the user.
     */
    private void concludeGame() {
        Human winner = playerManager.getHumanWinner();
        if (winner==null) consoleView.announceDefeatAndSayGoodbye();
        else consoleView.announceWinnerAndSayGoodbye(winner);
    }
}
