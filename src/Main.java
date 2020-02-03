import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    public static void main(String[] args) {
        Student studentOne = new Student("Braden", "Coates", 1095701);
        System.out.println(studentOne.toString());
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("main.fxml"));
        stage.setTitle("Student Card");
        stage.setScene(new Scene(root));
        stage.show();
    }
}
