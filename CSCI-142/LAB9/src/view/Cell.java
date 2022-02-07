package view;

import controller.BattleShip;
import controller.Observer;
import javafx.application.Platform;
import model.Location;

import javafx.scene.control.Button;
import model.Peg;
import model.Target;

public class Cell extends Button implements Observer<Boolean> {
    private BattleShip controller;
    private Location location;

    public Cell(BattleShip controller, Location location) {
        this.setStyle("-fx-background-color: #00FFFF;-fx-border-color:  #000000;-fx-pref-height: 28px;-fx-pref-width: 28px;");
        this.location = location;
        this.controller = controller;
        this.controller.registerTarget(this.location, this);
        this.setOnAction(e -> {
            this.controller.attack(this.location);
        });
    }

    @Override
    public void update(Boolean pushValue) {
        System.out.println(pushValue);
        Platform.runLater(
                () -> {
                    if (pushValue == true){
                        this.setStyle("-fx-background-color: #B22222;-fx-border-color:  #000000;-fx-pref-height: 28px;-fx-pref-width: 28px;");
                    }
                    else {
                        this.setStyle("-fx-background-color: #000080;-fx-border-color:  #000000;-fx-pref-height: 28px;-fx-pref-width: 28px;");
                    }
                }
        );
    }
}
