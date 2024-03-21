package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

import entity.Course;

public class DAOCourse extends DBConnect {
    public int addCourse(Course c){
        int n = 0;

        String sql = "INSERT INTO [dbo].[Course]\r\n" + //
                        "           ([CID]\r\n" + //
                        "           ,[name]\r\n" + //
                        "           ,[major])\r\n" + //
                        "     VALUES\r\n" + //
                        "           (?,?,?,?>)";

        try{
            PreparedStatement pre = con.prepareStatement(sql);
            pre.setString(1, c.getCID());
            pre.setString(2, c.getName());
            pre.setString(3, c.getMajor());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOCourse.class.getName()).log(Level.SEVERE, null, ex);
        }

        return n;
    }

    public int updateCourse(Course c){
        int n = 0;

        String sql = "UPDATE [dbo].[Course]\r\n" + //
                        "           SET\r\n" + //
                        "           [name] =?,\r\n" + //
                        "           [major] =?\r\n" + //
                        "           WHERE [CID] =?";

        try{
            PreparedStatement pre = con.prepareStatement(sql);
            pre.setString(1, c.getName());
            pre.setString(2, c.getMajor());
            pre.setString(3, c.getCID());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOCourse.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public String selectCourse(String name){
        String result = "";

        String sql = "SELECT * FROM [dbo].[Course]\r\n" + //
                        "           WHERE [name] =?";
        
        try{
            PreparedStatement pre = con.prepareStatement(sql);
            pre.setString(1, name);
            ResultSet rs = pre.executeQuery();
            while(rs.next()){
                result += rs.getString("CID") + " " + rs.getString("name") + " " + rs.getString("major") + "\n";
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOCourse.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public Vector<Course> getAll(String sql){
        Vector<Course> vector = new Vector<Course>();
        
        try{
            Statement state = con.createStatement();
            ResultSet rs = state.executeQuery(sql);
            while(rs.next()){
                vector.add(new Course(rs.getString("CID"), rs.getString("name"), rs.getString("major")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOCourse.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return vector;  
    }

    public Vector<Course> searchName(String name){
        Vector<Course> vector = new Vector<Course>();
        String sql = "select * from Course where name = '" + name + "'";
        try{
            Statement state = con.createStatement();
            ResultSet rs = state.executeQuery(sql);
            while(rs.next()){
                vector.add(new Course(rs.getString("CID"), rs.getString("name"), rs.getString("major")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOCourse.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }

    public int removeCourse(String CID){
        int n = 0;
        String sql = "DELETE FROM [dbo].[Course] WHERE cid ='" + CID + "'"
                + "AND ('" + CID + "' NOT IN (SELECT DISTINCT [CID] FROM [dbo].[StudentCourse])) "
                + "AND ('" + CID + "' NOT IN (SELECT DISTINCT [CID] FROM [dbo].[teacherCourseManage]))";
        try{
            PreparedStatement pre = con.prepareStatement(sql);
            pre.setString(1, CID);
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOCourse.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

}
