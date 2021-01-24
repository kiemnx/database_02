package vn.plusplus.database.service;

public class Counter{
    private String maker;
    private Integer quantity;

    public Counter(String maker, Integer quantity) {
        this.maker = maker;
        this.quantity = quantity;
    }

    public String getMaker() {
        return maker;
    }

    public void setMaker(String maker) {
        this.maker = maker;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        String result = "Maker " + maker +" co so san pham la " + quantity ;
        return result;
    }
}