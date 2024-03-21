package entity;

public class studentCourse {
    private String studentID, courseID1, courseID2, courseID3;

    private double score1, score2, score3;

    public studentCourse() {
    }

    public studentCourse(String studentID, String courseID1, double score1, String courseID2, double score2, String courseID3,   double score3) {
        this.studentID = studentID;
        this.courseID1 = courseID1;
        this.courseID2 = courseID2;
        this.courseID3 = courseID3;
        this.score1 = score1;
        this.score2 = score2;
        this.score3 = score3;
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String getCourseID1() {
        return courseID1;
    }

    public void setCourseID1(String courseID1) {
        this.courseID1 = courseID1;
    }

    public String getCourseID2() {
        return courseID2;
    }

    public void setCourseID2(String courseID2) {
        this.courseID2 = courseID2;
    }

    public String getCourseID3() {
        return courseID3;
    }

    public void setCourseID3(String courseID3) {
        this.courseID3 = courseID3;
    }

    public double getScore1() {
        return score1;
    }

    public void setScore1(double score1) {
        this.score1 = score1;
    }

    public double getScore2() {
        return score2;
    }

    public void setScore2(double score2) {
        this.score2 = score2;
    }

    public double getScore3() {
        return score3;
    }

    public void setScore3(double score3) {
        this.score3 = score3;
    }

    @Override
    public String toString() {
        return "studentCourse{" +
                "studentID='" + studentID + '\'' +
                ", courseID1='" + courseID1 + '\'' +
                ", courseID2='" + courseID2 + '\'' +
                ", courseID3='" + courseID3 + '\'' +
                ", score1=" + score1 +
                ", score2=" + score2 +
                ", score3=" + score3 +
                '}';
    }
}
