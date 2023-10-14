package eu.ansquare.intellicreator.one.ui;

import eu.ansquare.intellicreator.one.armor.ArmorMaker;
import eu.ansquare.intellicreator.one.block.BlockMaker;
import eu.ansquare.intellicreator.one.item.ItemMaker;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

;import java.io.File;


public class ICGuiController {
    private Stage stage;

    @FXML
    private void initialize() {

    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
