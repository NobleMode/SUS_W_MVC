/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import entity.Bill;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.ResultSet;

/**
 *
 * @author Duc Minh
 */
public class DAOBill extends DBConnect{
    public int addBill(Bill s) {
        int n = 0;
        
        String sql = "INSERT INTO [dbo].[bill]\n" +
                "           ([teacherid]\n" +
                "           ,[studentid]\n" +
                "           ,[tutorfee])\n" +
                "     VALUES(?,?,?)";

        try (PreparedStatement pre = con.prepareStatement(sql)) {
            //pre.setDataType(IndexOf?,value);
            pre.setString(1, s.getTeacherid());
            pre.setString(2, s.getStudentid());
            pre.setInt(3, s.getTutorfee());
            n = pre.executeUpdate();
        } catch(SQLException ex) {
             Logger.getLogger(DAOStudent.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return n;
    }
    
    public int updateBill(Bill s){
        int n = 0;
        
        String sql = "UPDATE [dbo].[bill]\n" +
        "           SET\n" +
        "           [tutorfee] =?,\n" +
        "           WHERE\n" +
        "           [teacherid] = ? and [studentid] = ?";
        try {
            PreparedStatement pre = con.prepareStatement(sql);
            //pre.setDataType(IndexOf?,value);
            pre.setInt(1, s.getTutorfee());
            pre.setString(2, s.getTeacherid());
            pre.setString(3, s.getStudentid());
            n = pre.executeUpdate();
        } catch (SQLException e) {
            Logger.getLogger(DAOStudent.class.getName()).log(Level.SEVERE, null, e);
        }
        
        return n;
    }
    
    public Vector<Bill> getAll (String sql) {
        Vector<Bill> vector = new Vector<Bill>();
        try {
            Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()){
                String teacherid = (rs.getString(1));
                String studentid = (rs.getString(2));
                int tutorfee = (rs.getInt(3));
                Bill bill = new Bill(teacherid, studentid, tutorfee);
                vector.add(bill);
            }
        } catch (Exception e) {
            Logger.getLogger(DAOStudent.class.getName()).log(Level.SEVERE, null, e);
        }

        return vector;
    }
    
    public int removeBill(String teacherid, String studentid) {
        int n = 0;

        String sql = "delete from bill where teacherid = '" + teacherid + "' AND studentid = '" + studentid + "'";

        try {
            Statement state = con.createStatement();
            n = state.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DAOBill.class.getName()).log(Level.SEVERE, null, ex);
        }

        return n;
    }
    
    public static void main(String[] args) {
        DAOBill dao = new DAOBill();
        Vector<Bill> vector = new Vector<>();
        
        dao.removeBill("CN807107","BC594633");
        dao.removeBill("CN807107","CA634887");
        dao.removeBill("CN807107","MK701421");
    }
}
