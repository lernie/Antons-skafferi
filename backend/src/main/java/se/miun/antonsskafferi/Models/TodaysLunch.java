package se.miun.antonsskafferi.Models;

import java.sql.Date;

public class TodaysLunch {
    private int foodId; //FK PK
    private Date todaysDate; //PK

    public int getFoodId() {
        return foodId;
    }

    public void setFoodId(int foodId) {
        this.foodId = foodId;
    }

    public Date getTodaysDate() {
        return todaysDate;
    }

    public void setTodaysDate(Date todaysDate) {
        this.todaysDate = todaysDate;
    }
}
