package view;

import model.Board;
import model.deck.Card;
import model.deck.CardSuit;
import model.player.Human;
import model.player.Player;
import utils.ConsoleInOut;
import utils.Pause;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Random;

/**
 * This class represents the console view for the Horse Race Betting game.
 * It provides various console output functionalities for interaction with users,
 * displaying messages, and gathering user input to control the game flow.
 * It is instantiated only in the GameController class, where all its methods will be called.
 * <hr>
 * <p><b>Responsibilities include:</b></p>
 * <ul>
 * <li>Displaying messages and prompts for player actions (such as placing bets, choosing horses).</li>
 * <li>Showing game information, including leaderboards, race updates, and game results.</li>
 * <li>Providing narration of the race's progress.</li>
 * </ul>
 * <p>Methods within this class are designed for different stages of the game, from setup
 * (player input and welcome messages) to game progression (displaying player actions,
 * leaderboard updates) and end of the game (winning announcements).</p>
 * <hr>
 * <p><b>Example usage:</b></p>
 * <pre>
 *     ConsoleView consoleView = new ConsoleView();
 *     consoleView.displayWelcomeMessage();
 *     consoleView.displayPlayersRanking(players);
 * </pre>
 * @see controller.GameController
 */
public class ConsoleView {


    public static void displayRaceWinner(Card winner) {
        System.out.println("And the winner is... the mighty " + winner.getDescription() + "! 🏆 Congratulations!");
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

    /**
     * Display the current ranking of players by their bankroll.
     * @param players The list of players to be displayed in the ranking.
     */
    public void displayPlayersRanking(ArrayList<Player> players) {
        System.out.println("This is the actual leaderboard:");
        Collections.sort(players);
        int i=1;
        for (Player player : players){

            System.out.println("["+(i++)+"] "+player.getUserName()+". Bankroll: "+player.getBankroll()+" chips.");
        }
        Pause.untilEnter();
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

    /**
     * Displays the current state of the board with the positions of the horses and the last drawn card.
     * @param board        Board: represents the current state of the board.
     * @param drawnCard    Card: represents the card that was drawn if there is any.
     * @param movesForward boolean: true if the movement of the last drawn card is forward, false otherwise.
     */
    public void displayBoard(Board board, Card drawnCard, boolean movesForward) {
        Card[][] boardArray = board.getBoard();
        int trackLength = board.getTrackLength();
        boolean isInFirstPosition;
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
            boardToPrint.append("The crupier draws the ").append(drawnCard.getDescription()).append("!!\n");
            isInFirstPosition = isHorseInFirstPosition(drawnCard, board);
            boardToPrint.append(narrateTurnResult(drawnCard, movesForward, isInFirstPosition)).append("\n");
        }

        System.out.println(boardToPrint);

        Pause.untilEnter();  // Pauses until the user presses Enter
    }

    /**
     * Checks if the horse with the same suit as the drawnCard is in the first position of the board.
     * @param drawnCard Card: The last drawn card
     * @param board Board: the board containing the state of the race.
     * @return boolean: true if the horse with the same suit as drawnCard is in the first position, false otherwise.
     */
    private boolean isHorseInFirstPosition(Card drawnCard, Board board) {
        CardSuit suit = drawnCard.getSuit();
        Card[][] boardArray = board.getBoard();

        // checks if there is a card with the same suit in the first column
        for (int row = 0; row < boardArray.length; row++) {
            if (boardArray[row][0] != null && boardArray[row][0].getSuit() == suit) {
                return true;  // the horse of the same suit as the las drawn card is in the first position
            }
        }
        return false;  // the horse is not in the first position
    }


    /**
     * Generates the narration of the horse's movement based on the drawn card and its position.
     * @param drawnCard Card: The last drawn card indicating which horse moves.
     * @param movesForward boolean: Indicates if the horse moves forward or backward.
     * @param isInFirstPosition boolean: Indicates if the horse is in the first position.
     * @return StringBuilder: contains the narration of the turn result.
     */
    private StringBuilder narrateTurnResult(Card drawnCard, boolean movesForward, boolean isInFirstPosition) {
        StringBuilder narration = new StringBuilder();
        String suitName = drawnCard.getSuit().name();  // Get the name of the suit

        if (movesForward) {
            if (isInFirstPosition) {
                narration.append(forwardFromFirstPosition());
            } else {
                narration.append(forwardNormal());
            }
        } else {
            if (isInFirstPosition) {
                narration.append(backwardsFromFirstPosition());
            } else {
                narration.append(backwardsNormal());
            }
        }
        return narration;
    }

    /**
     * Generates a narration indicating that a horse has moved forward from the first position on the board.
     * The narration will be chosen randomly from a predefined set of phrases.
     * @return StringBuilder containing a random narration about the horse's movement.
     */
    private StringBuilder forwardFromFirstPosition() {
        String[] phrases = {
                "The horse leaps forward from the starting line!",
                "Off to a great start, the horse moves ahead!",
                "The horse charges forward from its initial spot!",
                "With a burst of speed, the horse races ahead from the first position!",
                "The horse has broken free from the starting gate!"
        };
        Random random = new Random();
        int index = random.nextInt(phrases.length);
        return new StringBuilder(phrases[index]);
    }

    /**
     * Generates a narration indicating that a horse has moved forward normally on the board.
     * The narration will be chosen randomly from a predefined set of phrases.
     * @return StringBuilder containing a random narration about the horse's movement.
     */
    private StringBuilder forwardNormal() {
        String[] phrases = {
                "The horse moves steadily forward.",
                "With determination, the horse advances.",
                "The horse continues its journey, moving forward.",
                "A smooth stride forward for the horse.",
                "The horse picks up speed as it moves ahead."
        };
        Random random = new Random();
        int index = random.nextInt(phrases.length);
        return new StringBuilder(phrases[index]);
    }

    /**
     * Generates a narration indicating that the horse is attempting to move backward from the first position,
     * but cannot move because it is already at the start.
     * @return StringBuilder containing a random narration about the horse's movement.
     */
    private StringBuilder backwardsFromFirstPosition() {
        String[] phrases = {
                "The horse is at the starting line and cannot move backward.",
                "The horse tries to move backward but remains at the first position.",
                "The horse cannot retreat from the first position; it stays put.",
                "The horse attempts to go backward, but it's already at the beginning of the track.",
                "The horse realizes it can't move backward from the first position."
        };
        Random random = new Random();
        int index = random.nextInt(phrases.length);
        return new StringBuilder(phrases[index]);
    }
    private StringBuilder backwardsNormal() {
        String[] phrases = {
                "The horse gallops backward, making strategic moves.",
                "The horse takes a step back, reassessing its position.",
                "With a swift retreat, the horse moves back on the track.",
                "The horse gracefully backs away, looking for a better angle.",
                "In a tactical maneuver, the horse retreats to gain an advantage."
        };
        Random random = new Random();
        int index = random.nextInt(phrases.length);
        return new StringBuilder(phrases[index]);
    }

    /**
     * Announces the start of the race with a random message.
     */
    public void raceStartAnnouncement() {
        String[] announcements = {
                "Ladies and gentlemen, the race is about to begin! Get ready!",
                "And they're off! The race has officially started!",
                "It's time for the excitement to begin! The race starts now!",
                "Hold onto your hats! The horses are ready to race!",
                "The crowd goes wild as the race kicks off! Let the games begin!",
                "On your marks, get set, go! The race has started!",
                "With a loud cheer, the race is underway! May the best horse win!",
                "The gates have opened! The race is on!"
        };

        // Select a random announcement
        Random random = new Random();
        String randomAnnouncement = announcements[random.nextInt(announcements.length)];

        // Print the selected announcement
        System.out.println(randomAnnouncement+"\n");
        Pause.seconds(3);
    }
}

