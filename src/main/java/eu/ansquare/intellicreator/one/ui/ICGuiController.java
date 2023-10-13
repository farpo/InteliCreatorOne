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
    public TextField helmetNameField;
    @FXML
    public TextField chestNameField;
    @FXML
    public TextField leggingsNameField;
    @FXML
    public TextField bootsNameField;
    @FXML
    public Button createArmor;
    @FXML
    public Button armorTextureSelect;
    @FXML
    public Button armorLayerSelect;
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
    public File armorLayer;
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
        armorLayerSelect.setOnAction(event -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Open Resource File");
            armorLayer = fileChooser.showOpenDialog(stage);
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
        createArmor.setOnAction(event -> {
            actionResultLabel.setText(finalizeArmorWithActionResult());
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
            return "BLOCK CREATED";
        }
        return "MISSING PARAMETERS";
    }
    private String finalizeArmorWithActionResult(){
        if(hasHelmet.isSelected() && (helmetTexture == null || helmetNameField.getCharacters().isEmpty())) {
            return "MISSING PARAMETERS";
        }
        if(hasChestplate.isSelected() && (chestplateTexture == null || chestNameField.getCharacters().isEmpty())) {
            return "MISSING PARAMETERS";
        }
        if(hasLeggings.isSelected() && (leggingsTexture == null || leggingsNameField.getCharacters().isEmpty())) {
            return "MISSING PARAMETERS";
        }
        if(hasBoots.isSelected() && (bootsTexture == null || bootsNameField.getCharacters().isEmpty())) {
            return "MISSING PARAMETERS";
        }
        if(armorTexture != null && armorLayer != null && !armorNameField.getCharacters().isEmpty() && !armorGroupField.getCharacters().isEmpty() && !armorDurField.getCharacters().isEmpty() && !armorProtField.getCharacters().isEmpty()){
            ArmorMaker.createArmor(armorNameField.getCharacters().toString(), hasHelmet.isSelected(), hasChestplate.isSelected(), hasLeggings.isSelected(), hasBoots.isSelected(),
                    armorTexture, armorLayer, helmetTexture, chestplateTexture, leggingsTexture, bootsTexture, helmetNameField.getCharacters().toString(), chestNameField.getCharacters().toString(), leggingsNameField.getCharacters().toString(), bootsNameField.getCharacters().toString(),
                    armorGroupField.getCharacters().toString(), armorDurField.getCharacters().toString(), armorProtField.getCharacters().toString());
            return "ARMOR CREATED";
        }
        return "MISSING PARAMETERS";
    }

}
