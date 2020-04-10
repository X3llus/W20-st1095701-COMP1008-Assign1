import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NewStudent extends Application {

    @FXML private TextField txt_fName;
    @FXML private TextField txt_lName;
    @FXML private TextField txt_sNum;
    @FXML private Label lbl_error;
    @FXML private ImageView imgStudent;

    private Pattern names = Pattern.compile("^[a-zA-Z]+$");
    private Pattern studentNum = Pattern.compile("^[1-9][0-9]{6}$");

    private boolean checkPattern(String toCheck, Pattern pattern) {
        Matcher match = pattern.matcher(toCheck);
        return match.find();
    }

    private void changeScene (ActionEvent event, Student student) throws IOException {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("addActivity.fxml"));
        Parent activityParent = loader.load();

        Scene activityScene = new Scene(activityParent);

        AddActivity controller = loader.getController();
        controller.initData(student);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(activityScene);
        window.show();
    }

    public void imageButtonPressed(ActionEvent event) {
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Image");

        FileChooser.ExtensionFilter imageFilter =
                new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.jpeg", "*.png");
        fileChooser.getExtensionFilters().add(imageFilter);

        // Checks for operating system and assigns pictures directory accordingly
        String os = System.getProperty("os.name").toLowerCase();
        String userDirectoryString;
        if (os.contains("win")) {
            userDirectoryString = System.getProperty("user.home") + "\\Pictures";
        } else if (os.contains("nux") || os.contains("mac")) {
            userDirectoryString = System.getProperty("user.home") + "/Pictures";
        } else {
            userDirectoryString = System.getProperty("user.home");
        }

        File userDirectory = new File(userDirectoryString);

        // Just in case
        if (!userDirectory.canRead()) {
            userDirectory = new File(System.getProperty("user.home"));
        }

        fileChooser.setInitialDirectory(userDirectory);
        File imageFile = fileChooser.showOpenDialog(stage);

        if (imageFile != null && imageFile.isFile()) {
            imgStudent.setImage(new Image(imageFile.toURI().toString()));
        }
    }

    public void next(ActionEvent event) throws IOException {
        String fName = txt_fName.getText();
        String lName = txt_lName.getText();
        String sNum = txt_sNum.getText();

        boolean validFName = checkPattern(fName, names);
        boolean validLName = checkPattern(lName, names);
        boolean validSNum = checkPattern(sNum, studentNum);
        boolean invalidImage = (imgStudent.getImage() == null || imgStudent.getImage().isError());

        if (!validFName || !validLName || !validSNum || invalidImage) {
            lbl_error.setText( (validFName?"":"first name ") + (validLName?"":"last name ") + (validSNum?"":"student number ") + (invalidImage?"image ":"") + "are invalid");
        } else {
            Student student = new Student(fName, lName, Integer.parseInt(sNum), new ArrayList(), imgStudent.getImage());
            changeScene(event, student);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
    }
}
