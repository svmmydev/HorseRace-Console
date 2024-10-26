package view;

import model.Board;
import model.deck.Card;
import model.player.Human;
import model.player.Player;
import utils.ConsoleInOut;
import utils.Pause;

import java.util.ArrayList;
import java.util.HashSet;

public class ConsoleView {


    public static void displayRaceWinner(Card winner) { // TODO
    }

    /**
     * Displays through console the current bet of the player.
     * @param player Player: the player who's bet will be displayed.
     */
    public static void showPlayerBet(Player player) {
        System.out.println(
                player.getUserName()+" bid "+player.getBet().getBetAmount()+" chips to the "+player.getBet().getHorseBet().getDescription()+"."
        );
    }

    /**
     * Displays the welcome message through console when starting the app
     */
    public void displayWelcomeMessage() {
        System.out.println("Welcome to the Horses Race betting game!");
    }

    /**
     * Asks for user input to know the amount of human players that will participate in the game and their names.
     * The amount is limited by minHumans and maxPlayers.
     * Uses a HashSet to avoid repeated human names.
     * @param minHumans minimum amount of human players that the game needs
     * @param maxPlayers maximum amount of players (in this case humans) that the game accepts
     * @return String[]: an array with the name of the human players that will be participating
     */
    public String[] getHumanNames(int minHumans, int maxPlayers) {
        final String NAME_REGEXP = "[0-9A-Za-z._]{4,15}";
        HashSet<String> nameSet = new HashSet<>(); // To check for repeated human names
        int humanPlayersAmount = ConsoleInOut.getIntegerInRange(
                "How many human players will be playing?\n" +
                        "Minimum "+minHumans+" maximum "+maxPlayers+".",
                minHumans,
                maxPlayers
        );
        String[] humanNames = new String[humanPlayersAmount];
        String name;

        for (int i=0; i<humanPlayersAmount; i++) {

            do {
                name = ConsoleInOut.getStringWithRegex(
                        "Enter human player " + (i + 1) + " of " + humanPlayersAmount + " name:\n" +
                                "\"Length 4-15, letters, numbers, \\\".\\\" and \\\"_\\\"\"",
                        NAME_REGEXP
                );
                if (!nameSet.contains(name.toLowerCase())) {
                    humanNames[i] = name;
                    nameSet.add(name.toLowerCase());
                    break;
                }
                System.out.println("\""+name+"\" has already been chosen, chose another name.");
            } while (true);
        }

        return humanNames;
    }

    /**
     * Prompts the user to input how many bots will be playing as opponents in the game.
     * If maxBots is 0 it just displays a message and returns 0.
     * @param minBots int: minimum amount of bots the player can choose.
     * @param maxBots int: maximum amount of bots the player can choose.
     * @return int: the amount of bots that will be playing the game.
     */
    public int getNumberOfBots(int minBots, int maxBots) {

        if (maxBots<=0) {
            System.out.println("There is no room for bots, all players will be human");
            return 0;
        }
        return ConsoleInOut.getIntegerInRange(
                "How many AI controlled oponents do you want?\n" +
                        "Minimum "+minBots+" maximum "+maxBots+".",
                minBots,
                maxBots
        );
    }

    /**
     * Asks the player to choose a winning horse from the available options.
     * @param betOptions List of horses represented as Card objects.
     * @param player The player making the choice.
     * @return The Card object representing the chosen horse.
     */
    public Card askForBetCardToPlayer(ArrayList<Card> betOptions, Player player) {
        System.out.println(player.getUserName()+", it is your turn to choose:");
        displayBetOptions(betOptions);
        int chosenCardIndex = getPlayerChoice(betOptions.size());
        return betOptions.get(chosenCardIndex);
    }

    /**
     * Displays the available betting options (horses) to the player.
     * @param betOptions List of horses represented as Card objects.
     */
    private void displayBetOptions(ArrayList<Card> betOptions) {
        System.out.println("Choose a winning horse:");
        for (int i = 0; i < betOptions.size(); i++) {
            Card card = betOptions.get(i);
            System.out.println("[" + (i + 1) + "] " + card.getDescription());
        }
    }

    /**
     * Prompts the player for their choice based on the number of available options.
     * @param choicesAmount The total number of choices available.
     * @return The index of the chosen horse.
     */
    private int getPlayerChoice(int choicesAmount) {
        return -1 + ConsoleInOut.getIntegerInRange(
                "Choose a winning horse [1-" + choicesAmount + "]",
                1,
                choicesAmount
        );
    }

