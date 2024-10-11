package model.player;

public abstract class Player {
    private String userName;
    private int bankroll;
    private Bet bet;

    public Player(String userName, int bankroll) {
        this.userName = userName;
        this.bankroll = bankroll;
        this.bet = null;
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

    public int getBankroll() {
        return bankroll;
    }

    /**
     * Adds a specified amount of chips to the player's bankroll.
     * Used when the player wins a bet.
     * @param amount The amount of chips to be added to the player's bankroll.
     */
    public void addToBankroll(int amount) {
        this.bankroll += amount;
    }

    /**
     * Subtracts the parameter amount of chips from the player's bankroll and returns true.
     * If the amount is greater than the current bankroll, the method returns false and does nothing.
     * Used when the player places a bet.
     * @param amount The amount of chips to be subtracted from the player's bankroll.
     * @return true if the subtraction was successful, false if the player has insufficient bankroll.
     */
    public boolean subtractFromBankroll(int amount) {
        if (amount <= this.bankroll) {
            this.bankroll -= amount;
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return "Player{" +
                "userName='" + userName + '\'' +
                ", bet=" + bet +
                '}';
    }
}
