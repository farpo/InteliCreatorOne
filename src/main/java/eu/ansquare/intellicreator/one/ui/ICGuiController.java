package eu.ansquare.intellicreator.one.ui;

import eu.ansquare.intellicreator.one.Element;
import eu.ansquare.intellicreator.one.Main;
import eu.ansquare.intellicreator.one.armor.ArmorElement;
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
    public Button itemButton;
    @FXML
    public Button armorButton;

    @FXML
    private void initialize() throws IOException {
        redId.setVisible(false);
        elementId.setOnMouseClicked(event -> redId.setVisible(false));
        FXMLLoader blockUiFxmlLoader = new FXMLLoader(Main.class.getResource("/ui/BlockGui.fxml"));
        Scene blockScene = new Scene(blockUiFxmlLoader.load());
        FXMLLoader itemUiFxmlLoader = new FXMLLoader(Main.class.getResource("/ui/ItemGui.fxml"));
        Scene itemScene = new Scene(itemUiFxmlLoader.load());
        FXMLLoader armorUiFxmlLoader = new FXMLLoader(Main.class.getResource("/ui/ArmorGui.fxml"));
        Scene armorScene = new Scene(armorUiFxmlLoader.load());
        blockButton.setOnMouseClicked(event -> {
            if (!elementId.getCharacters().isEmpty()) {
                Stage blockStage = new Stage();
                blockStage.initOwner(blockButton.getScene().getWindow());
                blockStage.setScene(blockScene);
                BlockController controller = blockUiFxmlLoader.getController();
                controller.setStage(blockStage);
                controller.setId(elementId.getCharacters().toString());
                blockStage.showAndWait();
                elementObservableList.removeAll(Main.elementManager.asCollection());
                elementObservableList.addAll(Main.elementManager.asCollection());
            } else {
                redId.setVisible(true);
            }
        });
        itemButton.setOnMouseClicked(event -> {
            if (!elementId.getCharacters().isEmpty()) {
                Stage blockStage = new Stage();
                blockStage.initOwner(itemButton.getScene().getWindow());
                blockStage.setScene(itemScene);
                ItemController controller = itemUiFxmlLoader.getController();
                controller.setStage(blockStage);
                controller.setId(elementId.getCharacters().toString());
                blockStage.showAndWait();
                elementObservableList.removeAll(Main.elementManager.asCollection());
                elementObservableList.addAll(Main.elementManager.asCollection());
            } else {
                redId.setVisible(true);
            }
        });
        armorButton.setOnMouseClicked(event -> {
            if (!elementId.getCharacters().isEmpty()) {
                Stage blockStage = new Stage();
                blockStage.initOwner(armorButton.getScene().getWindow());
                blockStage.setScene(armorScene);
                ArmorController controller = armorUiFxmlLoader.getController();
                controller.setStage(blockStage);
                controller.setId(elementId.getCharacters().toString());
                blockStage.showAndWait();
                elementObservableList.removeAll(Main.elementManager.asCollection());
                elementObservableList.addAll(Main.elementManager.asCollection());
            } else {
                redId.setVisible(true);
            }
        });
        list.setItems(elementObservableList);
        list.setOnMouseClicked(event -> System.out.println(list.getSelectionModel().getSelectedItem().toString()));
    }
}
