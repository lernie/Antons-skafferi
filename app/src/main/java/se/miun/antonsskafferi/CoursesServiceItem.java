package se.miun.antonsskafferi;

/**
 * Created by My on 10/10/2017.
 */

public class CoursesServiceItem {
    String name;
    int id;
    int foodTypeId;
    int timeToCook;
    int price;
    int type;

    public int getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public int getFoodTypeId() {
        return foodTypeId;
    }

    public int getTimeToCook() {
        return timeToCook;
    }

    public int getPrice() {
        return price;
    }
}
