package project;

public class Card {

    private String cardName; 
    private int cardValue;           
    private char cardFigure;

    /**
     * Default constructor
     */
    public Card() {
    }

    /**
     * Constructor with three attributes name, value and figure.
     *
     * @param cardName name of the card
     * @param cardValue value that the card holds
     * @param cardFigure the shape on the card
     */
    public Card(String cardName, int cardValue, char cardFigure) {
        this.cardName = cardName;
        this.cardValue = cardValue;
        this.cardFigure = cardFigure;

    }

    /**
     *
     *
     * @return the name set on the card
     */
    public String getCardName() {
        return cardName;
    }

    /**
     *
     *
     * @param cardName set what ever name is passed in the parameter
     */
    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    /**
     *
     *
     * @return the shape of the card
     */
    public char getCardFigure() {
        return cardFigure;
    }

    /**
     *
     *
     * @param cardFigure this takes only a char to set the shape of the card
     */
    public void setCardFigure(char cardFigure) {
        this.cardFigure = cardFigure;
    }

    /**
     * this methods return the value on the card if it is greater then 10 then
     * 10 is returned
     *
     * @return the value of the card
     */
    public int getCardValue() {
        if (cardValue > 10) {
            return 10;
        }
        return cardValue;
    }

    /**
     * this method will set the value of the card
     *
     * @param cardValue takes int as the value of the card
     */
    public void setCardValue(int cardValue) {
        this.cardValue = cardValue;
    }

    /**
     * this method is used to print out the info of one card
     *
     * @return a string that has the card name, card shape and the value of the
     * card
     */
    @Override
    public String toString() {
        return String.format("\n%7s %2s %2s %7d", cardName, "of", cardFigure,
                getCardValue());

    }
}
