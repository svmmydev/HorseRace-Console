package view;

import model.Board;
import model.deck.Card;
import model.deck.CardSuit;
import model.player.Human;
import model.player.Player;
import utils.Colors;
import utils.ConsoleInOut;
import utils.Pause;

import java.util.*;

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
 *
 * @see controllers.GameController
 */
public class ConsoleView {


    public void displayRaceWinner(Card winner) {
        String message = getSuitColor(winner);

        //Define a fixed width for the top and bottom frame
        int borderWidth = 81; //Adjust this value according to the desired width of the borders

        int leftPaddingSize = 5; // Adjust this value to increase the shift to the right

        String leftPadding = " ".repeat(leftPaddingSize);
        String paddedMessage = leftPadding + message; //Build the final message

        //Build the top and bottom borders
        String topBorder = Colors.colorize("╔" + "═".repeat(borderWidth - 2) + "╗", Colors.VIBRANT_YELLOW);
        String bottomBorder = Colors.colorize("╚" + "═".repeat(borderWidth - 2) + "╝", Colors.VIBRANT_YELLOW);


        System.out.println(topBorder);
        System.out.println(paddedMessage);
        System.out.println(bottomBorder + "\n");
    }

    /**
     * Generates a congratulatory message for the winning card, with the card's suit highlighted
     * in its corresponding vibrant color.
     *
     * @param winner the winning card, whose suit color will be used to highlight the message
     * @return a formatted message indicating the winner, with vibrant color styling based on suit
     */
    private String getSuitColor(Card winner) {
        String suitColor;
        switch (winner.getSuit()) {
            case SWORDS -> suitColor = Colors.VIBRANT_BLUE;
            case CUPS -> suitColor = Colors.VIBRANT_RED;
            case CLUBS -> suitColor = Colors.VIBRANT_GREEN;
            case GOLD -> suitColor = Colors.VIBRANT_YELLOW;
            default -> suitColor = Colors.RESET;
        }

        // Define the message with the color of the suit
        return Colors.colorize("And the winner is... the mighty ", Colors.VIBRANT_BLUE) +
                Colors.colorize(winner.getDescription(), suitColor) +
                Colors.colorize("! 🏆 Congratulations! 🏆", Colors.VIBRANT_BLUE);
    }

    /**
     * Displays through console the current bet of the player.
     *
     * @param player Player: the player who's bet will be displayed.
     */
    public static void showPlayerBet(Player player) {
        System.out.println(
                Colors.colorize(player.getUserName() + " bid ", Colors.BLUE) +
                        Colors.colorize(player.getBet().getBetAmount() + " \uD83C\uDF6A", Colors.VIBRANT_YELLOW) +
                        Colors.colorize(" chips to the ", Colors.BLUE) +
                        player.getBet().getHorseBet().getDescription() + // sin color
                        Colors.colorize(".", Colors.BLUE)
        );
    }


    public void displayGameName() {
        System.out.println(Colors.colorize("╔═════════════════════════════════════════════════════════════════════════════════╗", Colors.VIBRANT_RED));
        System.out.println(Colors.colorize("║                                                                                 ║", Colors.VIBRANT_RED));
        System.out.println(Colors.colorize("""
                ║        ______  __                              ________                         ║
                ║        ___  / / _________________________      ___  __ ______ ___________       ║
                ║        __  /_/ /_  __ __  _____  ____  _ \\     __  /_/ _  __ `_  ____  _ \\      ║
                ║        _  __  / / /_/ _  /   _(__  )/  __/     _  _, _// /_/ // /__ /  __/      ║
                ║        /_/ /_/  \\____//_/    /____/ \\___/      /_/ |_| \\__,_/ \\___/ \\___/       ║
                ║                                                                                 ║
                ║                                                                                 ║""", Colors.VIBRANT_RED));
        System.out.println(Colors.colorize("╚═════════════════════════════════════════════════════════════════════════════════╝", Colors.VIBRANT_RED));
    }

