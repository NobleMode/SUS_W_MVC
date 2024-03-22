package entity;

public class StudentCart {

    String SID, fname, lname;
    int fee, hourPerDay;

    public StudentCart() {
    }

    public StudentCart(String SID, String fname, String lname, int fee, int hourPerDay) {
        this.SID = SID;
        this.fname = fname;
        this.lname = lname;
        this.fee = fee;
        this.hourPerDay = hourPerDay;
    }

    public int getHourPerDay() {
        return hourPerDay;
    }

    public String getSID() {
        return SID;
    }

    public String getFname() {
        return fname;
    }

    public String getLname() {
        return lname;
    }

    public int getFee() {
        return fee;
    }

    public void setHourPerDay(int hourPerDay) {
        this.hourPerDay = hourPerDay;
    }

    public void setSID(String SID) {
        this.SID = SID;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public void setFee(int fee) {
        this.fee = fee;
    }

    @Override
    public String toString() {
        return "StudentCart{" + "SID=" + SID + ", fname=" + fname + ", lname=" + lname + ", fee=" + fee + '}';
    }

}
