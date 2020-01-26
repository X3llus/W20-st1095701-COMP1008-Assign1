import java.util.regex.Pattern;

public class Student {
    private String fName, lName;
    private int sNum;

    Student(String fName, String lName, int sNum) {
        setfName(fName);
        setlName(lName);
        setsNum(sNum);
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        if (checkHasNum(fName)) {
            this.fName = fName;
        } else {
            System.out.println("Name must only contain letters");
        }
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        if (checkHasNum(lName)) {
            this.lName = lName;
        } else {
            System.out.println("Last name must only contain letters");
        }
    }

    public int getsNum() {
        return sNum;
    }

    public void setsNum(int sNum) {
        if (String.valueOf(sNum).length() == 7) {
            this.sNum = sNum;
        } else {
            System.out.println("Student number must be 7 characters");
        }
    }

    private boolean checkHasNum(String input) {
        String regex = "[a-zA-Z]+";
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(input).matches();
    }

    @Override
    public String toString() {
        return fName + " " + lName + " student #: " + sNum;
    }
}
