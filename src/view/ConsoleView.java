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
    public int getNumberOfBots(int minBots, int maxBots) { // TODO
        return 0;
    }

    public Card askForBetCard(ArrayList<Card> horsesArray) { //TODO
        return null;
    } //TODO

    public int askForBetAmount(int minBet, int maxBet) { //TODO
        return 0;
    } //TODO

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
