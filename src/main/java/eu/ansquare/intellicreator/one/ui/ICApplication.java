package eu.ansquare.intellicreator.one.ui;

import eu.ansquare.intellicreator.one.Main;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ICApplication extends Application {
    public static void main(String[] args){
        launch(args);
    }
    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Hello World!");
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/ui/ICGui.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setScene(scene);
        ICGuiController controller = fxmlLoader.getController();
        controller.setStage(stage);
        stage.show();
    }
}
