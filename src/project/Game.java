package project;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class Game extends Application {

    private MenuBar menuBar;
    private Menu fileMenu;
    private MenuItem exitItem;

    private Label lblTitle;
    private Label lblPlayer;
    private Label lblChips;
    private Label lblBet;

    private TextField txtPlayer;
    private TextField txtChips;
    private TextField txtBet;

    private Button btnNext;
    private Button btnPrevious;

    private Button btnStartGame;

    private TextArea txaAreaPlayer;
    private TextArea txaAreaCroupier;

    private Button btnDouble;
    private Button btnStand;
    private Button btnHit;

    Alert alert;

    Deck casinoDeck;
    Deck croupierDeck;
    Deck playerDeck;
    double bet;
    Player player;
    ArrayList<Player> playerList;
    private File file;
    private Scanner input;
    int activeUser;

    /**
     * Start metho, creates the Graphical Interface and show it
     *
     * @param primaryStage receives PrimaryStage object
     */
    @Override
    public void start(Stage primaryStage) {

        exitItem = new MenuItem("E_xit");
        exitItem.setMnemonicParsing(true);
        exitItem.setAccelerator(new KeyCodeCombination(KeyCode.E, KeyCombination.SHORTCUT_DOWN));
        exitItem.setOnAction(e -> eventCode((ActionEvent) e));

        fileMenu = new Menu("_File");
        fileMenu.setMnemonicParsing(true);
        fileMenu.getItems().add(exitItem);

        menuBar = new MenuBar();
        menuBar.getMenus().add(fileMenu);

//Title Label       
        lblTitle = new Label("Black Jack");
        HBox titleBox = new HBox(10, lblTitle);
        titleBox.setAlignment(Pos.CENTER_LEFT);
        lblTitle.setId("title");

//Player       
        lblPlayer = new Label("Player");
        lblPlayer.getStyleClass().add("label");
        txtPlayer = new TextField();
        txtPlayer.setPrefWidth(140);

// Next Button       
        btnNext = new Button("Next");
        btnNext.setPrefWidth(70);
        btnNext.setOnAction(e -> eventCode(e));

// Previous Button       
        btnPrevious = new Button("Previous");
        btnPrevious.setPrefWidth(70);
        btnPrevious.setOnAction(e -> eventCode(e));
// Cursors Box
        HBox cursorBox = new HBox(10, btnPrevious, btnNext);
        titleBox.setAlignment(Pos.CENTER);
//Player Box        
        VBox playerBox = new VBox(10, lblPlayer, txtPlayer, cursorBox);
        playerBox.setMaxWidth(220);
        playerBox.setAlignment(Pos.CENTER);

//Chips               
        lblChips = new Label("Chips");
        lblChips.getStyleClass().add("label");
        txtChips = new TextField();
        txtChips.setMaxWidth(140);
        VBox chipsBox = new VBox(10, lblChips, txtChips);
        chipsBox.setMaxWidth(220);
        chipsBox.setAlignment(Pos.CENTER);

//Bet               
        lblBet = new Label("Your Bet");
        lblBet.getStyleClass().add("label");
        txtBet = new TextField();
        txtBet.setMaxWidth(140);
        VBox betBox = new VBox(10, lblBet, txtBet);
        betBox.setMaxWidth(220);
        betBox.setAlignment(Pos.CENTER);

// Start Button       
        btnStartGame = new Button("_New\nGame");
        btnStartGame.setPrefWidth(100);
        btnStartGame.setMnemonicParsing(true);
        btnStartGame.setOnAction(e -> eventCode(e));
        btnStartGame.setId("btnNew");

        // Left Container Box
        VBox sideLBox = new VBox(20, titleBox, playerBox, chipsBox, betBox, btnStartGame);
        sideLBox.setMaxWidth(400);
        sideLBox.setId("gropubox");

//text Area
        txaAreaPlayer = new TextArea();
        txaAreaPlayer.setEditable(false);
        HBox areaBoxA = new HBox(txaAreaPlayer);
        areaBoxA.setAlignment(Pos.CENTER);

//text Area
        txaAreaCroupier = new TextArea();
        txaAreaCroupier.setEditable(false);
        HBox areaBoxB = new HBox(txaAreaCroupier);
        areaBoxB.setAlignment(Pos.CENTER);

//Buttons        
        btnDouble = new Button("_DOUBLE");
        btnDouble.setMnemonicParsing(true);
        btnDouble.setOnAction(e -> eventCode(e));
        btnDouble.setId("btnDouble");

        btnStand = new Button("_STAND");
        btnStand.setMnemonicParsing(true);
        btnStand.setOnAction(e -> eventCode(e));
        btnStand.setId("btnStand");

        btnHit = new Button("_HIT");
        btnHit.setMnemonicParsing(true);
        btnHit.setOnAction(e -> eventCode(e));
        btnHit.setId("btnHit");

        HBox buttonBox = new HBox(20, btnHit, btnStand, btnDouble);
        buttonBox.setAlignment(Pos.CENTER);

//Main group
        VBox sideRBox = new VBox(10, areaBoxB, buttonBox, areaBoxA);
        sideRBox.setAlignment(Pos.CENTER);
        sideRBox.setId("main");

//Main group
        HBox main = new HBox(10, sideLBox, sideRBox);
        main.setAlignment(Pos.CENTER);
        main.setId("main");
//Root pane
        VBox root = new VBox(menuBar, main);
        main.setPadding(new Insets(20));
        root.getStylesheets().add("styles.css");
        root.setId("root");
//Scene
        Scene scene = new Scene(root, 730, 480);

//Stage
        primaryStage.setTitle("Casino");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
        casinoDeck = new Deck();
        casinoDeck.populateArray();
        croupierDeck = new Deck();
        playerDeck = new Deck();

        readFile();

        bet = 0;
        activeUser = 0;
        refreshFields();
        buttonBlock();

    }
//Structure

    public static void main(String[] args) {
        launch(args);
    }

    /**
     * this is the method that catches any clicked node on the gui
     *
     * @param e an actionEvent object that is used to get the source of the
     * event
     */
    public void eventCode(ActionEvent e) {
        if (e.getSource() == exitItem) {
            playSound("12.wav");

        }
        if (e.getSource() == btnDouble) {
            playSound("12.wav");
            betDouble();
            return;
        }
        if (e.getSource() == btnStand) {
            playSound("12.wav");
            actionCroupier();
        }
        if (e.getSource() == btnHit) {
            playSound("12.wav");
            hitAction();
        }
        if (e.getSource() == btnStartGame) {
            playSound("start.wav");
            startGame();
        }
        if (e.getSource() == btnPrevious) {
            playSound("12.wav");
            if (activeUser > 0) {
                activeUser -= 1;
                refreshFields();
            }
        }
        if (e.getSource() == btnNext) {
            playSound("12.wav");
            if (activeUser < playerList.size() - 1) {
                activeUser += 1;
                refreshFields();
            }
            System.out.println(activeUser);
            System.out.println(playerList.size());
        }

    }

    /**
     * this is the method that that calls that deck and set the bets
     * to the players
     */
    private void startGame() {
        try {
            casinoDeck = new Deck();
            casinoDeck.populateArray();
            croupierDeck = new Deck();
            playerDeck = new Deck();
            if (getBet() <= playerList.get(activeUser).getChips()) {
                bet = getBet();
                playerList.get(activeUser).subChips(bet);

                refreshFields();
                cardForCroupier();
                cardForPlayer();
                cardForPlayer();
                displayTable();
                buttonEnable();
                checkBlackJack();
            } else {
                txaAreaPlayer.setText("Bet can be bigger than your credit.");
            }
        } catch (IllegalArgumentException | NullPointerException e) {
            txaAreaPlayer.setText(e.getMessage());
        }

    }

    /**
     * this is method that is used to block user from clicking the node during
     * the other player's turn
     */
    private void buttonBlock() {
        btnHit.setDisable(true);
        btnStand.setDisable(true);
        btnDouble.setDisable(true);
        btnNext.setDisable(false);
        btnPrevious.setDisable(false);
        btnStartGame.setDisable(false);
    }

    /**
     * this is the method that is called when the user wants to interact with
     * the gui on their turn
     */
    private void buttonEnable() {
        btnHit.setDisable(false);
        btnStand.setDisable(false);
        btnDouble.setDisable(false);
        btnPrevious.setDisable(true);
        btnNext.setDisable(true);
        btnStartGame.setDisable(true);
    }

    /**
     * this is the method that is called then the user select the hit node on
     * the gui
     */
    private void hitAction() {
        cardForPlayer();
        if (playerDeck.getCardsValue() > 21) {
            casinoWin();
        } else {
            displayTable();
        }
        saveFile();
    }

    /**
     * this is the method used to clear the user selection
     */
    public void cardForCroupier() {
        croupierDeck.add(casinoDeck.get(0));
        casinoDeck.remove(0);
    }

    /**
     * this is the method to give card to the user
     */
    public void cardForPlayer() {
        playerDeck.add(casinoDeck.get(0));
        casinoDeck.remove(0);
    }

    /**
     * this method display the total on the gui
     */
    private void displayTable() {
        txaAreaCroupier.setText(croupierDeck.toString() + "         Total: " + 
            croupierDeck.getCardsValue());
        txaAreaPlayer.setText(playerDeck.toString() + "         Total: " + 
            (playerDeck.getCardsValue()));
        refreshFields();
    }

    /**
     * this is the method the refreshes the field in the gui
     */
    private void refreshFields() {
        txtPlayer.setText(playerList.get(activeUser).getPlayerName());
        txtChips.setText("" + playerList.get(activeUser).getChips());
        txtBet.setText(String.valueOf(bet));
    }

    /**
     * this method doubles the bet set by the user
     */
    private void betDouble() {
        if (bet < playerList.get(activeUser).getChips()) {
            playerList.get(activeUser).subChips(bet);
            refreshFields();
        } else {
            txaAreaPlayer.setText("Bet can be bigger than your credit.");
        }
    }

    private void actionCroupier() {
        while (croupierDeck.getCardsValue() < 17) {
            cardForCroupier();
        }
        displayTable();

        if (croupierDeck.getCardsValue() > 21) {
            simpleWin();
        } else {
            if (croupierDeck.getCardsValue() > playerDeck.getCardsValue()) {
                casinoWin();
            } else {
                simpleWin();
            }
        }
        saveFile();
    }

    /**
     * this method checks if the game is won
     */
    private void checkBlackJack() {
        if (playerDeck.getCardsValue() == 11) {
            if (playerDeck.get(0).getCardValue() == 1
                    || playerDeck.get(1).getCardValue() == 1) {
                blackJackWin();
            }
        }
    }


    /**
     * this method checks if the player won and saves the record
     */
    private void simpleWin() {
        playerList.get(activeUser).addChips(bet * 2);
        refreshFields();
        displayTable();
        txaAreaPlayer.appendText("\nCongratulations You Win!!");
        buttonBlock();
        saveFile();
        playSound("winning.wav");
    }

    /**
     * this method checks if the casino won and saves the game
     */
    private void casinoWin() {
        displayTable();
        txaAreaPlayer.appendText("\nBad Luck my Friend!!");
        buttonBlock();
        playSound("loose.wav");
        saveFile();
    }

    /**
     * this method checks if the player has a black jack (21 exact) and 
     * save it on the file
     */
    private void blackJackWin() {
        playerList.get(activeUser).addChips(bet * 2.5);
        refreshFields();
        displayTable();
        txaAreaPlayer.appendText("\nCongratulations you get Black Jack!!");
        buttonBlock();
        playSound("winning.wav");
        saveFile();
    }

    /**
     * this is the method that get the bet of a specific user
     *
     * @return the value of the bet
     * @throws IllegalArgumentException if the bet is zero or a negative
     */
    public double getBet() throws IllegalArgumentException {
        double number;
        setStyle(lblBet);

        try {
            number = Double.parseDouble(txtBet.getText());
            if (number == 0) {
                setErrorStyle(lblBet);
                throw new IllegalArgumentException("Zero is an invalid Bet");
            }
            if (number < 0) {
                setErrorStyle(lblBet);
                throw new IllegalArgumentException("No negative Bets available");
            }
        } catch (NumberFormatException e) {
            setErrorStyle(lblBet);
            throw new IllegalArgumentException("Please enter a valid number");
        }
        return number;
    }

    /**
     * this is the method that sets the css style if the user makes an error
     *
     * @param lbl node on which the style will be applied
     */
    private void setErrorStyle(Label lbl) {
        lbl.getStyleClass().add("error");
    }

    /**
     * this is the method that will clear the error style
     *
     * @param lbl node on which the style will be applied
     */
    private void setStyle(Label lbl) {
        lbl.getStyleClass().clear();
        lbl.getStyleClass().add("label");
    }

    /**
     * this method reads everything on file and loads the deck
     */
    public void readFile() {
        try {
            playerList = new ArrayList();
            file = new File("Players.txt");
            if (file.createNewFile()) {
                throw new NullPointerException("Database file not found!");
            }

            input = new Scanner(file);
            input.useDelimiter("\\|\\s*");
            while (input.hasNext()) {
                String name = input.next();
                double varchips = input.nextDouble();
                playerList.add(new Player(name, varchips));
            }
        } catch (IOException e) {
            txaAreaPlayer.setText("Error reading File");

        } finally {
            input.close();//close scanner
        }

    }

    /**
     * this is the method that takes in-game record and the save it on the file
     */
    public void saveFile() {
        try (PrintWriter writer = new PrintWriter(file)) {
            for (int i = 0; i < playerList.size(); i++) {
                printRecord(writer,
                        playerList.get(i).getPlayerName(),
                        playerList.get(i).getChips());
            }
        } catch (IOException e) {
            txaAreaPlayer.setText("Error saving File");
        }

    }
/**
 * this is the method that plays music in the background of the gui
 * @param musicFile a file that contains all the music 
 */
    public void playSound(String musicFile) {
        Media sound = new Media(new File(musicFile).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.play();
    }
/**
 * this is the method that prints the info on the file
 * @param pw object of the printwrite to print on the file 
 * @param name name of the player
 * @param chips bet of the  user
 */
    public static void printRecord(PrintWriter pw, String name, double chips) {
        pw.print(name + "|");
        pw.print(chips + "|");
        pw.print("\r\n");
    }

}
