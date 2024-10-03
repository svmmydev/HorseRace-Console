package model.player;
import model.Bet;

public abstract class Player {
    private String userName;
    private Bet bet;

    public Player(String userName, Bet bet) {
        this.userName = userName;
        this.bet = bet;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Bet getBet() {
        return bet;
    }

    public void setBet(Bet bet) {
        this.bet = bet;
    }

    @Override
    public String toString() {
        return "Player{" +
                "userName='" + userName + '\'' +
                ", bet=" + bet +
                '}';
    }
}
