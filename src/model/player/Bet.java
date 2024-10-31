package model.player;

import model.deck.Card;

public class Bet {
    private int bet;
    private Card horseBet;

    public Bet(int bet, Card horseBet) {
        this.bet = bet;
        this.horseBet = horseBet;
    }

    public int getBetAmount() {
        return bet;
    }

    public Card getHorseBet() {
        return horseBet;
    }
}
