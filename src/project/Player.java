package project;

public class Player {

    private String playerName;
    private double chips;

    /**
     * default constructor
     */
    public Player() {
    }

    /**
     * constructor with param to set the player name and there bets
     *
     * @param playerName the name of the player playing as a string
     * @param chips the bet the player wants the set in the game as a double
     */
    public Player(String playerName, double chips) {
        this.playerName = playerName;
        this.chips = chips;
    }

    /**
     * this is the method to get the player name
     *
     * @return string that has the player name
     */
    public String getPlayerName() {
        return playerName;
    }

    /**
     * this method is used to set the player name
     *
     * @param playerName take a string and then sets the name of the player
     */
    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    /**
     * this method is used to get the amount that the player is betting
     *
     * @return double that is the bet set by the player
     */
    public double getChips() {
        return chips;
    }

    /**
     * this method is used to set the bet the player wants
     *
     * @param chips the bet amount in double
     */
    public void setChips(double chips) {
        this.chips = chips;
    }

    /**
     * this method is used to add more bet by the player
     *
     * @param chips amount that is double that will be add to the pervious bet
     */
    public void addChips(double chips) {
        this.chips += chips;
    }

    /**
     * this is method is used to remove the bet already set by the player
     *
     * @param chips amount that is double which will be subtracted from the bet
     */
    public void subChips(double chips) {
        this.chips -= chips;
    }

    /**
     * this method is used to print that info of the player and the amount of
     * the bet
     *
     * @return a string that contains all the info of the player
     */
    @Override
    public String toString() {
        return "PlayerName=" + playerName + "/nchips=" + chips;
    }

}
