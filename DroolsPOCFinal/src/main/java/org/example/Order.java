package org.example;

public class Order {
    private String name;
    private String cardType;
    private int price;
    private double discount;

    public Order(String name, String cardType, int price) {
        this.name = name;
        this.cardType = cardType;
        this.price = price;
        this.discount = 0.0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }
}
