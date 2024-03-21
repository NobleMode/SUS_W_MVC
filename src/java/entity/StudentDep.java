package entity;

public class StudentDep {
    private String StudentID, fname, lname, relation, address;
    private int phone;

    public StudentDep() {
    }

    public StudentDep(String studentID, String fname, String lname, String relation, String address, int phone) {
        StudentID = studentID;
        this.fname = fname;
        this.lname = lname;
        this.relation = relation;
        this.address = address;
        this.phone = phone;
    }

    public String getStudentID() {
        return StudentID;
    }

    public void setStudentID(String studentID) {
        StudentID = studentID;
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
        return "StudentDep{" +
                "StudentID='" + StudentID + '\'' +
                ", fname='" + fname + '\'' +
                ", lname='" + lname + '\'' +
                ", relation='" + relation + '\'' +
                ", address='" + address + '\'' +
                ", phone=" + phone +
                '}';
    }
}
