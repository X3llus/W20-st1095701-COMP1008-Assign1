import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NewStudent extends Application {

    @FXML private TextField txt_fName;
    @FXML private TextField txt_lName;
    @FXML private TextField txt_sNum;
    @FXML private Label lbl_error;
    @FXML private ImageView imgStudent;
    @FXML private DatePicker datePicker;

    private Pattern names = Pattern.compile("^[a-zA-Z]{2,}$");
    private Pattern studentNum = Pattern.compile("^[1-9][0-9]{6}$");

    private ArrayList<String> activities = new ArrayList<String>();
    private ArrayList<Student> currentStudents = new ArrayList<Student>();

    private boolean checkPattern(String toCheck, Pattern pattern) {
        Matcher match = pattern.matcher(toCheck);
        return match.find();
    }

    private void changeScene (ActionEvent event, Student student) throws IOException {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("studentCard.fxml"));
        Parent activityParent = loader.load();

        Scene activityScene = new Scene(activityParent);

        StudentCard controller = loader.getController();
        currentStudents.add(student);
        System.out.println(currentStudents.toString());
        controller.initData(currentStudents);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(activityScene);
        window.show();
    }

    public void fillNumber (int num, ArrayList<Student> students) {
        txt_sNum.setText(String.valueOf(num));
        currentStudents = students;
    }

    public void checkBoxPressed(ActionEvent event) {
        CheckBox box = (CheckBox) event.getSource();

        if (box.isSelected()) {
            activities.add(box.getText());
        } else {
            for (int i = 0; i < activities.size(); i++) {
                if (activities.get(i).equals(box.getText())) {
                    activities.remove(i);
                    break; // Break stops program from needlessly checking the rest of the list
                }
            }
        }
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
        LocalDate date = datePicker.getValue();
        LocalDate today = LocalDate.now();

        boolean validFName = checkPattern(fName, names);
        boolean validLName = checkPattern(lName, names);
        boolean validSNum = checkPattern(sNum, studentNum);
        boolean invalidImage = (imgStudent.getImage() == null || imgStudent.getImage().isError());
        boolean dateValid = (date != null && date.isBefore(today.minusYears(10)) && date.isAfter(today.minusYears(120)));


        if (!validFName || !validLName || !validSNum || invalidImage || !dateValid) {
            lbl_error.setText( (validFName?"":"first name ") + (validLName?"":"last name ") + (validSNum?"":"student number ") + (invalidImage?"image ":"") + (dateValid?"":"date ") + "are invalid");
        } else {
            Student student = new Student(fName, lName, Integer.parseInt(sNum), activities, imgStudent.getImage(), date);
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
