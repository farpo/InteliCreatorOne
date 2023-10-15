package eu.ansquare.intellicreator.one.ui;

import com.sun.glass.ui.CommonDialogs;
import eu.ansquare.intellicreator.one.Element;
import eu.ansquare.intellicreator.one.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.net.MalformedURLException;

public class BlockController extends GuiController{
    ObservableList<Element.ItemGroup> itemGroupObservableList = FXCollections.observableArrayList(Element.ItemGroup.values());

    @FXML
    public Button backButton;
    @FXML
    public Button chooseTexture;
    @FXML
    public Button chooseModel;
    @FXML
    private ImageView textureImage;
    @FXML
    private ComboBox<Element.ItemGroup> itemGroup;
    @FXML
    private TextField customName;
    @FXML
    private Label redCustomName;
    @FXML
    private Label redTexture;
    @FXML
    private Label redModel;
    @FXML
    private Label redItemGroup;
    @FXML
    private Button saveButton;

    private File texture;
    private File model;
    @FXML
    private void initialize() {
        redCustomName.setVisible(false);
        redModel.setVisible(false);
        redTexture.setVisible(false);
        redItemGroup.setVisible(false);
        backButton.setOnMouseClicked(event -> backButton.getScene().getWindow().hide());
        itemGroup.setItems(itemGroupObservableList);
        chooseTexture.setOnMouseClicked(event -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Select the texture");
            texture = fileChooser.showOpenDialog(stage);
            if(texture != null){
                redTexture.setVisible(false);
                try {
                    textureImage.setImage(new Image(texture.toURI().toURL().toString()));
                } catch (MalformedURLException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        chooseModel.setOnMouseClicked(event -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Select the model");
            model = fileChooser.showOpenDialog(stage);
            if(model != null){
                redModel.setVisible(false);
            }
        });
        saveButton.setOnMouseClicked(event -> save());
    }
    private void save(){
        if(texture == null){
            redTexture.setVisible(true);
        }
        if(model == null){
            redModel.setVisible(true);
        }
        if(customName.getCharacters().isEmpty()){
            redCustomName.setVisible(true);
        }
        if(itemGroup.getSelectionModel().getSelectedItem() == null){
            redItemGroup.setVisible(true);
        }
    }
}
