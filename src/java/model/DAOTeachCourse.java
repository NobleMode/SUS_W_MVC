package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

import entity.teacherCourseManage;

public class DAOTeachCourse extends DBConnect {
    public int addTeacherCourse(teacherCourseManage tcm){
        int n = 0;
        String sql = "INSERT INTO [dbo].[teacherCourseManage]\r\n" + //
                        "           ([teacherID]\r\n" + //
                        "           ,[courseID1]\r\n" + //
                        "           ,[courseID2]\r\n" + //
                        "           ,[courseID3])\r\n" + //
                        "     VALUES\r\n" + //
                        "           (?,?,?,?)";
        
        try{
            PreparedStatement pre = con.prepareStatement(sql);
            pre.setString(1, tcm.getTeacherID());
            pre.setString(2, tcm.getCourseID1());
            pre.setString(3, tcm.getCourseID2());
            pre.setString(4, tcm.getCourseID3());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOTeachCourse.class.getName()).log(Level.SEVERE, null, ex);
        }

        return n;
    }

    public int updateTeachCourse(teacherCourseManage tcm){
        int n = 0;
        String sql = "UPDATE [dbo].[teacherCourseManage]\r\n" + //
                        "           SET\r\n" + //
                        "           [courseID1] =?,\r\n" + //
                        "           [courseID2] =?,\r\n" + //
                        "           [courseID3] =?\n" +
                        " WHERE [teacherID] =?";

        try{
            PreparedStatement pre = con.prepareStatement(sql);
            pre.setString(1, tcm.getCourseID1());
            pre.setString(2, tcm.getCourseID2());
            pre.setString(3, tcm.getCourseID3());
            pre.setString(4, tcm.getTeacherID());
            n = pre.executeUpdate();
        }catch(SQLException e){
            Logger.getLogger(DAOTeachCourse.class.getName()).log(Level.SEVERE, null, e);
        }

        return n;
    }

    public String selectCourseIDByTeacherID(String teacherID){
        String result = "";
        String sql = "SELECT [courseID1],[courseID2],[courseID3] FROM [dbo].[teacherCourseManage] WHERE [teacherID] =?";
        try{
            PreparedStatement pre = con.prepareStatement(sql);
            pre.setString(1, teacherID);
            ResultSet rs = pre.executeQuery();
            if(rs.next()){
                result += rs.getString(1) + ";" + rs.getString(2) + ";" + rs.getString(3) + ";" + rs.getString(4);
            }
        }catch(SQLException e){
            Logger.getLogger(DAOTeachCourse.class.getName()).log(Level.SEVERE, null, e);
        }
        return result;
    }

    public Vector<teacherCourseManage> getAll(String sql) {
        Vector<teacherCourseManage> vector = new Vector<teacherCourseManage>();
        try {
            Statement state = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                vector.add(new teacherCourseManage(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOTeachCourse.class.getName()).log(Level.SEVERE, null, ex);
        }

        return vector;
    }

    public Vector<teacherCourseManage> searchCourseManages(String teacherID) {
        Vector<teacherCourseManage> vector = new Vector<teacherCourseManage>();
        String sql = "SELECT * FROM [dbo].[teacherCourseManage] WHERE [teacherID] =?";
        try {
            PreparedStatement pre = con.prepareStatement(sql);
            pre.setString(1, teacherID);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                vector.add(new teacherCourseManage(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOTeachCourse.class.getName()).log(Level.SEVERE, null, ex);
        }

        return vector;
    }

    public int deleteTeacherCourse(String teacherID) {
        int n = 0;
        String sql = "DELETE FROM [dbo].[teacherCourseManage] WHERE [teacherID] =?";
        try {
            Statement pre = con.createStatement();

            n = pre.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DAOTeachCourse.class.getName()).log(Level.SEVERE, null, ex);
        }

        return n;
    }
}
