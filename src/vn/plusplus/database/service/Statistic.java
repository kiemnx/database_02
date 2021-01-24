package vn.plusplus.database.service;

public class Statistic {
    private String maker;
    private Integer sold;
    private Float totalMoney;

    public Statistic(String maker, Integer sold, Float totalMoney) {
        this.maker = maker;
        this.sold = sold;
        this.totalMoney = totalMoney;
    }

    public String getMaker() {
        return maker;
    }

    public void setMaker(String maker) {
        this.maker = maker;
    }

    public Integer getSold() {
        return sold;
    }

    public void setSold(Integer sold) {
        this.sold = sold;
    }

    public Float getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(Float totalMoney) {
        this.totalMoney = totalMoney;
    }
}
