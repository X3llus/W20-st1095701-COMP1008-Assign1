import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class StudentCard implements Initializable {

    // Creates FXML id's
    @FXML ImageView imgStudent;
    @FXML Label fName;
    @FXML Label lName;
    @FXML Label sNum;
    @FXML TextArea activities;

    private Student currentStudent;

    private void populate() {
        fName.setText(currentStudent.getFName());
        lName.setText(currentStudent.getLName());
        sNum.setText(String.valueOf(currentStudent.getSNum()));
        imgStudent.setImage(currentStudent.getImage());
        activities.setText(currentStudent.getActivities());
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
     * @param student Activity List (String)
     */
    public void initData(Student student) {
        currentStudent = student;
        populate();
    }

    /**
     * builds new scene for adding and removing activities
     * @throws IOException if file location is wrong
     */
    public void changeScene (ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("addActivity.fxml"));
        Parent activityParent = loader.load();

        Scene activityScene = new Scene(activityParent);

        AddActivity controller = loader.getController();
        controller.initData(currentStudent);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(activityScene);
        window.show();
    }
}
