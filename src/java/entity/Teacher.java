/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author admin
 */
public class Teacher {
    private String TID, fname, lname ,position ,address ,dob , email ,password;
    private boolean gender;
    private int phone, salary;

    public Teacher() {
    }

    public Teacher(String TID, String fname, String lname, boolean gender, String dob, String position, String address, int phone, String email, String password, int salary) {
        this.TID = TID;
        this.fname = fname;
        this.lname = lname;
        this.gender = gender;
        this.dob = dob;
        this.position = position;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.password = password;
        this.salary = salary;
    }

    

    public String getTID() {
        return TID;
    }

    public void setTID(String TID) {
        this.TID = TID;
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

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
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

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Teacher [TID=" + TID +
               ", fname=" + fname +
               ", lname=" + lname +
               ", position=" + position +
               ", address=" + address +
               ", dob=" + dob +
               ", email=" + email +
               ", password=" + password +
               ", gender=" + gender +
               ", phone=" + phone +
               ", salary=" + salary + "]";
    }




}
