package eu.ansquare.intellicreator.one.ui;

import eu.ansquare.intellicreator.one.Element;
import eu.ansquare.intellicreator.one.Main;
import eu.ansquare.intellicreator.one.armor.ArmorElement;
import eu.ansquare.intellicreator.one.item.ItemElement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

import java.io.File;

public class ArmorController extends GuiController{
    ObservableList<Element.ItemGroup> itemGroupObservableList = FXCollections.observableArrayList(Element.ItemGroup.values());

    @FXML
    public Button backButton;
    @FXML
    public Button chooseTexture;
    @FXML
    public Button chooseSTexture;
    @FXML
    private ComboBox<Element.ItemGroup> itemGroup;
    @FXML
    private TextField customName;
    @FXML
    private Label redCustomName;
    @FXML
    private Label redTexture;
    @FXML
    private Label redSTexture;
    @FXML
    private Label redItemGroup;
    @FXML
    private Button saveButton;
    @FXML
    private Label idLabel;

    @FXML
    private Button helmetTexture;
    @FXML
    private TextField helmetName;
    @FXML
    private ImageView helmetImg;
    private File helmetTx;
    @FXML
    private Button chestTexture;
    @FXML
    private TextField chestplateName;
    @FXML
    private ImageView chestImg;
    private File chestTx;
    @FXML
    private Button legTexture;
    @FXML
    private TextField leggingsName;
    @FXML
    private ImageView legsImg;
    private File legTx;
    @FXML
    private Button bootsTexture;
    @FXML
    private TextField bootsName;
    @FXML
    private ImageView bootsImg;
    private File bootsTx;

    private File texture;
    private File textureS;

    @FXML
    private void initialize() {
        idLabel.setText(this.id);
        redCustomName.setVisible(false);
        redSTexture.setVisible(false);
        redTexture.setVisible(false);
        redItemGroup.setVisible(false);
        backButton.setOnMouseClicked(event -> backButton.getScene().getWindow().hide());
        itemGroup.setItems(itemGroupObservableList);
        itemGroup.setOnMouseClicked(event -> redItemGroup.setVisible(false));
        customName.setOnMouseClicked(event -> redCustomName.setVisible(false));
        chooseSTexture.setOnMouseClicked(event -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Select the texture");
            textureS = fileChooser.showOpenDialog(stage);
            if(textureS != null){
                redSTexture.setVisible(false);
            }
        });
        chooseTexture.setOnMouseClicked(event -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Select the texture");
            texture = fileChooser.showOpenDialog(stage);
            if(texture != null){
                redTexture.setVisible(false);
            }
        });
        helmetTexture.setOnMouseClicked(event -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Select the helmet texture");
            helmetTx = fileChooser.showOpenDialog(stage);
        });
        chestTexture.setOnMouseClicked(event -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Select the chestplate texture");
            chestTx = fileChooser.showOpenDialog(stage);
        });
        legTexture.setOnMouseClicked(event -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Select the leggings texture");
            legTx = fileChooser.showOpenDialog(stage);
        });
        bootsTexture.setOnMouseClicked(event -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Select the boots texture");
            bootsTx = fileChooser.showOpenDialog(stage);
        });
        saveButton.setOnMouseClicked(event -> save());
    }
    private void save(){
        redTexture.setVisible(texture == null);
        redSTexture.setVisible(textureS == null);
        redCustomName.setVisible(customName.getCharacters().isEmpty());
        redItemGroup.setVisible(itemGroup.getSelectionModel().getSelectedItem() == null);
        if(!redCustomName.isVisible() && !redTexture.isVisible() &&!redSTexture.isVisible() && !redItemGroup.isVisible()){
            ArmorElement element = new ArmorElement(this.id).group(itemGroup.getSelectionModel().getSelectedItem()).texture(texture, textureS).name(customName.getCharacters().toString());
            if(helmetTx != null && !helmetName.getCharacters().isEmpty()){
                element.helmet(new ItemElement(this.id + "_helmet").name(helmetName.getCharacters().toString()).texture(helmetTx));
            }
            if(chestTx != null && !chestplateName.getCharacters().isEmpty()){
                element.chestplate(new ItemElement(this.id + "_chestplate").name(chestplateName.getCharacters().toString()).texture(chestTx));
            }
            if(legTx != null && !leggingsName.getCharacters().isEmpty()){
                element.leggings(new ItemElement(this.id + "_leggings").name(leggingsName.getCharacters().toString()).texture(legTx));
            }
            if(bootsTx != null && !bootsName.getCharacters().isEmpty()){
                element.boots(new ItemElement(this.id + "_boots").name(bootsName.getCharacters().toString()).texture(bootsTx));
            }
            Main.elementManager.add(element);
            backButton.getScene().getWindow().hide();
        }
    }
}
