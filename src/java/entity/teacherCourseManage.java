package entity;

public class teacherCourseManage {
    private String teacherID, courseID1, courseID2, courseID3;

    public teacherCourseManage() {
    }

    public teacherCourseManage(String teacherID, String courseID1, String courseID2, String courseID3) {
        this.teacherID = teacherID;
        this.courseID1 = courseID1;
        this.courseID2 = courseID2;
        this.courseID3 = courseID3;
    }

    public String getTeacherID() {
        return teacherID;
    }

    public void setTeacherID(String teacherID) {
        this.teacherID = teacherID;
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

    @Override
    public String toString() {
        return "teacherCourseManage{" +
                "teacherID='" + teacherID + '\'' +
                ", courseID1='" + courseID1 + '\'' +
                ", courseID2='" + courseID2 + '\'' +
                ", courseID3='" + courseID3 + '\'' +
                '}';
    }
}
