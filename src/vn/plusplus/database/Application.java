package vn.plusplus.database;

import vn.plusplus.database.model.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Application {
    public static void main(String[] args) {
        System.out.println("-------- MySQL JDBC Connection Demo ------------");
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
        }
        catch (ClassNotFoundException e) {
            System.out.println("MySQL JDBC Driver not found !!");
            return;
        }
        String a = "";
        System.out.println("MySQL JDBC Driver Registered!");
        Connection connection = null;
        try {
            String databaseUrl= "jdbc:mysql://localhost:3306/student_cms_plusplus?characterEncoding=utf8";
            String user = "root";
            String pass = "1234";
            connection = DriverManager
                    .getConnection(databaseUrl, user, pass);
            System.out.println("SQL Connection to database established!");

            //Query DB
            List<Student> students = new ArrayList<>();
            Statement statement = connection.createStatement();

            String addressIn = "Tp.Hồ Chí Minh";
            int ageIn = 22;

            String query = "SELECT * FROM student WHERE address='"+ addressIn +"' AND age > "+ ageIn +";";

            ResultSet rs = statement.executeQuery(query);
            while (rs.next()){
                String id = rs.getString("id");
                String name = rs.getString("name");
                String mssv = rs.getString("mssv");
                String password = rs.getString("password");
                String phone = rs.getString("phone");
                String address = rs.getString("address");
                Integer age = rs.getInt("age");
                System.out.println("name: " + name +", mssv: " + mssv);
            }

            String phone = null;

            System.out.println(students);
//            return students;
        } catch (SQLException e) {
            System.out.println("Connection Failed! Check output console"  + e);
            return;
        }
    }
}
