package vn.plusplus.database;

import vn.plusplus.database.model.Laptop;
import vn.plusplus.database.model.Student;
import vn.plusplus.database.service.LaptopService;

import java.awt.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Application {
    public static void main(String[] args) {
        System.out.println("-------- MySQL JDBC Connection Demo ------------");
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("MySQL JDBC Driver not found !!");
            return;
        }
        String a = "";
        System.out.println("MySQL JDBC Driver Registered!");
        Connection connection = null;
        try {
            String databaseUrl = "jdbc:mysql://localhost:3306/store_cms_plusplus?characterEncoding=utf8";
            String user = "root";
            String pass = "1234";
            connection = DriverManager
                    .getConnection(databaseUrl, user, pass);
            System.out.println("SQL Connection to database established!");

            LaptopService laptopService = new LaptopService(connection);
            List<Laptop> laptops = laptopService.findLaptopByPrice(null, 30000000f);
            for(Laptop laptop : laptops){
                System.out.println("Name: " + laptop.getName() + ", maker: " + laptop.getMaker() +", price: " + laptop.getPrice());
            }
        } catch (SQLException e) {
            System.out.println("Connection Failed! Check output console" + e);
            return;
        }
    }

    public List<Laptop> findLaptopByCondition(Float minPrice, Float maxPrice,
                                              String ssd, Integer ram, Integer hdd) {
        List<Laptop> response = new ArrayList<>();
        //Build SQL statement

        //Execute sql query
        // access DB to obtain data


        return response;
    }
}
