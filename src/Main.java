import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    /**
     * Runs on program start, launches javafx stuff
     * @param args
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Builds the stage for the javafx main scene
     * @param stage
     * @throws Exception
     */
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("main.fxml"));
        stage.setTitle("Student Card");
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
