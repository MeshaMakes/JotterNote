package meshamakes;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("Fxmls/notepad.fxml"));
        //loads image from res folder (note: it cant be called img just image)
        primaryStage.getIcons().add(new Image("images/jn.jpg"));
        primaryStage.setTitle("Untitled - JotterNote");
        primaryStage.setScene(new Scene(root, 800, 475));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}