    /**
     * Displays the welcome message through console when starting the app
     */
    public void displayWelcomeMessage() {
        System.out.println(Colors.colorize("\n════════════════════════════════════════════════", Colors.ORANGE));
        System.out.println(Colors.colorize("🐴  Welcome to the Horses Race Betting Game! 🐴", Colors.GREEN));
        System.out.println(Colors.colorize("════════════════════════════════════════════════", Colors.ORANGE));
    }

    /**
     * Asks for user input to know the amount of human players that will participate in the game and their names.
     * The amount is limited by minHumans and maxPlayers.
     * Uses a HashSet to avoid repeated human names.
     *
     * @param minHumans  minimum amount of human players that the game needs
     * @param maxPlayers maximum amount of players (in this case humans) that the game accepts
     * @return String[]: an array with the name of the human players that will be participating
     */
    public String[] getHumanNames(int minHumans, int maxPlayers) {
        final String NAME_REGEXP = "[0-9A-Za-z._]{4,15}";
        HashSet<String> nameSet = new HashSet<>(); // To check for repeated human names
        int humanPlayersAmount = ConsoleInOut.getIntegerInRange(
                Colors.colorize("\n════════════════════════════════════════════════\n", Colors.YELLOW) +
                        Colors.colorize("How many human players will be playing?\n", Colors.BLUE) +
                        Colors.colorize(">> ", Colors.GREEN) +
                        Colors.colorize("Minimum " + minHumans + ", maximum " + maxPlayers + ".\n", Colors.YELLOW) +
                        Colors.colorize("════════════════════════════════════════════════", Colors.YELLOW),
                minHumans,
                maxPlayers
        );
        String[] humanNames = new String[humanPlayersAmount];
        String name;

        for (int i = 0; i < humanPlayersAmount; i++) {

            do {
                name = ConsoleInOut.getStringWithRegex(
                        Colors.colorize("\n════════════════════════════════════════════════\n", Colors.YELLOW) +
                                Colors.colorize("Enter human player " + (i + 1) + " of " + humanPlayersAmount + " name:\n", Colors.BLUE) +
                                Colors.colorize(">> ", Colors.GREEN) +
                                Colors.colorize("Length 4-15, letters, numbers, '.' and '_'.\n", Colors.YELLOW) +
                                Colors.colorize("════════════════════════════════════════════════", Colors.YELLOW),
                        NAME_REGEXP
                );
                if (!nameSet.contains(name.toLowerCase())) {
                    humanNames[i] = name;
                    nameSet.add(name.toLowerCase());
                    break;
                }
                System.out.println(Colors.colorize("\"" + name + "\" has already been chosen, chose another name.", Colors.RED));
            } while (true);
        }

        return humanNames;
    }

    /**
     * Prompts the user to input how many bots will be playing as opponents in the game.
     * If maxBots is 0 it just displays a message and returns 0.
     *
     * @param minBots int: minimum amount of bots the player can choose.
     * @param maxBots int: maximum amount of bots the player can choose.
     * @return int: the amount of bots that will be playing the game.
     */
    public int getNumberOfBots(int minBots, int maxBots) {

        if (maxBots <= 0) {
            System.out.println(Colors.colorize("There is no room for bots, all players will be human", Colors.RED));
            return 0;
        }
        return ConsoleInOut.getIntegerInRange(
                Colors.colorize("\n════════════════════════════════════════════════\n", Colors.YELLOW) +
                        Colors.colorize("How many AI-controlled opponents do you want?\n", Colors.BLUE) +
                        Colors.colorize(">> ", Colors.GREEN) +
                        Colors.colorize("Minimum " + minBots + ", maximum " + maxBots + ".", Colors.YELLOW) + "\n" +
                        Colors.colorize("════════════════════════════════════════════════", Colors.YELLOW),
                minBots,
                maxBots
        );

    }

    /**
     * Asks the player to choose a winning horse from the available options.
     *
     * @param betOptions List of horses represented as Card objects.
     * @param player     The player making the choice.
     * @return The Card object representing the chosen horse.
     */
    public Card askForBetCardToPlayer(ArrayList<Card> betOptions, Player player) {
        System.out.println(Colors.colorize("\n════════════════════════════════════════════════", Colors.YELLOW));
        System.out.println(Colors.colorize(player.getUserName() + ", it is your turn to choose:", Colors.BLUE));
        displayBetOptions(betOptions);
        int chosenCardIndex = getPlayerChoice(betOptions.size());
        return betOptions.get(chosenCardIndex);
    }

