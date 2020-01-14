public class Student {
    String fName, lName;
    int sNum;

    public Student(String fName, String lName, int sNum) {
        this.fName = fName;
        this.lName = lName;
        this.sNum = sNum;
    }

    @Override
    public String toString() {
        return "Student{" +
                "fName='" + fName + '\'' +
                ", lName='" + lName + '\'' +
                ", sNum=" + sNum +
                '}';
    }
}
