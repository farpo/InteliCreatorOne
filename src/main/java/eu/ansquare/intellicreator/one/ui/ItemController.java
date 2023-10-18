package eu.ansquare.intellicreator.one.ui;

import eu.ansquare.intellicreator.one.Element;
import eu.ansquare.intellicreator.one.Main;
import eu.ansquare.intellicreator.one.block.BlockElement;
import eu.ansquare.intellicreator.one.item.ItemElement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

import java.io.File;
import java.net.MalformedURLException;

public class ItemController extends GuiController{
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
    @FXML
    private Label idLabel;
    @FXML
    private TextField maxAmount;
    @FXML
    private CheckBox onHead;

    private File texture;
    private File model;
    public void setId(String id){
        super.setId(id);
        idLabel.setText(this.id);
    }
    @FXML
    private void initialize() {
        idLabel.setText(this.id);
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
        itemGroup.setOnMouseClicked(event -> redItemGroup.setVisible(false));
        customName.setOnMouseClicked(event -> redCustomName.setVisible(false));
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
        redTexture.setVisible(texture == null);
        redCustomName.setVisible(customName.getCharacters().isEmpty());
        redItemGroup.setVisible(itemGroup.getSelectionModel().getSelectedItem() == null);
        if(!redCustomName.isVisible() && !redTexture.isVisible() && !redItemGroup.isVisible()){
            ItemElement element = new ItemElement(this.id)
                    .group(itemGroup.getSelectionModel().getSelectedItem())
                    .model(model).texture(texture)
                    .name(customName.getCharacters().toString())
                    .maxAmount(maxAmount.getCharacters().toString())
                    .onHead(onHead.isSelected());
            Main.elementManager.add(element);
            backButton.getScene().getWindow().hide();
        }
    }
}
