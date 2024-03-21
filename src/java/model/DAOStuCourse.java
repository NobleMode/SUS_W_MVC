package model;

import entity.studentCourse;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DAOStuCourse extends DBConnect{
    public int addStudentCourse (studentCourse sc){
        int n = 0;
        String sql = "INSERT INTO [dbo].[studentCourse]\r\n" + //
                        "           ([studentID]\r\n" + //
                        "           ,[courseID1]\r\n" + //
                        "           ,[score1]\r\n" + //
                        "           ,[courseID2]\r\n" + //
                        "           ,[score2]\r\n" + //
                        "           ,[courseID3]\r\n" + //
                        "           ,[score3])\r\n" + //
                        "     VALUES\r\n" + //
                        "           (?,?,?,?,?,?,?)";
        try {
            PreparedStatement pre = con.prepareStatement(sql);
            pre.setString(1, sc.getStudentID());
            pre.setString(2, sc.getCourseID1());
            pre.setDouble(3, sc.getScore1());
            pre.setString(4, sc.getCourseID2());
            pre.setDouble(5, sc.getScore2());
            pre.setString(6, sc.getCourseID3());
            pre.setDouble(7, sc.getScore3());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOStuCourse.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int updateStudentCourse(studentCourse sc){
        int n = 0;
        String sql = "UPDATE [dbo].[studentCourse]\r\n" + //
                        "           SET\r\n" + //
                        "           [courseID1] =?,\r\n" + //
                        "           [score1] =?,\r\n" + //
                        "           [courseID2] =?,\r\n" + //
                        "           [score2] =?,\r\n" + //
                        "           [courseID3] =?,\r\n" + //
                        "           [score3] =?\r\n" + //
                        "     WHERE\r\n" + //
                        "           [studentID] =?";

        try {
            PreparedStatement pre = con.prepareStatement(sql);
            pre.setString(1, sc.getCourseID1());
            pre.setDouble(2, sc.getScore1());
            pre.setString(3, sc.getCourseID2());
            pre.setDouble(4, sc.getScore2());
            pre.setString(5, sc.getCourseID3());
            pre.setDouble(6, sc.getScore3());
            pre.setString(7, sc.getStudentID());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOStuCourse.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public String selectStudentCourse(String id){
        String sql = "SELECT * FROM [dbo].[studentCourse]\r\n" + //
                        "           WHERE\r\n" + //
                        "           [studentID] =?";
        String result = "";
        try{
            PreparedStatement pre = con.prepareStatement(sql);
            pre.setString(1, id);
            ResultSet rs = pre.executeQuery();
            while(rs.next()){
                result += rs.getString(1) + ";" +
                        rs.getString(2) + ";" +
                        rs.getDouble(3) + ";" +
                        rs.getString(4) + ";" +
                        rs.getDouble(5) + ";" +
                        rs.getString(6) + ";" +
                        rs.getDouble(7) + "\n";
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOStuCourse.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return result;
    }

    public Vector<studentCourse> getAll(String sql) {
        Vector<studentCourse> vector = new Vector<studentCourse>();
        try {
            Statement state = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                String sid = rs.getString(1);
                String course1 = rs.getString(2);
                double score1 = rs.getDouble(3);
                String course2 = rs.getString(4);
                double score2 = rs.getDouble(5);
                String course3 = rs.getString(6);
                double score3 = rs.getDouble(7);
                studentCourse stucou = new studentCourse(sid, course1, score1, course2, score2, course3, score3);

                vector.add(stucou);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOStuCourse.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }

    public int deleteStudentCourse(String id){
        int n = 0;
        String sql = "DELETE FROM [dbo].[studentCourse]\r\n" + //
                        "           WHERE\r\n" + //
                        "           [studentID] =?";
        try {
            PreparedStatement pre = con.prepareStatement(sql);
            pre.setString(1, id);
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOStuCourse.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }
}
