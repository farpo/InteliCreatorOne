package eu.ansquare.intellicreator.one.ui;

import eu.ansquare.intellicreator.one.BlockMaker;
import eu.ansquare.intellicreator.one.ItemMaker;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
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
            return "ITEM CREATED";
        }
        return "MISSING PARAMETERS";
    }

}
