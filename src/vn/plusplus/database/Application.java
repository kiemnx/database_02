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
            String databaseUrl= "jdbc:mysql://localhost:3306/student?characterEncoding=utf8";
            String user = "root";
            String pass = "1234";
            connection = DriverManager
                    .getConnection(databaseUrl, user, pass);
            System.out.println("SQL Connection to database established!");

            //Query DB
            List<Student> students = new ArrayList<>();
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM student.student;";
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()){
                String name = rs.getString(1);
                String idNumber = rs.getString(2);
                String address = rs.getString(3);
                String phone = rs.getString(4);
                String gender = rs.getString(5);
                Integer age = rs.getInt(6);
                System.out.println("name: " + name +", idNumber: " + idNumber);
                Student st = new Student(name, idNumber, address, phone, gender, age);
                students.add(st);
            }

            System.out.println(students);
//            return students;
        } catch (SQLException e) {
            System.out.println("Connection Failed! Check output console"  + e);
            return;
        }
    }
}
