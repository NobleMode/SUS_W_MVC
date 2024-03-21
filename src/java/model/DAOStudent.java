package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

import entity.Student;

public class DAOStudent extends DBConnect {

    public int addStudent(Student s) {
        int n = 0;
        
        String sql = "INSERT INTO [dbo].[Student]\n" +
                "           ([SID]\n" +
                "           ,[fname]\n" +
                "           ,[lname]\n" +
                "           ,[gender]\n" +
                "           ,[dob]\n" +
                "           ,[major]\n" +
                "           ,[address]\n" +
                "           ,[phone]\n" +
                "           ,[email]\n" +
                "           ,[password]\n" +
                "           ,[tutorfees])\n" +
                "     VALUES (?,?,?,?,?,?,?,?,?,?,?)";

        try (PreparedStatement pre = con.prepareStatement(sql)) {
            //pre.setDataType(IndexOf?,value);
            pre.setString(1, s.getSID());
            pre.setString(2, s.getFname());
            pre.setString(3, s.getLname());
            pre.setBoolean(4, s.isGender());
            pre.setString(5, s.getDob());
            pre.setString(6, s.getMajor());
            pre.setString(7, s.getAddress());
            pre.setInt(8, s.getPhone());
            pre.setString(9, s.getEmail());
            pre.setString(10, s.getPassword());
            pre.setInt(11, s.getFee());
            n = pre.executeUpdate();
        } catch(SQLException ex) {
             Logger.getLogger(DAOStudent.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return n;
    }

    public int updateStudent(Student s){
        int n = 0;
        
        String sql = "UPDATE [dbo].[Student]\n" +
        "           SET\n" +
        "           [fname] =?,\n" +
        "           [lname] =?,\n" +
        "           [gender] =?,\n" +
        "           [dob] =?,\n" +
        "           [major] =?,\n" +
        "           [address] =?,\n" +
        "           [phone] =?,\n" +
        "           [email] =?,\n" +
        "           [password] =?,\n" + // Add comma here
        "           [tutorfees] =?\n" +
        "     WHERE\n" +
        "           [SID] =?";
        try {
            PreparedStatement pre = con.prepareStatement(sql);
            //pre.setDataType(IndexOf?,value);
            pre.setString(1, s.getFname());
            pre.setString(2, s.getLname());
            pre.setBoolean(3, s.isGender());
            pre.setString(4, s.getDob());
            pre.setString(5, s.getMajor());
            pre.setString(6, s.getAddress());
            pre.setInt(7, s.getPhone());
            pre.setString(8, s.getEmail());
            pre.setString(9, s.getPassword());
            pre.setInt(10, s.getFee());
            pre.setString(11, s.getSID());
            n = pre.executeUpdate();
        } catch (SQLException e) {
            Logger.getLogger(DAOStudent.class.getName()).log(Level.SEVERE, null, e);
        }
        
        return n;
    }

    public String selectStudent(String name){
        String result = "";
        String sql = "SELECT * FROM [dbo].[Student]\n" +
                "           WHERE [fname] =?";
        
        try {
            PreparedStatement pre = con.prepareStatement(sql);
            //pre.setDataType(IndexOf?,value);
            pre.setString(1, name);
            ResultSet rs = pre.executeQuery();
            while(rs.next()){
                result += rs.getString(1) + ";" +
                        rs.getString(2) + ";" +  
                        rs.getString(3) + ";" +
                        rs.getBoolean(4) + ";" +
                        rs.getString(5) + ";" +
                        rs.getString(6) + ";" +
                        rs.getString(7) + ";" +
                        rs.getInt(8) + ";" +
                        rs.getString(9) + ";" +
                        rs.getInt(10) + ";" +
                        rs.getString(11) + "\n";
                
            }
        } catch (SQLException e) {
            Logger.getLogger(DAOStudent.class.getName()).log(Level.SEVERE, null, e);
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
            Logger.getLogger(DAOStudent.class.getName()).log(Level.SEVERE, null, ex);
        }

        return result;
    }

    public Vector<Student> getAll (String sql) {
        Vector<Student> vector = new Vector<Student>();
        try {
            Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()){
                String SID = (rs.getString(1));
                String Fname = (rs.getString(2));
                String Lname = (rs.getString(3));
                int sex = (rs.getInt(4));
                boolean gender  = (sex == 1 ? true : false);
                String Dob = (rs.getString(5));
                String Major = (rs.getString(6));
                String Address = (rs.getString(7));
                int Phone = (rs.getInt(8));
                String Email = (rs.getString(9));
                String Password = (rs.getString(10));
                int Fee = (rs.getInt(11));
                Student s = new Student(SID, Fname, Lname, gender, Dob, Major, Address, Phone, Email, Password, Fee );

                vector.add(s);
            }
        } catch (Exception e) {
            Logger.getLogger(DAOStudent.class.getName()).log(Level.SEVERE, null, e);
        }

        return vector;
    }

    public Vector<Student> searchFname(String name) {
        Vector<Student> vector = new Vector<Student>();
        String sql = "select * from Student where fname='" + name + "'";
        try {
            Statement statement = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = statement.executeQuery(sql);

            while(rs.next()){
                String SID = (rs.getString(1));
                String Fname = (rs.getString(2));
                String Lname = (rs.getString(3));
                int sex = (rs.getInt(4));
                boolean gender  = (sex == 1? true : false);
                String Dob = (rs.getString(5));
                String Major = (rs.getString(6));
                String Address = (rs.getString(7));
                int Phone = (rs.getInt(8));
                String Email = (rs.getString(9));
                String Password = (rs.getString(10));
                int Fee = (rs.getInt(11));
            }
        } catch (Exception e) {
            Logger.getLogger(DAOStudent.class.getName()).log(Level.SEVERE, null, e);
        }

        return vector;
    }

    public int removeStudent(String SID) {
        int  n = 0;

        String sql = "delete from Student where SID = '" + SID + "'"
                 + "AND ('" + SID + "' not in (select distinct StudentID from StudentDep))"
                 + "AND ('" + SID + "' not in (select distinct studentID from studentCourse))";

        try {
            Statement stmt = con.createStatement();
            n = stmt.executeUpdate(sql);

        } catch (SQLException ex) {
            Logger.getLogger(DAOStudent.class.getName()).log(Level.SEVERE, null, ex);
        }

        return n;
    }

    public boolean login(String user, String pass) {
        String sql = "SELECT * FROM Student WHERE [email] = ?";
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
