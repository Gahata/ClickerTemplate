package com.example.pjezi.clickertemplate;

public class Upgrade {

    private String title;
    private String description;
    private double price;
    private boolean purchased;
    private Building building;

    public Upgrade(String title, String description, double price, Building building) {
        this.title = title;
        this.description = description;
        this.price = price;
        this.purchased = false;
        this.building = building;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isPurchased() {
        return purchased;
    }

    public void Purchase(boolean purchased) {
        this.purchased = true;
    }

    public Building getBuilding() {
        return building;
    }

    public void setBuilding(Building building) {
        this.building = building;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
