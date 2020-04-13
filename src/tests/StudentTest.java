import javafx.scene.image.Image;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class StudentTest {

    private Student student;
    private String fName = "Braden";
    private String lName = "Coates";
    private int sNum = 1095701;
    private ArrayList<String> activities = (ArrayList<String>) Arrays.asList("Gaming", "Programming");
    private Image image = new Image("@resources/fish.jpg");
    private LocalDate date = LocalDate.parse("2001-01-01");

    @Before
    public void setup() throws Exception {
        student = new Student(fName, lName, sNum, activities, image, date);
    }

    @Test
    public void getFName() {
        assertEquals(student.getFName(), fName);
    }

    @Test
    public void setFName() {
        try {
            student.setFName("A");
            fail("Name must be 2 or more characters");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void getLName() {
        assertEquals(student.getLName(), lName);
    }

    @Test
    public void setLName() {
        try {
            student.setFName("A");
            fail("Name must be 2 or more characters");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void getSNum() {
        assertEquals(student.getSNum(), sNum);
    }

    @Test
    public void setSNum() {
        try {
            student.setSNum(1);
            fail("sNum must be between 1000000 - 9999999");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void getActivities() {
        assertEquals(student.getActivities(), "Gaming\nProgramming");
    }

    @Test
    public void getActivitiesList() {
        assertEquals(student.getActivitiesList(), activities);
    }

    @Test
    public void getImage() {
        assertEquals(student.getImage(), image);
    }

    @Test
    public void getBirthday() {
        assertEquals(student.getBirthday(), date);
    }

    @Test
    public void setBirthday() {
        try {
            student.setBirthday(LocalDate.now());
            fail("age must be 10 to 120");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void testToString() {
        assertEquals(student.toString(), "Braden Coates student #: 1095701");
    }
}