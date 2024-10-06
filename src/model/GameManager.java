package model;

import model.player.Bot;
import model.player.Human;
import model.player.Player;
import utils.ConsoleInOut;

import java.util.ArrayList;
import java.util.Random;

public class GameManager {
    private ArrayList<Card> horses; // The horse cards that will be racing
    private Card winnerHorse; // The horse card that won the last race
    private ArrayList<Player> players; // The active players for the game
    private int pot = 0; // The sum of all the players' bets in a given moment
    private final int INITIAL_BANKROLL = 100; // The initial bankroll when creating a player
    private final int MIN_BET = 2; // Minimum bet a player can place
    private final int MAX_BET = 20; // Maximum bet a player can place

    /**
     * Manages the main game loop, allowing players to place bets, race horses,
     * distribute winnings, and determine game termination conditions.
     */
    public void gameMenu() {

        createRaceHorses();
        createPlayers();

        do {
            placeBets();
            GameHorsesRace gameHorsesRace = new GameHorsesRace();
            //Card winnerHorse = gameHorsesRace.¿¿getWinner??();
            winnerHorse = horses.get(new Random().nextInt(horses.size())); // temporary until GameHorsesRace class is implemented
            ConsoleInOut.print(winnerHorse.getDescription() + " has won the race!! Let's check the rewards!");
            distributeBets();
            disposeOfLosers();

            if (players.isEmpty()) {
                ConsoleInOut.print("All players have lost and the game is over. Better Luck next time");
                break;
            } else if (players.size() == 1) {
                ConsoleInOut.print("We have a definitive winner! "+players.getFirst().getUserName()+ " won with a total of "+players.getFirst().getBankroll()+" chips!");
                break;
            }

            char confirmation = ConsoleInOut.getStringWithRegex("Do you want to keep playing? [Y]es [N]o", "[YySsNn]").charAt(0);

            if (confirmation == 'N' || confirmation == 'n') break;
            else {
                ConsoleInOut.print("Lets prepare a new game, get ready to bet...\n");
                displayGameStatus();
            }
        } while (true);

        ConsoleInOut.print("Thank You for playing");
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
                players.add(new Human(playerName, INITIAL_BANKROLL));
                humans--;
            } else {
                players.add(new Bot("Bot "+bots++, INITIAL_BANKROLL));
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
        ConsoleInOut.print("\nThe total pot is "+pot+" chips.\n");
    }

    /**
     * Extracts the winner players taking into account their bets and the winner horse and distributes the bet using a rule of three
     * The division is made with integers and the remaining chips stays in the por for the next race/game.
     */
    public void distributeBets() {
        ArrayList<Player> winners = getWinners();
        if (winners.isEmpty()) {
            ConsoleInOut.print("No one bid for the "+winnerHorse.getDescription()+". The pot of "+pot+" chips stays for the next game");
            return;
        }
        // Calculate the total bets from winners.
        int totalBetsFromWinners = 0;
        for (Player player : winners) {
            totalBetsFromWinners += player.getBet().getBet();
        }
        int chipsDistributed = 0;
        for (Player player : winners) {
            int playerBet = player.getBet().getBet();

            // Calculate and hand over the winning based on each player bet proportion.
            int winnings = (pot * playerBet) / totalBetsFromWinners; // Rule of three
            player.addToBankroll(winnings);
            chipsDistributed += winnings;

            // Display the winnings
            ConsoleInOut.print(player.getUserName()+" won "+winnings+" chips!");
        }
        pot -= chipsDistributed;
        ConsoleInOut.print("Any remaining chips due to impossible divisions will be carried over to the next game.\nPOT: "+pot);
    }

    /**
     * Removes players who do not have enough chips (less than the minimum bet) from the game.
     * It also informs the player and the remaining number of players after eliminations.
     */
    private void disposeOfLosers() {
        /*
        Since we cant remove element from an ArrayList during a for (it could cause a ConcurrentModificationException),
        we will create a new list to get all the players to remove from the global player list and remove them afterward.
        A more efficient solution could be to use Iterator, but we still have to learn how to use it
        TODO: Learn to use Iterators
         */
        ArrayList<Player> playersToRemove = new ArrayList<>();
        for (Player player : players) {
            if (player.getBankroll() < MIN_BET) {
                playersToRemove.add(player);
                ConsoleInOut.print(player.getUserName()+" doesn't have enough chips for the next game and it is disqualified.");
            }
        }
        players.removeAll(playersToRemove);
        ConsoleInOut.print(players.size()+" players remaining...");
    }

    /**
     * Places a random bet for a bot player.
     * The bot selects a random horse and bet amount in the range specified by the global variable MIN_BET and maxBet.
     * maxBet will be the minimum between MAX_BET and the player bankroll.
     * @param player Bot: The bot player placing the bet.
     */
    private void placeRandomBet(Bot player) {
        Random random = new Random();
        int choosenHorseIndex = random.nextInt(horses.size());
        int maxBet = Math.min(MAX_BET, player.getBankroll()); // Sets the maxBet bet in case the player don't have enough chips
        int betAmount = random.nextInt(maxBet - MIN_BET + 1) + MIN_BET; // random number from MIN_BET to maxBet
        Bet bet = new Bet(betAmount,horses.get(choosenHorseIndex));
        player.setBet(bet);
        player.subtractFromBankroll(betAmount);
        pot += betAmount;

        ConsoleInOut.print(player.getUserName()+" placed a bet of "+bet.getBet()+ " chips on "+bet.getHorseBet().getDescription());
    }

    /**
     * Prompts a human player to place a bet.
     * The player chooses a horse and enters a bet amount in the range specified by the global variable MIN_BET and maxBet.
     * maxBet will be the minimum between MAX_BET and the player bankroll.
     * @param player Human: The human player placing the bet.
     */
    private void placeHumanBet(Human player) {
        int choosenHorseIndex = -1 + ConsoleInOut.getIntegerInRange(
                "Choose the winner horse, " + player.getUserName() + ":\n"+displayRaceHorses(),
                1,
                horses.size()
        );
        int maxBet = Math.min(MAX_BET, player.getBankroll()); // Sets the maxBet bet in case the player don't have enough chips
        int betAmount = ConsoleInOut.getIntegerInRange("Enter your bet amount, " + player.getUserName() + ": ", MIN_BET, maxBet);
        player.setBet(new Bet(betAmount,horses.get(choosenHorseIndex)));
        player.subtractFromBankroll(betAmount);
        pot += betAmount;
    }

    /**
     * Returns an ArrayList of players who correctly guessed the winning horse based on their bets.
     * The method compares each player's bet with the winning horse and adds the players who guessed correctly to the list.
     * @return ArrayList of players who guessed the correct winning horse.
     */
    private ArrayList<Player> getWinners () {
        ArrayList<Player> winnersList = new ArrayList<>();
        for (Player player : players) {
            // TODO:
            //  Mejorar esta condición de if, ya que seguro que hay formas más eficientes de hacer la comparación.
            //  Para hacerlo hay que implementar equals en la clase card
            if (player.getBet().getHorseBet().getDescription().equals(winnerHorse.getDescription())) {
                winnersList.add(player);
            }
        }
        return winnersList;
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

    /**
     * Displays the current game status, showing each player's username and their total chips.
     */
    private void displayGameStatus() {
        ConsoleInOut.print("Game stats:");
        for (Player player : players) {
            ConsoleInOut.print(player.getUserName()+" has "+player.getBankroll()+" chips.");
        }
        ConsoleInOut.print(""); // newline
    }
}
