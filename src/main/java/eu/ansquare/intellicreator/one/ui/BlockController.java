package eu.ansquare.intellicreator.one.ui;

import com.sun.glass.ui.CommonDialogs;
import eu.ansquare.intellicreator.one.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.net.MalformedURLException;

public class BlockController extends GuiController{
    @FXML
    public Button backButton;
    @FXML
    public Button chooseTexture;
    @FXML
    private ImageView textureImage;

    private File texture;
    @FXML
    private void initialize() {
        backButton.setOnMouseClicked(event -> backButton.getScene().getWindow().hide());
        chooseTexture.setOnMouseClicked(event -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Select the texture");
            texture = fileChooser.showOpenDialog(stage);
            if(texture != null){
                try {
                    textureImage.setImage(new Image(texture.toURI().toURL().toString()));
                } catch (MalformedURLException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }
}
