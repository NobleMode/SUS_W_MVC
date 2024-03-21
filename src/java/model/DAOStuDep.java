package model;

import entity.StudentDep;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.PreparedStatement;
import java.util.Vector;
import java.sql.ResultSet;

public class DAOStuDep extends DBConnect{
    public int addStuDep(StudentDep s) {
        int n = 0;

        String sql = "INSERT INTO [dbo].[StudentDep] ([StudentID],[fname],[lname],[relation],[address],[phone]) VALUES (?,?,?,?,?,?)";

        try {
            PreparedStatement pre = con.prepareStatement(sql);
            pre.setString(1, s.getStudentID());
            pre.setString(2, s.getFname());
            pre.setString(3, s.getLname());
            pre.setString(4, s.getRelation());
            pre.setString(5, s.getAddress());
            pre.setInt(6, s.getPhone());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOStuDep.class.getName()).log(Level.SEVERE, null, ex);
        }

        return n;
    }

    public int updateStuDepBySID(StudentDep s) {
        int n = 0;
        
        String sql_update = "UPDATE [dbo].[StudentDep] SET [fname] =?,[lname] =?,[relation] =?,[address] =?,[phone] =? WHERE [StudentID] =?";

        try {
            PreparedStatement pre = con.prepareStatement(sql_update);
            pre.setString(1, s.getFname());
            pre.setString(2, s.getLname());
            pre.setString(3, s.getRelation());
            pre.setString(4, s.getAddress());
            pre.setInt(5, s.getPhone());
            pre.setString(6, s.getStudentID());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOStuDep.class.getName()).log(Level.SEVERE, null, ex);
        }

        return n;
    }

    public int updateStuDepByName(StudentDep s) {
        int n = 0;

        String sql = "UPDATE [dbo].[TeacherDep] SET [StudentID] =?,[relation] =?,[address] =?,[phone] =? WHERE [fname] =? AND [lname] =?";

        try {
            PreparedStatement pre = con.prepareStatement(sql);
            pre.setString(1, s.getStudentID());
            pre.setString(2, s.getRelation());
            pre.setString(3, s.getAddress());
            pre.setInt(4, s.getPhone());
            pre.setString(5, s.getFname());
            pre.setString(6, s.getLname());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOStuDep.class.getName()).log(Level.SEVERE, null, ex);
        }

        return n;
    }

    public Vector<StudentDep> getAll(String sql) {
        Vector<StudentDep> vector = new Vector<StudentDep>();
        try {
            Statement state = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                String SID = rs.getString(1);
                String Fname = rs.getString(2);
                String Lname = rs.getString(3);
                String Relation = rs.getString(4);
                String Addr = rs.getString(5);
                int Phone = rs.getInt(6);

                StudentDep studep = new StudentDep(SID, Fname, Lname, Relation, Addr, Phone);
                vector.add(studep);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOStuDep.class.getName()).log(Level.SEVERE, null, ex);
        }

        return vector;
    }

    public Vector<StudentDep> searchName(String sname) {
        Vector<StudentDep> vector = new Vector<StudentDep>();
        String sql = "SELECT * FROM [dbo].[StudentDep] WHERE [fname] = '" + sname + "'";
        try {
            Statement state = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                String SID = rs.getString(1);
                String Fname = rs.getString(2);
                String Lname = rs.getString(3);
                String Relation = rs.getString(4);
                String Addr = rs.getString(5);
                int Phone = rs.getInt(6);

                StudentDep studep = new StudentDep(SID, Fname, Lname, Relation, Addr, Phone);
                vector.add(studep);
            }
        } catch (SQLException e) {
            Logger.getLogger(DAOStuDep.class.getName()).log(Level.SEVERE, null, e);
        }

        return vector;
    }

    public int removeDep(String SID) {
        int n = 0;
        String sql = "DELETE FROM [dbo].[StudentDep] WHERE [StudentID] = ?";
        try {
            PreparedStatement pre = con.prepareStatement(sql);
            pre.setString(1, SID);
            n = pre.executeUpdate();
        } catch (SQLException e) {
            Logger.getLogger(DAOStuDep.class.getName()).log(Level.SEVERE, null, e);
        }

        return n;
    }
}