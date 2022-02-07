package view;

import controller.ConsoleWriter;
import javafx.scene.control.Label;

public class BattleShipWriter implements ConsoleWriter {

   private Label theConsole;

   public BattleShipWriter(Label console) {
      theConsole = console;
   }

   public void write (String text) {
        javafx.application.Platform.runLater(
                () -> theConsole.setText (text));
   }

}