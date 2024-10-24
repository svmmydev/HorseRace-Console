package model;

import model.deck.Card;
import model.deck.CardFace;
import model.deck.CardSuit;
import model.deck.FacedCard;
import model.player.Bet;
import model.player.Bot;
import model.player.Human;
import model.player.Player;
import utils.ConsoleInOut;

import java.util.ArrayList;
import java.util.Random;

public class PlayerManager {
    private final int INITIAL_BANKROLL = 100; // The initial bankroll when creating a player
    private final int MIN_BET = 2; // Minimum bet a player can place
    private final int MAX_BET = 20; // Maximum bet a player can place
    private final int MIN_HUMANS = 1;
    private final int MIN_BOTS = 0;
    private final int MIN_PLAYERS = 2;
    private final int MAX_PLAYERS = 6;
    private ArrayList<Player> players = new ArrayList<>(); // The active players for the game
    private int pot = 0; // The sum of all the players' bets in a given moment

    public int playerCount() {
        return players.size();
    }

    public boolean indexPlayerIsHuman(int i) {
        return players.get(i) instanceof Human;
    }

    public int getIndexPlayerBankroll(int i) {
        return players.get(i).getBankroll();
    }

    public int getMIN_BET() {
        return MIN_BET;
    }

    public int getMAX_BET() {
        return MAX_BET;
    }

    public int getMIN_HUMANS() {
        return MIN_HUMANS;
    }

    public int getMIN_BOTS() {
        return MIN_BOTS;
    }

    public int getMIN_PLAYERS() {
        return MIN_PLAYERS;
    }

    public int getMAX_PLAYERS() {
        return MAX_PLAYERS;
    }

    public ArrayList<Player> getPlayers() {
        return new ArrayList<>(players);
    }

    public Player getIndexPlayer(int i) {
        return players.get(i);
    }

    public int getPot() {
        return pot;
    }

    /**
     * Sets up the players for the game by creating human and bot players
     *
     * @param humanNames A String array with the names of the human players
     * @param numberOfBots The amount of bots to be created
     */
    public void setupPlayers(String[] humanNames, int numberOfBots) {
        int numberOfHumans = humanNames.length;
        int totalPlayers = numberOfHumans+numberOfBots;
        int bots = 1;

        // Basic check of the amount of players
        if (numberOfHumans<1 || totalPlayers>MAX_PLAYERS) {
            /*
            // Throw an exception once we studied exceptions
            throw new IllegalArgumentException("Invalid number of players: must have at least 1 human and not exceed max players.");
             */
            return; // Exit if the amount of players/humans is invalid
        }

        // Create players
        for (int i=0; i<totalPlayers; i++) {
            if (i<numberOfHumans) { // if there are still Human Players to create:
                String humanName = humanNames[i];
                players.add(new Human(humanName, INITIAL_BANKROLL));
            } else { // Otherwise create a Bot
                players.add(new Bot("Bot "+bots, INITIAL_BANKROLL));
                bots++;
            }
        }
    }

    /**
     * Calculates the amount of 'Human' and 'Bot' objects of the players list.
     *
     * @return bool If there are still more than one 'Human' or any 'Bot'.
     */
    public boolean isGameOver() {
        int humans=0;
        int bots=0;

        // Calculates the amount of each type of players
        for (Player player : players) {
            if (player instanceof Human) humans++;
            else if (player instanceof Bot) bots++;
        }

        if (humans > 1 || bots > 0) return false; // More than one 'Human' or at least one 'Bot'

        return (humans==0)||(humans==1&&bots==0); // no 'Human' left or only one without 'Bot'
    }

    /**
     * If there ir any winner, it redistribute the earnings depending on each player's bet. It also updates the pot.
     *
     * @param winner The winner card of the game.
     * @return bool If pot has been distributed or not.
     */
    public boolean distributeBetsAfterRace(Card winner) {
        int totalWinningBets = getWinningBetsAmount(winner); // Calculates how many bets won
        int totalEarnings = 0;

        if (totalWinningBets == 0) return false;

        // Calculates each earning depending on each bet
        for(Player player : players) {
            if (player.getBet().getHorseBet().equals(winner)) {
                int playerBet = player.getBet().getBetAmount();
                int winnings = (playerBet * pot) / totalWinningBets;
                player.addToBankroll(winnings);
                totalEarnings += winnings; // Accumulates the winnings
            }
        }
        pot -= totalEarnings; // Adjust the pot
        if (pot < 0) pot = 0; // Taking care of possible errors/truncated values

        return true;
    }

    /**
     * @param winner Winner card of the game.
     * @return int The amount of bets (each 'Player') which has won the game.
     */
    public int getWinningBetsAmount(Card winner) {
        int winnerCount = 0;

        // Calculates how many bets won
        for(Player player : players) {
            if (player.getBet().getHorseBet().equals(winner)) {
                winnerCount++;
            }
        }
        return winnerCount;
    }

    /**
     * Checks if any player's bankroll is out of range (MIN_BET), in that case it removes
     * him from the 'players' list.
     *
     * @return boolean If the list has been modified or not.
     */
    public boolean removeLosers() {
        ArrayList<Player> losers = new ArrayList<>(); // Losers list

        // Takes the losers
        for(Player player : players) {
            if (player.getBankroll() < MIN_BET) { // Checks minimum bet
                losers.add(player);
            }
        }

        return players.removeAll(losers); // Removes losers from original list

        /*
        Since we cant remove elements from an ArrayList during a for (it could cause a ConcurrentModificationException),
        we will create a new list to get all the players (to remove) from the global player list and remove them afterward.
        A more efficient solution could be to use an Iterator, but we still have to learn how to use it.
        TODO: Learn to use Iterators
        */
    }

    /**
     * It sets the bet for the 'Human' player.
     *
     * @param i Index of the 'Human' player in the list.
     * @param betAmount Bet amount established by the 'Human' player.
     * @param betCard Card indicated by the 'Human' player (bet).
     */
    public void indexHumanPlayerMakeBet(int i, int betAmount, Card betCard) {
        Player player = players.get(i);
        player.setBet(new Bet(betAmount, betCard));
    }

    /**
     * It randomly sets the bet for the 'Bot' players.
     *
     * @param i Index of the 'Bot' player in the list.
     * @param betOptions List of cards option to bet for.
     */
    public void indexBotPlayerMakeBet(int i, ArrayList<Card> betOptions) {
        Player player = players.get(i);
        ((Bot)player).makeBet(betOptions, this.MAX_BET, this.MIN_BET); // Randomly calculates the bets and options
    }

    /**
     * @return bool If the only last element is 'Human' or not.
     */
    public Human getHumanWinner() {
        Player player = players.getFirst();
        if (player instanceof Human) return (Human) player;
        return null;
    }
}
