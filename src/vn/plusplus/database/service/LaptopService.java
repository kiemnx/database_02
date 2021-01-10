package vn.plusplus.database.service;

import vn.plusplus.database.model.Laptop;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class LaptopService {

    Connection connection;

    public LaptopService(Connection connection) {
        this.connection = connection;
    }

    public List<Laptop> findLaptopByPrice(Float minPrice, Float maxPrice) {
        List<Laptop> response = new ArrayList<>();
        try{

            String query = "SELECT * FROM laptop WHERE true ";
            if (minPrice != null) {
                query = query + " AND price>=" + minPrice;
            }
            if (maxPrice != null) {
                query = query + " AND price<=" + maxPrice;
            }

            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()){
                String name = rs.getString("name");
                String maker = rs.getString("maker");
                Float price = rs.getFloat("price");
                Laptop laptop = new Laptop(name, maker,price);
                response.add(laptop);
            }
        } catch (Exception e){
            System.out.println("ERROR: " +e);
        }
        return response;
    }

}
