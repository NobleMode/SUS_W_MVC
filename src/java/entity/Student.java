package entity;

public class Student {

    private String SID, fname, lname, major, dob, address, email, password;
    private boolean gender;
    private int phone, fee;

    public Student() {
    }

    public Student(String SID, String fname, String lname, boolean gender, String dob, String major, String address, int phone, String email, String password, int fee) {
        this.SID = SID;
        this.fname = fname;
        this.lname = lname;
        this.major = major;
        this.dob = dob;
        this.address = address;
        this.email = email;
        this.password = password;
        this.gender = gender;
        this.phone = phone;
        this.fee = fee;
    }

    public int getFee() {
        return fee;
    }

    public void setFee(int fee) {
        this.fee = fee;
    }

    public String getSID() {
        return SID;
    }

    public void setSID(String SID) {
        this.SID = SID;
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

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Student{"
                + "SID='" + SID + '\''
                + ", fname='" + fname + '\''
                + ", lname='" + lname + '\''
                + ", major='" + major + '\''
                + ", dob='" + dob + '\''
                + ", address='" + address + '\''
                + ", email='" + email + '\''
                + ", password='" + password + '\''
                + ", gender=" + gender
                + ", phone=" + phone
                + ", fee=" + fee
                + '}';
    }
}
