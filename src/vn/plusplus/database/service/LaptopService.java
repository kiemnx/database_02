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

    public List<Counter> getCounterByMaker() throws Exception{
        String query= "SELECT maker, count(*) as quantity FROM laptop group by maker order by quantity desc;";
        List<Counter> response = new ArrayList<>();
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(query);
        while (rs.next()){
            String maker = rs.getString("maker");
            Integer quantity = rs.getInt("quantity");
            Counter counter = new Counter(maker, quantity);
            response.add(counter);
        }
        return response;
    }

    public List<Statistic> getStatisticByMaker() throws  Exception{
        String query = "SELECT distinct maker from laptop;";
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(query);
        List<Statistic> response = new ArrayList<>();
        //Step 1: Lay tat ca cac maker
        List<String> makerList = new ArrayList<>();
        while (rs.next()){
            String maker = rs.getString("maker");
            makerList.add(maker);
        }

        //Step 2: Lay thong tin san pham cua moi maker
        for(String maker : makerList){
            String queryLaptop = "SELECT price, sold from laptop where maker=' " + maker + "';";
            Statement statementLaptop = connection.createStatement();
            ResultSet rsLaptop = statementLaptop.executeQuery(queryLaptop);
            Float totalMoney = 0f;
            Integer quantity = 0;
            while (rsLaptop.next()){
                Integer price = rsLaptop.getInt("price");
                Integer sold = rsLaptop.getInt("sold");
                totalMoney += price * sold;
                quantity += sold;
            }
            Statistic statistic = new Statistic(maker, quantity, totalMoney);
            response.add(statistic);
        }
        return response;
    }

    public void insertMaker(String maker, String hotline, String address) throws Exception{
        try{
            String insert = "INSERT INTO maker(makerID, hotline, address) VALUES " +
                    "('"+maker+"', '"+hotline+"', '"+address+"');";
            Statement insertSt = connection.createStatement();
            boolean rs = insertSt.execute(insert);
            System.out.println("");
        } catch (Exception e){
            System.out.println(e);
        }
    }
}
