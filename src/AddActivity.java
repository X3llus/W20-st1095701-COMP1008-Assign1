import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AddActivity implements Initializable {

    // Creates FXML id's
    @FXML private TextField newItem;
    @FXML private TableView<Activity> table;
    @FXML private TableColumn<Activity, String> activitiesCol;

    private Student currentStudent;

    /**
     * initializes the scene
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        activitiesCol.setCellValueFactory(new PropertyValueFactory<Activity, String>("activity"));
    }

    /**
     * Builds new scene for student card
     * @throws IOException if file location is wrong
     */
    public void changeScene (ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("studentCard.fxml"));
        Parent mainParent = loader.load();

        Scene mainScene = new Scene(mainParent);

        StudentCard controller = loader.getController();
        currentStudent.setActivities(getList());
        controller.initData(currentStudent);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(mainScene);
        window.show();
    }

    /**
     * Builds the table
     * @param student accepts a student object
     */
    @FXML
    public void initData(Student student) {
        List<String> activities = student.getActivitiesList();
        currentStudent = student;
        for (String activity : activities) {
            Activity a = new Activity(activity);
            table.getItems().add(a);
        }
    }

    /**
     * Adds item in the text box
     * and resets it
     */
    public void addItem() {
        Activity a = new Activity(newItem.getText());
        newItem.setText("");
        table.getItems().add(a);
    }

    /**
     * Removes the currently selected item
     */
    public void removeItem() {
    Activity selected = table.getSelectionModel().getSelectedItem();
    table.getItems().remove(selected);
    }

    /**
     * Turns the table into a list of activities
     * @return List of activities
     */
    public List<String> getList() {
        List<String> activities = new ArrayList<>();
        TableColumn<Activity, String> column = activitiesCol;

        for (Activity activity: table.getItems()) {
            activities.add(column.getCellObservableValue(activity).getValue());
        }

        return activities;
    }

}
