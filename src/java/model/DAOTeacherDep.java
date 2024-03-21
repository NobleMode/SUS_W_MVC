package model;

import entity.TeacherDep;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.PreparedStatement;
import java.util.Vector;
import java.sql.ResultSet;

public class DAOTeacherDep extends DBConnect {

    public int addTeacherDep(TeacherDep td){
        int n = 0;

        String sql = "INSERT INTO [dbo].[TeacherDep] ([TeacherID],[fname],[lname],[relation],[address],[phone]) VALUES (?,?,?,?,?,?)";

        try {
            PreparedStatement pre = con.prepareStatement(sql);
            pre.setString(1, td.getTeacherID());
            pre.setString(2, td.getFname());
            pre.setString(3, td.getLname());
            pre.setString(4, td.getRelation());
            pre.setString(5, td.getAddress());
            pre.setInt(6, td.getPhone());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOTeacherDep.class.getName()).log(Level.SEVERE, null, ex);
        }

        return n;
    }

    public int updateTeacherDepByTID(TeacherDep td){
        int n = 0;

        String sql = "UPDATE [dbo].[TeacherDep] SET [fname] =?,[lname] =?,[relation] =?,[address] =?,[phone] =? WHERE [TeacherID] =?";

        try {
            PreparedStatement pre = con.prepareStatement(sql);
            pre.setString(1, td.getFname());
            pre.setString(2, td.getLname());
            pre.setString(3, td.getRelation());
            pre.setString(4, td.getAddress());
            pre.setInt(5, td.getPhone());
            pre.setString(6, td.getTeacherID());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOTeacherDep.class.getName()).log(Level.SEVERE, null, ex);
        }

        return n;
    }

    public int updateDepByName(TeacherDep td){
        int n = 0;

        String sql = "UPDATE [dbo].[TeacherDep] SET [TeacherID] =?,[relation] =?,[address] =?,[phone] =? WHERE [fname] =? AND [lname] =?";

        try {
            PreparedStatement pre = con.prepareStatement(sql);
            pre.setString(1, td.getTeacherID());
            pre.setString(2, td.getRelation());
            pre.setString(3, td.getAddress());
            pre.setInt(4, td.getPhone());
            pre.setString(5, td.getFname());
            pre.setString(6, td.getLname());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOTeacherDep.class.getName()).log(Level.SEVERE, null, ex);
        }

        return n;
    }

    public Vector<TeacherDep> getAll(String sql){
        Vector<TeacherDep> vector = new Vector<TeacherDep>();

        try {
            Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                String TID = rs.getString(1);
                String fname = rs.getString(2);
                String lname = rs.getString(3);
                String relation = rs.getString(4);
                String address = rs.getString(5);
                int phone = rs.getInt(6);
                
                TeacherDep td = new TeacherDep(TID, fname, lname, relation, address, phone);
                vector.addElement(td);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(DAOTeacherDep.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return vector;
    }

    public Vector<TeacherDep> searchName(String depName){
        Vector<TeacherDep> vector = new Vector<TeacherDep>();

        String sql = "select * from TeacherDep where fname = '" + depName + "'";
        try {
            Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                String TID = rs.getString(1);
                String fname = rs.getString(2);
                String lname = rs.getString(3);
                String relation = rs.getString(4);
                String address = rs.getString(5);
                int phone = rs.getInt(6);
                
                TeacherDep td = new TeacherDep(TID, fname, lname, relation, address, phone);
                vector.addElement(td);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(DAOTeacherDep.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
        return vector;
    }

    public int removeDep(String name){
        int n = 0;
        String sql = "DELETE FROM [dbo].[TeacherDep] WHERE [fname] = '" + name + "'";
        try {
            Statement pre = con.createStatement();
            n = pre.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DAOTeacherDep.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public static void main(String[] args) {
        
    }
}
