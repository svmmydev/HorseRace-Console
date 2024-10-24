package view;

import model.Board;
import model.deck.Card;
import model.player.Human;
import model.player.Player;
import utils.ConsoleInOut;

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
                player.getUserName()+" bid "+player.getBet().getBet()+" chips to the "+player.getBet().getHorseBet().getDescription()+"."
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

    public void displayBoard(Board board, Card drawnCard) { // TODO
    }

    public boolean askIfKeepPlaying() { // TODO
        return true;
    }

    public void displayNoWinningBets() { // TODO
    }

    public void displaySomePlayersLostMessage() { // TODO
    }

    public void sayGoodBye() { // TODO
    }

    public void announceDefeatAndSayGoodbye() { // TODO
        sayGoodBye();
    }

    public void announceWinnerAndSayGoodbye(Human winner) { // TODO
        sayGoodBye();
    }
}