    /**
     * Displays the available betting options (horses) to the player.
     *
     * @param betOptions List of horses represented as Card objects.
     */
    private void displayBetOptions(ArrayList<Card> betOptions) {
        System.out.println(Colors.colorize("Choose a winning horse:", Colors.BLUE));
        for (int i = 0; i < betOptions.size(); i++) {
            Card card = betOptions.get(i);
            System.out.println(Colors.colorize(">> ", Colors.GREEN) + Colors.colorize("[" + (i + 1) + "] ", Colors.GREEN) + card.getDescription());
        }
    }

    /**
     * Prompts the player for their choice based on the number of available options.
     *
     * @param choicesAmount The total number of choices available.
     * @return The index of the chosen horse.
     */
    private int getPlayerChoice(int choicesAmount) {
        return -1 + ConsoleInOut.getIntegerInRange(
                Colors.colorize("Choose a winning horse [1-" + choicesAmount + "]", Colors.BLUE),
                1,
                choicesAmount
        );
    }

    /**
     * Asks the player to input a bet amount between minBet and maxBet.
     *
     * @param player Player: the player making the choice.
     * @param minBet int: minimum bet the player can choose.
     * @param maxBet int: maximum bet the player can choose.
     * @return int: the bet amount in chips.
     */
    public int askForBetAmountToPlayer(Player player, int minBet, int maxBet) {
        return ConsoleInOut.getIntegerInRange(
                Colors.colorize(player.getUserName() + ", you have ", Colors.BLUE) +
                        Colors.colorize(player.getBankroll() + " \uD83C\uDF6A", Colors.VIBRANT_YELLOW) +
                        Colors.colorize(" chips.\n", Colors.BLUE) +
                        Colors.colorize("Please, choose a bet amount between ", Colors.BLUE) +
                        Colors.colorize(minBet + " \uD83C\uDF6A", Colors.VIBRANT_YELLOW) +
                        Colors.colorize(" and ", Colors.BLUE) +
                        Colors.colorize(maxBet + " \uD83C\uDF6A", Colors.VIBRANT_YELLOW) +
                        Colors.colorize(".", Colors.BLUE),
                minBet,
                maxBet
        );
    }

    /**
     * Display the current ranking of players by their bankroll.
     *
     * @param players The list of players to be displayed in the ranking.
     */
    public void displayPlayersRanking(ArrayList<Player> players) {
        System.out.println();
        System.out.println(Colors.colorize("This is the actual leaderboard:", Colors.BLUE));
        Collections.sort(players);
        int i = 1;
        for (Player player : players) {
            System.out.println(
                    Colors.colorize("[" + (i++) + "] ", Colors.BLUE) +
                            Colors.colorize(player.getUserName(), Colors.ORANGE) +
                            Colors.colorize(" - Bankroll: ", Colors.BLUE) +
                            Colors.colorize(player.getBankroll() + " \uD83C\uDF6A", Colors.VIBRANT_YELLOW) +
                            Colors.colorize(" chips.", Colors.BLUE)
            );
        }
        Pause.untilEnter();
    }

    /**
     * Constructs a string representation of a specific row in the board,
     * including the horse's description and its position on the track.
     *
     * @param card        Card: represents the card that races (usually a Knight).
     * @param col         int: the current position of the card on the track.
     * @param trackLength int: the total length of the track.
     * @return StringBuilder: contains the formatted row representation ready to be printed.
     */
    private StringBuilder getRow(Card card, int col, int trackLength, int knightIndex) {

        String[] knightColors = {Colors.VIBRANT_BLUE, Colors.VIBRANT_RED, Colors.VIBRANT_GREEN, Colors.VIBRANT_YELLOW};
        String[] horseColors = {Colors.BLUE, Colors.RED, Colors.GREEN, Colors.YELLOW};

        String knightColor = knightColors[knightIndex % knightColors.length];
        String horseColor = horseColors[knightIndex % horseColors.length];

        StringBuilder rowToPrint = new StringBuilder(Colors.colorize(String.format("%-20s", card.getDescription()), knightColor));
        String horseSymbol = Colors.colorize("[ ♞ ]", horseColor);
        String emptyCell = "[    ]";
        for (int i = 0; i < trackLength; i++) {
            if (i == col) {
                rowToPrint.append(horseSymbol);
            } else {
                rowToPrint.append(emptyCell);
            }

        }
        return rowToPrint;
    }

