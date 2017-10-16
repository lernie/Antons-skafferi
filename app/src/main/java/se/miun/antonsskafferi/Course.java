package se.miun.antonsskafferi;

import java.io.Serializable;

/**
 * Created by My on 9/28/2017.
 */

public class Course implements Serializable {
    public static final int MAIN = 0;
    public static final int DRINK = 1;
    public static final int APPETIZER = 2;
    public static final int DESERT = 3;

    private String name;
    private int cookingTime;
    private int type;
    private int price;


    public String getName() {
        return name;
    }

    public void setName(String Name) {
        this.name = Name;
    }

    // Drinks don't take any time to cook, do they?
    public Course(String name, int type, int price) {
        this(name, type, price, 0);
    }

    public Course(String name, int type, int price, int cookingTime){
        this.name = name;
        this.type = type;
        this.price = price;
        this.cookingTime = cookingTime;
    }

    public int getCookingTime() {
        return cookingTime;
    }

    public void setCookingTime(int cookingTime) {
        this.cookingTime = cookingTime;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}