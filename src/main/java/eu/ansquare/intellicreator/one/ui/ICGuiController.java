package eu.ansquare.intellicreator.one.ui;

import eu.ansquare.intellicreator.one.Element;
import eu.ansquare.intellicreator.one.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

;


public class ICGuiController {
    private Stage stage;
    ObservableList<Element> elementObservableList = FXCollections.observableArrayList(Main.elementManager.asCollection());

    @FXML
    public ListView list;

    @FXML
    private void initialize() {
        list.setItems(elementObservableList);

    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
