import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class StudentCard implements Initializable {

    // Creates FXML id's
    @FXML private ImageView imgStudent;
    @FXML private Label fName;
    @FXML private Label lName;
    @FXML private Label sNum;
    @FXML private TextArea activities;
    @FXML private Label birthday;
    @FXML private ListView<Student> listView;

    private ArrayList<Student> currentStudents = new ArrayList<Student>();
    private Student currentStudent;

    public void listViewClick() {
        currentStudent = listView.getSelectionModel().getSelectedItem();
        populate();
    }

    private void populate() {
        fName.setText(currentStudent.getFName());
        lName.setText(currentStudent.getLName());
        sNum.setText(String.valueOf(currentStudent.getSNum()));
        imgStudent.setImage(currentStudent.getImage());
        activities.setText(currentStudent.getActivities());
        birthday.setText(currentStudent.getBirthday().toString() + " Age: " + LocalDate.now().minusYears(currentStudent.getBirthday().getYear()).getYear());
    }

    /**
     * initializes the scene
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    /**
     * For when switching to this scene
     * makes the student
     * @param students Activity List (String)
     */
    public void initData(ArrayList<Student> students) {
        currentStudents = students;
        currentStudent = students.get(students.size() - 1);
        listView.getItems().addAll(currentStudents);
        populate();
    }

    /**
     * builds new scene for adding and removing activities
     * @throws IOException if file location is wrong
     */
    public void changeScene (ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("newStudent.fxml"));
        Parent activityParent = loader.load();

        Scene activityScene = new Scene(activityParent);

        NewStudent controller = loader.getController();
        if (currentStudents != null) {
            System.out.println(currentStudents.toString());
        }
        controller.fillNumber(Student.nextNum, currentStudents);
        Student.nextNum += 1;

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(activityScene);
        window.show();
    }
}
