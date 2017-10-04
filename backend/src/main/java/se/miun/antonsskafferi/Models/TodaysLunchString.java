package se.miun.antonsskafferi.Models;

public class TodaysLunchString {
    private String foodName; //FK PK
    private String todaysDate; //PK

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getTodaysDate() {
        return todaysDate;
    }

    public void setTodaysDate(String todaysDate) {
        this.todaysDate = todaysDate;
    }
}