    /**
     * Asks the player to input a bet amount between minBet and maxBet.
     * @param player Player: the player making the choice.
     * @param minBet int: minimum bet the player can choose.
     * @param maxBet int: maximum bet the player can choose.
     * @return int: the bet amount in chips.
     */
    public int askForBetAmountToPlayer(Player player, int minBet, int maxBet) {
        return ConsoleInOut.getIntegerInRange(
                player.getUserName()+", you have "+player.getBankroll()+" chips.\n" +
                        "Please, choose a bet amount between "+minBet+" and "+maxBet+".",
                minBet,
                maxBet
        );
    }

    public void displayPlayersRanking(ArrayList<Player> players) { // TODO
    }

    /**
     * Displays the current state of the board with the positions of the horses and the last drawn card.
     * @param board Board: represents the current state of the board.
     * @param drawnCard Card: represents the card that was drawn if there is any.
     */
    public void displayBoard(Board board, Card drawnCard) {
        Card[][] boardArray = board.getBoard();
        int trackLength = board.getTrackLength();
        StringBuilder boardToPrint = new StringBuilder();

        for (int row=0; row<boardArray.length; row++) {
            for (int col=0; col<trackLength; col++) {
                if (boardArray[row][col]!=null) {
                    boardToPrint.append(
                            getRow(boardArray[row][col], col, trackLength)
                    ).append("\n");
                    break;
                }
            }
        }

        // If a drawn card is provided, display a message indicating what it was
        if (drawnCard != null) {
            boardToPrint.append("Drawn card: ").append(drawnCard.getDescription()).append("\n");
        }

        System.out.println(boardToPrint);

        Pause.untilEnter();  // Pauses until the user presses Enter
    }

    /**
     * Constructs a string representation of a specific row in the board,
     * including the horse's description and its position on the track.
     *
     * @param card Card: represents the card that races (usually a Knight).
     * @param col int: the current position of the card on the track.
     * @param trackLength int: the total length of the track.
     * @return StringBuilder: contains the formatted row representation ready to be printed.
     */
    private StringBuilder getRow(Card card, int col, int trackLength) {

        StringBuilder rowToPrint = new StringBuilder(String.format("%-20s", card.getDescription()));
        for (int i=0; i<trackLength; i++) {
            if (i==col) {
                rowToPrint.append(" [X]");
            } else {
                rowToPrint.append(" [ ]");
            }

        }
        return rowToPrint;
    }

    /**
     * Prompts the user to decide if he wants to keep playing the game.
     * If he inputs Y/y/S/s it means "yes" N/n means "no",
     * @return boolean: true if the user answer yes, false otherwise,
     */
    public boolean askIfKeepPlaying() {
        final String YES_CHARACTERS = "YySs";
        final String NO_CHARACTERS = "Nn";
        String answer = ConsoleInOut.getStringWithRegex(
                        "Do you want to keep playing the game? [Y] Yes [N] No",
                        "[" + YES_CHARACTERS + NO_CHARACTERS + "]");

        return YES_CHARACTERS.contains(answer);
    }

    /**
     * Displays a message indicating that no player guessed the winning horse.
     * The message also states that the current pot of chips will carry over to the next round.
     * @param pot int: the amount of chips in the pot that will stay for the next round.
     */
    public void displayNoWinningBets(int pot) {
        System.out.println("Nobody guessed the winning Horse, that means that the pot of "+pot+" chips stays for the next round!!");
        Pause.untilEnter();
    }

    /**
     * Displays a message indicating that at least one player does not have enough chips to place a bet in the next round.
     * The message informs that the leaderboard will be updated accordingly.
     */
    public void displaySomePlayersLostMessage() {
        System.out.println("It seems that at least one player doesn't have enough chips to bet in the next round.\n" +
                "The leader board will be updated accordingly.");
    }

    /**
     * Displays a farewell message to the player upon exiting the game.
     * This method thanks the player for participating and expresses hope
     * that they had an enjoyable experience.
     */
    public void sayGoodBye() {
        System.out.println("Thank you for playing.\n" +
                "We hope you had fun. \n" +
                "TJS:CS Team");
    }

    /**
     * Announces that all human players have been eliminated and that the game is over.
     * Displays a farewell message to the players.
     */
    public void announceDefeatAndSayGoodbye() {
        System.out.println("All human players have been eliminated!\n" +
                "This is GAME OVER");
        sayGoodBye();
    }

    /**
     * Announces the winner of the game and displays a congratulatory message.
     * @param winner Human: the human player who won the game.
     */
    public void announceWinnerAndSayGoodbye(Human winner) {
        System.out.println("Congratulations "+winner.getUserName()+"!!\n" +
                "You defeated all your opponents and emerged victorious!!");
        sayGoodBye();
    }
}