    /**
     * Prompts the user to decide if he wants to keep playing the game.
     * If he inputs Y/y/S/s it means "yes" N/n means "no",
     *
     * @return boolean: true if the user answer yes, false otherwise,
     */
    public boolean askIfKeepPlaying() {
        final String YES_CHARACTERS = "YySs";
        final String NO_CHARACTERS = "Nn";
        String answer = ConsoleInOut.getStringWithRegex(
                Colors.colorize("Do you want to keep playing the game? [Y] Yes [N] No", Colors.BLUE),
                "[" + YES_CHARACTERS + NO_CHARACTERS + "]");

        return YES_CHARACTERS.contains(answer);
    }

    /**
     * Displays a message indicating that no player guessed the winning horse.
     * The message also states that the current pot of chips will carry over to the next round.
     *
     * @param pot int: the amount of chips in the pot that will stay for the next round.
     */
    public void displayNoWinningBets(int pot) {
        System.out.println(Colors.colorize("Nobody guessed the winning Horse, that means that the pot of " + pot + " chips stays for the next round!!", Colors.BLUE));
        Pause.untilEnter();
    }

    /**
     * Displays a message indicating that at least one player does not have enough chips to place a bet in the next round.
     * The message informs that the leaderboard will be updated accordingly.
     */
    public void displaySomePlayersLostMessage() {
        System.out.println(Colors.colorize("It seems that at least one player doesn't have enough chips to bet in the next round.\n", Colors.BLUE) +
                Colors.colorize("The leader board will be updated accordingly.", Colors.BLUE));
    }

    /**
     * Displays a farewell message to the player upon exiting the game.
     * This method thanks the player for participating and expresses hope
     * that they had an enjoyable experience.
     */
    public void sayGoodBye() {
        System.out.println(Colors.colorize("""
                \nThank you for playing!!
                We hope you had fun!!\s
                """, Colors.VIBRANT_PURPLE));

        //Final title with writing delay
        String title = """
                      O                                                                     O
                {o)xxx|===============-  "The Java Scrolls: Code Assassins"  -===============|xxx(o}
                      O                                                                     O""";
        for (char c : title.toCharArray()) {
            System.out.print(Colors.colorize(String.valueOf(c), Colors.VIBRANT_GREEN));
            Pause.milliseconds(20);
        }
        System.out.println();
    }


    /**
     * Announces that all human players have been eliminated and that the game is over.
     * Displays a farewell message to the players.
     */
    public void announceDefeatAndSayGoodbye() {
        System.out.println(Colors.colorize("All human players have been eliminated!\n" +
                "This is GAME OVER", Colors.BLUE));
        sayGoodBye();
    }

    /**
     * Announces the winner of the game and displays a congratulatory message.
     *
     * @param winner Human: the human player who won the game.
     */
    public void announceWinnerAndSayGoodbye(Human winner) {
        System.out.println(Colors.colorize("Congratulations " + winner.getUserName() + "!!\n", Colors.BLUE) +
                Colors.colorize("You defeated all your opponents and emerged victorious!!", Colors.BLUE));
        sayGoodBye();
    }

