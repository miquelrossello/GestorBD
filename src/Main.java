import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("login/login.fxml"));
        primaryStage.setTitle("Gestor de Base de Dades");
        Scene startScene = new Scene(root);
        primaryStage.setScene(startScene);
        primaryStage.setResizable(true);
        primaryStage.getIcons().add(new Image("/img/icon_app"));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
