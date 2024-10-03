package model;

public class Bet {
    private int bet;
    private Card horseBet;

    public int getBet() {
        return bet;
    }

    public void setBet(int bet) {
        this.bet = bet;
    }

    public Card getHorseBet() {
        return horseBet;
    }

    public void setHorseBet(Card horseBet) {
        this.horseBet = horseBet;
    }
}
