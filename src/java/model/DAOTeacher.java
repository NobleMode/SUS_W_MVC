package model;

import entity.Teacher;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DAOTeacher extends DBConnect{

    public int addTeacher(Teacher t) {
        int n = 0;
        String sql = "INSERT INTO [dbo].[Teacher]\n" +
                "           ([TID]\n" +
                "           ,[fname]\n" +
                "           ,[lname]\n" +
                "           ,[gender]\n" +
                "           ,[dob]\n" +
                "           ,[position]\n" +
                "           ,[address]\n" +
                "           ,[phone]\n" +
                "           ,[email]\n" +
                "           ,[password]\n" +
                "           ,[salary])\n" +
                "     VALUES\n" +
                "           (?,?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement pre = con.prepareStatement(sql);
            //pre.setDataType(IndexOf ?,value);
            pre.setString(1, t.getTID());
            pre.setString(2, t.getFname());
            pre.setString(3, t.getLname());
            pre.setBoolean(4, t.isGender());
            pre.setString(5, t.getDob());
            pre.setString(6, t.getPosition());
            pre.setString(7, t.getAddress());
            pre.setInt(8, t.getPhone());
            pre.setString(9, t.getEmail());
            pre.setString(10, t.getPassword());
            pre.setInt(11, t.getSalary());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOTeacher.class.getName()).log(Level.SEVERE, null, ex);
        }

        return n;
    }

    public int updateTeacher(Teacher t) {
        int n = 0;
        String sql = "UPDATE [dbo].[Teacher]\n" +
                "   SET [fname] = ?\n" +
                "      ,[lname] = ?\n" +
                "      ,[gender] = ?\n" +
                "      ,[dob] = ?\n" +
                "      ,[position] = ?\n" +
                "      ,[address] = ?\n" +
                "      ,[phone] = ?\n" +
                "      ,[email] = ?\n" +
                "      ,[password] = ?\n" +
                "      ,[salary] = ?\n" +
                " WHERE [TID] = ?";
        try {
            PreparedStatement pre = con.prepareStatement(sql);
            pre.setString(1, t.getFname());
            pre.setString(2, t.getLname());
            pre.setBoolean(3, t.isGender());
            pre.setString(4, t.getDob());
            pre.setString(5, t.getPosition());
            pre.setString(6, t.getAddress());
            pre.setInt(7, t.getPhone());
            pre.setString(8, t.getEmail());
            pre.setString(9, t.getPassword());
            pre.setInt(10, t.getSalary());
            pre.setString(11, t.getTID());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOTeacher.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public String selectTeacher(String name) {
        String result = "";
        String sql = "SELECT * FROM Teacher WHERE [FName] = ?";
        try {
            PreparedStatement pre = con.prepareStatement(sql);
            pre.setString(1, name);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                result += rs.getString(1) + "; "
                        + rs.getString(2) + "; "
                        + rs.getString(3) + "; "
                        + rs.getBoolean(4) + ";"
                        + rs.getString(5) + "; "
                        + rs.getString(6) + ";"
                        + rs.getString(7) + "; "
                        + rs.getInt(8) + "; "
                        + rs.getString(9) + "; "
                        + rs.getInt(10) + "; "
                        + rs.getString(11) + "\n";
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOTeacher.class.getName()).log(Level.SEVERE, null, ex);
        }

        return result;
    }

    public String sqlCode(String code) {
        String result = "";
        int columnCount;

        try {
            PreparedStatement pre = con.prepareStatement(code);
            ResultSet rs = pre.executeQuery();
            columnCount = rs.getMetaData().getColumnCount();
            for (int i = 1; i <= columnCount; i++) {
                if (rs.getMetaData().getColumnName(i).equalsIgnoreCase("address")) {
                    result += String.format("|%-35s|", rs.getMetaData().getColumnName(i));
                } else {
                    result += String.format("|%-15s|", rs.getMetaData().getColumnName(i));
                }
            }
            while (rs.next()) {
                result += "\n" + String.format("|%-15s||%-15s||%-15s||%-35s||%-15s||%-15s||%-15s||%-15s|",
                        rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
                        rs.getString(5), rs.getBoolean(6), rs.getString(7), rs.getString(8));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOTeacher.class.getName()).log(Level.SEVERE, null, ex);
        }

        return result;
    }

    public Vector<Teacher> getAll(String sql) {
        Vector<Teacher> vector = new Vector<Teacher>();
        try {
            Statement state = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                String tid = rs.getString(1);
                String fname = rs.getString(2);
                String lname = rs.getString(3);
                int sex = rs.getInt(4);
                boolean gender = (sex == 1 ? true : false);
                String dob = rs.getString(5);
                String pos = rs.getString(6);
                String address = rs.getString(7);
                int phone = rs.getInt(8);
                String email = rs.getString(9);
                String password = rs.getString(10);
                int salary = rs.getInt(11);
                Teacher t = new Teacher(tid, fname, lname, gender, dob, pos, address, phone, email, password, salary);

                vector.add(t);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOTeacher.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }

    public Vector<Teacher> searchFname(String name) {
        Vector<Teacher> vector = new Vector<Teacher>();
        String sql = "select * from Teacher where fname = '" + name + "'";
        try {
            Statement state = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                String tid = rs.getString(1);
                String fname = rs.getString(2);
                String lname = rs.getString(3);
                int sex = rs.getInt(4);
                boolean gender = (sex == 1 ? true : false);
                String dob = rs.getString(5);
                String pos = rs.getString(6);
                String address = rs.getString(7);
                int phone = rs.getInt(8);
                String email = rs.getString(9);
                String password = rs.getString(10);
                int salary = rs.getInt(11);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOTeacher.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }

    public int removeTeacher(String TID) {
        int n = 0;
        String sql = "delete from Teacher where TID = '" + TID + "'"
                + "AND ('" + TID + "' not in (select distinct TID from TeacherDep))"
                + "AND ('" + TID + "' not in (select distinct TID from teacherCourseManage))"
                + "AND ('" + TID + "' not in (select distinct TID from Major))";
        try {
            Statement state = con.createStatement();
            n = state.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DAOTeacher.class.getName()).log(Level.SEVERE, null, ex);
        }

        return n;
    }

    public boolean login(String user, String pass) {
        String sql = "SELECT * FROM Teacher WHERE [email] = ?";
        try {
            PreparedStatement pre = con.prepareStatement(sql);
            pre.setString(1, user);
            ResultSet rs = pre.executeQuery();
            while(rs.next()) {
                if(user.equals(rs.getString(9)) && pass.equals(rs.getString(10))) {
                    return true;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOTeacher.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}
