package entity;

public class Course {
    private String CID, name, major;

    public Course() {
    }

    public Course(String CID, String name, String major) {
        this.CID = CID;
        this.name = name;
        this.major = major;

    }

    public String getCID() {
        return CID;
    }

    public void setCID(String CID) {
        this.CID = CID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    @Override
    public String toString() {
        return "Course{" +
                "CID='" + CID + '\'' +
                ", name='" + name + '\'' +
                ", major='" + major + '\'' +
                '}';
    }
}
