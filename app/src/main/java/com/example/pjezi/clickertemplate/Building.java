package com.example.pjezi.clickertemplate;

import static com.example.pjezi.clickertemplate.MainActivity.buildings;
import static com.example.pjezi.clickertemplate.MainActivity.resources;


public class Building {
    String name;
    String description;
    int amount;
    //upgrade level defining production speed multiplier
    int level;
    //base cost is a cost of first building
    double baseCost;
    //cost is cost in currency of next building
    double cost;
    //production is currency/second value of a single building
    double production;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public double getBaseCost() {
        return baseCost;
    }

    public void setBaseCost(double baseCost) {
        this.baseCost = baseCost;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public double getProduction() {
        return production;
    }

    public void setProduction(double production) {
        this.production = production;
    }
}