    /**
     * Displays the current state of the board with the positions of the horses and the last drawn card.
     *
     * @param board        Board: represents the current state of the board.
     * @param drawnCard    Card: represents the card that was drawn if there is any.
     * @param movesForward boolean: true if the movement of the last drawn card is forward, false otherwise.
     */
    public void displayBoard(Board board, Card drawnCard, boolean movesForward) {
        Card[][] boardArray = board.getBoard();
        int trackLength = board.getTrackLength();
        boolean isInFirstPosition;
        StringBuilder boardToPrint = new StringBuilder();

        // Define box border characters
        String topLeftCorner = "┌";
        String topRightCorner = "┐";
        String bottomLeftCorner = "└";
        String bottomRightCorner = "┘";
        String horizontalLine = "─";


        int borderWidth = trackLength * 6 + 22;  //Box width according to characters
        String borderLine = horizontalLine.repeat(borderWidth);

        //Create the top of the box
        boardToPrint.append(topLeftCorner).append(borderLine).append(topRightCorner).append("\n");

        // Create each row of the board
        for (int row = 0; row < boardArray.length; row++) {
            for (int col = 0; col < trackLength; col++) {
                if (boardArray[row][col] != null) {
                    boardToPrint.append("  ")  // Space at start to adjust alignment
                            .append(getRow(boardArray[row][col], col, trackLength, row))
                            .append("\n");
                    break;
                }
            }
        }

        // Create the bottom of the box
        boardToPrint.append(bottomLeftCorner).append(borderLine).append(bottomRightCorner).append("\n");


        // If a drawn card is provided, display a message indicating what it was
        if (drawnCard != null) {
            String suitColor = switch (drawnCard.getSuit()) {
                case SWORDS -> Colors.VIBRANT_BLUE;
                case CUPS -> Colors.VIBRANT_RED;
                case CLUBS -> Colors.VIBRANT_GREEN;
                case GOLD -> Colors.VIBRANT_YELLOW;
            };
            boardToPrint.append(Colors.colorize("\nThe crupier draws the ", Colors.VIBRANT_PURPLE))
                    .append(Colors.colorize(drawnCard.getDescription(), suitColor))
                    .append(Colors.colorize("!!\n", Colors.VIBRANT_PURPLE));
            isInFirstPosition = isHorseInFirstPosition(drawnCard, board);

            boardToPrint.append(Colors.colorize("═══════════════════════════════════════════════════════════════════════════════\n", Colors.VIBRANT_PURPLE));
            boardToPrint.append(narrateTurnResult(movesForward, isInFirstPosition)).append("\n");
        }

        System.out.println(boardToPrint);

        Pause.untilEnter();
    }

    /**
     * Displays the current turn number along with the direction of movement (forward or backward).
     *
     * @param currentTurn the current turn number to display
     */
    public void displayCurrentTurn(int currentTurn) {
        boolean movesForward = currentTurn % 5 != 0; // Avanza si no es múltiplo de 5

        System.out.println(
                Colors.colorize("|| RONDA: " + currentTurn + " ||", Colors.GREEN) + " " + getTurnDirectionText(movesForward)
        );
    }

    /**
     * Returns a formatted string indicating the direction of movement for the current turn.
     *
     * @param movesForward a boolean indicating the direction of movement;
     *                     true if moving forward, false if moving backward
     * @return a formatted string in vibrant green if moving forward ("Forward") or in vibrant red if moving backward ("Back")
     */
    public String getTurnDirectionText(boolean movesForward) {
        if (movesForward) {
            return Colors.colorize(">>> Forward", Colors.VIBRANT_GREEN);  // Texto en verde si avanza
        } else {
            return Colors.colorize("<<< Back", Colors.VIBRANT_RED);  // Texto en rojo si retrocede
        }
    }

    /**
     * Checks if the horse with the same suit as the drawnCard is in the first position of the board.
     *
     * @param drawnCard Card: The last drawn card
     * @param board     Board: the board containing the state of the race.
     * @return boolean: true if the horse with the same suit as drawnCard is in the first position, false otherwise.
     */
    private boolean isHorseInFirstPosition(Card drawnCard, Board board) {
        CardSuit suit = drawnCard.getSuit();
        Card[][] boardArray = board.getBoard();

        // checks if there is a card with the same suit in the first column
        for (Card[] cards : boardArray) {
            if (cards[0] != null && cards[0].getSuit() == suit) {
                return true;  // the horse of the same suit as the las drawn card is in the first position
            }
        }
        return false;  // the horse is not in the first position
    }


