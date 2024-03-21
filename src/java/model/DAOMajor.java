package model;

import entity.Major;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DAOMajor extends DBConnect {
    public int addMajor(Major m){
        int n = 0;

        String sql = "INSERT INTO [dbo].[Major]\n" +
                "           ([MID]\n" +
                "           ,[teacherID]\n" +           
                "           ,[name]\n" +
                "           ,[description])\n" +
                "     VALUES\n" +
                "           (?,?,?,?)";

        try {
            PreparedStatement pre = con.prepareStatement(sql);
            pre.setString(1, m.getMID());
            pre.setString(2, m.getTeacherID());
            pre.setString(3, m.getName());
            pre.setString(4, m.getDescription());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOMajor.class.getName()).log(Level.SEVERE, null, ex);
        }

        return n;
    }

    public int updateMajor(Major m){
        int n = 0;

        String sql = "UPDATE [dbo].[Major]\n" +
                "           SET\n" +
                "           [teacherID] =?,\n" +
                "           [name] =?,\n" +
                "           [description] =?\n" +
                "           WHERE [MID] =?";
        
        try{
            PreparedStatement pre = con.prepareStatement(sql);
            pre.setString(1, m.getTeacherID());
            pre.setString(2, m.getName());
            pre.setString(3, m.getDescription());
            pre.setString(4, m.getMID());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOMajor.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public String selectMajor(String name){
        String sql = "SELECT * FROM [dbo].[Major]\n" +
                "           WHERE [name] =?";
        String result = "";
        try{
            PreparedStatement pre = con.prepareStatement(sql);
            pre.setString(1, name);
            ResultSet rs = pre.executeQuery();
            while(rs.next()){
                result += rs.getString(1) + ";" +
                rs.getString(2) + ";" +
                rs.getString(3) + ";" +
                rs.getString(4) + "\n";
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOMajor.class.getName()).log(Level.SEVERE, null, ex);
        }

        return result;
    }

    public Vector<Major> getAll(String sql) {
        Vector<Major> vector = new Vector<Major>();
        try {
            Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()){
                String MID = rs.getString(1);
                String teacherID = rs.getString(2);
                String name = rs.getString(3);
                String description = rs.getString(4);
                Major m = new Major(MID, teacherID, name, description);
                vector.add(m);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOMajor.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }

    public Vector<Major> searchName(String Mname){
        Vector<Major> vector = new Vector<Major>();
        String sql = "select * from Major where name = '" + Mname + "'";
        try {
            Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                String MID = rs.getString(1);
                String teacherID = rs.getString(2);
                String name = rs.getString(3);
                String description = rs.getString(4);
                Major m = new Major(MID, teacherID, name, description);
                vector.add(m);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOMajor.class.getName()).log(Level.SEVERE, null, ex);
        }

        return vector;
    }

    public int removeMajor(String mid){
        int n = 0;
        String sql = "DELETE FROM [dbo].[Major] WHERE MID = '" + mid + "'"
                 + " AND ('" + mid + "' NOT IN (SELECT DISTINCT [MID] FROM [dbo].[Course])) AND ('" + mid + "' NOT IN (SELECT DISTINCT [MID] FROM [dbo].[Student]))";
        try{
            Statement stmt = con.createStatement();
            n = stmt.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DAOMajor.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }
}
