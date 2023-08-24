package eu.ansquare.intellicreator.one.ui;

import eu.ansquare.intellicreator.one.BlockMaker;
import eu.ansquare.intellicreator.one.ItemMaker;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

;import java.io.File;


public class ICGuiController {
    @FXML
    private Button createItemButton;
    @FXML
    private Button itemtextureSelect;
    @FXML
    private Button itemModelSelect;
    @FXML
    private TextField itemNameField;
    @FXML
    private TextField itemGroupField;
    @FXML
    private Button createBlockButton;
    @FXML
    private Button blockTextureSelect;
    @FXML
    private Button blockModelSelect;
    @FXML
    private TextField blockNameField;
    @FXML
    private TextField blockItemGroupField;
    @FXML
    private Label actionResultLabel;
    @FXML
    public CheckBox isItemFood;
    @FXML
    public TextField itemHunger;
    //ARMOR
    @FXML
    public TextField armorNameField;
    @FXML
    public TextField armorGroupField;
    @FXML
    public TextField armorDurField;
    @FXML
    public TextField armorProtField;
    @FXML
    public Button createArmor;
    @FXML
    public Button armorTextureSelect;
    @FXML
    public Button helmetButton;
    @FXML
    public Button chestplateButton;
    @FXML
    public Button leggingsButton;
    @FXML
    public Button bootsButton;
    @FXML
    public CheckBox hasHelmet;
    @FXML
    public CheckBox hasChestplate;
    @FXML
    public CheckBox hasLeggings;
    @FXML
    public CheckBox hasBoots;
    public File armorTexture;
    public File helmetTexture;
    public File chestplateTexture;
    public File leggingsTexture;
    public File bootsTexture;


    private Stage stage;

    private File textureFile;
    private File modelFile;
    private File blocktextureFile;
    private File blockmodelFile;
    private String itemName;
    private String itemGroup;

    @FXML
    private void initialize() {
        itemtextureSelect.setOnAction(event -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Open Resource File");
            textureFile = fileChooser.showOpenDialog(stage);
        });
        itemModelSelect.setOnAction(event -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Open Resource File");
            modelFile = fileChooser.showOpenDialog(stage);
        });
        createItemButton.setOnAction(event -> {
            actionResultLabel.setText(finalizeItemWithActionResult());
        });
        blockTextureSelect.setOnAction(event -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Open Resource File");
            blocktextureFile = fileChooser.showOpenDialog(stage);
        });
        blockModelSelect.setOnAction(event -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Open Resource File");
            blockmodelFile = fileChooser.showOpenDialog(stage);
        });
        createBlockButton.setOnAction(event -> {
            actionResultLabel.setText(finalizeBlockWithActionResult());
        });
        //ARMOR
        armorTextureSelect.setOnAction(event -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Open Resource File");
            armorTexture = fileChooser.showOpenDialog(stage);
        });
        helmetButton.setOnAction(event -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Open Resource File");
            helmetTexture = fileChooser.showOpenDialog(stage);
        });
        chestplateButton.setOnAction(event -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Open Resource File");
            chestplateTexture = fileChooser.showOpenDialog(stage);
        });
        leggingsButton.setOnAction(event -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Open Resource File");
            leggingsTexture = fileChooser.showOpenDialog(stage);
        });
        bootsButton.setOnAction(event -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Open Resource File");
            bootsTexture = fileChooser.showOpenDialog(stage);
        });
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    private String finalizeItemWithActionResult(){
        if(textureFile != null && !itemNameField.getCharacters().isEmpty() && !itemGroupField.getCharacters().isEmpty()){
            ItemMaker.createItem(itemNameField.getCharacters().toString(), textureFile.getPath(), itemGroupField.getCharacters().toString().toUpperCase(), modelFile);
            return "ITEM CREATED";
        }
        return "MISSING PARAMETERS";
    }
    private String finalizeBlockWithActionResult(){
        if(blocktextureFile != null && !blockNameField.getCharacters().isEmpty() && !blockItemGroupField.getCharacters().isEmpty()){
            BlockMaker.createBlock(blockNameField.getCharacters().toString(), blocktextureFile.getPath(), blockItemGroupField.getCharacters().toString().toUpperCase(), blockmodelFile);
            if(isItemFood.isSelected())
            return "ITEM CREATED";
        }
        return "MISSING PARAMETERS";
    }

}
