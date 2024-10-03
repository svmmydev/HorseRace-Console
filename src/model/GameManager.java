package model;

import model.player.Bot;
import model.player.Human;
import model.player.Player;
import utils.ConsoleInOut;

import java.util.ArrayList;
import java.util.Random;

public class GameManager {
    private ArrayList<Card> horses;
    private ArrayList<Player> players; // The active players for the game
    private int pot = 0; // The sum of all the players bets
    private final int INITIAL_BANKROLL = 100;
    private int MIN_BET = 2; // Minimum bet a player can place
    private int MAX_BET = 20; // Maximum bet a plager can place


    public void gameMenu() {

        createRaceHorses();
        createPlayers();
        placeBets();
        GameHorsesRace gameHorsesRace = new GameHorsesRace();
        //Card winnerHorse = gameHorsesRace.¿¿getWinner??();
        Card winnerHorse =  horses.get(new Random().nextInt(horses.size())); // temporary until GameHorsesRace class is implemented

    }


    /**
     * Creates the race horses for the game.
     * Each horse is represented as a FacedCard with a KNIGHT face and different suits.
     */
    private void createRaceHorses() {
        horses = new ArrayList<>();
        for (CardSuit suit : CardSuit.values()) {
            horses.add(new FacedCard(CardFace.KNIGHT,suit));
        }
    }

    /**
     * Creates players for the game.
     * Players can be human or bot players.
     * The number of players and human players is determined through user input.
     */
    private void createPlayers() {

        players = new ArrayList<>();
        int playerAmount = ConsoleInOut.getIntegerInRange("How many players will be betting? (2-8)",2,8); // maximum 8 bidders
        int maxHumans = Math.min(playerAmount, 4);
        int humans = ConsoleInOut.getIntegerInRange("How many players will be human? (1-"+maxHumans+")",1,maxHumans);

        int bots = 1;
        for (int i=1; i<=playerAmount; i++) {
            String playerName;
            int bet;
            if (humans>0) {
                playerName = ConsoleInOut.getStringWithRegex(
                        "Please enter player "+i+" nickname (Length 4-15, letters, numbers, \".\" and \"_\")",
                        "[0-9A-Za-z._]{4,15}"
                );
                players.add(new Human(playerName));
                humans--;
            } else {
                players.add(new Bot("Bot "+bots++));
            }
        }
    }

    /**
     * Places bets for all players in the game.
     * It distinguishes between human and bot players.
     */
    private void placeBets() {
        for (Player player : players) {
            if (player instanceof Human) {
                placeHumanBet((Human) player);
            } else {
                placeRandomBet((Bot) player);
            }
        }
    }

    /**
     * Places a random bet for a bot player.
     * The bot selects a random horse and bet amount in the range specified by the global variables MIN_BET and MAX_BET.
     * @param player Bot: The bot player placing the bet.
     */
    private void placeRandomBet(Bot player) {
        Random random = new Random();
        int choosenHorseIndex = random.nextInt(horses.size());
        int betAmount = random.nextInt(MAX_BET - MIN_BET + 1) + MIN_BET; // random number from MIN_BET to MAX_BET
        Bet bet = new Bet(betAmount,horses.get(choosenHorseIndex));
        player.setBet(bet);

        ConsoleInOut.print(player.getUserName()+" placed a bet of "+bet.getBet()+ " on "+bet.getHorseBet().getDescription());
    }

    /**
     * Prompts a human player to place a bet.
     * The player chooses a horse and enters a bet amount in the range specified by the global variables MIN_BET and MAX_BET.
     * @param player Human: The human player placing the bet.
     */
    private void placeHumanBet(Human player) {
        int choosenHorseIndex = -1 + ConsoleInOut.getIntegerInRange(
                "Choose the winner horse " + player.getUserName() + ":\n"+displayRaceHorses(),
                1,
                horses.size()
        );

        int betAmount = ConsoleInOut.getIntegerInRange("Enter your bet amount, " + player.getUserName() + ": ", MIN_BET, MAX_BET);
        player.setBet(new Bet(betAmount,horses.get(choosenHorseIndex)));
    }

    /**
     * Displays the list of race horses with their respective indices +1.
     * @return String: A numbered list of the available race horses.
     */
    private String displayRaceHorses() {
        StringBuilder racehorses = new StringBuilder();
        for (int i=1; i<= horses.size();i++) {
            racehorses.append("[").append(i).append("] ").append(horses.get(i - 1).getDescription()).append("\n");
        }
        return racehorses.toString();
    }
}
