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

public class Controller implements Initializable {

    // Creates FXML id's
    @FXML ImageView imgStudent;
    @FXML Label fName;
    @FXML Label lName;
    @FXML Label sNum;
    @FXML TextArea activities;

    // Makes student
    private Student studentOne = new Student("Braden", "coates", 1095701, Arrays.asList("Programming", "Reading"), new Image("./fish.jpg"));

    /**
     * initializes the scene
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        fName.setText(studentOne.getFName());
        lName.setText(studentOne.getLName());
        sNum.setText(String.valueOf(studentOne.getSNum()));
        imgStudent.setImage(studentOne.getImage());
        activities.setText(studentOne.getActivities());
    }

    /**
     * For when switching back to this scene
     * resets the activities list and resets the text
     * @param a Activity List (String)
     */
    public void initData(List a) {
        studentOne.setActivities(a);
        activities.setText(studentOne.getActivities());
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
        controller.initData(studentOne.getActivitiesList());

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(activityScene);
        window.show();
    }
}
