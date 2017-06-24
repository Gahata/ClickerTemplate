package com.example.pjezi.clickertemplate;

import android.content.res.Resources;

import static com.example.pjezi.clickertemplate.MainActivity.buildings;
import static com.example.pjezi.clickertemplate.MainActivity.upgrades;

public class Upgrade {

    private String name;
    private String description;
    private double price;
    private boolean purchased;
    //building that the upgrade is associated with
    private Building building;

    public Upgrade(String title, String description, double price, Building building) {
        this.name = title;
        this.description = description;
        this.price = price;
        this.purchased = false;
        this.building = building;
    }

    void main() {
        upgrades.add(new Upgrade(Resources.getSystem().getString(R.string.upgrade1_name),Resources.getSystem().getString(R.string.upgrade1_description), 10000, buildings.get(0)));
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
