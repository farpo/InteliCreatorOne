package eu.ansquare.intellicreator.one.ui;

import eu.ansquare.intellicreator.one.Element;
import eu.ansquare.intellicreator.one.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

;import java.io.IOException;


public class ICGuiController extends GuiController {
    ObservableList<Element> elementObservableList = FXCollections.observableArrayList(Main.elementManager.asCollection());

    @FXML
    public ListView list;
    @FXML
    public TextField elementId;
    @FXML
    public Label redId;
    @FXML
    public Button blockButton;

    @FXML
    private void initialize() throws IOException {
        redId.setVisible(false);
        elementId.setOnMouseClicked(event -> redId.setVisible(false));
        FXMLLoader blockUiFxmlLoader = new FXMLLoader(Main.class.getResource("/ui/BlockGui.fxml"));
        Scene scene = new Scene(blockUiFxmlLoader.load());
        blockButton.setOnMouseClicked(event -> {
            if (!elementId.getCharacters().isEmpty()) {
                Stage blockStage = new Stage();
                blockStage.initOwner(blockButton.getScene().getWindow());
                blockStage.setScene(scene);
                BlockController controller = blockUiFxmlLoader.getController();
                controller.setStage(blockStage);
                controller.setId(elementId.getCharacters().toString());
                // showAndWait will block execution until the window closes...
                blockStage.showAndWait();

            } else {
                redId.setVisible(true);
            }
        });
        list.setItems(elementObservableList);
        list.setOnMouseClicked(event -> System.out.println(list.getSelectionModel().getSelectedItem().toString()));
    }
}
