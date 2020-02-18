import javafx.scene.image.Image;
import java.util.List;
import java.util.regex.Pattern;

public class Student {
    private String fName, lName;
    private int sNum;
    private List<String> activities;
    private Image image;

    /**
     * Constructor to verify and set variables
     * @param fName First Name
     * @param lName Last Name
     * @param sNum Student Number
     * @param activities Favourite Activities
     * @param image Students picture
     */
    Student(String fName, String lName, int sNum, List activities, Image image) {
        setFName(fName);
        setLName(lName);
        setSNum(sNum);
        setActivities(activities);
        setImage(image);
    }

    /**
     * @return First Name
     */
    public String getFName() {
        return fName;
    }

    /**
     * Sets and verifies the first name of student
     * @param fName First Name
     * @throws IllegalArgumentException If name is not valid
     */
    public void setFName(String fName) throws IllegalArgumentException {
        fName = fName.substring(0, 1).toUpperCase() + fName.substring(1);
        if (checkHasNum(fName)) {
            this.fName = fName;
        } else {
            throw new IllegalArgumentException("First name must contain 2 or more letters and not numbers or special characters");
        }
    }

    /**
     * @return Last name
     */
    public String getLName() {
        return lName;
    }

    /**
     * Sets and verifies the last name
     * @param lName Last name
     * @throws IllegalArgumentException if last name is not valid
     */
    public void setLName(String lName) throws IllegalArgumentException {
        lName = lName.substring(0, 1).toUpperCase() + lName.substring(1);
        if (checkHasNum(lName)) {
            this.lName = lName;
        } else {
            throw new IllegalArgumentException("First name must contain 2 or more letters and not numbers or special characters");
        }
    }

    /**
     * @return Student Number
     */
    public int getSNum() {
        return sNum;
    }

    /**
     * Sets and verifies the student number
     * @param sNum student number
     * @throws IllegalArgumentException if student number is not valid
     */
    public void setSNum(int sNum) throws IllegalArgumentException {
        if (sNum > 1000000 && sNum < 9999999) {
            this.sNum = sNum;
        } else {
            throw new IllegalArgumentException("Student number must be in range 1000000 - 9999999");
        }
    }

    /**
     * @return activities formatted
     */
    public String getActivities() {
        StringBuilder activities = new StringBuilder();
        for (int i = 0; i < this.activities.size(); i++) {
            activities.append(this.activities.get(i)).append("\n");
        }
        return activities.toString();
    }

    /**
     * @return Activities list
     */
    public List<String> getActivitiesList() {
        return this.activities;
    }

    /**
     * Sets the activities
     * @param activities List of activities
     */
    public void setActivities(List activities) {
        this.activities = activities;
    }

    /**
     * adds onto activities
     * @param activities new activities
     */
    public void addActivities(List activities) {
        this.activities.addAll(activities);
    }

    /**
     * add single activity
     * @param activity singular activity
     */
    public void addActivities(String activity) {
        this.activities.add(activity);
    }

    /**
     * @return student picture
     */
    public Image getImage() {
        return image;
    }

    /**
     * sets student picture
     * @param image image
     */
    public void setImage(Image image) {
        this.image = image;
    }

    /**
     * Checks an inputted string for proper characters
     * @param input sting to check
     * @return boolean
     */
    private boolean checkHasNum(String input) {
        String regex = "^[A-Z].[a-z]+";
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(input).matches();
    }

    /**
     * @return First name, last name, student number
     */
    @Override
    public String toString() {
        return fName + " " + lName + " student #: " + sNum;
    }
}
