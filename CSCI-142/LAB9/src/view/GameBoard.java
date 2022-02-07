package view;

import controller.Observer;
import controller.ShipData;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import controller.BattleShip;
import model.Board;
import model.Location;
import model.ShipModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * The provided javadocs only includes items that are
 * inherited from Application. All of which you will
 * not need to implement except for start which is
 * already provided below. Please replace this comment
 * block with something useful when you create your
 * implementation.
 */
public class GameBoard extends Application implements Observer<ShipData> {
    private BattleShipWriter console;
    private BattleShip controller;
    private Label status;


    private Map<Location, Button> buttonlist = new HashMap<>();

    public void init() {
        this.status = new Label();
        this.console = new BattleShipWriter(this.status);
        this.controller = new BattleShip(this.console);
        this.controller.addShips(this);
    }

    public void start(Stage stage) {
        Label BATTLESHIP = new Label("BATTLESHIP");
        Label HASBROCO = new Label("Hasbro xd");
        HASBROCO.setStyle("-fx-text-fill: white;-fx-background-color:#000000 #cf1020");
        BATTLESHIP.setAlignment(Pos.CENTER);
        BATTLESHIP.setStyle("-fx-text-fill: white;-fx-background-color:#000000 #cf1020");
        GridPane gridpane = new GridPane();
        for (int i = 0; i < BattleShip.NUM_COLS; i++){
            for (int j = 0; j < BattleShip.NUM_ROWS; j++){
                Cell cell = new Cell(this.controller, new Location(i , j));
                gridpane.add(cell, i, j);
            }
        }
        GridPane lowerGrid = new GridPane();
        for (int k = 0; k < BattleShip.NUM_COLS; k++){
            for (int l = 0; l <BattleShip.NUM_ROWS; l++){
                boolean condition = controller.getTheBoard().checking(new Location(k, l));
                if (condition == true) {
                    Button empty = new Button();
                    empty.setStyle("-fx-background-color: #B22222;-fx-border-color:  #000000;-fx-pref-height: 28px;-fx-pref-width: 28px;");
                    lowerGrid.add(empty, k, l);
                    buttonlist.put(new Location(l, k), empty);
                }
                else {
                    Button kek = new Button();
                    kek.setStyle("-fx-background-color: #00FFFF;-fx-border-color:  #000000;-fx-pref-height: 28px;-fx-pref-width: 28px;");
                    lowerGrid.add(kek, k, l);
                    buttonlist.put(new Location(l, k), kek);
                }
            }
        }

        status.setStyle("-fx-text-fill: white;");

        VBox vbox = new VBox(BATTLESHIP, gridpane, status, lowerGrid, HASBROCO);
        vbox.setStyle("-fx-background-color: #000000;");
        vbox.setAlignment(Pos.CENTER);
        Scene scene = new Scene(vbox);
        stage.setScene(scene);
        stage.setTitle("Battleship");
        stage.setResizable(false);
        stage.show();
    }

    @Override
    public void update(ShipData pushValue) {
        if (buttonlist.containsKey(pushValue.getBowLocation())){
            buttonlist.get(pushValue.getBowLocation()).setStyle("-fx-background-color:#FFFFFF #cf1020;-fx-pref-height: 28px;-fx-pref-width: 28px;");
            buttonlist.remove(pushValue.getBowLocation());
        }
    }
}
