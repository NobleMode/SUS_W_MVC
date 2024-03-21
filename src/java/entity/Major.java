package entity;

public class Major {
    private String MID, teacherID, name, description;

    public Major() {
    }

    public Major(String MID, String teacherID, String name, String description) {
        this.MID = MID;
        this.teacherID = teacherID;
        this.name = name;
        this.description = description;
    }

    public String getMID() {
        return MID;
    }

    public void setMID(String MID) {
        this.MID = MID;
    }

    public String getTeacherID() {
        return teacherID;
    }

    public void setTeacherID(String teacherID) {
        this.teacherID = teacherID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Major{" +
                "MID='" + MID + '\'' +
                ", teacherID='" + teacherID + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
