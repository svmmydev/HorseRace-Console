package view;

import model.Board;
import model.deck.Card;
import model.player.Human;
import model.player.Player;

import java.util.ArrayList;

public class ConsoleView {


    public static void displayRaceWinner(Card winner) { // TODO
    }

    public void displayWelcomeMessage() { //TODO
    }

    public String[] getHumanNames(int minHumans, int maxPlayers) { // TODO
        return null;
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
