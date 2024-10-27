package model.player;

public abstract class Player implements Comparable<Player> {
    private String userName;
    private int bankroll;
    private Bet bet;

    public Player(String userName, int bankroll) {
        this.userName = userName;
        this.bankroll = bankroll;
        this.bet = null;
    }

    /**
     * Compares this Player with argumentPlayer for order.
     * First it compares them for their bankroll in ascending order, and if equals, compares them by alphabetical order.
     *
     * @param argumentPlayer the Player to be compared to.
     * @return int: positive value if this Player goes first, negative the other way around, and 0 if equals
     */
    @Override
    public int compareTo(Player argumentPlayer) {
        if (this.bankroll != argumentPlayer.getBankroll()) {
            // Whoever has more chips in the bankroll goes first
            return Integer.compare(argumentPlayer.getBankroll(), this.bankroll);
        } else {
            return this.userName.compareTo(argumentPlayer.getUserName());
        }
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

    /**
     * Checks if the player has the amount indicated by himself and make the bet.
     *
     * @param bet The bet provided from the player
     * @param amount The amount of the bet provided from the player
     */
    public void setBet(Bet bet, int amount) {
        if (subtractFromBankroll(amount)) {
            this.bet = bet;
        }
    }

    public int getBankroll() {
        return bankroll;
    }

    /**
     * Adds a specified amount of chips to the player's bankroll.
     * Used when the player wins a bet.
     *
     * @param amount The amount of chips to be added to the player's bankroll.
     */
    public void addToBankroll(int amount) {
        this.bankroll += amount;
    }

    /**
     * Subtracts the parameter amount of chips from the player's bankroll and returns true.
     * If the amount is greater than the current bankroll, the method returns false and does nothing.
     * Used when the player places a bet.
     *
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
}
