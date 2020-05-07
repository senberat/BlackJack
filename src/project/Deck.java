package project;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Deck extends ArrayList<Card> {

    private Scanner input;  // Snanner variable declaration
    private Card card;
    private File file;      // File objecto declaration

    /**
     * Default constructor, creates an empty deck object.
     */
    public Deck() {
    }

    /**
     * this is a method that creates the random deck of cards and adds it to the
     * arraylist of this class
     */
    public void populateArray() {
        String[] face = {"Ace", "Two", "Three", "Four", "Five", "Six", "Seven",
            "Eight", "Nine", "Ten", "Jack", "Queen", "King"};
        char[] figure = {'\u2660', '\u2665', '\u2663', '\u2666'};
        for (int i = 0; i < face.length; i++) {
            for (int j = 0; j < figure.length; j++) {
                this.add(new Card(face[i], i + 1, figure[j]));

                Collections.shuffle(this);

            }
        }
    }

    /**
     * this will get the total value of all the cards in the deck
     *
     * @return an int that is the total value of the cards
     */
    public int getCardsValue() {
        int total = 0;
        for (Card val : this) {
            total += val.getCardValue();

        }
        return total;
    }

    /**
     * this method is used to get the info of every card in the deck and store
     * it a string variable
     *
     * @return a string that has all the cards name, value and shape
     */
    @Override
    public String toString() {
        String values = "";
        for (Card a : this) {
            values += a.toString();
        }
        return values;
    }
}
