package com.example.pjezi.clickertemplate;

import android.content.res.Resources;

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

    void main() {

        Building building1 = new Building();
        building1.name = resources.getString(R.string.building1_name);
        building1.level = 1;
        building1.baseCost = 15;
        building1.cost = building1.baseCost;
        building1.production = 0.1;
        buildings.add(building1);

        Building building2 = new Building();
        building2.name = resources.getString(R.string.building2_name);
        building2.amount = 0;
        building2.level = 1;
        building2.baseCost = 100;
        building2.cost = building2.baseCost;
        building2.production = 1;
        buildings.add(building2);

        Building building3 = new Building();
        building3.name = resources.getString(R.string.building3_name);
        building3.amount = 0;
        building3.level = 1;
        building3.baseCost = 1100;
        building3.cost = building3.baseCost;
        building3.production = 8;
        buildings.add(building3);

        Building building4 = new Building();
        building4.name = resources.getString(R.string.building4_name);
        building4.amount = 0;
        building4.level = 1;
        building4.baseCost = 12000;
        building4.cost = building4.baseCost;
        building4.production = 47;
        buildings.add(building4);

        Building building5 = new Building();
        building5.name = resources.getString(R.string.building5_name);
        building5.amount = 0;
        building5.level = 1;
        building5.baseCost = 130000;
        building5.cost = building5.baseCost;
        building5.production = 260;
        buildings.add(building5);

        Building building6 = new Building();
        building6.name = resources.getString(R.string.building6_name);
        building6.amount = 0;
        building6.level = 1;
        building6.baseCost = 1400000;
        building6.cost = building6.baseCost;
        building6.production = 1400;
        buildings.add(building6);

        Building building7 = new Building();
        building7.name = resources.getString(R.string.building7_name);
        building7.amount = 0;
        building7.level = 1;
        building7.baseCost = 20000000;
        building7.cost = building7.baseCost;
        building7.production = 7800;
        buildings.add(building7);

        Building building8 = new Building();
        building8.name = resources.getString(R.string.building8_name);
        building8.amount = 0;
        building8.level = 1;
        building8.baseCost = 330000000;
        building8.cost = building8.baseCost;
        building8.production = 44000;
        buildings.add(building8);

        Building building9 = new Building();
        building9.name = resources.getString(R.string.building9_name);
        building9.amount = 0;
        building9.level = 1;
        building9.baseCost = 5100000000d;
        building9.cost = building9.baseCost;
        building9.production = 260000;
        buildings.add(building9);

        Building building10 = new Building();
        building10.name = resources.getString(R.string.building10_name);
        building10.amount = 0;
        building10.level = 1;
        building10.baseCost = 75000000000d;
        building10.cost = building10.baseCost;
        building10.production = 1600000;
        buildings.add(building10);

        Building building11 = new Building();
        building11.name = resources.getString(R.string.building11_name);
        building11.amount = 0;
        building11.level = 1;
        building11.baseCost = 1000000000000d;
        building11.cost = building11.baseCost;
        building11.production = 10000000;
        buildings.add(building11);

        Building building12 = new Building();
        building12.name = resources.getString(R.string.building12_name);
        building12.amount = 0;
        building12.level = 1;
        building12.baseCost = 14000000000000d;
        building12.cost = building12.baseCost;
        building12.production = 65000000;
        buildings.add(building12);


        //TODO set numerical values that are anyhow proper for buildings 13-15
        Building building13 = new Building();
        building13.name = resources.getString(R.string.building13_name);
        building13.amount = 0;
        building13.level = 1;
        building13.baseCost = 14000000000000d;
        building13.cost = building13.baseCost;
        building13.production = 65000000;
        buildings.add(building13);

        Building building14 = new Building();
        building14.name = resources.getString(R.string.building14_name);
        building14.amount = 0;
        building14.level = 1;
        building14.baseCost = 14000000000000d;
        building14.cost = building14.baseCost;
        building14.production = 65000000;
        buildings.add(building14);

        Building building15 = new Building();
        building15.name = resources.getString(R.string.building15_name);
        building15.amount = 0;
        building15.level = 1;
        building15.baseCost = 14000000000000d;
        building15.cost = building15.baseCost;
        building15.production = 65000000;
        buildings.add(building15);
    }

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