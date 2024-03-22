/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author Duc Minh
 */
public class Bill {
    String teacherid, studentid;
    int tutorfee;

    public Bill() {
    }

    public Bill(String teacherid, String studentid, int tutorfee) {
        this.teacherid = teacherid;
        this.studentid = studentid;
        this.tutorfee = tutorfee;
    }

    public String getTeacherid() {
        return teacherid;
    }

    public String getStudentid() {
        return studentid;
    }

    public int getTutorfee() {
        return tutorfee;
    }

    public void setTeacherid(String teacherid) {
        this.teacherid = teacherid;
    }

    public void setStudentid(String studentid) {
        this.studentid = studentid;
    }

    public void setTutorfee(int tutorfee) {
        this.tutorfee = tutorfee;
    }

    @Override
    public String toString() {
        return "Bill{" + "teacherid=" + teacherid + ", studentid=" + studentid + ", tutorfee=" + tutorfee + '}';
    }
}
