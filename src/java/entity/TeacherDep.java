package entity;

public class TeacherDep {
    private String TeacherID, fname, lname, relation, address;
    private int phone;

    public TeacherDep() {
    }

    public TeacherDep(String teacherID, String fname, String lname, String relation, String address, int phone) {
        TeacherID = teacherID;
        this.fname = fname;
        this.lname = lname;
        this.relation = relation;
        this.address = address;
        this.phone = phone;
    }

    public String getTeacherID() {
        return TeacherID;
    }

    public void setTeacherID(String teacherID) {
        TeacherID = teacherID;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "TeacherDep{" +
                "TeacherID='" + TeacherID + '\'' +
                ", fname='" + fname + '\'' +
                ", lname='" + lname + '\'' +
                ", relation='" + relation + '\'' +
                ", address='" + address + '\'' +
                ", phone=" + phone +
                '}';
    }
}