    /**
     * Generates the narration of the horse's movement based on the drawn card and its position.
     *
     * @param movesForward      boolean: Indicates if the horse moves forward or backward.
     * @param isInFirstPosition boolean: Indicates if the horse is in the first position.
     * @return StringBuilder: contains the narration of the turn result.
     */
    private StringBuilder narrateTurnResult(boolean movesForward, boolean isInFirstPosition) {
        StringBuilder narration = new StringBuilder();

        if (movesForward) {
            narration.append(forwardNormal());
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
     * Generates a narration indicating that a horse has moved forward normally on the board.
     * The narration will be chosen randomly from a predefined set of phrases.
     *
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
        String announcerEmoji = "\uD83C\uDF99";  //announcer emoji
        Random random = new Random();
        int index = random.nextInt(phrases.length);
        String coloredPhrase = Colors.colorize(announcerEmoji + announcerEmoji + " " + phrases[index] + " " + announcerEmoji + announcerEmoji, Colors.VIBRANT_PURPLE);
        return new StringBuilder(coloredPhrase);
    }

    /**
     * Generates a narration indicating that the horse is attempting to move backward from the first position,
     * but cannot move because it is already at the start.
     *
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
        String announcerEmoji = "\uD83C\uDF99";
        Random random = new Random();
        int index = random.nextInt(phrases.length);
        String coloredPhrase = Colors.colorize(announcerEmoji + announcerEmoji + " " + phrases[index] + " " + announcerEmoji + announcerEmoji, Colors.VIBRANT_PURPLE);
        return new StringBuilder(coloredPhrase);
    }

    /**
     * Creates a list where there are defined a few random phrases.
     *
     * @return StringBuilder The message to show on the console
     */
    private StringBuilder backwardsNormal() {
        String[] phrases = {
                "The horse gallops backward, making strategic moves.",
                "The horse takes a step back, reassessing its position.",
                "With a swift retreat, the horse moves back on the track.",
                "The horse gracefully backs away, looking for a better angle.",
                "In a tactical maneuver, the horse retreats to gain an advantage."
        };
        String announcerEmoji = "\uD83C\uDF99";
        Random random = new Random();
        int index = random.nextInt(phrases.length);
        String coloredPhrase = Colors.colorize(announcerEmoji + announcerEmoji + " " + phrases[index] + " " + announcerEmoji + announcerEmoji, Colors.VIBRANT_PURPLE);
        return new StringBuilder(coloredPhrase);
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

        System.out.println(Colors.colorize("═══════════════════════════════════════════════════════════════════════════════", Colors.VIBRANT_PURPLE));
        // Select a random announcement
        Random random = new Random();
        String randomAnnouncement = announcements[random.nextInt(announcements.length)];
        String announcerEmoji = "\uD83C\uDF99";  // Emoji de locutor

        // Print the selected announcement
        System.out.println(Colors.colorize(announcerEmoji + announcerEmoji + " " + randomAnnouncement + " " + announcerEmoji + announcerEmoji + "\n", Colors.VIBRANT_PURPLE));
        System.out.print(Colors.colorize("🐴 Wait a second, we are adjusting horsehoes",Colors.ORANGE));
        System.out.print(Colors.colorize(".",Colors.ORANGE));
        Pause.milliseconds(1500);
        System.out.print(Colors.colorize(".",Colors.ORANGE));
        Pause.milliseconds(1500);
        System.out.println(Colors.colorize(". \n",Colors.ORANGE));
        Pause.milliseconds(1500);
    }

    /**
     * Displays the message to show each player and his earnings
     *
     * @param playerEarnings The HashMap to iterate
     */
    public void displayEarnings(HashMap<String, Integer> playerEarnings) {
        System.out.println(Colors.colorize("Winners:", Colors.BLUE));
        playerEarnings.forEach((playerName, earnings) ->
                        System.out.println(
                                (Colors.colorize("🐴 "+playerName,Colors.ORANGE)+
                                Colors.colorize(" won "+ earnings + " \uD83C\uDF6A chips.", Colors.BLUE))));
    }
}